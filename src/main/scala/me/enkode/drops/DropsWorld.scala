package me.enkode.drops

import me.enkode.jsphysics._
import scala.scalajs.js
import js.annotation.JSExport
import js.Dynamic.global

object DropsWorld {
  val fillBlack = Drawable.FillStyle("black")
  val strokeBlack = Drawable.StrokeStyle("black", 2)

  val gravity = 1.5
  val minR = 1.0
  val maxR = 3.5

  case class FallingCircle(
    s: Vector,
    v: Vector = (0, 0),
    a: Vector = (0d, gravity),
    r: Double = 2,
    t: Long = System.currentTimeMillis())
    extends Sprite {
    override def visible(width: Double, height: Double): Boolean = s.x < width && s.y < height
    override def draw(): Drawable = {
      val head = Drawable.Circle(s, r, fillBlack, strokeBlack)
      val tail = {
        val size = v * 2.9
        Drawable.Line(s, s - Vector(size.x, size.y), strokeBlack.copy(size = r))
      }
      Drawable.CompoundDrawable(Seq(head, tail))
    }

    override def update(): Sprite = {
      val now = System.currentTimeMillis().toDouble
      val vScale = .975 + (r/maxR) * .025
      val (s0, v0, a0, t0) = (this.s, this.v, this.a, this.t)
      val Δt = (now - t0) / 1000
      val Δs = (a0 * (Δt * Δt)) / 2
      val v = ((a0 * Δt) + v0) * vScale
      val s =  s0 + v - Δs
      copy(
        s = s,
        v = v,
        t = now.toLong
      )
    }
  }
}

@JSExport
class DropsWorld(canvasId: String) extends World {
  import DropsWorld._

  override def frameRate: Int = 100

  val drops = new Scene(sprites = Seq.empty[Sprite])

  override var scenes = Seq(drops)
  override val canvas: Canvas = new HtmlCanvas(canvasId)

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
    val randomR = minR + Random.nextDouble() * (maxR - minR)
    scenes = Seq(scenes.head.copy(
      sprites = scenes.head.sprites :+ FallingCircle(s = (randomX, 10.0), r = randomR)
    ))
  }
}
