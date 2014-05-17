package me.enkode.jsphysics

sealed trait Drawable
object Drawable {
  case class FillStyle(color: String)
  case class StrokeStyle(color: String, size: Double)

  case class CompoundDrawable(drawables: Seq[Drawable]) extends Drawable

  case class Circle(c: Vector, r: Double, fill: FillStyle, stroke: StrokeStyle) extends Drawable

  case class Line(start: Vector, length: Vector, stroke: StrokeStyle) extends Drawable
}