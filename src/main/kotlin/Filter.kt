import org.w3c.dom.Element
import org.w3c.dom.get

class Filter(private val element: Element) {

    fun filter(): Boolean = filterTitle() || filterLocation() || filterCompany()

    private val titleContainsFilters = setOf(
        "android", "(intern)", "mobile", "ios", "principal", "c++"
    )

    private fun filterTitle(): Boolean {
        val title = element.getElementsByClassName("job-card-list__title")[0]?.textContent ?: ""
        return titleContainsFilters.any { title.contains(it, ignoreCase = true) }
    }

    private val locationEndingFilters = setOf(
        "IE", "GB", "United Kingdom", "Ireland", "RU", "Poland", "PL", "Türkiye", "UY", "IN", "India", "AR", "FI", "LT", "TN",
        "Finland", "SE", "HU", "PH", "TR", "ZA", "GR", "Greece", "RO", "UA", "MX", "CO", "MY", "Uruguay", "Norway",
        "Brasil", "BR", "Denmark", "Sweden", "DK", "Romania", "CZ","SK","México", "NO", "Spain", "ES", "VN",
        "Italy", "PT", "FR", "São Paulo e Região", "Croatia", "Bulgaria", "Belarus", "Italia", "Tel Aviv, IL", "KH",
        "United Arab Emirates", "Serbia", "IT", "ID", "Costa Rica", "Turkey", "South Africa", "Israel", "Lithuania",
        "Slovakia", "Czechia", "Ukraine", "Colombia", "Kenya", "Estonia"
    )

    private fun filterLocation(): Boolean {
        val location = element
            .getElementsByClassName("job-card-container__metadata-wrapper")[0]
            ?.textContent?.substringBefore(" Remote")?.trim()
            ?: ""
        return locationEndingFilters.any(location::endsWith)
    }

    private val companyFilters = setOf(
        "Amazon", "DevFactory", "Crossover for Work", "Pinterest", "Snap Inc.", "Disney Streaming Services"
    )

    private fun filterCompany(): Boolean {
        val company = element.getElementsByClassName("job-card-container__company-name")[0]
            ?.textContent?.trim()
            ?.removeSuffix("Remote")
            ?.trim()
            ?: ""
        return companyFilters.any { it == company }
    }
}
