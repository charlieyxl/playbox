package com.awoo.test.eight

import scala.collection.concurrent.RDCSS_Descriptor
import scala.collection.mutable.ArrayBuffer

/**
  * Created by yuxiaolu on 9/22/16.
  */
class Bundle extends Item{
  val items = new ArrayBuffer[Item]

  def add(item: Item): Unit = {
    items += item
  }

  override def price():Double = {
    items./:(0.0)(_ + _.price())
  }

  override def description():String = {
    (for (i <- items) yield i.description()).mkString(", ")
  }
}
