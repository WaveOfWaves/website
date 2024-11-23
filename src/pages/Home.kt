package pages

import components.*
import kotlinx.html.*
import me.dvyy.shocky.markdown
import me.dvyy.shocky.page.Page
import org.intellij.lang.annotations.Language

fun Page.homePage() = html {
    head {
        link(rel = "stylesheet", href = "/assets/tailwind/styles.css")
    }
    body("bg-stone-800 text-white") {
        fullScreenImage("/assets/mia_render.jpg") {
            div("flex items-center justify-center h-full relative") {
                img(
                    src = "/assets/logo.png",
                    classes = "mx-auto md:w-1/2 w-3/4 transform transition-transform duration-300 hover:scale-105"
                )
                div("absolute bottom-0 left-0 right-0 h-32 bg-gradient-to-t from-stone-800 to-transparent") {}
            }
        }

        div("relative") {
            div("absolute bottom-0 transform -translate-y-4 w-full") {
                navigation()
            }
        }
        main("prose prose-invert prose-stone prose-lg md:mx-auto max-w-screen-lg mt-12") {
            (this as HTMLTag).markdown(
                """
                # About us

                Mine in Abyss is a community-run recreation of the Made in Abyss anime in Minecraft. We host a survival server where players can build in our map of the abyss, as well as a creative server where our talented team of builders to create new structures from the anime.
            """.trimIndent()
            )

            div("flex flex-row space-x-4") {
                coloredButton("Visit the wiki", primary, "https://wiki.mineinabyss.com", icon = { icons.infoCircle })
                div("group") {
                    coloredButton("Learn how to contribute", secondary, "https://mineinabyss.com/contributing",
                        icon = { div("group-hover:animate-heartBeat") { icons.heart } }
                    )
                }
            }

            (this as HTMLTag).markdown(
                """
                ## Our servers

                We host multiple servers for different things.

                - **Survival**: `survive.mineinabyss.com`, play and build with other members of the community
                - **Build**: `build.mineinabyss.com`, explore the map with flight enabled. Build in the creative world, then apply for builder on the Discord server.
                - **Archive**: `archive.mineinabyss.com`, an archive of past reset maps, containing old survival builds and towns. 

                # Picture gallery

                <img src="assets/images/survival-min.png" alt=""></img>
                <img src="assets/images/orth-min.png" alt=""></img>

                <div class="grid grid-cols-2 md:grid-cols-2 gap-4">
                </div>
            """.trimIndent()
            )
        }
    }
}


fun test(@Language("markdown") code: String) {
    println(code)
}
