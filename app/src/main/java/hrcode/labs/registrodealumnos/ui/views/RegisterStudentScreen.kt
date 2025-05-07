package hrcode.labs.registrodealumnos.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RegisterStudentScreen(navController: NavController) {

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Registrate sus datos",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Nombre") },
                    modifier = Modifier.padding(8.dp)
                )
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Apellidos") },
                    modifier = Modifier.padding(8.dp)
                )
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Código") },
                    modifier = Modifier.padding(8.dp)
                )
                TextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Carrera") },
                    modifier = Modifier.padding(8.dp)
                )


            }
        }
    }



@Preview(name = "RegisterStudent")
@Composable
private fun PreviewRegisterStudentScreen() {
    val navController = NavController(context = LocalContext.current)
    RegisterStudentScreen(navController)
}
