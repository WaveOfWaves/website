package pages

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.decodeFromStream
import components.Profile
import components.contributorCard
import kotlinx.html.div
import kotlinx.html.h2
import me.dvyy.shocky.markdown
import me.dvyy.shocky.page.Page
import templates.default
import kotlin.io.path.Path
import kotlin.io.path.inputStream

fun Page.contributors() = default {
    markdown("""
        This is a WIP page we hope will showcase the cool people contributing to this project soon!        
    """.trimIndent())
    val contributors =
        Yaml.default.decodeFromStream<Map<String, List<Profile>>>(Path("site/contributors.yml").inputStream())
    contributors.forEach { (team, profiles) ->
        h2 { +team }
        div("grid grid-cols-1 md:grid-cols-3 gap-4") {
            for (profile in profiles) contributorCard(profile)
        }
    }
}
