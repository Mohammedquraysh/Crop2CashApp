package com.example.exhibitappcrop2cash.repository

import com.example.exhibitappcrop2cash.model.ExhibitModelResponse
import retrofit2.Response

interface ExhibitRepositoryInterface {
    suspend fun getExhibit () : Response<ExhibitModelResponse>
}