package com.lntu.screens.mushroom.mushroom_details

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
class MushroomDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navigator: Navigator,
    private val mushroomsRepository: MushroomsRepository
) : ViewModel() {
    private val id: String? = savedStateHandle.toRoute<MushroomDetailsConstants.Args>().id
    private val _state = MutableStateFlow(MushroomDetailsUiState(id = id))
    internal val state: StateFlow<MushroomDetailsUiState>
        get() = _state

    init {
        viewModelScope.launch {
             val mushroom = mushroomsRepository.getMushroomById(id!!).first()
            _state.value = _state.value.copy(
                id = mushroom.id,
                name = mushroom.name,
                description = mushroom.description,
                weight = mushroom.weight
            )
        }
    }

    fun onBackClicked() {
        navigator.popBackStack()
    }
}