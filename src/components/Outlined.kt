package components

import kotlinx.html.FlowContent
import kotlinx.html.div

fun FlowContent.outlined(content: FlowContent.() -> Unit) {
    div("bg-stone-800 border border-stone-700 rounded-lg") {
        content()
    }
}
