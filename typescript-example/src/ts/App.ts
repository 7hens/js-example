import { Application, ApplicationOptions, loader, Sprite, BaseTexture, Point } from 'pixi.js';
import { Observable } from "rxjs";
import { Subscriber } from 'rxjs/Subscriber';
import Widget from './base/Widget';

export default class App extends Application {
    constructor(options: ApplicationOptions) {
        super(options);
    }

    resize() {
        this.renderer.resize(window.outerWidth, window.outerHeight);
    }

    bootstrap() {
        loader.add('cat', '/src/res/cat.png')
            .load(() => {
                let catTexture = loader.resources.cat.texture;
                let cat = new Widget(catTexture);
                cat.position.set(300, 200);
                this.stage.addChild(cat);
                cat.moveTo(0, 0, 100).subscribe(it => {
                    // cat.rotation += 0.01;
                    cat.alpha -= 0.005;
                });
            })
    }

    testRx(): Observable<number> {
        return Observable.create((e: Subscriber<number>) => {
            e.next(1);
            e.next(2);
            e.next(100);
            e.complete();
        });
    }

    interval(): Observable<number> {
        let action: (deltaTime: number) => void;
        return Observable
            .create((e: Subscriber<number>) => {
                action = it => e.next(it);
                this.ticker.add(action);
            })
            .finally(() => {
                this.ticker.remove(action);
            });
    }

    timer(duration: number): Observable<number> {
        let action: (deltaTime: number) => void;
        let count = 0;
        return Observable
            .create((e: Subscriber<number>) => {
                action = delta => {
                    e.next(delta);
                    count += delta;
                    if (count >= duration) {
                        e.complete();
                    }
                };
                this.ticker.add(action);
            })
            .finally(() => {
                this.ticker.remove(action);
            });
    }

    static instance: App = new App({
        width: window.outerWidth,
        height: window.outerHeight,
        antialias: true,
        transparent: false
    })
}
