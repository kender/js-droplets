package me.enkode.jsphysics

case class Vector(x: Double, y: Double) {
  import scala.math._

  def + (that: Vector) = Vector(this.x + that.x, this.y + that.y)
  def plus(that: Vector) = this + that

  lazy val r = sqrt(x*x + y*y)
  lazy val θ = ???
}
