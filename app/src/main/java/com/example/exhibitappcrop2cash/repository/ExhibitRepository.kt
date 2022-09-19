package com.example.exhibitappcrop2cash.repository

import com.example.exhibitappcrop2cash.model.ExhibitModelResponse
import com.example.exhibitappcrop2cash.network.ExhibitModuleApiInterface
import retrofit2.Response
import javax.inject.Inject

class ExhibitRepository @Inject constructor(private val exhibitModuleApiInterface: ExhibitModuleApiInterface): ExhibitRepositoryInterface {
    override suspend fun getExhibit(): Response<ExhibitModelResponse> {
        return exhibitModuleApiInterface.getExhibitList()
    }
}