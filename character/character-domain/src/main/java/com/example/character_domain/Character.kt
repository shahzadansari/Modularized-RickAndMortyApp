package com.example.character_domain

data class Character(
    val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val type: String,
    val gender: Gender,
    val origin: Location,
    val location: Location,
    val imageUrl: String
) {
    companion object
}

enum class CharacterStatus(val value: String) {
    Alive("Alive"),
    Dead("Dead"),
    Unknown("unknown");
}

enum class Gender(val gender: String) {
    Male("Male"),
    Female("Female"),
    Genderless("Genderless"),
    Unknown("unknown");
}

data class Location(
    val name: String,
    val url: String
)

val Character.Companion.example by lazy {
    Character(
        id = 1,
        name = "Rick Sanchez",
        status = CharacterStatus.Alive,
        species = "Human",
        type = "",
        gender = Gender.Male,
        origin = Location(name = "Earth (C-137) ", url = "https://rickandmortyapi.com/api/location/1"),
        location = Location(name = "Citadel of Ricks", url = "https://rickandmortyapi.com/api/location/3"),
        imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
    )
}