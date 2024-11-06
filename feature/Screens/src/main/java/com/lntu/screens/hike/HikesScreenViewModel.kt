package com.lntu.screens.hike

import androidx.lifecycle.ViewModel
import com.lntu.domain.navigation.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HikesScreenViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

}