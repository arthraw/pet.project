package com.project.petproject.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.project.petproject.presentation.add_edit_user.AddEditUserEvent
import com.project.petproject.presentation.add_edit_user.AddEditUserViewModel
import com.project.petproject.presentation.components.phoneInputValidation
import com.project.petproject.ui.theme.Blue40
import com.project.petproject.ui.theme.Brown80
import com.project.petproject.ui.theme.Orange80
import com.project.petproject.ui.theme.PetprojectTheme
import com.project.petproject.ui.theme.White
import com.project.petproject.ui.theme.mainFontFamily
import com.project.petproject.ui.theme.petFontFamily

class ContactFormsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetprojectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ContactForm()
                }
            }
        }
    }

    @Composable
    private fun ContactFormTitle() {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.padding(0.dp, 50.dp))
            Text(
                text = "Como podemos te contatar?",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(5.dp),
                style = MaterialTheme.typography.bodyLarge,
                fontFamily = mainFontFamily,
                fontSize = 38.sp,
                lineHeight = 38.sp,
                letterSpacing = 0.5.sp,
                color = Brown80,
            )
            Spacer(modifier = Modifier.padding(0.dp, 20.dp))
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun ContactForm() {
        var textPhone by remember { mutableStateOf(TextFieldValue("")) }
        var textEmail by remember { mutableStateOf(TextFieldValue("")) }
        var validFormInputFlag by remember { mutableStateOf(false) }
        var isValid by remember { mutableStateOf(false) }

        val viewModel: AddEditUserViewModel = AddEditUserViewModel()


        Surface(
            color = Orange80,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                ContactFormTitle()
                Text(
                    text = "Informe um número e um email para contato:",
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .padding(5.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = mainFontFamily,
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    letterSpacing = 0.5.sp,
                    color = Brown80,
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text =  stringResource(R.string.label_user_number),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = mainFontFamily,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(60.dp, 0.dp),
                    color = White
                )

                Spacer(modifier = Modifier.padding(5.dp))

                TextField(
                    value = textPhone,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    onValueChange = {input: TextFieldValue ->
                        val newValue = if (input.text.isBlank()) {
                            input.text.toString()
                        } else input.text
                        if (input.text.length > 13) {
                            validFormInputFlag = true
                        }
                        textPhone = input.copy(
                            text = newValue,
                        )
                        isValid = input.text.isNotEmpty()
                        viewModel.onEvent(AddEditUserEvent.EnteredPhone(input.text))
                        phoneInputValidation(input.annotatedString)
                    },
                    visualTransformation = { phoneInputValidation(it) },
                    placeholder = { Text(text = "Telefone")},
                    singleLine = true,
                    isError = !isValid,
                    modifier = Modifier
                        .shadow(elevation = 8.dp, ambientColor = Color.Black, clip = true)
                        .clip(shape = RoundedCornerShape(10.dp)),

                )
                
                Spacer(modifier = Modifier.padding(12.dp))

                Text(
                    text =  stringResource(R.string.label_user_email),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.bodyLarge,
                    fontFamily = mainFontFamily,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(60.dp, 0.dp),
                    color = White
                )

                Spacer(modifier = Modifier.padding(5.dp))

                TextField(
                    value = textEmail,
                    isError = !isValid,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    onValueChange = {input: TextFieldValue ->
                        val newValue = if (input.text.isBlank()) {
                            input.text.toString()

                        } else input.text
                        textEmail = input.copy(
                            text = newValue,
                            selection = TextRange(newValue.length)
                        )
                        isValid = input.text.isNotEmpty()
                        viewModel.onEvent(AddEditUserEvent.EnteredEmail(input.text))
                    },
                    placeholder = { Text(text = "Email")},
                    singleLine = true,
                    modifier = Modifier
                        .shadow(elevation = 8.dp, ambientColor = Color.Black, clip = true)
                        .clip(shape = RoundedCornerShape(10.dp)),
                )
                Spacer(modifier = Modifier.padding(30.dp))

                if (validFormInputFlag) {
                    ValidFormInput()
                    validFormInputFlag = true
                }

                Button(
                    onClick = {
                        if (textEmail.text.isNotEmpty() && textPhone.text.isNotEmpty()) {
                            val intent =
                                Intent(this@ContactFormsActivity, ContactFormsActivity::class.java)
                            startActivity(intent)
                        }
                        else {
                            validFormInputFlag = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue40
                    ),
                    modifier = Modifier
                        .height(50.dp)
                        .padding(25.dp, 5.dp)
                        .offset(x = 90.dp)
                ) {
                    Text(
                        text = "Finalizar",
                        fontFamily = petFontFamily,
                        style = MaterialTheme.typography.bodyLarge,
                        color = White,
                        fontSize = 17.sp,
                        maxLines = 1,
                    )
                }
            }
        }
    }

    @Composable
    private fun ValidFormInput() {
        var isValid by remember { mutableStateOf(false) }
        if (!isValid) {
            Text(
                text = "Nenhum campo pode estar vazio.",
                color = Color.Red,
                modifier = Modifier
                    .offset(y = (-30).dp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}