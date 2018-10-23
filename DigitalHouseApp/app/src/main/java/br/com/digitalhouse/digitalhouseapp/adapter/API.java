package br.com.digitalhouse.digitalhouseapp.adapter;

import java.util.List;

import br.com.digitalhouse.digitalhouseapp.model.Post;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("/posts")
    Call<List<Post>> getPosts();

    @GET("/posts")
    Observable<List<Post>> getPostsRX();

}
