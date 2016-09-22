package com.awoo.test.eight

/**
  * Created by yuxiaolu on 9/22/16.
  */
class LabeledPoint(label: String, x: Double, y: Double) extends Point(x, y) {
  override def toString: String = {
    "label=" + label + ", x=" + x + ", y=" + y
  }
}
