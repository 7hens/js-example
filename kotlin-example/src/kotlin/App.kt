import common.moveTo
import modules.PIXI
import modules.Rx
import kotlin.browser.window

open class App(options: dynamic = null) : PIXI.Application(options) {

    fun timer(duration: Double): Rx.Observable<Double> {
        lateinit var action: (Double) -> Unit
        var count = 0.0
        return Rx.Observable
                .create<Double> { e ->
                    action = { delta ->
                        e.next(delta)
                        count += delta
                        if (count >= duration) {
                            e.complete()
                        }
                    }
                    ticker.add(action)
                }
                .finally {
                    ticker.remove(action)
                }
    }

    companion object : App() {
        init {
            resize()
        }

        fun resize() {
            renderer.resize(window.outerWidth, window.outerHeight)
        }

        fun bootstrap() {
            loader.add("cat", "/src/res/cat.png")
                    .load {
                        val catTexture = loader.resources.cat.texture
                        val cat = PIXI.Sprite(catTexture)
                        cat.position.set(300, 300)
                        stage.addChild(cat)
                        cat.moveTo(0, 0, 100.0)
                                .subscribe {
                                    cat.alpha -= 0.005
                                }
                    }
        }
    }
}
