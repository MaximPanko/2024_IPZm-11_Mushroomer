package com.lntu.test

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

const val testRoute = "test_route"

@Composable
fun TestRoute(
    testViewModel: TestViewModel = hiltViewModel()
) {
    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Text(text = "Привіт грибник! Знайди мудрого гриба")
    }
}