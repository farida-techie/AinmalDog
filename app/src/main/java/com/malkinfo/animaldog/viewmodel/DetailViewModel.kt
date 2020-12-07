package com.malkinfo.animaldog.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.malkinfo.animaldog.model.AnimalBreed

class DetailViewModel:ViewModel() {
    val animalLiveData = MutableLiveData<AnimalBreed>()
    fun fetch(){
        val animal = AnimalBreed("1",
                "Tiger",
                "15 years",
                "breedGroup",
                "breedFor",
                "temperament",
                "")

        animalLiveData.value = animal
    }
}