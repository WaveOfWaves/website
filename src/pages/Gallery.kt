package pages

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.decodeFromStream
import components.card
import kotlinx.html.classes
import kotlinx.html.div
import me.dvyy.shocky.markdown
import me.dvyy.shocky.page.Page
import templates.default
import kotlin.io.path.*

fun Page.gallery() = default {
    classes += "max-w-screen-xl"
    markdown(
        """
        Welcome to our gallery! These images are posted by players in the `#promotional-images` channel on Discord.
        Click on any image to view a full size, uncompressed version.
    """.trimIndent()
    )
    div("grid grid-cols-1 space-y-4") {
        Path("site/assets/gallery")
            .listDirectoryEntries()
            .filter { !it.nameWithoutExtension.endsWith("-min") && it.extension == "webp" }
            .map { it.relativeTo(Path("site")) to it.getImageMetaOrNull() }
            .sortedWith(compareBy({ it.second?.order ?: 1000.0 }, { it.first.nameWithoutExtension }))
            .forEach { (image, meta) ->
                val minimized = image.parent / (image.nameWithoutExtension + "-min.webp")
                card(
                    meta?.title ?: image.nameWithoutExtension.capitalize(),
                    subtitle = if (meta != null) buildString {
                        append("Author: ${meta.author}")
                        if (meta.desc != null) {
                            append(" — ")
                            append(meta.desc)
                        }
                    } else null,
                    imageHeight = "560",
                    showContent = false,
                    url = image.pathString,
                    image = minimized.pathString
                )
            }
    }
}
