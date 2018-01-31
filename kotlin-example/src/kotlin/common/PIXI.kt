package common

import modules.PIXI
import modules.Rx

fun PIXI.DisplayObject.moveTo(targetX: Int, targetY: Int, duration: Double): Rx.Observable<Double> {
    val speedX = (targetX - x) / duration
    val speedY = (targetY - y) / duration
    return App.timer(duration)
            .doOnNext { deltaTime: Double ->
                this.x += speedX.times(deltaTime).toInt()
                this.y += speedY.times(deltaTime).toInt()
            }
            .doOnComplete {
                this.x = targetX
                this.y = targetY
            }
}