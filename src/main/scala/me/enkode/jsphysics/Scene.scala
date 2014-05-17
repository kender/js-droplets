package me.enkode.jsphysics

case class Scene(sprites: Seq[Sprite]) {
  def update(): Scene = {
    copy(
      sprites = sprites map { _.update() }
    )
  }
}
