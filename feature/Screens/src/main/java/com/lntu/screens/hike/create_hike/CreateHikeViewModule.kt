package com.lntu.screens.hike.create_hike

import androidx.lifecycle.ViewModel
import com.lntu.domain.navigation.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateHikeViewModule  @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

}