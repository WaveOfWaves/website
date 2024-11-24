import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.ScaleMethod
import com.sksamuel.scrimage.webp.WebpWriter
import me.dvyy.shocky.Shocky
import me.dvyy.shocky.page.CommonFrontMatter
import me.dvyy.shocky.page.Page
import me.dvyy.shocky.siteRouting
import pages.blogIndex
import pages.gallery
import pages.homePage
import templates.blogPost
import templates.default
import kotlin.io.path.*

@OptIn(ExperimentalPathApi::class)
suspend fun main(args: Array<String>) = Shocky(
    dest = Path("out"),
    route = siteRouting(path = Path("site")) {
        template("default", Page::default)
        template("gallery", Page::gallery)
        template("home", Page::homePage)
        template("blog", Page::blogPost)

        pages(".")

        "blog" {
            generate(meta = CommonFrontMatter(title = "Blog", url = "/blog")) { blogIndex() }
        }
    },
    assets = listOf(Path("site/assets")),
    // If enabled, will auto download and run tailwind standalone binary
    useTailwind = true,
    watch = listOf(Path("site")),
).run(args).also {
    println("Generating images...")
    Path("site/assets/gallery").walk()
        .filter { it.isRegularFile() && it.extension == "png" }
        .forEach { path ->
            val outputPath =
                Path("out") / path.parent.relativeTo(Path("site")) / (path.nameWithoutExtension + "-min.webp")
            if (outputPath.exists()) return@forEach
            println("Compressing $path")
            ImmutableImage.loader()
                .fromPath(path)
                .scaleToHeight(560, ScaleMethod.Bicubic)
                .output(WebpWriter.DEFAULT, outputPath)
        }
    println("Done generating images!")
}
