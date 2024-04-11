package com.example.unsplash.presentation

import SearchPhotosScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.unsplash.presentation.ui.theme.UnsplashTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnsplashTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenRoute.SearchPhotoScreen.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        //画像検索画面
                        composable(route = ScreenRoute.SearchPhotoScreen.route) {
                            SearchPhotosScreen(navController)
                        }
                        // 画像検索画面
                        composable(route = ScreenRoute.PhotoDetailScreen.route) {}
                    }
                }
            }
        }
    }
}
