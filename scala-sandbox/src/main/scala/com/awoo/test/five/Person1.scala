package com.awoo.test.five

/**
  * Created by yuxiaolu on 9/21/16.
  */
class Person1 (name: String) {
  private val _firstName = name.split(" ")(0)
  private val _lastName = name.split(" ")(1)

  def firstName() = _firstName

  def lastName() = _lastName
}
