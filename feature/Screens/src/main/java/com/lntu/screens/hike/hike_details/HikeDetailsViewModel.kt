package com.lntu.screens.hike.hike_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.lntu.domain.hikes.HikesRepository
import com.lntu.domain.mushrooms.MushroomsRepository
import com.lntu.domain.navigation.Navigator
import com.lntu.screens.hike.create_hike.CreateHikeConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HikeDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navigator: Navigator,
    private val hikesRepository: HikesRepository,
    private val mushroomRepository: MushroomsRepository
) : ViewModel() {
    private val id: String = savedStateHandle.toRoute<HikeDetailsScreenConstants.Args>().id
    private val _state = MutableStateFlow(HikeDetailsUiState(id = id))
    internal val state: StateFlow<HikeDetailsUiState>
        get() = _state


    init {
        viewModelScope.launch {
            val hikeId = state.value.id
            mushroomRepository.getMushroomsByHikeId(hikeId).collectLatest { mushrooms ->
                _state.update {
                    it.copy(
                        mushrooms = mushrooms.map { mushroom ->
                            HikeDetailsUiState.MushroomUiState(
                                hikeId = mushroom.hikeId,
                                id = mushroom.id,
                                name = mushroom.name,
                                description = mushroom.description,
                                weight = mushroom.weight
                            )
                        }
                    )
                }
            }
        }
    }

    fun onBackClicked() {
        navigator.popBackStack()
    }

    fun onMushroomClicked(mushroomId: String) {
        navigator.navigateToMushroomDetails(mushroomId)
    }

    fun onAddMushroomClicked() {
        navigator.navigateToCreateMushroom(id = null, hikeId = state.value.id, name = null, description = null, weight = null)
    }

    fun onMushroomEditClicked(mushroomId: String) {
        val mushroom = state.value.mushrooms.first { it.id == mushroomId }
        navigator.navigateToCreateMushroom(id = mushroom.id, hikeId = mushroom.hikeId, name = mushroom.name, description = mushroom.description, weight = mushroom.weight)
    }

    fun onMushroomDeleteClicked(mushroomId: String) {
        viewModelScope.launch {
            mushroomRepository.deleteMushroom(mushroomId)
        }
    }
}