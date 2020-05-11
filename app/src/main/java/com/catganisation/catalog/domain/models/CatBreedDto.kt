package com.catganisation.catalog.domain.models

import android.os.Parcel
import android.os.Parcelable

data class CatBreedDto(
    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String,
    val temperament: String,
    val countryCode: String,
    val country: String,
    val wikipediaUrl: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(imageUrl)
        parcel.writeString(temperament)
        parcel.writeString(countryCode)
        parcel.writeString(country)
        parcel.writeString(wikipediaUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CatBreedDto> {
        override fun createFromParcel(parcel: Parcel): CatBreedDto {
            return CatBreedDto(parcel)
        }

        override fun newArray(size: Int): Array<CatBreedDto?> {
            return arrayOfNulls(size)
        }
    }
}