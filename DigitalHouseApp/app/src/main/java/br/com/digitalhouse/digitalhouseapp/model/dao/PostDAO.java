package br.com.digitalhouse.digitalhouseapp.model.dao;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

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
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDAO {

	// Mock (simulação) da lista --> poderia vir do SQL ou da API
	public void getPosts(Context context, ServiceListener listener) {
		List<Post> postList = new ArrayList<>();

		if (isConnected(context)){
			getNetworkDataRX(listener);
		} else {
			getLocalData(context, listener);
		}

	}

	private void getNetworkData(ServiceListener listener) {
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

	private void getNetworkDataRX(final ServiceListener listener) {
		RetrofitService.getApiService().getPostsRX()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(posts -> {
					listener.onSuccess(posts);
				}, throwable -> {
					listener.onError(throwable);
				});
	}

	private void getLocalData(Context context, ServiceListener listener) {
		List<Post> postList;
		try {
			Gson gson = new Gson();

			AssetManager am = context.getAssets();

			InputStream newJson = am.open("posts.json");
			BufferedReader br = new BufferedReader(new InputStreamReader(newJson));

			Post[] posts = gson.fromJson(br, Post[].class);

			postList = Arrays.asList(posts);

			listener.onSuccess(postList);
		} catch(IOException e){
			Log.e("LOG", e.getMessage());
			e.printStackTrace();
			listener.onError(e);
		}
	}
}
