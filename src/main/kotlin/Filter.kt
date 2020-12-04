import org.w3c.dom.Element
import org.w3c.dom.get

class Filter(private val element: Element) {

    fun filter(): Boolean = filterTitle() || filterLocation() || filterCompany()

    private val titleContainsFilters = listOf(
        "android", "(intern)", "mobile", "ios"
    )

    private fun filterTitle(): Boolean {
        val title = element.getElementsByClassName("job-card-list__title")[0]?.textContent ?: ""
        return titleContainsFilters.any { title.contains(it, ignoreCase = true) }
    }

    private val locationEndingFilters = listOf(
        "IE", "GB", "United Kingdom", "Ireland", "RU", "Poland", "PL", "Türkiye", "UY", "IN", "India", "AR", "FI", "LT", "TN",
        "Finland", "SE", "HU", "PH", "TR", "ZA", "GR", "Greece", "RO", "UA", "MX", "CO", "MY", "Uruguay", "Norway",
        "Brasil", "BR", "Denmark", "Sweden", "DK", "Romania", "CZ","SK","México", "NO"
    )

    private fun filterLocation(): Boolean {
        val location = element
            .getElementsByClassName("job-card-container__metadata-wrapper")[0]
            ?.textContent?.substringBefore(" Remote")?.trim()
            ?: ""
        return locationEndingFilters.any { location.endsWith(it) }
    }

    private val companyFilters = listOf(
        "Amazon", "DevFactory", "Crossover for Work"
    )

    private fun filterCompany(): Boolean {
        val company = element.getElementsByClassName("job-card-container__company-name")[0]
            ?.textContent?.trim()
            ?: ""
        return companyFilters.any { it == company }
    }
}
