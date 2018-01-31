(function (_, Kotlin, $module$rxjs, $module$pixi_js) {
  'use strict';
  var Rx$Observable$Companion = $module$rxjs.Observable;
  var Unit = Kotlin.kotlin.Unit;
  var throwUPAE = Kotlin.throwUPAE;
  var PIXI$Sprite = $module$pixi_js.Sprite;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var PIXI$Application = $module$pixi_js.Application;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var numberToInt = Kotlin.numberToInt;
  App.prototype = Object.create(PIXI$Application.prototype);
  App.prototype.constructor = App;
  App$Companion.prototype = Object.create(App.prototype);
  App$Companion.prototype.constructor = App$Companion;
  function App(options) {
    App$Companion_getInstance();
    if (options === void 0)
      options = null;
    PIXI$Application.call(this, options);
  }
  function App$timer$lambda$lambda(closure$e, closure$count, closure$duration) {
    return function (delta) {
      closure$e.next(delta);
      closure$count.v += delta;
      if (closure$count.v >= closure$duration) {
        closure$e.complete();
      }
      return Unit;
    };
  }
  function App$timer$lambda(closure$count, closure$duration, closure$action, this$App) {
    return function (e) {
      closure$action.v = App$timer$lambda$lambda(e, closure$count, closure$duration);
      this$App.ticker.add(closure$action.v == null ? throwUPAE('action') : closure$action.v);
      return Unit;
    };
  }
  function App$timer$lambda_0(this$App, closure$action) {
    return function () {
      this$App.ticker.remove(closure$action.v == null ? throwUPAE('action') : closure$action.v);
      return Unit;
    };
  }
  App.prototype.timer_14dthe$ = function (duration) {
    var action = {v: null};
    var count = {v: 0.0};
    return Rx$Observable$Companion.create(App$timer$lambda(count, duration, action, this)).finally(App$timer$lambda_0(this, action));
  };
  function App$Companion() {
    App$Companion_instance = this;
    App.call(this);
    this.resize();
  }
  App$Companion.prototype.resize = function () {
    this.renderer.resize(window.outerWidth, window.outerHeight);
  };
  function App$Companion$bootstrap$lambda$lambda(closure$cat) {
    return function (it) {
      closure$cat.alpha = closure$cat.alpha - 0.005;
      return Unit;
    };
  }
  function App$Companion$bootstrap$lambda(this$App$) {
    return function () {
      var catTexture = this$App$.loader.resources.cat.texture;
      var cat = new PIXI$Sprite(catTexture);
      cat.position.set(300, 300);
      this$App$.stage.addChild(cat);
      moveTo(cat, 0, 0, 100.0).subscribe(App$Companion$bootstrap$lambda$lambda(cat));
      return Unit;
    };
  }
  App$Companion.prototype.bootstrap = function () {
    this.loader.add('cat', '/src/res/cat.png').load(App$Companion$bootstrap$lambda(this));
  };
  App$Companion.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Companion',
    interfaces: [App]
  };
  var App$Companion_instance = null;
  function App$Companion_getInstance() {
    if (App$Companion_instance === null) {
      new App$Companion();
    }
    return App$Companion_instance;
  }
  App.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'App',
    interfaces: []
  };
  function moveTo$lambda(this$moveTo, closure$speedX, closure$speedY) {
    return function (deltaTime) {
      this$moveTo.x = this$moveTo.x + numberToInt(closure$speedX * deltaTime) | 0;
      this$moveTo.y = this$moveTo.y + numberToInt(closure$speedY * deltaTime) | 0;
      return Unit;
    };
  }
  function moveTo$lambda_0(closure$targetX, this$moveTo, closure$targetY) {
    return function () {
      this$moveTo.x = closure$targetX;
      this$moveTo.y = closure$targetY;
      return Unit;
    };
  }
  function moveTo($receiver, targetX, targetY, duration) {
    var speedX = (targetX - $receiver.x | 0) / duration;
    var speedY = (targetY - $receiver.y | 0) / duration;
    return doOnComplete(App$Companion_getInstance().timer_14dthe$(duration).do(moveTo$lambda($receiver, speedX, speedY)), moveTo$lambda_0(targetX, $receiver, targetY));
  }
  function doOnError$lambda(it) {
    return Unit;
  }
  function doOnError$lambda_0() {
    return Unit;
  }
  function doOnError($receiver, onError) {
    return $receiver.do(doOnError$lambda, onError, doOnError$lambda_0);
  }
  function doOnComplete$lambda(it) {
    return Unit;
  }
  function doOnComplete$lambda_0(it) {
    return Unit;
  }
  function doOnComplete($receiver, onComplete) {
    return $receiver.do(doOnComplete$lambda, doOnComplete$lambda_0, onComplete);
  }
  function main$lambda(it) {
    App$Companion_getInstance().resize();
    return Unit;
  }
  function main(args) {
    var tmp$;
    (tmp$ = document.body) != null ? tmp$.appendChild(App$Companion_getInstance().view) : null;
    window.addEventListener('resize', main$lambda);
    App$Companion_getInstance().bootstrap();
  }
  Object.defineProperty(App, 'Companion', {
    get: App$Companion_getInstance
  });
  _.App = App;
  var package$common = _.common || (_.common = {});
  package$common.moveTo_uenv3n$ = moveTo;
  package$common.doOnError_ckewwr$ = doOnError;
  package$common.doOnComplete_niakts$ = doOnComplete;
  _.main_kand9s$ = main;
  main([]);
  Kotlin.defineModule('kotlinApp', _);
  return _;
}(module.exports, require('kotlin'), require('rxjs'), require('pixi.js')));

//# sourceMappingURL=kotlinApp.js.map
