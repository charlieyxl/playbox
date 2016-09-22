package com.awoo.test.eight

/**
  * Created by yuxiaolu on 9/22/16.
  */
class SavingsAccount(initalBalance: Double) extends BankAccount(initalBalance) {
  private var monthlyCount = 0
  private val freeCount = 3

  override def deposit(amount: Double) = {
    super.deposit(amount)
    chargeFee()
  }

  override def withdraw(amount: Double) = {
    super.withdraw(amount)
    chargeFee()
  }

  private final def chargeFee() = {
    monthlyCount += 1
    if (monthlyCount > freeCount)
      balance -= 1
  }

  def earnMonthlyInterest() = {
    balance *= 1.01
    monthlyCount = 0
  }
}
