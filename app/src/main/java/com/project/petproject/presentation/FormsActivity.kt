package com.project.petproject.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.petproject.R
import com.project.petproject.ui.theme.Blue40
import com.project.petproject.ui.theme.Brown80
import com.project.petproject.ui.theme.Orange80
import com.project.petproject.ui.theme.PetprojectTheme
import com.project.petproject.ui.theme.White
import com.project.petproject.ui.theme.mainFontFamily
import com.project.petproject.viewmodel.add_edit_user.AddEditUserEvent
import com.project.petproject.viewmodel.add_edit_user.AddEditUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp


@AndroidEntryPoint
@HiltAndroidApp
class FormsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PetprojectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FormPage()
                }
            }
        }
    }

    @Composable
    fun FormTitle() {
        Surface(
            modifier = Modifier
                .height(250.dp)
                .fillMaxSize()
                .fillMaxHeight()
                .padding(10.dp, 0.dp),
            color = Orange80
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Text(
                    text = "Seu novo amigo quer saber seu nome...",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(5.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = mainFontFamily,
                    fontSize = 38.sp,
                    lineHeight = 38.sp,
                    letterSpacing = 0.5.sp,
                    color = Brown80,
                )
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun FormPage()  {
        var text by remember { mutableStateOf(TextFieldValue("")) }
        var isValid by remember { mutableStateOf(false) }
        var validFormNameFlag by remember { mutableStateOf(false) }

        val viewModel: AddEditUserViewModel = AddEditUserViewModel()

        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            color = Orange80
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                FormTitle()
                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    text = stringResource(R.string.label_username),
                    fontSize = 20.sp,
                    color = White,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = mainFontFamily,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(60.dp, 0.dp)
                )

                Spacer(modifier = Modifier.padding(8.dp))

                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start,
                ) {
                    TextField(
                        value = text,
                        maxLines = 1,
                        isError = !isValid,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        onValueChange = {input : TextFieldValue ->
                            val newValue = if (input.text.isBlank()) {
                                input.text.toString()

                            } else input.text
                            text = input.copy(
                                text = newValue,
                                selection = TextRange(newValue.length)
                            )
                            isValid = input.text.isNotEmpty()
                            viewModel.onEvent(AddEditUserEvent.EnteredName(input.text))
                        },
                        modifier = Modifier
                            .absoluteOffset(x = 5.dp)
                            .shadow(elevation = 8.dp, ambientColor = Color.Black, clip = true)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        placeholder = { Text(text = "Nome Completo") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "UserIcon"
                            )
                        },
                        trailingIcon = {
                            Button(
                                onClick = {
                                    if (text.text.isNotEmpty()) {
                                        val intent =
                                            Intent(this@FormsActivity, ContactFormsActivity::class.java)
                                        startActivity(intent)
                                    }
                                    else {
                                        validFormNameFlag = true
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Blue40,
                                    contentColor = White,
                                )
                            ) {}
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "NextButton",
                            )
                        }
                    )
                }
                if (validFormNameFlag) {
                    validFormName()
                    validFormNameFlag = true
                }
            }
        }
    }

    @Composable
    private fun validFormName() {
        var isValid by remember { mutableStateOf(false) }
        if (!isValid) {
            Text(
                text = "Este campo não pode ser vazio.",
                color = Color.Red,
                modifier = Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }

}