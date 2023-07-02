package com.example.ui_character_list.di

import com.example.character_interactors.CharacterInteractors
import com.example.character_interactors.GetCharacters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CharactersListModule {

    @Provides
    @Singleton
    fun provideGetCharactersInteractor(interactors: CharacterInteractors): GetCharacters {
        return interactors.getCharacters
    }

}