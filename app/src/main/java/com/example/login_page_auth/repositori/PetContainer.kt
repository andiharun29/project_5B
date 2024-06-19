package com.example.login_page_auth.repositori

import com.google.firebase.firestore.FirebaseFirestore

interface AppContainer {
    val petRepositori:PetRepositori
}

class PetContainer : AppContainer{
    private val firestore:FirebaseFirestore = FirebaseFirestore.getInstance()

    override val petRepositori: PetRepositori by lazy {
        PetRepositoriImpl(firestore)
    }
}