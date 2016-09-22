package com.awoo.test.eight

/**
  * Created by yuxiaolu on 9/22/16.
  */
class BankAccount(initialBalance: Double) {
  var balance: Double = initialBalance
  def deposit(amount: Double) = { balance += amount}
  def withdraw(amount: Double) = {balance -= amount}
}
