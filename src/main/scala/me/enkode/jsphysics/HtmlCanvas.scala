package me.enkode.jsphysics

class HtmlCanvas(id: String) extends Canvas {
  import scala.scalajs.js
  import js.Dynamic._

  def doubleOf(d: ⇒ Dynamic): Double = d match {
    case n: js.Number ⇒ n.toDouble
  }

  val canvas = global.document.getElementById(id)
  val context = canvas.getContext("2d")
  val height = canvas.height.asInstanceOf[js.Number].toDouble
  val width = canvas.width.asInstanceOf[js.Number].toDouble

  println(s"$width, $height")
  override def clear(): Unit = {
    context.clearRect(0, 0, width, height)
  }

  override def render: PartialFunction[Drawable, Unit] = {
    case Drawable.Circle(c, r, fill, stroke) ⇒
      context.beginPath()
      context.arc(c.x, c.y, r, 0, 2 * global.Math.PI)
      context.fillStyle = fill.color
      context.fill()
      context.lineWidth = stroke.size
      context.strokeStyle = stroke.color
      context.stroke()
  }
}
