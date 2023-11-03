package com.project.petproject.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.petproject.R
import com.project.petproject.ui.theme.Blue40
import com.project.petproject.ui.theme.Orange80
import com.project.petproject.ui.theme.PetprojectTheme
import com.project.petproject.ui.theme.White
import com.project.petproject.ui.theme.petFontFamily
import com.project.petproject.ui.theme.titleFontFamily
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetprojectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PageInfo()
                }
            }
        }
    }

    @Composable
    fun WelcomePage() {
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
                WelcomeButton()
            }
        }
    }

    @Composable
    private fun WelcomeButton() {
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
                    onClick = {
                        val intent = Intent(this@WelcomeActivity, FormsActivity::class.java)
                        startActivity(intent)
                    },
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

    @Preview
    @Composable
    fun PageInfo() {
        return WelcomePage()
    }
}