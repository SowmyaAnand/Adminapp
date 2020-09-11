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
    @GET("viewAllPopup")
    Call<ListCategoryResponse> firstpop();
    @GET("viewAllUpFlyers")
    Call<ListCategoryResponse> allFlyers();
    @GET("viewAllDownFlyers")
    Call<ListCategoryResponse> allseconfFlyers();
    @GET("viewAllDeals")
    Call<ListCategoryResponse> viewalldeal();
    @FormUrlEncoded
    @POST("listSubCategory")
    Call<ListCategoryResponse> SubCategory(@Field("typeId") int id);
    @FormUrlEncoded
    @POST("subItemList")
    Call<ListCategoryResponse> Items(@Field("subId") int id);

    @FormUrlEncoded
    @POST("activateItem")
    Call<ItemActivateResponse> ItemActivate(@Field("itemId") int id,
                                            @Field("status") int status);
    @GET("viewComments")
    Call<ListCategoryResponse> messagelist();
    @FormUrlEncoded
    @POST("userDetails")
    Call<ListCategoryResponse> Myaccount(@Field("userId") int id);
    @GET("allOrders")
    Call<ListCategoryResponse> orderslist();
    @FormUrlEncoded
    @POST("orderStatus")
    Call<ListCategoryResponse> changeOrderStatus(@Field("orderId") int id,
                                            @Field("status") int status);
    @FormUrlEncoded
    @POST("loginData")
    Call<LoginResponse> Loginapi(@Field("username") String usernameres,
                                            @Field("password") String passwordres,
                                        @Field("type") String typeres
                                        );
    @FormUrlEncoded
    @POST("editCategory")
    Call<LoginResponse> EditCategory(@Field("typeId") String typeId,
                                 @Field("createdBy") String createdby,
                                 @Field("categoryName") String categoryName,
                                     @Field("categoryImage") String categoryImage
    );

}
