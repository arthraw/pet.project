package com.project.petproject.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.petproject.R
import com.project.petproject.presentation.theme.Blue40
import com.project.petproject.presentation.theme.Brown80
import com.project.petproject.presentation.theme.Orange80
import com.project.petproject.presentation.theme.White
import com.project.petproject.ui.theme.PetprojectTheme
import com.project.petproject.ui.theme.mainFontFamily
import com.project.petproject.ui.theme.petFontFamily
import com.project.petproject.utils.Screens
import com.project.petproject.utils.VisualPhoneTransformation
import com.project.petproject.viewmodel.add_edit_user.AddEditUserEvent
import com.project.petproject.viewmodel.add_edit_user.AddEditUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFormsActivity : ComponentActivity() {

    private lateinit var viewModel: AddEditUserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddEditUserViewModel::class.java)

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
private fun ContactFormTitle(name: String?) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.padding(0.dp, 50.dp))
        Text(
            text = "Como podemos te contatar $name?",
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
fun ContactForm(navController: NavHostController, viewModel: AddEditUserViewModel, name: String?) {
    var textPhone by remember { mutableStateOf(TextFieldValue("")) }
    var textEmail by remember { mutableStateOf(TextFieldValue("")) }
    var validFormInputFlag by remember { mutableStateOf(false) }
    var isValid by remember { mutableStateOf(false) }

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
            ContactFormTitle(name = name)
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
                text = stringResource(R.string.label_user_number),
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

            val maxTelephoneNumber = 11

            OutlinedTextField(
                value = textPhone,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                onValueChange = {
                    if (it.text.length > 13) {
                        validFormInputFlag = true
                    }
                    textPhone = it.copy(
                        text = it.text.take(maxTelephoneNumber),
                    )
                    isValid = it.text.isNotEmpty()
                    viewModel.onEvent(AddEditUserEvent.EnteredPhone(textPhone.text))
                },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = Color.Transparent,
                    errorLabelColor = Color.Transparent
                ),
                placeholder = { Text(text = "Telefone") },
                singleLine = true,
                visualTransformation = VisualPhoneTransformation(),
                modifier = Modifier
                    .shadow(elevation = 8.dp, ambientColor = Color.Black, clip = true)
                    .clip(shape = RoundedCornerShape(10.dp)),
            )

            Spacer(modifier = Modifier.padding(12.dp))

            Text(
                text = stringResource(R.string.label_user_email),
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

            OutlinedTextField(
                value = textEmail,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                onValueChange = { input: TextFieldValue ->
                    val newValue = if (input.text.isBlank()) {
                        input.text.toString()

                    } else input.text
                    textEmail = input.copy(
                        text = newValue,
                        selection = TextRange(newValue.length)
                    )
                    isValid = input.text.isNotEmpty()
                    viewModel.onEvent(AddEditUserEvent.EnteredEmail(textEmail.text))
                },
                placeholder = { Text(text = "Email") },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    unfocusedLabelColor = Color.Transparent,
                    errorLabelColor = Color.Transparent
                ),
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
                        navController.navigate(Screens.DescriptionScreen.route)
                    } else {
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
                    text = "Próximo",
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
