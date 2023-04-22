package `in`.rk585.notes.ui.authentication.login

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import `in`.rk585.notes.core.common.authentication.LoginViewModel
import `in`.rk585.notes.core.common.viewModel

object LoginScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = viewModel { loginViewModel() }

        LoginScreen(viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LoginScreen(
    viewModel: LoginViewModel
) {
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            LoginScreenContent(
                modifier = Modifier.fillMaxSize(),
                onClickCreateNewAccount = { /**TODO**/ },
                onClickLogin = viewModel::login,
                onClickForgetPassword = { /**TODO**/ }
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun LoginScreenContent(
    modifier: Modifier = Modifier,
    onClickLogin: (email: String, password: String) -> Unit,
    onClickForgetPassword: () -> Unit,
    onClickCreateNewAccount: () -> Unit
) {
    val (email, onEmailChanged) = remember { mutableStateOf(TextFieldValue()) }
    val (password, onPasswordChanged) = remember { mutableStateOf(TextFieldValue()) }
    val isInputValid by remember(email.text, password.text) {
        derivedStateOf {
            email.text.isNotEmpty() && password.text.isNotEmpty()
        }
    }
    val passwordFocusRequester by remember { mutableStateOf(FocusRequester()) }
    var passwordVisibility by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()).then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 12.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Text(
            text = "Sign in",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(Dp.Hairline))
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChanged,
            placeholder = { Text("Email address") },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { passwordFocusRequester.requestFocus() }
            ),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(0.45f)
            )
        )
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChanged,
            placeholder = { Text("Password") },
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisibility = !passwordVisibility },
                    enabled = password.text.isNotEmpty()
                ) {
                    Crossfade(targetState = passwordVisibility) { isVisible ->
                        Icon(
                            imageVector = if (isVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
                            contentDescription = null
                        )
                    }
                }
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (!isInputValid) return@KeyboardActions
                    keyboardController?.hide()
                    onClickLogin(email.text, password.text)
                }
            ),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(0.45f)
            )
        )
        Button(
            onClick = {
                if (!isInputValid) return@Button
                keyboardController?.hide()
                onClickLogin(email.text, password.text)
            },
            modifier = Modifier.defaultMinSize(
                TextFieldDefaults.MinWidth,
                ButtonDefaults.MinHeight
            ),
            enabled = isInputValid,
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Sign in",
                fontWeight = FontWeight.SemiBold
            )
        }
        Text(
            text = "Forgot your password?",
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClickForgetPassword
            ),
            fontWeight = FontWeight.Medium,
            color = LocalContentColor.current.copy(0.9f),
            style = MaterialTheme.typography.labelLarge
        )
        Spacer(modifier = Modifier.height(Dp.Hairline))
        Text("Don't have an account?")
        OutlinedButton(
            onClick = onClickCreateNewAccount,
            modifier = Modifier.defaultMinSize(
                TextFieldDefaults.MinWidth,
                ButtonDefaults.MinHeight
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Create new account",
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
