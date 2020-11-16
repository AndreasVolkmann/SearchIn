import kotlinx.browser.document
import kotlinx.browser.window
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
        val filter = Filter(jobElement)

        if (filter.filter()) {
            println("Removing ${jobElement.textContent}")
            jobElement.remove()
        }

        i++
    }
}
