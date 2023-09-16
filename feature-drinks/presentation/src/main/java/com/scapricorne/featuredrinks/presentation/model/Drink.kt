package com.scapricorne.featuredrinks.presentation.model

import com.scapricorne.featuredrinks.api.model.IBoilVolume
import com.scapricorne.featuredrinks.api.model.IDrink
import com.scapricorne.featuredrinks.api.model.IVolume
import java.io.Serializable

data class Drink(
    override val id: Int?,
    override val name: String?,
    override val tagline: String?,
    override val firstBrewed: String?,
    override val description: String?,
    override val imageUrl: String?,
    override val volume: IVolume?,
    override val boilVolume: IBoilVolume?,
    override val foodPairing: List<String>,
    override val brewersTips: String?,
    override val contributedBy: String?
) : IDrink, Serializable

fun IDrink.toDomain(): Drink {
    return Drink(id = id,
        name = name,
        tagline = tagline,
        firstBrewed = firstBrewed,
        description = description,
        imageUrl = imageUrl,
        volume = volume,
        boilVolume = boilVolume,
        foodPairing = foodPairing,
        brewersTips = brewersTips,
        contributedBy = contributedBy
    )
}