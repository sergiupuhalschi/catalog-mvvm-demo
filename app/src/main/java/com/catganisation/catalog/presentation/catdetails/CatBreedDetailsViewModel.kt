package com.catganisation.catalog.presentation.catdetails

import androidx.lifecycle.MutableLiveData
import com.catganisation.catalog.domain.models.CatBreed
import com.catganisation.catalog.presentation.common.BaseViewModel
import com.catganisation.catalog.utils.SingleLiveEvent
import javax.inject.Inject

class CatBreedDetailsViewModel @Inject constructor() : BaseViewModel() {

    val imageUrl = MutableLiveData<String>()
    val name = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val countryCode = MutableLiveData<String>()
    val temperament = MutableLiveData<String>()
    val url = MutableLiveData<String>()

    val openUrl = SingleLiveEvent<String>()

    fun setCatBreed(catBreed: CatBreed) {
        imageUrl.value = catBreed.imageUrl
        name.value = catBreed.name
        description.value = catBreed.description
        countryCode.value = catBreed.countryCode
        temperament.value = catBreed.temperament
        url.value = catBreed.wikipediaUrl
    }

    fun onUrlClicked() {
        openUrl.value = url.value
    }
}