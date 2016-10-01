package com.awoo.test.seventeen

/**
  * Created by yuxiaolu on 9/30/16.
  */
object SeventeenMain {
  def main(args: Array[String]) {
    //17.1
//    val p = new Pair(1, "one").swap()
//    println(p.first + " " + p.second)

    //17.2
//    val p2 = new Pair2[String]("a", "b")
//    p2.swap()
//    println(p2.first + " " + p2.second)

    //17.3
//    val p3 = new Pair3(1, "one")
//    val s3 = swap3(p3)
//    println(s3.first + " " + s3.second)

    //17.6
//    println(middle("world"))

    //17.10
    val p10 = new Pair10(1, 2)
    p10.swap()
    println(p10.first + " " + p10.second)
  }

  //17.3
  def swap3[T, S](pair: Pair3[T, S]): Pair3[S, T] = {
    new Pair3[S, T](pair.second, pair.first)
  }

  //17.6
  def middle[T](input: Iterable[T]): T = {
    input.dropRight(input.size / 2).last
  }
}
