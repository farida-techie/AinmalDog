package com.malkinfo.animaldog.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class AnimalBreed(
        @ColumnInfo(name = "breed_id")
        @SerializedName("id")
        val breedId:String?,

        @ColumnInfo(name = "breed_name")
        @SerializedName("name")
        val dogBreed:String?,

        @ColumnInfo(name = "life_span")
        @SerializedName("life_span")
        val lifeSpan:String?,

        @ColumnInfo(name = "breed_group")
        @SerializedName("breed_group")
        val breedGroup:String?,

        @ColumnInfo(name = "breed_for")
        @SerializedName("breed_for")
        val breedFor:String?,

        @SerializedName("temperament")
        val temperament:String?,

        @ColumnInfo(name = "dog_url")
        @SerializedName("url")
        val imageUri:String?
){
        @PrimaryKey(autoGenerate = true)
        var uuid:Int = 0
}
