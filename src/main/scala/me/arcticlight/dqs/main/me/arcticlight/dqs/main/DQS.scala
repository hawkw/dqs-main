package me.arcticlight.dqs.main.me.arcticlight.dqs.main

import me.arcticlight.animations.ScalaTween._
import me.arcticlight.dqs.main.me.arcticlight.dqs.animatables.Animatables._
import processing.core._


object DQS {
  def main(args: Array[String]) = {
    PApplet.main(Array("me.arcticlight.dqs.main.me.arcticlight.dqs.main.DQS"))
  }
}

class DQS extends PApplet {
  val rect = new AHRect(AnimationTarget(new PVector(0,0)), AnimationTarget(0), AnimationTarget(20), AnimationTarget(Color(0,0,0,0)))
  var anim: ParTimeline = null

  val EaseOutQuint: Float => Float = {
    x => {
      val t = x - 1
      t*t*t*t*t + 1
    }
  }

  /** t = x
    * b = 0
    * c = 1
    * d = 1
    *
    * x -= 1
    *
  Math.easeOutQuint = function (t, b, c, d) {
	t /= d;
	t--;
	return c*(t*t*t*t*t + 1) + b;
};
    */


  override def settings(): Unit = {
    fullScreen()
    pixelDensity(displayDensity())
  }

  override def setup(): Unit = {
    background(0)
    anim = ParTimeline(
      Tween(rect.fill, Color(255,255,255,255), Color(255,255,255,255), 1f),
      Tween(rect.width, 0f, 40f, 0.33f).ease(EaseOutQuint),
      SeqTimeline(
        Tween(rect.height, 20f, 40f).ease(EaseOutQuint),
        Tween(rect.width, 40f, width.toFloat).ease(EaseOutQuint)
      )()
    )()
  }

  override def draw(): Unit = {
    background(0)
    anim.seekTo(mouseX / width.toFloat)
    System.out.println(rect.width)

    rect.draw(this)
  }
}
