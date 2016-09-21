package com.awoo.test.five

import scala.beans.BeanProperty

class Student {
  @BeanProperty var name: String = _
  @BeanProperty var id: Long = 0
}