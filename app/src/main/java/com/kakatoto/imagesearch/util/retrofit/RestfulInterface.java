package com.kakatoto.imagesearch.util.retrofit;


import com.kakatoto.imagesearch.model.SearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface RestfulInterface {

    @GET("search/image?")
    Call<SearchResult> requestImageList(@Query("apikey") String apikey,
                                        @Query(value = "q", encoded = true) String query,
                                        @Query("result") int result,
                                        @Query("pageno") int pageNo,
                                        @Query("output") String type);


}