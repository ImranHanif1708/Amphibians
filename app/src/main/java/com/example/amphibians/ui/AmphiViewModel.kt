package com.example.amphibians.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.data.Amphibians
import com.example.amphibians.data.AmphibiansRepository
import com.example.amphibians.data.NetworkAmphibiansRepository
import com.example.amphibians.network.AmphibiansApi
import com.example.amphibians.network.ApiService
import kotlinx.coroutines.launch
import java.io.IOException

//private val amphibiansRepository: AmphibiansRepository

sealed interface AmphibiansUiState{
    data class Success(val amphibians: List<Amphibians>) : AmphibiansUiState
    object Loading : AmphibiansUiState
    object Error : AmphibiansUiState
}
class AmphiViewModel(private val amphibiansRepository: AmphibiansRepository) : ViewModel() {

    var amphibiansUiState : AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    init {
        getAmphibians()
    }

    fun getAmphibians() {
        viewModelScope.launch {
            amphibiansUiState = AmphibiansUiState.Loading
            try {
                val listResult = amphibiansRepository.getAmphibians()
                amphibiansUiState = AmphibiansUiState.Success(listResult)
            }
            catch (e: IOException) {
                amphibiansUiState = AmphibiansUiState.Error
                Log.d( "AmphiViewModel", "IOException: ${e.message}")
            }

        }
    }

    fun retryAmphibians() {
        amphibiansUiState = AmphibiansUiState.Loading
        getAmphibians()
    }

    companion object {
        val Factory : ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val amphibiansApiService: ApiService = AmphibiansApi.retrofitService
                val amphibiansRepository: AmphibiansRepository = NetworkAmphibiansRepository(amphibiansApiService)
                AmphiViewModel(amphibiansRepository)
            }
        }
    }
}


