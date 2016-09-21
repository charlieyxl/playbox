package com.awoo.test.five

class Counter (var value: Int) {
  def increament() {
    if (value < Int.MaxValue) {
      value += 1
    }
  }
  
  def current = value
}