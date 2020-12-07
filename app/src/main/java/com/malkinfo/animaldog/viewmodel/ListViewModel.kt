package com.malkinfo.animaldog.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.malkinfo.animaldog.model.AnimalBreed
import com.malkinfo.animaldog.model.DogDao
import com.malkinfo.animaldog.model.DogDatabase
import com.malkinfo.animaldog.model.DogsApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ListViewModel(application:Application) :BaseViewModel(application){

    private val dogsService = DogsApiService()
    private val disposable = CompositeDisposable()
    val animal = MutableLiveData<List<AnimalBreed>>()
    val animalLoadError = MutableLiveData<Boolean>()
    val loding = MutableLiveData<Boolean>()
    fun refresh(){
       fetechfromRemote()
    }
    private fun fetechfromRemote(){
        loding.value = true
        disposable.add(dogsService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<AnimalBreed>>(){
                    override fun onSuccess(dogList: List<AnimalBreed>) {
                       dogRetrieved(dogList)
                    }

                    override fun onError(e: Throwable) {
                        animalLoadError.value = true
                        loding.value = false
                        e.printStackTrace()
                    }

                })
        )

    }
    private fun dogRetrieved(animalList:List<AnimalBreed>){
        animal.value = animalList
        animalLoadError.value = false
        loding.value = false
    }
    private fun storeDogsLocally(list:List<AnimalBreed>){
        launch {
            val dao  = DogDatabase(getApplication()).dogDao()
            dao.deleteAllDogs()
            val result  = dao.insertAll(*list.toTypedArray()) as List<Long>
            var i = 0
            while (i< list.size){
                list[i].uuid = result[i].toInt()
                ++i
            }
            dogRetrieved(list)

        }
    }
}