package components

import kotlinx.html.*

fun FlowContent.card(
    title: String,
    subtitle: String? = null,
    image: String? = null,
    url: String? = null,
    imageHeight: String = "280px",
    icon: HTMLTag.() -> Unit = {},
    content: FlowContent.() -> Unit,
) {
    div("flex flex-col bg-stone-800 border border-stone-700 rounded-lg shadow-md") {
        a(href = url) {
            div("group w-full ${if (image != null) "h-[$imageHeight]" else ""} relative overflow-hidden") {
                if (image != null) {
                    lazyImg(
                        src = image,
                        classes = "w-full h-full object-cover rounded-t-lg m-0 transform transition-transform duration-300 group-hover:scale-110"
                    ) {
                    }
                    div("absolute bottom-0 left-0 right-0 h-32 bg-gradient-to-t from-stone-800 to-transparent") {}
                }
                div("${if (image != null) "absolute" else "pt-4"} bottom-0 w-full px-4 mb-2 drop-shadow-lg") {
                    div("flex flex-row items-center space-x-1") {
                        icon()
                        p("text-2xl font-bold") { +title }
                    }
                    if (subtitle != null) code("text-sm select-all") { +subtitle }
                }
            }
        }
        div(if (subtitle == null) "p-4" else "px-4 pb-4") {
            div("text-sm") {
                content()
            }
        }
    }
}
