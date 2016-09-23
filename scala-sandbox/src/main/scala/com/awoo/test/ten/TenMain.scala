package com.awoo.test.ten

import java.awt.geom.Ellipse2D.Double

/**
  * Created by yuxiaolu on 9/23/16.
  */
object TenMain {
  def main(args: Array[String]) {
    //10.1
//    val egg = new Double(5, 10, 20, 30) with RectangleLike
//    egg.translate(10, 20)

    //10.2
    val point1 = new OrderedPoint(4, 5)
    val point2 = new OrderedPoint(5, 6)
    println(point1.compare(point2))
  }
}
