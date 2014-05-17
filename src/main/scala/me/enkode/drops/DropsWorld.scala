package me.enkode.drops

import me.enkode.jsphysics._
import scala.scalajs.js
import js.annotation.JSExport
import js.Dynamic.global


@JSExport
class DropsWorld(canvasId: String) extends World {

  override def frameRate: Int = 2

  override val canvas: Canvas = new HtmlCanvas(canvasId)

  val fillBlack = FillStyle("black")
  val strokeBlack = StrokeStyle("black", 1)
  case class StaticCircle(c: Vector, r: Double) extends Sprite {
    override def draw() = Drawable.Circle(c, r, fillBlack, strokeBlack)
    override def update() = this

    override val t = 0l
    override val s = c
    override val v = (0, 0).toVector
    override val a = (0, 0).toVector
  }

  val simpleScene = new Scene(
    sprites = Seq(
      StaticCircle((100,100), 16)
    )
  )

  override var scenes = Seq(simpleScene)

  var running = false
  override def queueNextIn(ms: Long): Unit = {
    if (running) global.window.setTimeout(this.run _, ms)
  }

  @JSExport
  def start() {
    running = true
    queueNextIn(0)
  }

  @JSExport
  def stop() {
    running = false
  }
}
