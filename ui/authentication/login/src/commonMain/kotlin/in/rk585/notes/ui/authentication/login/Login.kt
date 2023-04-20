package `in`.rk585.notes.ui.authentication.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import `in`.rk585.notes.core.common.authentication.LoginViewModel
import `in`.rk585.notes.core.common.viewModel

object LoginScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = viewModel { loginViewModel() }

        Login(
            viewModel = viewModel,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Login(
    viewModel: LoginViewModel,
    modifier: Modifier = Modifier
) {
    val (email, setEmail) = remember { mutableStateOf(TextFieldValue()) }
    val (password, setPassword) = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = setEmail,
            label = { Text("Email") }
        )
        OutlinedTextField(
            value = password,
            onValueChange = setPassword,
            label = { Text("Password") }
        )
        FilledTonalButton(onClick = { viewModel.login(email.text, password.text) }) {
            Text("Login with Email")
        }
    }
}
