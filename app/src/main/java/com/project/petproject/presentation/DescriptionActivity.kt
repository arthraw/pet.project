package com.project.petproject.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.project.petproject.ui.theme.Blue40
import com.project.petproject.ui.theme.Brown80
import com.project.petproject.ui.theme.Orange80
import com.project.petproject.ui.theme.PetprojectTheme
import com.project.petproject.ui.theme.White
import com.project.petproject.ui.theme.mainFontFamily
import com.project.petproject.ui.theme.petFontFamily
import com.project.petproject.viewmodel.add_edit_user.AddEditUserViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DescriptionActivity : AppCompatActivity(){

    private lateinit var viewModel: AddEditUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetprojectTheme {
                val navController = rememberNavController()
                NavigationProvider(navController = navController, viewModel = viewModel)
            }
        }
    }

}


@Composable
private fun DescTitle(
    modifier: Modifier,
) {
    Text(
        text = "Agora nos conte um pouco sobre você...",
        modifier = modifier,
        fontWeight = FontWeight.Bold,
        fontFamily = mainFontFamily,
        style = MaterialTheme.typography.bodyLarge,
        fontSize = 35.sp,
        lineHeight = 38.sp,
        letterSpacing = 0.5.sp,
        color = Brown80,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutUser(navController: NavHostController) {
    var textDesc by remember { mutableStateOf(TextFieldValue("")) }
    var validFormInputFlag by remember { mutableStateOf(false) }
    var isValid by remember { mutableStateOf(false) }

    Surface(
        color = Orange80,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.padding(15.dp))
            Row (
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start
            ){
                DescTitle(modifier = Modifier.padding(10.dp))
            }
            Spacer(modifier = Modifier.padding(15.dp))
            Row (
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(10.dp)
            ){
                Text(
                    text = "Escreva uma breve descrição sobre você e diga o que te motiva a adotar um novo amigo. ",
                    modifier = Modifier,
                    fontWeight = FontWeight.Bold,
                    fontFamily = mainFontFamily,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 18.sp,
                    lineHeight = 28.sp,
                    letterSpacing = 0.5.sp,
                    color = Brown80,
                )
            }
        }
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = textDesc,
                    maxLines = 5,
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
                    },
                    placeholder = { Text(text = "Sobre mim...") },
                    modifier = Modifier
                        .shadow(elevation = 8.dp, ambientColor = Color.Black, clip = true)
                        .height(150.dp)
                        .width(350.dp)
                        .clip(shape = RoundedCornerShape(10.dp)),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        unfocusedLabelColor = Color.Transparent,
                        errorLabelColor = Color.Transparent
                    ),
                )
            }

            if (validFormInputFlag) {
                ValidFormInput()
                validFormInputFlag = true
            }
        }
        Column (
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        if (textDesc.text.isNotEmpty()) {
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
                ) {
                    Text(
                        text = "Enviar",
                        fontFamily = petFontFamily,
                        style = MaterialTheme.typography.bodyLarge,
                        color = White,
                        fontSize = 17.sp,
                        maxLines = 1,
                    )
                }
            }
            Spacer(modifier = Modifier.padding(50.dp))
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
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Este campo e obrigatório.",
                color = Color.Red,
                modifier = Modifier
                    .offset(y = (20).dp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}
