package br.com.digitalhouse.digitalhouseapp.model.dao;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.digitalhouse.digitalhouseapp.interfaces.ServiceListener;
import br.com.digitalhouse.digitalhouseapp.model.Post;
import br.com.digitalhouse.digitalhouseapp.model.dao.network.RetrofitService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDAO {

    public void getPosts(Context context, final ServiceListener listener){
        List<Post> postList = new ArrayList<>();

        if (isConnected(context)){
           getNetworkDataRx(listener);
           // getNetworkDate(listener);

        }else {
            getLocalDate(context, listener, postList);
        }
    }

    private void getNetworkDate(final ServiceListener listener) {
        Call<List<Post>> call = RetrofitService.getApiService().getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.body() != null){
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                listener.onError(t);
            }
        });
    }

    public void getNetworkDataRx(final ServiceListener listener){
        RetrofitService.getApiService().getPostsRx()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(posts -> listener.onSuccess(posts), throwable -> listener.onError(throwable));
    }

    private void getLocalDate(Context context, ServiceListener listener, List<Post> postList) {
        try {
            Gson gson = new Gson();

            AssetManager manager = context.getAssets();

            InputStream postsJson = manager.open("posts.json");
            BufferedReader bufferReaderIn = new BufferedReader(new InputStreamReader(postsJson));

            Post[] postArray = gson.fromJson(bufferReaderIn, Post[].class);

            postList.addAll(Arrays.asList(postArray));

            listener.onSuccess(postList);

        }catch (IOException exception){
            Log.e("JSON","Erro ao ler arquivo posts.json");
            listener.onError(exception);
        }
    }

    private boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo;

        if (connectivityManager != null) {
            networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected() &&
                    (networkInfo.getType() == ConnectivityManager.TYPE_WIFI
                            || networkInfo.getType() == ConnectivityManager.TYPE_MOBILE);
        }
        return false;
    }
}
