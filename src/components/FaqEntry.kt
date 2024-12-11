package components

import kotlinx.html.FlowContent
import kotlinx.html.details
import kotlinx.html.div
import kotlinx.html.summary
import me.dvyy.shocky.markdown

fun FlowContent.faqEntry(question: String, answer: String) {
    details("border border-stone-700 rounded-lg") {
        summary("p-4 cursor-pointer text-lg font-semibold select-none") {
            +question
        }
        div("px-4 pb-4 text-sm") {
            markdown(answer)
        }
    }
}

fun FlowContent.faq(entries: Map<String, String>) {
    div("not-prose flex flex-col space-y-4") {
        for ((question, answer) in entries.entries) {
            faqEntry(question, answer)
        }
    }
}
