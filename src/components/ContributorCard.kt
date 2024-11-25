package components

import kotlinx.html.*
import kotlinx.serialization.Serializable
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

@Serializable
data class Profile(
    val name: String,
    val gravatar: String,
    val blurb: String,
)

fun FlowContent.contributorCard(profile: Profile) = with(profile) {

    outlined {
        div("not-prose flex flex-row items-center gap-4") {
            lazyImg(
                src = "https://gravatar.com/avatar/$gravatar?size=256",
                alt = name,
                classes = "w-24 h-24 rounded-l-md"
            ) {}
            div("flex flex-col") {
                h2("text-xl font-bold") { +name }
                p("text-sm") { +blurb }
            }
        }
    }
}

object HashUtil {
    @OptIn(ExperimentalStdlibApi::class)
    fun hash(input: String): String {
        return MessageDigest.getInstance("SHA-256").digest(input.toByteArray(UTF_8)).toHexString()
    }
}
