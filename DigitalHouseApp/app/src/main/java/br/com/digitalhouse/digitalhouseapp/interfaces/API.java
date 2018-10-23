package br.com.digitalhouse.digitalhouseapp.interfaces;

import java.util.List;

import br.com.digitalhouse.digitalhouseapp.model.Post;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
	@GET("/posts")
	Call<List<Post>> getPosts();

	@GET("/posts")
	Observable<List<Post>> getPostsRX();

	/*@POST("/posts")
	Call<Boolean> createPost(@Body Post post);*/
}