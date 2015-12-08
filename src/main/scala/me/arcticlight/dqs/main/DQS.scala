package me.arcticlight.dqs.main

import me.arcticlight.animations.ScalaTween._
import processing.core._


object DQS {
  def main(args: Array[String]) = {
    PApplet.main(Array("me.arcticlight.dqs.main.DQS"))
  }
}

class DQS extends PApplet {
  override def settings(): Unit = {
    fullScreen()
  }

  override def setup(): Unit = {
    background(0)
  }

  override def draw(): Unit = {
    color(255)
    ellipse(40,40,40,40)
  }
}
