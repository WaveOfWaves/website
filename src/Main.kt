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
import templates.redirect
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

        val redirectUrls= mapOf(
            "bedrock" to "https://mineinabyss.com/2021/06/21/bedrock-and-console-support.html",
            "bestiary" to "https://www.notion.so/7c8ca1d90cbf483381b21e3479519754?v=ab90de5dfe02492ea22a8befa6e3e744",
            "config" to "https://www.notion.so/Suggesting-Config-Changes-066d972235e94549896aee9ae357a166",
            "contributing" to "https://www.notion.so/mineinabyss/Mine-In-Abyss-5d7d8bc07fd04007a459c27ea2019c3f",
            "discord" to "https://discord.com/invite/jDd7x8V",
            "guildmaster" to "https://map.mineinabyss.com/#world;orthNW;-91,64,-539;6",
            "issues" to "https://github.com/MineInAbyss/Issue-Tracker/issues/new?assignees=&labels=type%3Abug&template=bug_report.yml",
            "launchy" to "https://github.com/MineInAbyss/launchy/releases/latest/",
            "map" to "https://map.mineinabyss.com/#world;orthSE;-9,64,-91;3",
            "mobdrops" to "https://mineinabyss.notion.site/Mob-Drops-23dfcacc6a4d453896d584e7ced9ba41",
            "mobspawns" to "https://github.com/MineInAbyss/server-config/tree/master/servers/minecraft/plugins/Geary/mineinabyss/mobs/spawns",
            "orespawns" to "https://mineinabyss.notion.site/Ore-Spawn-Drop-Rates-96c6000470b2491686b974cc59e44f38",
            "resourcepack" to "https://github.com/MineInAbyss/MineInAbyss-resourcepack/releases/latest",
            "rules" to "https://www.notion.so/mineinabyss/Server-Rules-50481c93c3234665a2fbdeee0835fc18",
            "suggestions" to "https://github.com/MineInAbyss/Issue-Tracker/issues/new/choose",
            "translated_resources" to "https://mineinabyss.notion.site/Translated-Resources-8f3393a404094d29b3f377f368e7bd2f",
            "villagertrades" to "https://mineinabyss.notion.site/15cb6f2412904e398bc57d7162a59bcc?v=42a06df67ac6416486e49d198b288d7d",
        )

        redirectUrls.forEach { (from, to) ->
            generate(from, meta = CommonFrontMatter()) {
                redirect(to)
            }
        }
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
                .output(WebpWriter.DEFAULT.withoutAlpha().withMultiThread(), outputPath)
        }
    println("Done generating images!")
}
