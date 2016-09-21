package com.awoo.test.five

/**
  * Created by yuxiaolu on 9/21/16.
  */
class Employee {
  var name = ""
  var salary = 0.0

  def this(_name: String, _salary: Double) {
    this()
    this.name = _name
    this.salary = _salary
  }
}
