package com.lntu.screens.hike

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lntu.domain.hikes.HikesRepository
import com.lntu.domain.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HikesScreenViewModel @Inject constructor(
    private val navigator: Navigator,
    private val hikesRepository: HikesRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HikesScreenUiState.DEFAULT)
    internal val state: StateFlow<HikesScreenUiState>
        get() = _state

    init {
        viewModelScope.launch {
            hikesRepository.getAllHikes().collectLatest { hikes ->
                _state.update {
                    it.copy(
                        hikes = hikes.map { hike ->
                            HikesScreenUiState.HikeUiState(
                                id = hike.id,
                                name = hike.name,
                                date = hike.date
                            )
                        }
                    )
                }
            }
        }
    }

    fun onCreateNewHikeClicked() {
        navigator.navigateToCreateHike("1")
    }

    fun onHikeClicked() {
        navigator.navigateToCreateHike("1")
    }

    fun onHikeEditClicked() {
        navigator.navigateToCreateHike("1")
    }

    fun onDeleteHikeClicked(hikeId: String) {
        viewModelScope.launch {
            hikesRepository.deleteHike(hikeId)
        }
    }
}