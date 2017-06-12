package com.kakatoto.imagesearch.util.retrofit;


import com.kakatoto.imagesearch.model.ImageSearchResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by USER on 2017-01-09.
 */

public interface RestfulInterface {

    @GET("search/image?")
    Call<ImageSearchResult> requestImageList(@Query("apikey") String apikey,
                                             @Query(value = "q", encoded = true) String query,
                                             @Query("result") int result,
                                             @Query("pageno") int pageNo);


  /*  @FormUrlEncoded
    @POST("Anony/api/memberCheck.php")
    Call<MemberRepo> requestMember(@Field("MODE") String mode, @Field("ANDROID_ID") String androidId);


    @FormUrlEncoded
    @POST("/Anony/api/boardListAll.php")
    Call<ArrayList<BoardItem>> requestTalkList(@Field("MODE") String mode, @Field("LIMIT") String limit);

    @FormUrlEncoded
    @POST("Anony/api/boardPostDelete.php")
    Call<Result> requestDelPost(@Field("MODE") String mode, @Field("BSEQ") String besq);


    @FormUrlEncoded
    @POST("Anony/api/newCommentSaveTest.php")
    Call<ArrayList<BoardItem>> requestNewComment(@Field("MODE") String mode,
                                                 @Field("BSEQ") String besq,
                                                 @Field("B_ANDROID_ID") String bAndroidId,
                                                 @Field("ANDROID_ID") String androiId,
                                                 @Field("GENDER") String gender,
                                                 @Field("NEW_COMMENT") String newComment);

    @FormUrlEncoded
    @POST("/Anony/api/commentDelete.php")
    Call<Result> requestDelComment(@Field("ID") String id, @Field("BSEQ") String besq);


    @FormUrlEncoded
    @POST("/Anony/api/boardDetail.php")
    Call<BoardItem> requestHeaderInfo(@Field("MODE") String mode, @Field("BSEQ") String besq);


    @FormUrlEncoded
    @POST("/Anony/api/UserLikeHateState.php")
    Call<Result> requestReportPost(@Field("MODE") String mode, @Field("BSEQ") String besq, @Field("ANDROID_ID") String androiId);

    @FormUrlEncoded
    @POST("/Anony/api/commentListAll.php")
    Call<ArrayList<BoardItem>> requestCommentList(@Field("MODE") String mode, @Field("BSEQ") String besq);


    @FormUrlEncoded
    @POST("/Anony/api/commentHateState.php")
    Call<Result> requestReportComment(@Field("MODE") String mode, @Field("BSEQ") String besq, @Field("ANDROID_ID") String androiId);


    @Multipart
    @POST("/Anony/api/newPostSave.php")
    Call<MultiPartResult> requestSendPost(@Part("MODE") RequestBody mode,
                                          @Part("ANDROID_ID") RequestBody androidId,
                                          @Part("GENDER") RequestBody gender,
                                          @Part("NEW_POST") RequestBody new_post,
                                          @Part MultipartBody.Part file);*/

}