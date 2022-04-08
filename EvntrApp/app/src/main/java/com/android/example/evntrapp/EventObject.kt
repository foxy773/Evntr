package com.android.example.evntrapp


data class APIObject(
    val ms: Int,
    val query: String,
    val result: ResultObject
)

class ResultObject (
    var categories: List<CategoryObject>,
    var cities: List<City>,
    var events: List<EventsObject>
        )

class CategoryObject (
var category: String
    )


data class City(
    var city: String

)

data class EventsObject(
    var date: String?,
    var description: String?,
    var eventCategory: ArrayList<String?>,
    var eventCreator: EventCreatorObject?,
    var place: PlaceObject?,
    var price: Int?,
    var speakers: ArrayList<String?>?,
    var thumbnail: String?,
    var title: String?

)


data class EventCreatorObject(
    var creatorLogo: String?,
    var name: String
)
data class PlaceObject (
    var address: String,
    var city: String,
    var place: String

        )



