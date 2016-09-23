package com.awoo.test.ten

/**
  * Created by yuxiaolu on 9/23/16.
  */
class OrderedPoint(x:Int, y:Int) extends java.awt.Point(x, y) with Ordered[OrderedPoint] {
  override def compare(that: OrderedPoint): Int = {
    if (x >= that.x && y > that.y || x > that.x && y >= that.y)
      1
    else if (x == that.x && y == that.y)
      0
    else
      -1
  }
}
