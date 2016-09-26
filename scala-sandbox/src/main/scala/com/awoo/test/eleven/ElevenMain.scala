package com.awoo.test.eleven

/**
  * Created by yuxiaolu on 9/25/16.
  */
object ElevenMain {
  def main(args: Array[String]) {
    //11.3
//    val fraction1 = Fraction(3, 5)
//    val fraction2 = Fraction(4, 5)
//    println("+: " + (fraction1 + fraction2))
//    println("-: " + (fraction1 - fraction2))
//    println("*: " + (fraction1 * fraction2))
//    println("/: " + (fraction1 / fraction2))

    //11.4
//    val money1 = Money(2, 50)
//    val money2 = Money(1, 60)
//    println("+: " + (money1 + money2))
//    println("-: " + (money1 - money2))
//    println("-: " + (money2 - money1))
//    println("==: " + (money1 == money1))
//    println("==: " + (money1 == money2))

    //11.9
    val fullName = new RichFile("/home/awoo", "test", ".txt")
    val RichFile(dir, file, ext) = fullName
    println(dir)
    println(file)
    println(ext)
  }
}
