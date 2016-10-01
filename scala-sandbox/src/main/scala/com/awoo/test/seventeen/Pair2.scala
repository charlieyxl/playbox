package com.awoo.test.seventeen

/**
  * Created by yuxiaolu on 9/30/16.
  */
class Pair2[T] (var first: T, var second: T) {
  def swap(): Unit = {
    val temp = first
    first = second
    second = temp
  }
}
