package com.awoo.test.ten

/**
  * Created by yuxiaolu on 9/23/16.
  */
trait RectangleLike {
  def translate(x:Double, y:Double): Unit = {
    println("translate: x=" + x + ", y=" + y)
  }

  def grow(x:Double, y:Double): Unit = {
    println("grow: x=" + x + ", y=" + y)
  }
}
