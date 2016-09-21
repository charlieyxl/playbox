package com.didi.nourish.demo

import org.apache.spark.{SparkContext, SparkConf}

/**
  * Created by yuxiaolu on 9/19/16.
  */
object SparkTest {
  def main(args: Array[String]) {
    val sparkConf = new SparkConf
    sparkConf.setMaster("local[10]")
    sparkConf.setAppName("Collection test")

    val sparkContext = new SparkContext(sparkConf)
    val rdd = sparkContext.parallelize(Array(1,2,3,4), 2)
    rdd.foreach(x => println(x))
    sparkContext.stop()
  }
}
