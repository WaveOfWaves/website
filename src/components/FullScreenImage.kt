package components

import kotlinx.html.*

fun FlowContent.fullScreenImage(url: String, content: FlowContent.() -> Unit = {}) {
    div("w-full h-96 md:h-screen bg-cover bg-center bg-no-repeat") {
        style = "background-image: url('$url');"
        content()
    }
}

fun FlowContent.navigation() =
    div("flex justify-center items-center p-2 bg-stone-800 rounded-lg border border-stone-700 mx-auto w-max") {
        div("flex space-x-2") {
            coloredButton("Discord", "#677cc7", "/", icon = { icons.discord })
            coloredButton("GitHub", "#24292e", "/", icon = { icons.github })
            coloredButton("Patreon", "#e76551", "/", icon = { icons.patreon })
        }
    }

val primary = "#ea580c"
val secondary = "#c2410c"

fun FlowContent.coloredButton(text: String, backgroundColor: String, url: String, icon: A.() -> Unit = {}) {
    a(
        href = url,
        classes = "inline-block px-6 py-2 h-10 text-white uppercase rounded-md font-bold text-sm no-underline px-2 flex items-center space-x-2 w-max"
    ) {
        style = "background-color: $backgroundColor;"
        icon()
        span { +text }
    }
}
