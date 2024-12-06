package com.lntu.screens.hike.hike_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lntu.domain.hikes.HikesRepository
import com.lntu.domain.mushrooms.MushroomsRepository
import com.lntu.domain.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HikeDetailsViewModel @Inject constructor(
    private val navigator: Navigator,
    private val hikesRepository: HikesRepository,
    private val mushroomRepository: MushroomsRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HikeDetailsUiState.DEFAULT)
    internal val state: StateFlow<HikeDetailsUiState>
        get() = _state

    init {
        viewModelScope.launch {
            _state.update { it.copy(
                id = state.value.id,
                mushrooms = mushroomRepository.getMushroomsByHikeId(state.value.id).first().map { mushroom ->
                    HikeDetailsUiState.MushroomUiState(
                        hikeId = state.value.id,
                        id = mushroom.id,
                        name = mushroom.name,
                        description = mushroom.description,
                        weight = mushroom.weight
                    )
                }
            ) }
        }
    }

    fun onBackClicked() {
        navigator.popBackStack()
    }

    fun onMushroomClicked() {
        //navigator.navigateToMushroomDetails()
    }

    fun onAddMushroomClicked() {
        navigator.navigateToCreateMushroom()
    }
}