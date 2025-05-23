package hrcode.labs.registrodealumnos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import hrcode.labs.registrodealumnos.data.student.StudentViewModel
import hrcode.labs.registrodealumnos.data.student.StudentViewModelFactory
import hrcode.labs.registrodealumnos.ui.theme.RegistroDeAlumnosTheme
import hrcode.labs.registrodealumnos.ui.components.BottomNavigationBar
import hrcode.labs.registrodealumnos.ui.views.AllStudentsScreen
import hrcode.labs.registrodealumnos.ui.views.HomeScreen
import hrcode.labs.registrodealumnos.ui.views.RegisterStudentScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.getValue


class MainActivity : ComponentActivity() {
    private val viewModel: StudentViewModel by viewModels {
        StudentViewModelFactory((application as App).studentRepository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.dummyData()
        setContent {
            RegistroDeAlumnosTheme{
                enableEdgeToEdge()
                RegisterStudentsApp(viewModel)
            }

        }
    }
}

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Filled.Home)
    object RegisterStudent : Screen("register_student", "Registrar", Icons.Filled.Create)
    object AllStudents : Screen("all_students", "Lista", Icons.Filled.Search)
}

@Composable
fun RegisterStudentsApp(viewModel: StudentViewModel) {
    val navController = rememberNavController()


    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        },
    ) { inner ->
            NavHost(
                navController = navController,
                modifier = Modifier
                    .padding(inner),
                startDestination = Screen.Home.route

            ) {
                // Pantalla de inicio
                composable(Screen.Home.route) {
                    HomeScreen(navController)
                }
                composable(Screen.RegisterStudent.route) {
                    RegisterStudentScreen(navController, viewModel)
                }
                composable(Screen.AllStudents.route) {
                    AllStudentsScreen(navController, viewModel)
                }
        }
    }

}

