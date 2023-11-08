package com.project.petproject.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.project.petproject.ui.theme.Orange80
import com.project.petproject.ui.theme.PetprojectTheme
import com.project.petproject.viewmodel.add_edit_user.AddEditUserEvent
import com.project.petproject.viewmodel.add_edit_user.AddEditUserViewModel

class DescriptionActivity : AppCompatActivity(){

    private lateinit var viewModel: AddEditUserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetprojectTheme {
                AboutUser()
            }
        }
    }

    @Composable
    fun DescTitle(
        modifier: Modifier,
    ) {
        Text(
            text = "Agora nos conte um pouco sobre você..."
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AboutUser() {

        var textDesc by remember { mutableStateOf(TextFieldValue("")) }
        var validFormInputFlag by remember { mutableStateOf(false) }
        var isValid by remember { mutableStateOf(false) }

        viewModel = ViewModelProvider(this).get(AddEditUserViewModel::class.java)

        Surface(
            color = Orange80,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column (
                modifier = Modifier.fillMaxWidth()
            ) {

                DescTitle(modifier = Modifier.align(Alignment.Start))

                Text(
                    text = "Escreva uma breve descrição sobre você e seus interesses: ",
                    modifier = Modifier
                )

                TextField(
                    value = textDesc,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    onValueChange = { input: TextFieldValue ->
                        val newValue = if (input.text.isBlank()) {
                            input.text.toString()
                        } else input.text
                        if (input.text.length > 13) {
                            validFormInputFlag = true
                        }
                        textDesc = input.copy(
                            text = newValue,
                        )
                        isValid = input.text.isNotEmpty()
                        viewModel.onEvent(AddEditUserEvent.AboutUser(input.text))
                    },
                    placeholder = { Text(text = "Telefone") },
                    singleLine = true,
                    isError = !isValid,
                    modifier = Modifier
                        .shadow(elevation = 8.dp, ambientColor = Color.Black, clip = true)
                        .clip(shape = RoundedCornerShape(10.dp)),
                )

                if (validFormInputFlag) {
                    ValidFormInput()
                    validFormInputFlag = true
                }

            }
        }
    }

    @Composable
    fun DescSubmitButton(modifier: Modifier) {
        Row (
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .shadow(elevation = 8.dp, ambientColor = Color.Black, clip = true)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .align(Alignment.Bottom),
            ) {
                Text(text = "Enviar")
            }
        }
    }

    @Composable
    private fun ValidFormInput() {
        var isValid by remember { mutableStateOf(false) }
        if (!isValid) {
            Text(
                text = "Este campo e obrigatório.",
                color = Color.Red,
                modifier = Modifier
                    .offset(y = (-30).dp),
                fontWeight = FontWeight.Bold
            )
        }
    }

}