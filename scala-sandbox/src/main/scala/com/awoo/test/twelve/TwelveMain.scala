package com.awoo.test.twelve

/**
  * Created by yuxiaolu on 9/26/16.
  */
object TwelveMain {
  def main(args: Array[String]) {
    //12.1
//    println(values(x => x * x, -5, 5))

    //12.2
//    val arr = Array(1, 2, 5, 4, 3)
//    println(maxValue(arr))

    //12.3
//    println(factorial(3))

    //12.4
//    println(factorial2(0))

    //12.5
//    println(largest(x => 10 * x - x * x, 1 to 10))

    //12.6
//    println(largestAnswer(x => 10 * x - x * x, 1 to 10))

    //12.7
//    println(adjustToPair(_ * _)((6, 7)))
//    println(adjustToPairMethod(_ * _)((6, 7)))
//    println(adjustToPairAns(_ * _)((6, 7)))
//    println((adjustToPairOri _).curried(_ * _)((6, 7)))

    //12.8
//    println(compareStrArrLength(Array("hello", "world"))(Array(5, 5)))

    //12.9
    println(_corresponds(Array("hello", "world"), Array(5, 5), _compareStrArrLength))
  }

  //12.1
  def values(fun: (Int) => Int, low: Int, high: Int): Seq[Tuple2[Int, Int]] = {
    for (i <- low to high) yield Tuple2(i, fun(i))
  }

  //12.2
  def maxValue(arr: Array[Int]): Int = {
    arr.reduceLeft {_.max(_)}
  }

  //12.3
  def factorial(num: Int): Int = num match {
    case 0 => 1
    case _ => (1 to num).reduceLeft {_ * _}
  }

  //12.4
  def factorial2(num: Int): Int = {
//    (1 to num).product
    (1 to num).foldLeft(1) {_ * _}
  }

  //12.5
  def largest(fun: (Int) => Int, input: Seq[Int]): Int = {
    input.map(fun).max
  }

  //12.6
  def largestAnswer(fun: (Int) => Int, input: Seq[Int]): Int = {
    input.map(fun).zip(input).max._2
  }

  //12.7
  def adjustToPair(fun: (Int, Int) => Int)(t: (Int, Int)): Int = {
    fun(t._1, t._2)
  }
  //it is a function, others are methods
  def adjustToPairMethod: ((Int, Int) => Int) => ((Int, Int)) => Int = (fun) => (t) => {
    fun(t._1, t._2)
  }
  def adjustToPairAns(fun: (Int, Int) => Int) = (x: (Int, Int)) => fun(x._1, x._2)
  def adjustToPairOri(fun: (Int, Int) => Int, pair: (Int, Int)): Int = {
    fun(pair._1, pair._2)
  }

  //12.8
  def compareStrArrLength: (Seq[String]) => (Seq[Int]) => Boolean = (arr1) => (arr2) => {
    arr1.corresponds(arr2)(_.length == _)
  }

  //12.9
  def _compareStrArrLength: (String, Int) => Boolean = (str, n) => {
    str.length == n
  }
  def _corresponds[A, B]: (Seq[A], Seq[B], (A, B) => Boolean) => Boolean = (arr1, arr2, fun) => {
    arr1.zip(arr2).map(t => fun(t._1, t._2)).reduceLeft(_ & _)
  }
}
