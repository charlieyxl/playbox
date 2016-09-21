package com.awoo.test.five

class Time (val hours: Int, val minutes: Int) {
  def before(other: Time):Boolean = {
    if (hours < other.hours)
      true
    else if (hours == other.hours)
      minutes < other.minutes
    else
      false
  }
}