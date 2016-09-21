package com.awoo.test.six

/**
  * Created by yuxiaolu on 9/21/16.
  */
object Deck extends Enumeration {
  type Deck = Value

  val Spade = Value(1, "Spade")
  val Heart = Value("Heart")
  val Club = Value("Club")
  val Diamond = Value("Diamond")

  def isRedColor(card: Deck):Boolean = {
    card == Heart || card == Club
  }
}
