package com.awoo.test.five

class BankAccount (private[this] var balance: Double = 0.0) {
  def deposit(amount:Double) {
    balance += amount
  }
  
  def withdraw(amount:Double) {
    balance -= amount
  }
  
  def getBalance = balance
}