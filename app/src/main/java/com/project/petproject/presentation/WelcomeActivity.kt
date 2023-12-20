package com.project.petproject.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.petproject.R
import com.project.petproject.ui.theme.Blue40
import com.project.petproject.ui.theme.Orange80
import com.project.petproject.ui.theme.PetprojectTheme
import com.project.petproject.ui.theme.White
import com.project.petproject.ui.theme.petFontFamily
import com.project.petproject.ui.theme.titleFontFamily
import com.project.petproject.utils.Screens
import com.project.petproject.viewmodel.add_edit_user.AddEditUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeActivity : ComponentActivity() {

    private lateinit var viewModel: AddEditUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[AddEditUserViewModel::class.java]
        setContent {
            PetprojectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationProvider(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun NavigationProvider(navController: NavHostController, viewModel: AddEditUserViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screens.WelcomeScreen.route
    ) {
        composable(
            route = Screens.WelcomeScreen.route,
            popEnterTransition = { slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left) },
            popExitTransition = { slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right) }
        ) {
            WelcomePage(navController = navController)

        }
        composable(
            route = Screens.FormsScreen.route,
            popEnterTransition = { slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left) },
            popExitTransition = { slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right) },
        ) {
            FormPage(
                navController = navController,
                viewModel = viewModel,
            )
        }
        composable(
            route = Screens.ContactScreen.route,
            popEnterTransition = { slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left) },
            popExitTransition = { slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right) }
        ) {
            AboutUser(
                navController = navController,
            )
        }
        composable(
            route = Screens.DescriptionScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
            ) { entry ->
            ContactForm(
                navController = navController,
                viewModel = viewModel,
                name = entry.arguments?.getString("name"),
            )
        }
    }
}

@Composable
fun WelcomePage(navController: NavHostController) {
    Surface(
        color = Orange80,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(40.dp)
                .fillMaxWidth()
        ) {
            Image(
                painterResource(R.drawable.logo_pet_heart),
                contentDescription = "Logotipo da Maria Pets",
                modifier = Modifier
                    .requiredSize(170.dp)
                    .clip(RoundedCornerShape(50.dp))
            )

            Text(
                text = "Maria Pets",
                fontFamily = titleFontFamily,
                fontSize = 48.sp,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
            WelcomeButton(
                toFormScreen = {
                    navController.navigate(Screens.FormsScreen.route) {
                        popUpTo(Screens.WelcomeScreen.route) {
                            inclusive = true
                        }
                    }
                    Log.d("TESTE","TESTE")
                }
            )
        }
    }
}

@Composable
private fun WelcomeButton(toFormScreen: () -> Unit) {
    Surface(
        color = Orange80,
        modifier = Modifier
            .fillMaxWidth()
            .background(Orange80)
    ) {
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(70.dp)
                .fillMaxWidth()
        ) {
            Image(
                painterResource(R.drawable.pet_simple),
                contentDescription = "Logo de um pet",
                modifier = Modifier
                    .requiredSize(170.dp)
                    .padding(25.dp)
            )
            Button(
                onClick = { toFormScreen },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue40
                ),
                modifier = Modifier
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Entrar",
                    fontFamily = petFontFamily,
                    style = MaterialTheme.typography.bodyLarge,
                    color = White,
                    fontSize = 27.sp,
                    maxLines = 1,
                )
            }
        }
    }
}
