package com.example.login_page_auth

import com.example.login_page_auth.model.Pet

data class AddUIState(
    val addPet: AddPet = AddPet(),
)

data class AddPet(
    val id: String ="",
    val namapet: String = "",
    val jenispet: String = "",
    val telpon: String = "",
)

fun AddPet.toPet() = Pet(
    id = id,
    namapet = namapet,
    jenispet = jenispet,
    telpon = telpon
)

data class DetailUIState(
    val addPet: AddPet = AddPet(),
)

fun Pet.toDetailPet(): AddPet =
    AddPet(
        id = id,
        namapet = namapet,
        jenispet = jenispet,
        telpon = telpon
    )
fun  Pet.toUIStatePet(): AddUIState = AddUIState(
    addPet = this.toDetailPet()
)
data class HomeUIState(
    val listPet: List<Pet> = listOf(),
    val datalength: Int =0
)