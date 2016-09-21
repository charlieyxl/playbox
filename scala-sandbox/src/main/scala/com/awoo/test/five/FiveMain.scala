package com.awoo.test.five

object FiveMain {
  def main(args: Array[String]) {
    //5.1
    //    val counter = new Counter(Int.MaxValue)
    //    counter.increament()
    //    println(counter.current)
    //    counter.value = 10
    //    println(counter.current)

    //5.2
    //    val bankAccount = new BankAccount
    //    bankAccount.deposit(100)
    //    bankAccount.withdraw(50)
    //    println(bankAccount.getBalance)

    //5.3
    //    val time1 = new Time(10, 10)
    //    val time2 = new Time(11, 0)
    //    val time3 = new Time (10, 20)
    //    println(time1.before(time2))
    //    println(time1.before(time3))
    //    println(time2.before(time3))

    //5.4
    //    val time1 = new Time1(10, 10)
    //    val time2 = new Time1(11, 0)
    //    val time3 = new Time1(10, 20)
    //    println(time1.mins())
    //    println(time1.before(time2))
    //    println(time1.before(time3))
    //    println(time2.before(time3))

    //5.5
    //    val student = new Student
    //    student.name = "awoo"
    //    student.setId(1)
    //    println(student.id + " / " + student.name)

    //5.6
    //    val person = new Person(-1)
    //    println(person.age)

    //5.7
    //    val person = new Person1("Charles Yu")
    //    println(person.firstName())
    //    println(person.lastName())

    //5.8
    //    val car1 = new Car("Chevrolet", "Camaro")
    //    val car2 = new Car("Chevrolet", "Camaro", 2016)
    //    val car3 = new Car("Chevrolet", "Camaro", "8AW00")
    //    val car4 = new Car("Chevrolet", "Camaro", 2016, "8AW00")
    //    println(car1.manufacturer() + "/" + car1.model() + "/" + car1.carPlate() + "/" + car1.yearOfModel())
    //    println(car2.manufacturer() + "/" + car2.model() + "/" + car2.carPlate() + "/" + car2.yearOfModel())
    //    println(car3.manufacturer() + "/" + car3.model() + "/" + car3.carPlate() + "/" + car3.yearOfModel())
    //    println(car4.manufacturer() + "/" + car4.model() + "/" + car4.carPlate() + "/" + car4.yearOfModel())

    //5.10
    val employee = new Employee("John", 1000)
    println(employee.name + "/" + employee.salary)
  }
}