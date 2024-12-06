package com.lntu.screens.mushroom.create_mushroom

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.lntu.domain.mushrooms.MushroomsRepository
import com.lntu.domain.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateMushroomViewModule @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navigator: Navigator,
    private val mushroomsRepository: MushroomsRepository
) : ViewModel() {
    private val id: String? = savedStateHandle.toRoute<CreateMushroomConstants.Args>().id
    private val _state = MutableStateFlow(CreateMushroomUiState(id = id))
    internal val state: StateFlow<CreateMushroomUiState>
        get() = _state

    init {
        if (state.value.isEditMode) {
            viewModelScope.launch {
                val mushroom = mushroomsRepository.getMushroomById(id!!).first()
                _state.value = _state.value.copy(
                    name = mushroom.name,
                    description = mushroom.description ?: "",
                    weight = mushroom.weight ?: 0.0
                )
            }
        }
    }

    fun onButtonBackClicked() {
        navigator.popBackStack()
    }

    fun onMushroomNameFieldChanged(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun onMushroomDescriptionFieldChanged(description: String) {
        _state.value = _state.value.copy(description = description)
    }

    fun onMushroomWeightFieldChanged(weight: Double) {
        _state.value = _state.value.copy(weight = weight)
    }

    fun onButtonAddClicked() {
        viewModelScope.launch {
            val state = _state.value
            if (state.name.length < CreateMushroomConstants.NAME_MUSHROOM_MAX_SYMBOLS && state.isEditMode) {
                when (state.id?.let { mushroomsRepository.updateMushroom(it, state.name, state.description, state.weight) }) {
                    navigator.popBackStack() -> Unit
                }
            } else if (state.name.length < CreateMushroomConstants.NAME_MUSHROOM_MAX_SYMBOLS) {
                when (mushroomsRepository.addNewMushroom(state.hikeId ,state.name, state.description, state.weight)) {
                    navigator.popBackStack() -> Unit
                }
            }
        }
    }
}