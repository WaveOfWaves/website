import me.dvyy.shocky.Shocky
import me.dvyy.shocky.page.CommonFrontMatter
import me.dvyy.shocky.page.Page
import me.dvyy.shocky.siteRouting
import pages.homePage
import kotlin.io.path.Path

suspend fun main(args: Array<String>) = Shocky(
    dest = Path("out"),
    route = siteRouting(path = Path("site")) {
        page("index.md", Page::homePage)
    },
    assets = listOf(Path("site/assets")),
    // If enabled, will auto download and run tailwind standalone binary
    useTailwind = true,
    watch = listOf(Path("site")),
).run(args)
