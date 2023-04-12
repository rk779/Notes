package in.rk585.notes

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform