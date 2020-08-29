package com.dailyestoreapp.adminapp;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ResponseInterface1 {
    @GET("listcategory")
    Call<ListCategoryResponse> CategoryList();
    @FormUrlEncoded
    @POST("listSubCategory")
    Call<ListCategoryResponse> SubCategory(@Field("typeId") int id);
    @FormUrlEncoded
    @POST("itemList")
    Call<ListCategoryResponse> Items(@Field("typeId") int id);
    @FormUrlEncoded
    @POST("activateItem")
    Call<ItemActivateResponse> ItemActivate(@Field("itemId") int id,
                                            @Field("status") int status);
    @GET("viewComments")
    Call<ListCategoryResponse> messagelist();
    @FormUrlEncoded
    @POST("userDetails")
    Call<ListCategoryResponse> Myaccount(@Field("userId") int id);
}
