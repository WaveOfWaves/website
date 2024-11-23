package components

import kotlinx.html.FlowContent
import kotlinx.html.details
import kotlinx.html.div
import kotlinx.html.summary

fun FlowContent.faqEntry(question: String, answer: String) {
    details("border border-stone-700 rounded-lg") {
        summary("p-4 cursor-pointer text-lg font-semibold select-none") {
            +question
        }
        div("px-4 pb-4 text-sm") {
            +answer
        }
    }
}
