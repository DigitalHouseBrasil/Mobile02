package br.com.digitalhouse.app.mob2.model.dao;

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
import java.util.Arrays;
import java.util.List;

import br.com.digitalhouse.app.mob2.interfaces.ServiceListener;
import br.com.digitalhouse.app.mob2.model.Post;
import br.com.digitalhouse.app.mob2.model.dao.network.RetrofitService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDAO {

    public void getPosts(Context context, final ServiceListener listener) {

        /* Se estamos conectados a internet chamamos a api*/
        if (isConnected(context)) {
            getNetworkDataRX(listener);

        } else {
            /* se não temos conexão pegamos os posts da pasta assets*/
            getLocalData(context, listener);
        }
    }

    private void getNetworkData(final ServiceListener listener) {

        //Prepara a chamada para a  API
        Call<List<Post>> call = RetrofitService.getApiService().getPosts();

        // Coloca a chamada na fila
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                // Se a resposta não for nula disparamos o listener de sucesso
                if (response.body() != null) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                // disparamos o listener de sucesso
                listener.onError(t);
            }
        });
    }


    /* Chamada do api de posts usando RxJava*/
    private void getNetworkDataRX(final ServiceListener listener) {

        RetrofitService.getApiService().getPostsRX()
                .subscribeOn(Schedulers.io()) //faz a requisição na thread de entreada e saida de dado
                .observeOn(AndroidSchedulers.mainThread()) // quando retornar expoe os dados na main thread
                .subscribe(posts -> {
                    // dispara o listener de sucesso
                    listener.onSuccess(posts);
                }, throwable -> {
                    // dispara o listener de erro
                    listener.onError(throwable);
                });
    }

    private void getLocalData(Context context, ServiceListener listener) {
        List<Post> postList;
        try {
            //Criar um stream para ler o arquivo JSON.
            AssetManager manager = context.getAssets();
            InputStream newsJson = manager.open("posts.json");
            BufferedReader bufferReaderIn = new BufferedReader(new InputStreamReader(newsJson));

            //Criar um objeto da classe Gson que permita analisar o JSON de forma simples.
            Gson gson = new Gson();

            //Utilizando o objeto gson e o método fromJson, fazer a análise do arquivo que temos no
            // bufferReaderIn, usando como “molde” a classe News.
            Post[] postArray = gson.fromJson(bufferReaderIn, Post[].class);

            // Transforma Array[] em ArrayList
            postList = Arrays.asList(postArray);

            listener.onSuccess(postList);

        } catch (IOException exception) {
            Log.e("JSON", "erro ao ler arquivo posts.json");
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
