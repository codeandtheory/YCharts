package co.yml.kmm.charts

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform