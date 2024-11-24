package pages

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.decodeFromStream
import kotlinx.serialization.Serializable
import java.nio.file.Path
import kotlin.io.path.div
import kotlin.io.path.exists
import kotlin.io.path.inputStream
import kotlin.io.path.nameWithoutExtension

@Serializable
data class ImageMeta(
    val author: String,
    val title: String? = null,
    val desc: String? = null,
    val order: Double? = null,
    val tags: List<String> = listOf(),
    val date: String? = null,
    val compress: String = "med",
)


fun Path.getImageMetaOrNull(): ImageMeta? = (parent / ("$nameWithoutExtension.yml"))
    .takeIf { it.exists() }
    ?.runCatching { Yaml.default.decodeFromStream<ImageMeta>(inputStream()) }
    ?.onFailure { it.printStackTrace() }
    ?.getOrNull()
