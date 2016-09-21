package com.awoo.test.five

/**
  * Created by yuxiaolu on 9/21/16.
  */
class Person (private var privateAge: Int) {
  if (privateAge < 0) privateAge = 0

  def age = privateAge
  def age_=(newValue: Int): Unit = {
    if (newValue > privateAge) privateAge = newValue
  }
}
