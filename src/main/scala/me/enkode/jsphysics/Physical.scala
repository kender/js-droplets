package me.enkode.jsphysics

trait Physical[T] { self: T ⇒
  val t: Long
  val a: Vector
  val v: Vector
  val s: Vector

  def update(): T
}
