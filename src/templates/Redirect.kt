package templates

import kotlinx.html.*
import me.dvyy.shocky.page.Page

fun Page.redirect(redirectUrl: String) = html {
    head {
        link(rel = LinkRel.stylesheet, href = "/assets/tailwind/styles.css")
        meta {
            httpEquiv = "refresh"
            content = "0; url=$redirectUrl"
        }
    }
    body("bg-stone-800 text-stone-100 min-h-screen flex items-center justify-center") {
        a(href = redirectUrl, classes = "underline") {
            +"Click here if you are not automatically redirected..."
        }
    }
}
