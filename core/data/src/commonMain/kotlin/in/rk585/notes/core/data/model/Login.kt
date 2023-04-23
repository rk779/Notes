package `in`.rk585.notes.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginWithEmail(
    val email: String,
    val password: String
)
