package com.example.login_page_auth.ui.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_page_auth.AddPet
import com.example.login_page_auth.AddUIState
import com.example.login_page_auth.repositori.PetRepositori
import com.example.login_page_auth.toPet
import com.example.login_page_auth.toUIStatePet
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositori: PetRepositori
) : ViewModel() {

    var petUiState by   mutableStateOf(AddUIState())
        private set

    private val petId: String = checkNotNull(savedStateHandle[EditDestination.petId])

    init {
        viewModelScope.launch {
            petUiState =
                repositori.getPetById(petId)
                    .filterNotNull()
                    .first()
                    .toUIStatePet()
        }
    }

    fun updateUIState(addEvent: AddPet) {
        petUiState = petUiState.copy(addPet = addEvent)
    }

    suspend fun updatePet(){
        repositori.update(petUiState.addPet.toPet())
    }

}