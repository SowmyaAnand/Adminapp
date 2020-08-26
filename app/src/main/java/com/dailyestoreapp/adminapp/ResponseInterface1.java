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
}
