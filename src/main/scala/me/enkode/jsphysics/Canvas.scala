package me.enkode.jsphysics

trait Canvas {
  def clear()

  def render: PartialFunction[Drawable, Unit]
}


