package com.awoo.test.six

import Deck._

/**
  * Created by yuxiaolu on 9/21/16.
  */
object SixMain {
  def main(args: Array[String]) {
    //6.1
    //    println(Conversions.inchesToCentimeters(1))
    //    println(Conversions.milesToKilometers(1))

    //6.2
    //    println(InchesToCentimeters.convert(1))

    //6.4
    //    val point = Point(3, 4)
    //    println(point.desc())

    //6.6
    Deck.values.foreach(println(_))
    println(Spade)
    println(Deck.isRedColor(Club))
  }
}
