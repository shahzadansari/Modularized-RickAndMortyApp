package com.example.ui_character_list.ui

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import com.example.character_domain.Character
import com.example.character_domain.CharacterStatus
import com.example.character_domain.Gender
import com.example.core.Queue
import com.example.core.UIComponent
import com.example.ui_character_list.ui.components.FilterChipState

data class CharactersListState(
    val isLoading: Boolean = false,
    val characters: List<Character> = listOf(), // For the actual Characters List
    val unfilteredCharacters: List<Character> = listOf(),
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
    val statusFilters: SnapshotStateList<FilterChipState> = statusFiltersState.toMutableStateList(),
    val genderFilters: SnapshotStateList<FilterChipState> = genderFiltersState.toMutableStateList()
) {
    private val enabledStatusFilters get() = statusFilters.toList().filter { it.selected.value }.map { it.label }
    private val enabledGenderFilters get() = genderFilters.toList().filter { it.selected.value }.map { it.label }

    val filteredCharacters
        get() = unfilteredCharacters.filter { character ->
            enabledStatusFilters.contains(character.status.name)
        }.filter { character ->
            enabledGenderFilters.contains(character.gender.name)
        }
}

private val statusFiltersState = CharacterStatus.entries
    .map { characterStatus ->
        FilterChipState(label = characterStatus.name)
    }

private val genderFiltersState = Gender.entries
    .map { characterStatus ->
        FilterChipState(label = characterStatus.name)
    }