package com.example.login_page_auth.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login_page_auth.HomeUIState
import com.example.login_page_auth.model.Pet
import com.example.login_page_auth.repositori.PetRepositori
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


    sealed class PetUIState {
    data class Success(val pet: Flow<List<Pet>>) : PetUIState()
    object Error : PetUIState()
    object Loading : PetUIState()
}

class ListViewModel(private val petRepositori: PetRepositori) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val homeUIState: StateFlow<HomeUIState> = petRepositori.getAll()
        .filterNotNull()
        .map {
            HomeUIState (listPet = it.toList(), it.size ) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUIState()

        )

}