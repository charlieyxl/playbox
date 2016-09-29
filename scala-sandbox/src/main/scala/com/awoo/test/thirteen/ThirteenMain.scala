package com.awoo.test.thirteen

import scala.annotation.tailrec
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by yuxiaolu on 9/28/16.
  */
object ThirteenMain {
  def main(args: Array[String]) {
    //m + (t._1 -> ArrayBuffer[Int]),表示新生成一个Map,哪怕是可变集合
    //    val m = mutable.Map[Int, Char]()
    //    m.put(1, 'a')
    //    println(m)

    //13.1
    //    println(indexes("Mississippi"))

    //不可变集合, + 会生成一个新的,如果要添加的值在原集合中已有,则会覆盖原来的值
    //    val m = Map[Int, Char]()
    //    val m1 = m + (1 -> 'a')
    //    val m2 = m1 + (1 -> 'b')
    //    println(m1 + "; " + m2)

    //13.2
    //    println(indexesImmu("Mississippi"))

    //13.3
//    println(removeZeroFromList(List[Int](1, 0, 3, 5, 0, 2)))
//    println(remove0FromListTailRec(List[Int](1, 0, 3, 5, 0, 2)))

    //13.4
//    println(findValues(Array("a", "b", "c"), Map("a" -> 1, "c" -> 3)))

    //13.5
//    println(_mkString(Array("a", "b", "c"), ","))

    //13.6
//    val l = List(1, 2, 3, 4)
//    //+: funcion in List, is not the same as +: in package object scala
//    println((List[Int]() /: l)(_.+:(_)))

    //13.7
//    val prices = List(1.2, 2.5, 3)
//    val quantitis = List(4, 5, 8)
//    val _7 = prices.zip(quantitis).map(Function.tupled(_ * _))
//    println(_7)

    //13.8
//    println(_grouped(Array(1, 2, 3, 4, 5, 6), 3).map(_.mkString(" ")).mkString("\n"))

    //13.10
    println(countExistence("test"))
  }

  //13.1
  def indexes: String => mutable.Map[Char, ArrayBuffer[Int]] = (input) => {
    input.zipWithIndex.foldLeft(mutable.Map[Char, ArrayBuffer[Int]]()) {
      (m, t) => {
        m += (t._1 -> (m.getOrElse(t._1, ArrayBuffer[Int]()) += t._2))
      }
    }
  }

  //13.2
  def indexesImmu: String => Map[Char, ArrayBuffer[Int]] = (input) => {
    input.zipWithIndex.foldLeft(Map[Char, ArrayBuffer[Int]]()) {
      (m, t) => {
        m + (t._1 -> (m.getOrElse(t._1, ArrayBuffer[Int]()) += t._2))
      }
    }
  }

  //13.3
  //head recursive would push a lot of method call into stack, and lead to stack OOM issue
  def removeZeroFromList(input: List[Int]): List[Int] = input match {
    case Nil => Nil
    case head :: tail if head == 0 => removeZeroFromList(tail)
    case head :: tail => head +: removeZeroFromList(tail)
  }
  //tail recursive would be translate into a loop to avoid stack OOM issue
  def remove0FromListTailRec(input: List[Int]): List[Int] = {
    @tailrec
    def _remove0FromListTailRec(input: List[Int], result: List[Int]): List[Int] = input match {
      //Below definition is not applicable for @tailrec enhancement
//    def _remove0FromListTailRec: (List[Int], List[Int]) => List[Int] = (input, result) => input match {
      case Nil => result
      case head :: tail if head == 0 => _remove0FromListTailRec(tail, result)
      case head :: tail => _remove0FromListTailRec(tail, result :+ head)
    }
    _remove0FromListTailRec(input, List[Int]())
  }

  //13.4
  def findValues: (Seq[String], Map[String, Int]) => Seq[Int] = (input, m) => {
    //map.get(x) return Option[Int], flatMap would get ArrayBuffer[Int] instead of ArrayBuffer[Option[Int]]
    input.flatMap(x => m.get(x))
  }

  //13.5
  def _mkString: (Seq[String], String) => String = (list, sep) => {
    list.reduceLeft(_ + sep + _)
  }

  //13.8
  def _grouped(arr: Array[Int], num: Int): Array[Array[Int]] = {
    arr.grouped(num).toArray
  }

  //13.10
  def countExistence(str: String): mutable.Map[Char, Int] = {
    //Below implementation is not Thread safe, without .par it would be but is sequential
//    val fre = new mutable.HashMap[Char, Int]
//    for (c <- str.par) fre(c) = fre.getOrElse(c, 0) + 1
//    fre

    str.aggregate(mutable.Map[Char, Int]())(
      (m: mutable.Map[Char, Int], c: Char) => m += (c -> (m.getOrElse(c, 0) + 1)),
      (m1, m2) => m1 ++ m2.map(t => t._1 -> (t._2 + m1.getOrElse(t._1, 0)))
    )
  }
}
