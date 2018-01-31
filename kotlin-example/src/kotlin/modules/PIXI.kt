@file:Suppress("unused")

package modules

import org.khronos.webgl.Float32Array
import org.w3c.dom.*

@JsModule("pixi.js")
external object PIXI {
    val DATA_URI: String

    open class Application(options: dynamic = definedExternally) {
        var loader: loaders.Loader
        var renderer: SystemRenderer
        val screen: Rectangle
        var stage: Container
        var ticker: ticker.Ticker
        val view: HTMLCanvasElement
        fun destroy(removeView: Boolean)
        fun render()
        fun start()
        fun stop()
    }

    interface ApplicationOptions {
        val autoStart: Boolean? get() = definedExternally
        val width: Number? get() = definedExternally
        val height: Number? get() = definedExternally
        val view: HTMLCanvasElement? get() = definedExternally
        val transparent: Boolean? get() = definedExternally
        val antialias: Boolean? get() = definedExternally
        val preserveDrawingBuffer: Boolean? get() = definedExternally
        val resolution: Double? get() = definedExternally
        val forceCanvas: Boolean? get() = definedExternally
        val backgroundColor: Int? get() = definedExternally
        val clearBeforeRender: Boolean? get() = definedExternally
        val roundPixels: Boolean? get() = definedExternally
        val forceFXAA: Boolean? get() = definedExternally
        val legacy: Boolean? get() = definedExternally
        val powerPreference: String? get() = definedExternally
        val sharedTicker: Boolean? get() = definedExternally
        val sharedLoader: Boolean? get() = definedExternally
    }

    object ticker {
        open class Ticker {
            var autoStart: Boolean
            var deltaTime: Double
            var elapsedMS: Double
            val FPS: Double
            var lastTime: Double
            var minFPS: Double
            var speed: Double
            var started: Boolean
            fun add(fn: (Double) -> Unit, context: () -> Unit = definedExternally, priority: Int = definedExternally): Ticker
            fun addOnce(fn: (Double) -> Unit, context: () -> Unit = definedExternally, priority: Int = definedExternally): Ticker
            fun destroy()
            fun remove(fn: (Double) -> Unit, context: () -> Unit = definedExternally): Ticker
            fun start()
            fun stop()
            fun update(currentTime: Long)
        }
    }

    abstract class SystemRenderer {
        var autoSize: Boolean
        var backgroundColor: Int
        var blendModes: dynamic
        var clearBeforeRender: Boolean
        val height: Double
        val options: dynamic
        var preserveDrawingBuffer: Boolean
        var resolution: Double
        var roundPixels: Boolean
        var screen: Rectangle
        var transparent: Boolean
        var type: RENDERER_TYPE
        var view: HTMLCanvasElement
        val width: Double
        fun destroy(removeView: Boolean)
        //TODO
        fun generateTexture(displayObject: DisplayObject, scaleMode: Double, resolution: Double, region: Rectangle): dynamic

        fun resize(screenWidth: Number, screenHeight: Number)
    }

    open class CanvasRenderer(options: dynamic) : SystemRenderer {
        var context: CanvasRenderingContext2D
        //TODO
    }

    open class WebGLRenderer : SystemRenderer {
        //TODO
    }

    object loaders {
        open class Loader(baseUrl: String = definedExternally, concurrency: Double = definedExternally) {
            var baseUrl: String
            var concurrency: Double
            var progress: Double
            var loading: Boolean
            val resources: dynamic
            fun add(name: String, url: String = definedExternally, options: dynamic = definedExternally, cb: () -> Unit = definedExternally): Loader
            fun load(cb: () -> Unit): Loader
            fun reset(): Loader
        }
    }

    val loader: loaders.Loader

    open class BaseTexture(source: HTMLElement, scaleMode: Double, resolution: Double) {
        val hasLoaded: Boolean
        val height: Boolean
        val imageType: String
        var imageUrl: String
        val isLoading: Boolean
        var mipmap: Boolean
        val origSource: ImageBitmap
        var premultipliedAlpha: Boolean
        val realHeight: Double
        val realWidth: Double
        var resolution: Double
        var scaleMode: Double
        val source: HTMLElement
        val sourceScale: Double
        var textureCacheIds: Array<String>
        val width: Double
        var wrapMode: Double
    }

    open class Texture(baseTexture: BaseTexture, frame: Rectangle = definedExternally, orig: Rectangle = definedExternally, trim: Rectangle = definedExternally, rotate: Number = definedExternally) {
        var baseTexture: BaseTexture
        var frame: Rectangle
        var height: Double
        var noFrame: Boolean
        var orig: Rectangle
        var requiresUpdate: Boolean
        var rotate: Double
        var textureCacheIds: Array<String>
        var trim: Rectangle
        var valid: Boolean
        var width: Double

        companion object {
            val EMPTY: Texture
            val WHITE: Texture
            fun addToCache(texture: Texture, id: String)
            fun from(source: Int): Texture
            fun from(source: String): Texture
            fun from(source: HTMLImageElement): Texture
            fun from(source: HTMLCanvasElement): Texture
            fun from(source: HTMLVideoElement): Texture
            fun from(source: BaseTexture): Texture
            fun fromCanvas(canvas: HTMLCanvasElement, scaleMode: Number = definedExternally, origin: String = definedExternally): Texture
            fun fromFrame(frameId: String): Texture
        }
    }

    enum class BLEND_MODES { NORMAL, ADD, MULTIPLY, SCREEN, OVERLAY, DARKEN, LIGHTEN, COLOR_DODGE, COLOR_BURN, HARD_LIGHT, SOFT_LIGHT, DIFFERENCE, EXCLUSION, HUE, SATURATION, COLOR, LUMINOSITY }

    enum class DRAW_MODES { POINTS, LINES, LINE_LOOP, LINE_STRIP, TRIANGLES, TRIANGLE_STRIP, TRIANGLE_FAN }

    enum class GC_MODES { AUTO, MANUAL }

    enum class RENDERER_TYPE { UNKNOWN, WEBGL, CANVAS }

    interface IPoint {
        var x: Int
        var y: Int
        fun copy(p: IPoint)
        fun set(x: Int = definedExternally, y: Int = definedExternally)
    }

    open class Point(x: Double = definedExternally, y: Double = definedExternally) : IPoint {
        override var x: Int
        override var y: Int
        override fun copy(p: IPoint)
        override fun set(x: Int, y: Int)
        fun clone(): Point
        fun equals(p: IPoint): Boolean
    }

    open class ObservablePoint(cb: () -> Unit, scope: Any, x: Double = definedExternally, y: Double = definedExternally) : IPoint {
        override var x: Int
        override var y: Int
        override fun copy(p: IPoint)
        override fun set(x: Int, y: Int)
    }

    enum class SHAPES { POLY, RECT, CIRC, ELIP, RREC }

    open class Rectangle(x: Double = definedExternally, y: Double = definedExternally, width: Double = definedExternally, height: Double = definedExternally) {
        var x: Double
        var y: Double
        var width: Double
        var height: Double
        var left: Double
        var right: Double
        var top: Double
        var bottom: Double
        val type: SHAPES
        fun clone(): Rectangle
        fun contains(x: Double, y: Double): Boolean
        fun copy(rectangle: Rectangle): Rectangle
        fun enlarge(rectangle: Rectangle)
        fun fit(rectangle: Rectangle)
        fun pad(paddingX: Double, paddingY: Double)

        companion object {
            val EMPTY: Rectangle
        }
    }

    abstract class TransformBase {
        var localTransform: Matrix
        var worldTransform: Matrix
        abstract var pivot: IPoint
        abstract var position: IPoint
        abstract var rotation: Double
        abstract var scale: IPoint
        abstract var skew: IPoint
        abstract fun setFromMatrix(matrix: Matrix)
        fun updateTransform(parentTransform: TransformBase)
        fun updateLocalTransform()
        fun updateWorldTransform(parentTransform: TransformBase)

        companion object {
            val IDENTITY: TransformBase
        }
    }

    open class Transform : TransformBase {
        override var pivot: IPoint
        override var position: IPoint
        override var rotation: Double
        override var scale: IPoint
        override var skew: IPoint
        override fun setFromMatrix(matrix: Matrix)
    }

    open class TransformStatic : TransformBase {
        override var pivot: IPoint
        override var position: IPoint
        override var rotation: Double
        override var scale: IPoint
        override var skew: IPoint
        override fun setFromMatrix(matrix: Matrix)
    }

    open class Matrix(a: Double = definedExternally, b: Double = definedExternally, c: Double = definedExternally, d: Double = definedExternally, tx: Double = definedExternally, ty: Double = definedExternally) {
        var a: Double
        var b: Double
        var c: Double
        var d: Double
        var tx: Double
        var ty: Double
        fun append(matrix: Matrix): Matrix
        fun apply(pos: Point, newPos: Point = definedExternally): Point
        fun applyInverse(pos: Point, newPos: Point = definedExternally): Point
        fun clone(): Matrix
        fun copy(matrix: Matrix): Matrix
        fun decompose(transform: TransformBase): TransformBase
        fun fromArray(array: DoubleArray)
        fun identity(): Matrix
        fun invert(): Matrix
        fun prepend(matrix: Matrix): Matrix
        fun rotate(angle: Double): Matrix
        fun scale(x: Double, y: Double): Matrix
        fun set(a: Double, b: Double, c: Double, d: Double, tx: Double, ty: Double): Matrix
        fun setTransform(x: Double, y: Double, pivotX: Double, pivotY: Double, scaleX: Double, scaleY: Double, rotation: Double, skewX: Double, skewY: Double): Matrix
        fun toArray(transpose: Boolean, out: Float32Array): DoubleArray
        fun translate(x: Double, y: Double): Matrix
    }

    open class DisplayObject {
        var alpha: Double
        var buttonMode: Boolean
        var cacheAsBitmap: Boolean
        var cursor: String
        var filterArea: Rectangle
        var filters: dynamic
        var hitArea: dynamic
        var interactive: Boolean
        var localTransform: dynamic
        var mask: dynamic
        var name: String
        var parent: Container
        var pivot: IPoint
        var position: IPoint
        var renderable: Boolean
        var rotation: Double
        var scale: IPoint
        var skew: IPoint
        var transform: IPoint
        var visible: Boolean
        val worldAlpha: Double
        val worldTransform: Matrix
        val worldVisible: Boolean
        var x: Int
        var y: Int

        fun destroy()
        fun getBounds(skipUpdate: Boolean, rect: Rectangle): Rectangle
        fun getGlobalPosition(point: Point, skipUpdate: Boolean): Point
        fun getLocalBounds(rect: Rectangle): PIXI.Rectangle
        fun renderCanvas(renderer: CanvasRenderer)
        fun renderWebGL(renderer: WebGLRenderer)
        fun setParent(container: Container): Container
        fun setTransform(x: Double = definedExternally, y: Double = definedExternally,
                         scaleX: Double = definedExternally, scaleY: Double = definedExternally,
                         rotation: Double = definedExternally,
                         skewX: Double = definedExternally, skewY: Double = definedExternally,
                         pivotX: Double = definedExternally, pivotY: Double = definedExternally): DisplayObject

        fun toGlobal(position: Point, point: Point = definedExternally, skipUpdate: Boolean = definedExternally): Point
        fun toLocal(position: Point, from: DisplayObject = definedExternally, point: Point = definedExternally, skipUpdate: Boolean = definedExternally): Point
        fun updateTransform()
    }

    open class Container : DisplayObject {
        val children: Array<DisplayObject>
        var interactiveChildren: Boolean
        var height: Int
        var width: Int
        fun addChild(vararg child: DisplayObject): DisplayObject
        fun addChildAt(child: DisplayObject, index: Int): DisplayObject
        fun calculateBounds()
        fun destroy(forAll: Boolean)
        fun getChildAt(index: Int): DisplayObject
        fun getChildByName(name: String): DisplayObject
        fun getChildIndex(child: DisplayObject): Int
        fun removeChild(vararg child: DisplayObject): DisplayObject
        fun removeChildAt(index: Int): DisplayObject
        fun removeChildren(beginIndex: Int = definedExternally, endIndex: Int = definedExternally): Array<DisplayObject>
        fun setChildIndex(child: DisplayObject, index: Int)
        fun swapChildren(child: DisplayObject, child2: DisplayObject)
    }

    open class Sprite(texture: Texture) : Container {
        var anchor: ObservablePoint
        var blendMode: BLEND_MODES
        var pluginName: String
        var shader: dynamic
        var texture: Texture
        var tint: Int

        fun calculateTrimmedVertices()
        fun calculateVertices()
        fun containsPoint(point: Point): Boolean

        companion object {
            fun from(source: Int): Sprite
            fun from(source: String): Sprite
            fun from(source: BaseTexture): Sprite
            fun from(source: HTMLCanvasElement): Sprite
            fun from(source: HTMLVideoElement): Sprite
            fun fromFrame(frameId: String): Sprite
            fun fromImage(imageId: String, crossOrigin: Boolean, scaleMode: Int): Sprite
        }
    }
}