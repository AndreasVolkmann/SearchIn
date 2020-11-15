import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.Element
import org.w3c.dom.get

fun main() {
    console.log("Start SearchIn")
    listen()
}

private fun listen() {
    window.setTimeout({
        filter()
        listen()
    }, 1000)
}

private fun filter() {
    val cards = document.getElementsByClassName("job-card-container")

    var i = 0
    while (i < cards.length) {
        val jobElement = cards[i]!!

        if (filterTitle(jobElement) || filterLocation(jobElement)) {
            jobElement.remove()
        }

        i++
    }
}

private val titleContainsFilters = listOf(
        "android", "(intern)", "mobile"
)

private fun filterTitle(element: Element): Boolean {
    val title = element.getElementsByClassName("job-card-list__title")[0]?.textContent ?: ""
    return titleContainsFilters.any { title.contains(it, ignoreCase = true) }
}

private val locationEndingFilters = listOf(
        "IE", "GB", "United Kingdom", "Ireland", "RU", "Poland", "PL", "Türkiye", "UY", "IN", "AR", "FI", "LT", "TN"
)

private fun filterLocation(element: Element): Boolean {
    val location = element
            .getElementsByClassName("job-card-container__metadata-wrapper")[0]
            ?.textContent?.trim()
            ?: ""
    return locationEndingFilters.any { location.endsWith(it) }
}