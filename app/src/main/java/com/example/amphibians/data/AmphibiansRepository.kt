package com.example.amphibians.data

import com.example.amphibians.network.ApiService

interface AmphibiansRepository {
    suspend fun getAmphibians(): List<Amphibians>
}

class NetworkAmphibiansRepository(private val amphibiansApiService: ApiService) : AmphibiansRepository {
    override suspend fun getAmphibians(): List<Amphibians> = amphibiansApiService.getAmphibians()
}