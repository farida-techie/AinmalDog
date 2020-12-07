package com.malkinfo.animaldog.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DogDao {
    @Insert
    suspend fun insertAll(vararg dogs:AnimalBreed)

    @Query("SELECT * FROM animalbreed")
    suspend fun getAllDog():List<AnimalBreed>

    @Query("SELECT * FROM animalbreed")
    suspend fun getDog(dogId:Int):AnimalBreed

    @Query("DELETE FROM animalbreed")
    suspend fun deleteAllDogs()
}