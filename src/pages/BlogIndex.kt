package pages

import components.card
import kotlinx.html.div
import kotlinx.html.h2
import kotlinx.html.hr
import kotlinx.html.p
import me.dvyy.shocky.page.Page
import me.dvyy.shocky.page.Pages
import templates.default
import kotlin.io.path.Path

fun Page.blogIndex() = default {
    val posts = Pages
        .walk(Path("site/blog"), Path("site"))
        .map { it.readFrontMatter() }

    posts.groupBy { it.date?.year?.toString() ?: "Unknown year" }
        .toSortedMap { a, b -> b.compareTo(a) }
        .forEach { (year, posts) ->
            h2("text-center text-4xl mb-6") { +year }
            div("not-prose grid grid-cols-1 gap-4") {
                posts.sortedByDescending { it.date }.forEach { post ->
                    card(post.title, url = post.url) {
                        p { +(post.desc ?: "") }
//                        div("flex") {
//                            post.tags.forEach { p { +it } }
//                        }
                    }
                }
            }
        }
}
