package com.example.todo.retrofit

import android.os.Parcel
import android.os.Parcelable

data class Recipe(
    val idMeal: String,
    val strMeal: String,
    val strDrinkAlternate: String,
    val strCategory: String,
    val strArea: String,
    val strInstructions: String,
    val strMealThumb: String,
    val strTags: String,
    val strYoutube: String,
    val strIngredient1: String,
    val strIngredient2: String,
    val strIngredient3: String,
    val strIngredient4: String,
    val strIngredient5: String,
    val strIngredient6: String,
    val strIngredient7: String,
    val strIngredient8: String,
    val strIngredient9: String,
    val strIngredient10: String,
    val strIngredient11: String,
    val strIngredient12: String,
    val strIngredient13: String,
    val strIngredient14: String,
    val strIngredient15: String,
    val strIngredient16: String,
    val strIngredient17: String,
    val strIngredient18: String,
    val strIngredient19: String,
    val strIngredient20: String,
    val strMeasure1: String,
    val strMeasure2: String,
    val strMeasure3: String,
    val strMeasure4: String,
    val strMeasure5: String,
    val strMeasure6: String,
    val strMeasure7: String,
    val strMeasure8: String,
    val strMeasure9: String,
    val strMeasure10: String,
    val strMeasure11: String,
    val strMeasure12: String,
    val strMeasure13: String,
    val strMeasure14: String,
    val strMeasure15: String,
    val strMeasure16: String,
    val strMeasure17: String,
    val strMeasure18: String,
    val strMeasure19: String,
    val strMeasure20: String,
    val strSource: String,
    val strImageSource: String,
    val strCreativeCommonsConfirmed: String,
    val dateModified: String,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(idMeal)
        parcel.writeString(strMeal)
        parcel.writeString(strDrinkAlternate)
        parcel.writeString(strCategory)
        parcel.writeString(strArea)
        parcel.writeString(strInstructions)
        parcel.writeString(strMealThumb)
        parcel.writeString(strTags)
        parcel.writeString(strYoutube)
        parcel.writeString(strIngredient1)
        parcel.writeString(strIngredient2)
        parcel.writeString(strIngredient3)
        parcel.writeString(strIngredient4)
        parcel.writeString(strIngredient5)
        parcel.writeString(strIngredient6)
        parcel.writeString(strIngredient7)
        parcel.writeString(strIngredient8)
        parcel.writeString(strIngredient9)
        parcel.writeString(strIngredient10)
        parcel.writeString(strIngredient11)
        parcel.writeString(strIngredient12)
        parcel.writeString(strIngredient13)
        parcel.writeString(strIngredient14)
        parcel.writeString(strIngredient15)
        parcel.writeString(strIngredient16)
        parcel.writeString(strIngredient17)
        parcel.writeString(strIngredient18)
        parcel.writeString(strIngredient19)
        parcel.writeString(strIngredient20)
        parcel.writeString(strMeasure1)
        parcel.writeString(strMeasure2)
        parcel.writeString(strMeasure3)
        parcel.writeString(strMeasure4)
        parcel.writeString(strMeasure5)
        parcel.writeString(strMeasure6)
        parcel.writeString(strMeasure7)
        parcel.writeString(strMeasure8)
        parcel.writeString(strMeasure9)
        parcel.writeString(strMeasure10)
        parcel.writeString(strMeasure11)
        parcel.writeString(strMeasure12)
        parcel.writeString(strMeasure13)
        parcel.writeString(strMeasure14)
        parcel.writeString(strMeasure15)
        parcel.writeString(strMeasure16)
        parcel.writeString(strMeasure17)
        parcel.writeString(strMeasure18)
        parcel.writeString(strMeasure19)
        parcel.writeString(strMeasure20)
        parcel.writeString(strSource)
        parcel.writeString(strImageSource)
        parcel.writeString(strCreativeCommonsConfirmed)
        parcel.writeString(dateModified)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Recipe

        if (idMeal != other.idMeal) return false
        if (strMeal != other.strMeal) return false
        if (strDrinkAlternate != other.strDrinkAlternate) return false
        if (strCategory != other.strCategory) return false
        if (strArea != other.strArea) return false
        if (strInstructions != other.strInstructions) return false
        if (strMealThumb != other.strMealThumb) return false
        if (strTags != other.strTags) return false
        if (strYoutube != other.strYoutube) return false
        if (strIngredient1 != other.strIngredient1) return false
        if (strIngredient2 != other.strIngredient2) return false
        if (strIngredient3 != other.strIngredient3) return false
        if (strIngredient4 != other.strIngredient4) return false
        if (strIngredient5 != other.strIngredient5) return false
        if (strIngredient6 != other.strIngredient6) return false
        if (strIngredient7 != other.strIngredient7) return false
        if (strIngredient8 != other.strIngredient8) return false
        if (strIngredient9 != other.strIngredient9) return false
        if (strIngredient10 != other.strIngredient10) return false
        if (strIngredient11 != other.strIngredient11) return false
        if (strIngredient12 != other.strIngredient12) return false
        if (strIngredient13 != other.strIngredient13) return false
        if (strIngredient14 != other.strIngredient14) return false
        if (strIngredient15 != other.strIngredient15) return false
        if (strIngredient16 != other.strIngredient16) return false
        if (strIngredient17 != other.strIngredient17) return false
        if (strIngredient18 != other.strIngredient18) return false
        if (strIngredient19 != other.strIngredient19) return false
        if (strIngredient20 != other.strIngredient20) return false
        if (strMeasure1 != other.strMeasure1) return false
        if (strMeasure2 != other.strMeasure2) return false
        if (strMeasure3 != other.strMeasure3) return false
        if (strMeasure4 != other.strMeasure4) return false
        if (strMeasure5 != other.strMeasure5) return false
        if (strMeasure6 != other.strMeasure6) return false
        if (strMeasure7 != other.strMeasure7) return false
        if (strMeasure8 != other.strMeasure8) return false
        if (strMeasure9 != other.strMeasure9) return false
        if (strMeasure10 != other.strMeasure10) return false
        if (strMeasure11 != other.strMeasure11) return false
        if (strMeasure12 != other.strMeasure12) return false
        if (strMeasure13 != other.strMeasure13) return false
        if (strMeasure14 != other.strMeasure14) return false
        if (strMeasure15 != other.strMeasure15) return false
        if (strMeasure16 != other.strMeasure16) return false
        if (strMeasure17 != other.strMeasure17) return false
        if (strMeasure18 != other.strMeasure18) return false
        if (strMeasure19 != other.strMeasure19) return false
        if (strMeasure20 != other.strMeasure20) return false
        if (strSource != other.strSource) return false
        if (strImageSource != other.strImageSource) return false
        if (strCreativeCommonsConfirmed != other.strCreativeCommonsConfirmed) return false
        if (dateModified != other.dateModified) return false

        return true
    }

    override fun hashCode(): Int {
        var result = idMeal.hashCode()
        result = 31 * result + strMeal.hashCode()
        result = 31 * result + strDrinkAlternate.hashCode()
        result = 31 * result + strCategory.hashCode()
        result = 31 * result + strArea.hashCode()
        result = 31 * result + strInstructions.hashCode()
        result = 31 * result + strMealThumb.hashCode()
        result = 31 * result + strTags.hashCode()
        result = 31 * result + strYoutube.hashCode()
        result = 31 * result + strIngredient1.hashCode()
        result = 31 * result + strIngredient2.hashCode()
        result = 31 * result + strIngredient3.hashCode()
        result = 31 * result + strIngredient4.hashCode()
        result = 31 * result + strIngredient5.hashCode()
        result = 31 * result + strIngredient6.hashCode()
        result = 31 * result + strIngredient7.hashCode()
        result = 31 * result + strIngredient8.hashCode()
        result = 31 * result + strIngredient9.hashCode()
        result = 31 * result + strIngredient10.hashCode()
        result = 31 * result + strIngredient11.hashCode()
        result = 31 * result + strIngredient12.hashCode()
        result = 31 * result + strIngredient13.hashCode()
        result = 31 * result + strIngredient14.hashCode()
        result = 31 * result + strIngredient15.hashCode()
        result = 31 * result + strIngredient16.hashCode()
        result = 31 * result + strIngredient17.hashCode()
        result = 31 * result + strIngredient18.hashCode()
        result = 31 * result + strIngredient19.hashCode()
        result = 31 * result + strIngredient20.hashCode()
        result = 31 * result + strMeasure1.hashCode()
        result = 31 * result + strMeasure2.hashCode()
        result = 31 * result + strMeasure3.hashCode()
        result = 31 * result + strMeasure4.hashCode()
        result = 31 * result + strMeasure5.hashCode()
        result = 31 * result + strMeasure6.hashCode()
        result = 31 * result + strMeasure7.hashCode()
        result = 31 * result + strMeasure8.hashCode()
        result = 31 * result + strMeasure9.hashCode()
        result = 31 * result + strMeasure10.hashCode()
        result = 31 * result + strMeasure11.hashCode()
        result = 31 * result + strMeasure12.hashCode()
        result = 31 * result + strMeasure13.hashCode()
        result = 31 * result + strMeasure14.hashCode()
        result = 31 * result + strMeasure15.hashCode()
        result = 31 * result + strMeasure16.hashCode()
        result = 31 * result + strMeasure17.hashCode()
        result = 31 * result + strMeasure18.hashCode()
        result = 31 * result + strMeasure19.hashCode()
        result = 31 * result + strMeasure20.hashCode()
        result = 31 * result + strSource.hashCode()
        result = 31 * result + strImageSource.hashCode()
        result = 31 * result + strCreativeCommonsConfirmed.hashCode()
        result = 31 * result + dateModified.hashCode()
        return result
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}