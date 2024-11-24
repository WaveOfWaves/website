import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.decodeFromStream
import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.ScaleMethod
import com.sksamuel.scrimage.nio.ImageWriter
import com.sksamuel.scrimage.webp.WebpWriter
import me.dvyy.shocky.page.CommonFrontMatter
import me.dvyy.shocky.page.Page
import me.dvyy.shocky.shocky
import pages.blogIndex
import pages.gallery
import pages.getImageMetaOrNull
import pages.homePage
import templates.blogPost
import templates.default
import templates.redirect
import java.nio.file.Path
import kotlin.io.path.*

@OptIn(ExperimentalPathApi::class)
suspend fun main(args: Array<String>) = shocky {
    dest("out")
    assets("site/assets")
    watch("site")
    siteRoot("site")

    routing {
        template("default", Page::default)
        template("gallery", Page::gallery)
        template("home", Page::homePage)
        template("blog", Page::blogPost)

        pages(".")

        val redirectUrls = Yaml.default
            .decodeFromStream<Map<String, String>>((siteRoot / "redirects.yml").inputStream())

        redirectUrls.forEach { (from, to) ->
            generate(from, meta = CommonFrontMatter()) {
                redirect(to)
            }
        }
        "blog" {
            generate(meta = CommonFrontMatter(title = "Blog", url = "/blog")) { blogIndex() }
        }
    }

    afterGenerate {
        println("Generating images...")
        Path("site/assets/gallery").walk()
            .filter { it.isRegularFile() && it.extension == "webp" }
            .forEach { path ->
                println("Compressing $path")
                compressImage(path, { scaleToHeight(560, ScaleMethod.Bicubic) }, "tiny", WebpWriter.DEFAULT.withMultiThread().withoutAlpha().withQ(75).withM(4))
                compressImage(path, { scaleToHeight(1080, ScaleMethod.Bicubic) }, "min", WebpWriter.DEFAULT.withMultiThread().withoutAlpha().withQ(85).withM(4))
            }
        println("Done generating images!")
    }
}.run {
    when (args.firstOrNull()) {
        "compress" -> compress()
        else -> run(args)
    }
}

fun compressImage(
    path: Path,
    edit: ImmutableImage.() -> ImmutableImage,
    ext: String,
    writer: ImageWriter,
) {
    val outputPath = Path("out") / path.parent.relativeTo(Path("site")) / (path.nameWithoutExtension + "-$ext.webp")
    if (outputPath.exists()) return
    ImmutableImage.loader()
        .fromPath(path)
        .scaleToHeight(1080, ScaleMethod.Bicubic)
        .run(edit)
        .output(writer, outputPath)
}

fun compress() {
    val compress = Path("compress").takeIf { it.exists() } ?: return
    compress.listDirectoryEntries().filter { it.extension == "png" }.forEach { path ->
        val out = path.parent / (path.nameWithoutExtension + ".webp")
        if (out.exists()) return@forEach
        println("Compressing $path")
        ImmutableImage.loader()
            .fromPath(path)
            .output(
                WebpWriter.DEFAULT.withLossless().withoutAlpha().withMultiThread(),
                out
            )
    }
}
