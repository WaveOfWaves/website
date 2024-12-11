package pages

import components.faq
import me.dvyy.shocky.markdown
import me.dvyy.shocky.page.Page
import me.dvyy.shocky.page.Pages
import templates.default
import kotlin.io.path.Path

fun Page.faq() = default {
    val homepage = Pages.single(Path("site/index.md"), Path("site"))

    markdown("""
        Welcome to our FAQ, find answers to commonly asked questions here!
    """.trimIndent())

    faq(homepage.readFrontMatter().meta<HomeMeta>().faq)
}
