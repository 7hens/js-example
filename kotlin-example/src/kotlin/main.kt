import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {
    document.body ?.appendChild(App.view)
    window.addEventListener("resize", { App.resize() })
    App.bootstrap()
}
