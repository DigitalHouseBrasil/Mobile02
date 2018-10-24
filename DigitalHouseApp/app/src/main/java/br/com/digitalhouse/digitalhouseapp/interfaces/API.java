package br.com.digitalhouse.digitalhouseapp.interfaces;

import java.util.List;

import br.com.digitalhouse.digitalhouseapp.model.Post;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("/posts")
    Call<List<Post>>getPosts();



}
