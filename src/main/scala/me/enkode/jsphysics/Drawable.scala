package me.enkode.jsphysics

case class FillStyle(color: String)
case class StrokeStyle(color: String, size: Double)
trait Drawable {
  val fill: FillStyle
  val stroke: StrokeStyle
}

object Drawable {
  case class Circle(c: Vector, r: Double, fill: FillStyle, stroke: StrokeStyle) extends Drawable
}