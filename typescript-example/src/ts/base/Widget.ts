import { Point, Sprite, Texture } from "pixi.js";
import { Observable } from 'rxjs';
import App from "../App";

export default class Widget extends Sprite {
    constructor(texture: Texture) {
        super(texture)
    }

    distanceTo(position: Point): number {
        let deltaX = position.x - this.x;
        let deltaY = position.y - this.y;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    moveTo(targetX: number, targetY: number, duration: number): Observable<number> {
        let speedX = (targetX - this.x) / duration;
        let speedY = (targetY - this.y) / duration;

        let doOnNext = (deltaTime: number) => {
            this.x += speedX * deltaTime;
            this.y += speedY * deltaTime;
        };
        let doOnComplete = () => {
            this.x = targetX;
            this.y = targetY;
        };
        return App.instance.timer(duration)
            .do(doOnNext, () => { }, doOnComplete);
    }
}
