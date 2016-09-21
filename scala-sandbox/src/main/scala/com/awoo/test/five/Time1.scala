package com.awoo.test.five

/**
  * Created by yuxiaolu on 9/21/16.
  */
class Time1 (hours: Int, minutes: Int) {
  private val value = hours * 60 + minutes

  def before(other: Time1): Boolean = {
    value < other.value
  }

  def mins() = value
}
