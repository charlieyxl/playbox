package com.awoo.test.seventeen

/**
  * Created by yuxiaolu on 9/30/16.
  */
class Pair10[S, T] (var first: S, var second: T) {
  def swap()(implicit ev: S =:= T, ev2: T =:= S): Unit = {
    val temp = first
    first = second
    second = temp
  }
}
