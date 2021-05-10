package com.example.retrofit03

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface ApiPoint {
    @GET("person")
    fun getPerson(): Call<ListResponse<personRespon>>

    @GET("person/{id}")
    fun getId(@Path("id")id:Int):Call<SingleResponse<personRespon>>

    @DELETE("person/{id}")
    fun delete(@Path("id")id: Int): Call<Void>

    @FormUrlEncoded
    @POST("person")
    fun PostData
                (@Field("first_name")first_name : String,
                 @Field("last_name")last_name : String,
                 @Field("email")email : String) : Call<ListResponse<personRespon>>

    @FormUrlEncoded
    @PUT("person/{id}")
    fun UpdateData(@Path("id")id : Int,
        @Field("first_name")first_name: String,
        @Field("last_name")last_name: String,
        @Field("email")email: String ): Call<SingleResponse<personRespon>>



}