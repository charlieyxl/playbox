package com.awoo.test.five

class Time {
  private var minutes: Int = 0
  
  def this(hours: Int, minutes: Int) {
    this()
    this.minutes = hours*60 + minutes
  }
  
  def before(other: Time):Boolean = {
    minutes < other.minutes
  }
}