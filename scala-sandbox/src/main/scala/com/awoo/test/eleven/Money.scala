package com.awoo.test.eleven

/**
  * Created by yuxiaolu on 9/26/16.
  */
class Money(d: Int, c: Int) {
  private val cents: Int = d * 100 + c

  override def toString: String = {
    if (cents < 0)
      "$-" + (-cents / 100) + "." + (-cents % 100)
    else
      "$" + (cents / 100) + "." + (cents % 100)
  }

  def + (other: Money): Money = {
    val result = cents + other.cents
    Money(result / 100, result % 100)
  }

  def - (other: Money): Money = {
    val result = cents - other.cents
    Money(result / 100, result % 100)
  }

  def == (other: Money): Boolean = {
    cents == other.cents
  }
}

object Money {
  def apply(d: Int, c: Int) = new Money(d, c)
}