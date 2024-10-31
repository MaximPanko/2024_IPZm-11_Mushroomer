package com.lntu.test

import androidx.lifecycle.ViewModel
import com.lntu.domain.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val navigator: Navigator
) : ViewModel() {

}