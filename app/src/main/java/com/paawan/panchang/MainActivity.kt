package com.paawan.panchang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.paawan.panchang.core.theme.PaawanPanchangTheme
import com.paawan.panchang.presentation.MainViewModel
import com.paawan.panchang.presentation.navigation.PaawanNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val isSystemDark = isSystemInDarkTheme()
            val isDarkModePref by mainViewModel.isDarkMode.collectAsState()
            val useDarkTheme = isDarkModePref ?: isSystemDark

            PaawanPanchangTheme(darkTheme = useDarkTheme) {
                Surface {
                    PaawanNavGraph()
                }
            }
        }
    }
}
