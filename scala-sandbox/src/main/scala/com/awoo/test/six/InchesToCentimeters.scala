package com.awoo.test.six

/**
  * Created by yuxiaolu on 9/21/16.
  */
object InchesToCentimeters extends UnitConversion {
  override def convert(value: Double): Double = {
    value * 2.54
  }
}
