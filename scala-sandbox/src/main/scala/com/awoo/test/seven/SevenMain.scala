package com.awoo.test.seven

import com.awoo.test.seven.sub.PackageTest

import java.util.{HashMap => JavaHashMap}
import scala.collection.JavaConversions._

/**
  * Created by yuxiaolu on 9/21/16.
  */
object SevenMain {
  def main(args: Array[String]) {
    //7.1
//    val test = new PackageTest()
//    println(test.foo("hello"))

    //7.5
//    println(SevenMain.giveRaise(1.2))

    //7.6
    val javaMap = new JavaHashMap[Integer, String]()
    javaMap.put(1, "one")
    javaMap.put(2, "two")
    val scalaMap1 = javaMap.mapValues(_.toString)
    println(scalaMap1)
    //TODO how to convert to scala map without JavaConversions
  }

  private[com] def giveRaise(rate:Double):Int = {
    rate.toInt
  }
}
