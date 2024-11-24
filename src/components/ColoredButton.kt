package components

import kotlinx.html.*

fun FlowContent.coloredButton(
    text: String,
    classes: String,
    url: String,
    icon: A.() -> Unit = {},
) {
    a(
        href = url,
        classes = "$classes inline-block px-6 py-2 h-10 text-white hover:text-stone-100 uppercase rounded-md font-bold text-sm no-underline px-2 flex items-center space-x-2 w-max"
    ) {
        icon()
        span { +text }
    }
}
