package com.scapricorne.featuredrinks.data.model

import com.scapricorne.featuredrinks.api.model.IBoilVolume
import com.scapricorne.featuredrinks.api.model.IDrink
import com.scapricorne.featuredrinks.api.model.IVolume
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

data class DrinkDto(
    @Json(name = "id")
    override val id: Int?,

    @Json(name = "name")
    override val name: String?,

    @Json(name = "tagline")
    override val tagline: String?,

    @Json(name = "first_brewed")
    override val firstBrewed: String?,

    @Json(name = "description")
    override val description: String?,

    @Json(name = "image_url")
    override val imageUrl: String?,

    @Json(name = "volume")
    override val volume: VolumeDto?,

    @Json(name = "boil_volume")
    override val boilVolume: BoilVolumeDto?,

    @Json(name = "food_pairing")
    override val foodPairing: List<String>,

    @Json(name = "brewers_tips")
    override val brewersTips: String?,

    @Json(name = "contributed_by")
    override val contributedBy: String?
) : IDrink, Serializable

@JsonClass(generateAdapter = true)
data class BoilVolumeDto(
    @Json(name = "value") override val value: Int?,
    @Json(name = "unit") override val unit: String?
) : IBoilVolume

@JsonClass(generateAdapter = true)
data class VolumeDto(
    @Json(name = "value") override val value: Int?,
    @Json(name = "unit") override val unit: String?
) : IVolume
