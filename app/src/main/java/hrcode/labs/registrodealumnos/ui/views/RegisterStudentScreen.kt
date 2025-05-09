package hrcode.labs.registrodealumnos.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import hrcode.labs.registrodealumnos.data.student.StudentViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import hrcode.labs.registrodealumnos.domain.Student
import hrcode.labs.registrodealumnos.ui.theme.RegistroDeAlumnosTheme


@Composable
fun RegisterStudentScreen(navController: NavController, viewModel: StudentViewModel = viewModel()) {
    val students by viewModel.students.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(16.dp)
    ) {
        var name by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var studentCode by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }

        // Error states
        var nameError by remember { mutableStateOf("") }
        var lastNameError by remember { mutableStateOf("") }
        var studentCodeError by remember { mutableStateOf("") }
        var emailError by remember { mutableStateOf("") }

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = ""
            },
            label = { Text("Nombres") },
            modifier = Modifier.fillMaxWidth(),
            isError = nameError.isNotEmpty(),
            supportingText = {
                if (nameError.isNotEmpty()) {
                    Text(text = nameError)
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = lastName,
            onValueChange = {
                lastName = it
                lastNameError = ""
            },
            label = { Text("Apellidos") },
            modifier = Modifier.fillMaxWidth(),
            isError = lastNameError.isNotEmpty(),
            supportingText = {
                if (lastNameError.isNotEmpty()) {
                    Text(text = lastNameError)
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = studentCode,
            onValueChange = {
                studentCode = it
                studentCodeError = ""
            },
            label = { Text("Student Code") },
            modifier = Modifier.fillMaxWidth(),
            isError = studentCodeError.isNotEmpty(),
            supportingText = {
                if (studentCodeError.isNotEmpty()) {
                    Text(text = studentCodeError)
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = ""
            },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            isError = emailError.isNotEmpty(),
            supportingText = {
                if (emailError.isNotEmpty()) {
                    Text(text = emailError)
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                // Validate fields
                var isValid = true

                if (name.isEmpty()) {
                    nameError = "El nombre es requerido"
                    isValid = false
                }

                if (lastName.isEmpty()) {
                    lastNameError = "El apellido es requerido"
                    isValid = false
                }

                if (studentCode.isEmpty()) {
                    studentCodeError = "El código de estudiante es requerido"
                    isValid = false
                } else if (!studentCode.matches(Regex("^\\d{10}$"))) {
                    studentCodeError = "El código debe tener exactamente 10 dígitos"
                    isValid = false
                }

                if (email.isEmpty()) {
                    emailError = "El email es requerido"
                    isValid = false
                }

                // Submit if valid
                if (isValid) {
                    val newStudent = Student(
                        name = name,
                        lastName = lastName,
                        studentCode = studentCode,
                        email = email,
                    )
                    viewModel.add(newStudent)
                    name = ""
                    lastName = ""
                    studentCode = ""
                    email = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Estudiante")
        }
    }
}



@Preview(name = "RegisterStudent")
@Composable
private fun PreviewRegisterStudentScreen() {
    val navController = NavController(context = LocalContext.current)
    RegistroDeAlumnosTheme {
        RegisterStudentScreen(navController)
    }

}
