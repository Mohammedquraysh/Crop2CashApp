package com.example.exhibitappcrop2cash.network

import com.example.exhibitappcrop2cash.model.ExhibitModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ExhibitModuleApiInterface {

    @GET("list")
    suspend fun getExhibitList(): Response<ExhibitModelResponse>
}