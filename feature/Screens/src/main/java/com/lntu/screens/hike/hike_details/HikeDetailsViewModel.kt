package com.lntu.screens.hike.hike_details

import androidx.lifecycle.ViewModel
import com.lntu.domain.hikes.HikesRepository
import com.lntu.domain.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HikeDetailsViewModel @Inject constructor(
    private val navigator: Navigator,
    private val hikesRepository: HikesRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HikeDetailsUiState.DEFAULT)
    internal val state: StateFlow<HikeDetailsUiState>
        get() = _state

    init {

    }
}