package com.example.luxuryshop;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductService {

    @GET("/api/products")
    Call<List<ProductDto>> listProduct();

    @FormUrlEncoded
    @POST("/product/insert")
    Call<ProductDto> addProduct(@FieldMap Map<String, String> fields);

    @FormUrlEncoded
    @PATCH("/product/update/{productCd}")
    Call<ProductDto> updateProduct(@Path("productCd") String productCd,  @FieldMap Map<String, String> fields);

    @DELETE("/product/delete/{productCd}")
    Call<ProductDto> deleteProduct(@Path("productCd") String productCd);
}

