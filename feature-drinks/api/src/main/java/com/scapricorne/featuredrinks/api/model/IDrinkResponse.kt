package com.scapricorne.featuredrinks.api.model

interface IDrink {
    val id: Int?
    val name: String?
    val tagline: String?
    val firstBrewed: String?
    val description: String?
    val imageUrl: String?
    val volume: IVolume?
    val boilVolume: IBoilVolume?
    val foodPairing: List<String>
    val brewersTips: String?
    val contributedBy: String?
}

interface IVolume {
    val value: Int?
    val unit: String?
}

interface IBoilVolume {
    val value: Int?
    val unit: String?
}
