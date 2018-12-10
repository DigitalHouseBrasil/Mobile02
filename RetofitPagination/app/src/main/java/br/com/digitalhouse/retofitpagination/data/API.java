package br.com.digitalhouse.retofitpagination.data;

import br.com.digitalhouse.retofitpagination.model.MovieResults;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    @GET("movie/{category}")
    Call<MovieResults> getMovies(
            @Path("category") String category,
            @Query("api_key") String api_key,
            @Query("language") String language,
            @Query("page") int page,
            @Query("origin") String origin
    );
}
