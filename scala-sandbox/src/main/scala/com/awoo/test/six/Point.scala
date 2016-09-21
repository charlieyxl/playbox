package com.awoo.test.six

/**
  * Created by yuxiaolu on 9/21/16.
  */
class Point (x: Double, y: Double) {
  def desc():String = {
    "x=" + x + ", y=" + y
  }
}

object Point {
  def apply(x: Double, y: Double) = new Point(x, y)
}
