package `in`.rk585.notes.ui.authentication.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
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
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.focus.focusRequester
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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import `in`.rk585.notes.core.base.utils.EMAIL_REGEX

object RegisterScreen : Screen {

    @Composable
    override fun Content() {
        RegisterScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RegisterScreen() {
    val navigator = LocalNavigator.currentOrThrow
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
            RegisterScreenContent(
                modifier = Modifier.fillMaxSize(),
                onClickSignIn = navigator::pop
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
internal fun RegisterScreenContent(
    modifier: Modifier = Modifier,
    onClickSignIn: () -> Unit
) {
    val (name, onNameChange) = remember { mutableStateOf(TextFieldValue()) }
    val (username, onUsernameChange) = remember { mutableStateOf(TextFieldValue()) }
    val (email, onEmailChanged) = remember { mutableStateOf(TextFieldValue()) }
    val (password, onPasswordChange) = remember { mutableStateOf(TextFieldValue()) }
    val isInputValid by remember(name.text, username.text, email.text, password.text) {
        derivedStateOf {
            name.text.isNotEmpty() && username.text.isNotEmpty()
                    && username.text.length >= 5 && email.text.isNotEmpty()
                    && email.text.matches(EMAIL_REGEX) && password.text.isNotEmpty()
        }
    }
    var passwordVisibility by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    val (
        emailFocusRequester,
        usernameFocusRequester,
        passwordFocusRequester
    ) = remember { FocusRequester.createRefs() }

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()).then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = 12.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        Text(
            text = "Create new account",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(Dp.Hairline))
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            placeholder = { Text("Name") },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { emailFocusRequester.requestFocus() }
            ),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(0.45f)
            )
        )
        OutlinedTextField(
            value = email,
            onValueChange = onEmailChanged,
            modifier = Modifier.focusRequester(emailFocusRequester),
            placeholder = { Text("Email address") },
            supportingText = {
                AnimatedVisibility(email.text.isNotEmpty() && !email.text.matches(EMAIL_REGEX)) {
                    Text("Enter a valid email address")
                }
            },
            isError = (email.text.isNotEmpty() && !email.text.matches(EMAIL_REGEX)),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { usernameFocusRequester.requestFocus() }
            ),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(0.45f)
            )
        )
        OutlinedTextField(
            value = username,
            onValueChange = onUsernameChange,
            modifier = Modifier.focusRequester(usernameFocusRequester),
            placeholder = { Text("Username") },
            supportingText = {
                AnimatedVisibility(username.text.isNotEmpty() && username.text.length <= 5) {
                    Text("Username should have a minimum of 5 characters")
                }
            },
            isError = (username.text.isNotEmpty() && username.text.length <= 5),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
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
            onValueChange = onPasswordChange,
            modifier = Modifier.focusRequester(passwordFocusRequester),
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
            },
            modifier = Modifier.defaultMinSize(
                TextFieldDefaults.MinWidth,
                ButtonDefaults.MinHeight
            ),
            enabled = isInputValid,
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Register",
                fontWeight = FontWeight.SemiBold
            )
        }
        ElevatedButton(
            onClick = onClickSignIn,
            modifier = Modifier.defaultMinSize(
                TextFieldDefaults.MinWidth,
                ButtonDefaults.MinHeight
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Sign in",
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
