package br.com.digitalhouse.digitalhouseapp.interfaces;

import java.util.List;

import br.com.digitalhouse.digitalhouseapp.model.Post;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("/posts")
    Call<List<Post>> getPosts();

    @GET("/posts")
<<<<<<< HEAD
    Observable<List<Post>> getPostsRx();

=======
    Observable<List<Post>> getPostsRX();

    /*@POST("/posts")
    Call<Boolean> create(@Body Post post);*/
>>>>>>> e1aef20639c6180cbcaf10dc3f093b07d873b37e
}
