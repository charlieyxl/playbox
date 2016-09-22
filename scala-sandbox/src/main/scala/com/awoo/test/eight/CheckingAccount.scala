package com.awoo.test.eight

/**
  * Created by yuxiaolu on 9/22/16.
  */
class CheckingAccount(initialBalance: Double) extends BankAccount(initialBalance) {
  override def deposit(amount: Double) = {
    super.deposit(amount)
    balance -= 1
  }

  override def withdraw(amount: Double) = {
    super.withdraw(amount)
    balance -= 1
  }
}
