import com.soywiz.klock.*
import com.soywiz.korau.sound.readSound
import com.soywiz.korge.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.*


suspend fun main() = Korge(width = 512, height = 512, bgcolor = Colors["#2b2b2b"]) {

    val boingyBoi = resourcesVfs["boing.wav"].readSound()

    val rectangle = solidRect(this.width, this.height, color = Colors.CORNFLOWERBLUE)
    addChild(rectangle)

    val rightPaddle = solidRect(15, 60, color = Colors.BURLYWOOD)
    val leftPaddle = solidRect(15, 60, color = Colors.BURLYWOOD)
    addChild(rightPaddle)
    addChild(leftPaddle)

    val circle = Circle(32.0, Colors.BROWN).xy(10f, 10f)
    addChild(circle)

    val velocity = Point(4.0, 4.0)

    circle.x = 16.0

    addFixedUpdater(30.timesPerSecond) {
        circle.xy(circle.x + velocity.x, circle.y + velocity.y)
        if (circle.x > this.width - 2 * circle.radius - rightPaddle.width) {
            velocity.x = velocity.x * -1
            boingyBoi.play()
        } else if (circle.x < leftPaddle.width) {
            velocity.x = velocity.x * -1
            boingyBoi.play()
        }
        if (circle.y > this.height - 2 * circle.radius) {
            velocity.y = velocity.y * -1
            boingyBoi.play()
        } else if (circle.y < 0) {
            velocity.y = velocity.y * -1
            boingyBoi.play()
        }
        leftPaddle.xy(0.0, circle.y)
        rightPaddle.xy(this.width - rightPaddle.width, circle.y)
    }
}