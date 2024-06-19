package com.example.login_page_auth.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_page_auth.DetailUIState
import com.example.login_page_auth.repositori.PetRepositori
import com.example.login_page_auth.toDetailPet
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel (
    savedStateHandle: SavedStateHandle,
    private val repository: PetRepositori
): ViewModel(){

    companion object{
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val petId: String = checkNotNull(savedStateHandle[DetailDestination.petId])

    val uiState: StateFlow<DetailUIState> =
        repository.getPetById(petId)
            .filterNotNull()
            .map {
                DetailUIState(addPet = it.toDetailPet())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = DetailUIState()
            )
    suspend fun deletePet(){
        repository.delete(petId)
    }
}