package test.pathao.pathaomovie.Interfaces;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import test.pathao.pathaomovie.Models.ApiResponse;

public interface ApiInterface {
    @GET("/3/movie/top_rated?api_key=4bc53930f5725a4838bf943d02af6aa9&language=en-US")
    Call<ApiResponse> getTopRatedList(@Query("page") int pageNumber);

    @GET("/3/movie/now_playing?api_key=4bc53930f5725a4838bf943d02af6aa9&language=en-US")
    Call<ApiResponse> getNowPlayingList(@Query("page") int pageNumber);

    @GET("/3/movie/upcoming?api_key=4bc53930f5725a4838bf943d02af6aa9&language=en-US")
    Call<ApiResponse> getUpcomingList(@Query("page") int pageNumber);
}
