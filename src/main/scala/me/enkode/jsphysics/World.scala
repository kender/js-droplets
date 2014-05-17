package me.enkode.jsphysics

trait World {
  val canvas: Canvas
  var scenes: Seq[Scene]
  def queueNextIn(ms: Long)
  def frameRate: Int

  def run() {
    def clear() {
      canvas.clear()
    }

    def update() {
      scenes = scenes map { _.update() }
    }

    def render() {
      for {
        scene    ← scenes
        sprite   ← scene.sprites
        drawable = sprite.draw()
      } yield {
        canvas render drawable
      }
    }

    def wait() {
      queueNextIn( (1 / (frameRate.toDouble / 1000)).toLong )
    }

    clear()
    update()
    render()
    wait()
  }
}
