import me.dvyy.shocky.Shocky
import me.dvyy.shocky.page.CommonFrontMatter
import me.dvyy.shocky.page.Page
import me.dvyy.shocky.siteRouting
import pages.blogIndex
import pages.gallery
import pages.homePage
import templates.blogPost
import templates.default
import kotlin.io.path.Path

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
).run(args)
