package components

import kotlinx.html.*

fun FlowContent.fullScreenImage(url: String, content: FlowContent.() -> Unit = {}) {
    div("w-full h-96 md:h-screen bg-cover bg-center bg-no-repeat") {
        style = "background-image: url('$url');"
        content()
    }
}
