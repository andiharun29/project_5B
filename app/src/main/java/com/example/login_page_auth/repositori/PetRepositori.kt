package com.example.login_page_auth.repositori

import android.content.ContentValues
import android.util.Log
import com.example.login_page_auth.model.Pet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await


interface PetRepositori {
    fun getAll(): Flow<List<Pet>>
    suspend fun add(pet: Pet): String
    suspend fun update(pet: Pet)
    suspend fun delete(id: String)
    fun getPetById(id: String):Flow<Pet>

}
class PetRepositoriImpl(private val firestore: FirebaseFirestore): PetRepositori{
    val user = FirebaseAuth.getInstance().currentUser
    val uid = user?.uid.toString()

    override fun getAll(): Flow<List<Pet>> = flow{
        val snapshot = firestore.collection("User")
            .document(uid)
            .collection("Pet")
            .orderBy("namapet", Query.Direction.ASCENDING)
            .get()
            .await()
        val pet = snapshot.toObjects(Pet::class.java)
        emit(pet)
    }.flowOn(Dispatchers.IO)

    override suspend fun add(pet: Pet): String {
        return try {
            val documentReference = firestore.collection("User")
                .document(uid)
                .collection("Pet")
                .document()


            pet.id = documentReference.id
            documentReference.set(pet)

            "Succes + ${documentReference.id}"
        } catch (e: Exception){
            Log.w(ContentValues.TAG,"Error adding document",e)
            "Failed $e"
        }
    }

    override suspend fun update(pet: Pet) {
        firestore.collection("User")
            .document(uid)
            .collection("Pet")
            .document(pet.id)
            .set(pet)
            .await()
    }

    override suspend fun delete(id: String) {
        firestore.collection("User")
            .document(uid)
            .collection("Pet")
            .document(id)
            .delete()
            .await()
    }

    override fun getPetById(id: String): Flow<Pet> {
        return flow {
            val snapshot = firestore.collection("User")
                .document(uid)
                .collection("Pet")
                .document(id)
                .get()
                .await()
            val pet = snapshot.toObject(Pet::class.java)
            emit(pet!!)
        }.flowOn(Dispatchers.IO)
    }
}