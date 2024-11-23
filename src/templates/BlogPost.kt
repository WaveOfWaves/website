package templates

import me.dvyy.shocky.markdown
import me.dvyy.shocky.page.Page

fun Page.blogPost() = default {
//    postInformation(this@blogPost)
    markdown(content)
}

//fun FlowContent.postInformation(page: Page) {
//    div("flex-col") {
//        page.desc?.let {
//            div("my-2") {
//                secondaryText(it)
//            }
//        }
//        div("my-2 flex overflow-x-auto space-x-1 items-center content-start scrollbar-hide") {
//            page.formattedDate?.let {
//                icons.calendar
//                secondaryText(it)
//            }
//
//            if (page.tags.isNotEmpty()) {
//                div("pl-2") { icons.tags }
//                for (tag in page.tags) outlinedChip(tag)
//            }
//        }
//    }
//}
