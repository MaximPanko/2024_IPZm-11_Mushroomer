package com.lntu.screens.hike.create_hike

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.lntu.domain.hikes.HikesRepository
import com.lntu.domain.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateHikeViewModule @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navigator: Navigator,
    private val hikeRepository: HikesRepository
) : ViewModel() {
    private val id: String? = savedStateHandle.toRoute<CreateHikeConstants.Args>().id
    private val _state = MutableStateFlow(CreateHikeUi(id = id))
    internal val state: StateFlow<CreateHikeUi>
        get() = _state

    init {
        if (state.value.isEditMode) {
            viewModelScope.launch {
                val hike = hikeRepository.getHikeById(id!!).first()
                _state.update {
                    it.copy(
                        name = hike.name
                    )
                }
            }
        }
    }

    fun onButtonCancelClicked() {
        navigator.popBackStack()
    }

    fun onButtonSaveClicked() {
        viewModelScope.launch {
            val state = _state.value
            if (state.name.length < CreateHikeConstants.NAME_HIKE_MAX_SYMBOLS && state.isEditMode) {
               when (state.id?.let { hikeRepository.updateHike(it, state.name) }) {
                   navigator.popBackStack() -> Unit
               }
            }
            else if (state.name.length < CreateHikeConstants.NAME_HIKE_MAX_SYMBOLS) {
                when (hikeRepository.addNewHike(state.name)) {
                    navigator.popBackStack() -> Unit
                }
            }
        }
    }

    fun onHikeNameFieldChanged(name: String) {
        _state.update {
            it.copy(name = name)
        }
    }
}