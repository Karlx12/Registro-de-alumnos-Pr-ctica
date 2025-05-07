package hrcode.labs.registrodealumnos.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import hrcode.labs.registrodealumnos.R
import hrcode.labs.registrodealumnos.ui.theme.RegistroDeAlumnosTheme

@Composable
fun HomeScreen(navController: NavController) {

    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_uns),
                contentDescription = "Logo Uns",
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(16.dp)
            )
            Text(
                text = "Bienvenido a Registro de Alumnos",
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(name = "HomeScreen")
@Composable
private fun PreviewHomeScreen() {
    val navController = NavController(context = LocalContext.current)
    RegistroDeAlumnosTheme {
        HomeScreen(navController)
    }
}