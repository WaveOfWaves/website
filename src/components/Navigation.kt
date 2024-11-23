package components

import kotlinx.html.FlowContent
import kotlinx.html.div
import me.dvyy.shocky.page.Page

fun FlowContent.navigation(outlined: Boolean = true, center: Boolean = true, page: Page) =
    div(buildString {
        appendLine("flex items-center p-2 bg-stone-800 rounded-lg")
        if(center) appendLine("justify-center mx-auto w-max")
        if(outlined) appendLine("border border-stone-700")
    }) {
        val selected = "bg-stone-700"
        val unselected = "bg-stone-800"
        fun isSelected(url: String) = if(page.url == url) selected else unselected
        div("flex space-x-2") {
            coloredButton("About", isSelected("/"), "/#about")
            coloredButton("Gallery", isSelected("/gallery"), "/gallery")//, icon = { icons.camera })
            coloredButton("Blog", isSelected("/blog"), "/blog")//, icon = { icons.article })
            coloredButton("Wiki", unselected, "https://wiki.mineinabyss.com")//, icon = { icons.infoCircle })
        }
    }
