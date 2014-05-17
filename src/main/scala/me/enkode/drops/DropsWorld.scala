package me.enkode.drops

import me.enkode.jsphysics._
import scala.scalajs.js
import js.annotation.JSExport
import js.Dynamic.global


@JSExport
class DropsWorld(canvasId: String) extends World {

  override def frameRate: Int = 50

  override val canvas: Canvas = new HtmlCanvas(canvasId)

  val fillBlack = FillStyle("black")
  val strokeBlack = StrokeStyle("black", 1)
  case class FallingCircle(
    s: Vector,
    v: Vector = (0, 0),
    a: Vector = (0, 5),
    r: Double = 4,
    t: Long = System.currentTimeMillis())
    extends Sprite {
    override def visible(width: Double, height: Double): Boolean = s.x < width && s.y < height
    override def draw(): Drawable = Drawable.Circle(s, r, fillBlack, strokeBlack)

    override def update(): Sprite = {
      val now = System.currentTimeMillis().toDouble
      val (s0, v0, a0, t0) = (this.s, this.v, this.a, this.t)
      val Δt = (now - t0) / 1000
      val Δs = (a0 * (Δt * Δt)) / 2
      val v = (a0 * Δt) + v0
      val s =  s0 + v - Δs
      copy(
        s = s,
        v = v,
        t = now.toLong
      )
    }
  }

  val simpleScene = new Scene(
    sprites = Seq.empty[Sprite]
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

  @JSExport
  def poke() {
    import scala.util.Random
    val randomX = Random.nextDouble * canvas.width
    scenes = Seq(scenes.head.copy(
      sprites = scenes.head.sprites :+ FallingCircle((randomX, 10.0))
    ))
  }
}
