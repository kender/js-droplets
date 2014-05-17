package me.enkode.jsphysics

trait Sprite extends Physical[Sprite] {
  def draw(): Drawable
}
