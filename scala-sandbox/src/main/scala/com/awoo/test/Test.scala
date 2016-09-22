package com.awoo.test

import scala.collection.JavaConversions.mapAsScalaMap
import scala.collection.immutable.SortedMap
import scala.collection.mutable.{ArrayBuffer, Buffer, HashMap}
import scala.io.Source
import scala.math._

object Test {
  def main(args: Array[String]): Unit = {
    //        test1
    //    println(test2a(-1))
    //    test2b
    //    test2c1()
    //        test2c2(10)
    //    println(test2d("Hello"))
    //    println(test2e("Hello"))
    //    println(test2f("Hello"))
    //    println(test2g(2, 5))
    //    println(test3a(10).mkString("<", ", ", ">"))
    //    println(test3b(test3a(11)).mkString("<", ", ", ">"))
    //    println(test3c(test3a(11)).mkString("<", ", ", ">"))
    //    println(test3d(test3a(11)))
    //    println(test3e(Array(1.0, 2.0, 3.0, 4.0)))
    //    println(test3f1(test3a(11)).mkString("<", ", ", ">"))
    //    println(test3g(Array(1, 2, 1, 3, 4, 3)).mkString("<", ", ", ">"))
    //    test3h(Array(-2, 1, 2, -1, 3, 4))
    //    test3i
    //    test4a
    //    test4b()
    //    test4c()
    //    test4d()
    //    test4e()
    //    test4f()
    //    println(test4g(test3a(10)))
    //    println(test4h(test3a(10), 5))
    //    test4i()

//    test9a()
//    test9c()
//    test9d()
    test9f()
  }

  def test1() {
    val res1 = sqrt(3)
    val res2 = pow(res1, 2)
    println(res2)

    println("try" * 3)

    println(10 max 2)

    //    println(BigInt(2).pow(1024))

    println("abcd".head)
    println("abcd".charAt("abcd".length() - 1))

    println("abcd".take(2))
    println("abcd".drop(3))
    println("abcd".takeRight(1))
    println("abcd".dropRight(2))

    printf("sigum is %d", -10.signum)
    println("==========================")
  }

  def test2a(x: Int) = {
    if (x > 0) x
    else if (x == 0) 0
    else -x
  }

  def test2b() = {
    val emptyBlock = {}
    println(emptyBlock)
    println(emptyBlock.getClass())
  }

  def test2c1() = {
    for (i <- (0 to 10).reverse) println(i)
  }

  def test2c2(n: Int) = {
    var i = n
    while (i >= 0) {
      println(i)
      i -= 1
    }
  }

  def test2d(word: String) = {
    var result = 1L
    for (i <- word) {
      result *= i
    }
    result
  }

  def test2e(word: String) = {
    //1会被认为是char，1L则是数字
    word./:(1L)(_ * _)
  }

  def test2f(word: String): Long = {
    if (word.length() == 1)
      word.head
    else
      word.head * test2f(word.tail)
  }

  def test2g(x: Double, n: Int): Double = {
    if (n == 0) {
      1
    } else if (n > 0) {
      x * test2g(x, n - 1)
    } else {
      test2g(x, n + 1) / x
    }
  }

  def test3a(n: Int): Array[Int] = {
    0 until n toArray
  }

  def test3b(arr: Array[Int]): Array[Int] = {
    val a = ArrayBuffer[Int]()
    for (i <- 0 until(arr.length, 2)) {
      if (i + 2 < arr.length) {
        a += arr(i + 1);
        a += arr(i)
      } else {
        a += arr(i)
      }
    }
    a.toArray
  }

  def test3c(arr: Array[Int]): Array[Int] = {
    val result = for (i <- 0 until arr.length) yield {
      if (i + 1 < arr.length) {
        if (i % 2 == 1)
          arr(i - 1)
        else
          arr(i + 1)
      } else {
        arr(i)
      }
    }
    result.toArray
  }

  def test3d(arr: Array[Int]): Buffer[Int] = {
    val positives = for (i <- 0 until arr.length if arr(i) > 0) yield i
    val nonPositives = for (i <- 0 until arr.length if arr(i) <= 0) yield i
    positives.toBuffer ++ nonPositives.toBuffer
  }

  def test3e(arr: Array[Double]): Double = {
    arr./:(0.0)(_ + _) / arr.length
  }

  def test3f1(arr: Array[Int]): Array[Int] = {
    val reversed = for (i <- 1 to arr.length) yield arr(arr.length - i)
    reversed.toArray
  }

  def test3f2(arrBuffer: ArrayBuffer[Int]): ArrayBuffer[Int] = {
    arrBuffer.reverse
  }

  def test3g(arr: Array[Int]): Array[Int] = {
    arr.distinct
  }

  def test3h(arr: Array[Int]) {
    val indices = for (i <- 0 until arr.length if arr(i) < 0) yield i
    indices.reverse.dropRight(1)
    val buf = arr.toBuffer
    for (j <- 0 until indices.length) {
      buf.remove(indices(j))
    }
    println(buf)
  }

  def test3i() {
    val result = for (ele <- java.util.TimeZone.getAvailableIDs if ele.startsWith("America/")) yield ele.drop("America/".length)
    println(result.sortWith(_ > _).mkString("\n"))
  }

  def test4a() {
    val equipment = Map("Java" -> 95, "JavaScript" -> 80, "Scala" -> 30)
    val discountedEq = for ((k, v) <- equipment) yield (k, 0.9 * v)
    println(discountedEq)
  }

  def test4b() {
    val input = Source.fromFile("/Users/yuxiaolu/workspace/playbox/scala-sandbox/src/main/resource/input.txt", "UTF-8")
    try {
      val countMap = new HashMap[String, Int]
      var count = 0
      for (line <- input.getLines()) {
        count = countMap.getOrElse(line, 0)
        count += 1
        countMap(line) = count
      }

      println(countMap)
    } finally {
      input.close()
    }
  }

  def test4c() {
    val input = Source.fromFile("/Users/yuxiaolu/workspace/playbox/scala-sandbox/src/main/resource/input.txt", "UTF-8")
    try {
      var countMap = Map[String, Int]()
      var count = 0
      for (line <- input.getLines()) {
        count = countMap.getOrElse(line, 0)
        count += 1
        countMap = countMap + (line -> count)
      }

      println(countMap)
    } finally {
      input.close()
    }
  }

  def test4d() {
    val input = Source.fromFile("/Users/yuxiaolu/workspace/playbox/scala-sandbox/src/main/resource/input.txt", "UTF-8")
    try {
      var countMap = SortedMap[String, Int]()
      var count = 0
      for (line <- input.getLines()) {
        count = countMap.getOrElse(line, 0)
        count += 1
        countMap = countMap + (line -> count)
      }

      println(countMap)
    } finally {
      input.close()
    }
  }

  def test4e() {
    val input = Source.fromFile("/Users/yuxiaolu/workspace/playbox/scala-sandbox/src/main/resource/input.txt", "UTF-8")
    try {
      val countMap: scala.collection.mutable.Map[String, Int] = new java.util.TreeMap[String, Int]
      var count = 0
      for (line <- input.getLines()) {
        count = countMap.getOrElse(line, 0)
        count += 1
        countMap(line) = count
      }

      println(countMap)
    } finally {
      input.close()
    }
  }

  def test4f() {
    val weekdays = scala.collection.mutable.LinkedHashMap[String, Int]()
    weekdays += ("Monday" -> java.util.Calendar.MONDAY)
    weekdays += ("Tuesday" -> java.util.Calendar.TUESDAY)
    weekdays += ("Wednesday" -> java.util.Calendar.WEDNESDAY)
    weekdays += ("Thursday" -> java.util.Calendar.THURSDAY)
    weekdays += ("Friday" -> java.util.Calendar.FRIDAY)
    weekdays += ("Saturday" -> java.util.Calendar.SATURDAY)
    weekdays += ("Sunday" -> java.util.Calendar.SUNDAY)
    println(weekdays)
  }

  def test4g(arr: Array[Int]): (Int, Int) = {
    val buffer = arr.toBuffer
    (buffer.max, buffer.min)
  }

  def test4h(arr: Array[Int], v: Int): (Int, Int, Int) = {
    var ltNum = 0;
    var eqNum = 0;
    var gtNum = 0
    for (ele <- arr) {
      if (ele < v)
        ltNum += 1
      else if (ele == v)
        eqNum += 1
      else
        gtNum += 1
    }
    (ltNum, eqNum, gtNum)
  }

  def test4i() {
    println("Hello".zip("World"))
  }

  def test9a(): Unit = {
    val input = Source.fromFile("/Users/yuxiaolu/workspace/playbox/scala-sandbox/src/main/resource/nine.txt", "UTF-8")
    println(input.getLines().toArray.reverse.mkString("\n"))
  }

  def test9c(): Unit = {
    val input = Source.fromFile("/Users/yuxiaolu/workspace/playbox/scala-sandbox/src/main/resource/nine.txt", "UTF-8")
    for (i <- input.mkString.split("\\s+") if i.length > 5) println(i)
  }

  def test9d(): Unit = {
    val input = Source.fromFile("/Users/yuxiaolu/workspace/playbox/scala-sandbox/src/main/resource/nine_double.txt", "UTF-8")
    val doubles = input.mkString.split("\\s+").map(_.toDouble)
    println(doubles.sum)
    println(doubles.max)
    println(doubles.min)
  }

  def test9f(): Unit ={
    val input = Source.fromFile("/Users/yuxiaolu/workspace/playbox/scala-sandbox/src/main/resource/nine.txt", "UTF-8")
    val numPattern = "[0-9]+".r
    for (i <- numPattern.findAllIn(input.mkString).toArray) println(i)
  }
}