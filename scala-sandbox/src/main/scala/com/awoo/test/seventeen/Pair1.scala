package com.awoo.test.seventeen

/**
  * Created by yuxiaolu on 10/1/16.
  */
class Pair1[T <: Comparable[T]] (val first: T, val second: T) {
  def smaller(): T = {
    if (first.compareTo(second) < 0)
      first
    else
      second
  }
}
