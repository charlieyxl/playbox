package com.awoo.test.eleven

/**
  * Created by yuxiaolu on 9/26/16.
  */
class Fraction(n: Int, d: Int) {
  private val num: Int = if (d == 0) 1 else n * sign(d) / gcd(n, d)
  private val den: Int = if (d == 0) 0 else d * sign(d) / gcd(n, d)

  override def toString = num + "/" + den

  def abs(a: Int): Int = if (a >= 0) a else -a
  def sign(a: Int) = if (a > 0) 1 else if (a < 0) -1 else 0
  def gcd(a: Int, b:Int): Int = if (b == 0) abs(a) else gcd(b, a % b)

  def +(other: Fraction): Fraction = {
    val den_sum = den * other.den
    val num_sum = num * other.den + other.num * den
    Fraction(num_sum, den_sum)
  }

  def -(other: Fraction): Fraction = {
    val den_sub = den * other.den
    val num_sub = num * other.den - other.num * den
    Fraction(num_sub, den_sub)
  }

  def *(other: Fraction): Fraction = {
    val den_mul = den * other.den
    val num_mul = num * other.num
    Fraction(num_mul, den_mul)
  }

  def /(other: Fraction): Fraction = {
    val den_div = den * other.num
    val num_div = num * other.den
    Fraction(num_div, den_div)
  }
}

object Fraction {
  def apply(n: Int, d: Int) = new Fraction(n, d)
}
