package com.awoo.test.seventeen

/**
  * Created by yuxiaolu on 9/30/16.
  */
class Pair[T, S] (val first: T, val second: S) {
  def swap() = new Pair(second, first)
}
