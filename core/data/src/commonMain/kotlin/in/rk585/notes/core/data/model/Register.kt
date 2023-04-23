package `in`.rk585.notes.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterWithEmail(
    val email: String,
    val name: String,
    val password: String
)
