package me.arcticlight.dqs.main.me.arcticlight.dqs.animatables

import processing.core._
import me.arcticlight.animations.ScalaTween._

/**
  * Created by Max on 12/8/2015.
  */
object Animatables {
  private def clamp255(x: Int): Int = x match {
    case _ if x < 0 => 0
    case _ if x > 255 => 255
    case _ => x
  }
  private def clamp255(x: Float): Float = x match {
    case _ if x < 0 => 0
    case _ if x > 255 => 255
    case _ => x
  }

  implicit object PVectorIsTweenOps extends TweenOps[PVector] {
    override def mult(a: PVector, b: Float): PVector = a.mult(b)

    override def sub(a: PVector, b: PVector): PVector = a.sub(b)

    override def add(a: PVector, b: PVector): PVector = a.add(b)
  }

  case class Color(r: Int, g: Int, b: Int, a: Int = 255)

  implicit object ColorIsTweenOps extends TweenOps[Color] {
    override def mult(a: Color, b: Float): Color = Color(clamp255(a.r*b).toInt,
                                                         clamp255(a.g*b).toInt,
                                                         clamp255(a.b*b).toInt,
                                                         clamp255(a.a*b).toInt)

    override def sub(a: Color, b: Color): Color = Color(clamp255(a.r-b.r),
                                                        clamp255(a.g-b.g),
                                                        clamp255(a.b-b.b),
                                                        clamp255(a.a-b.a))

    override def add(a: Color, b: Color): Color = Color(clamp255(a.r+b.r),
                                                        clamp255(a.g+b.g),
                                                        clamp255(a.b+b.b),
                                                        clamp255(a.a+b.a))
  }

  class AHRect(val pos: AnimationTarget[PVector],
               val width: AnimationTarget[Float],
               val height: AnimationTarget[Float],
               val color: AnimationTarget[Color] = AnimationTarget(Color(0,255,0,255)),
               val fill: AnimationTarget[Color] = AnimationTarget(Color(255,0,255,255))) {
    def draw(context: PApplet): Unit = {
      context.stroke(color.target.r.toFloat, color.target.g.toFloat, color.target.b.toFloat, color.target.a.toFloat)
      context.fill(fill.target.r.toFloat, fill.target.g.toFloat, fill.target.b.toFloat, fill.target.a.toFloat)
      context.rectMode(PConstants.CORNER)
      context.rect(pos.target.x, pos.target.y, width.target, height.target)
    }
  }
}
