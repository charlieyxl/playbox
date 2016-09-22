package com.awoo.test.seven

/**
  * Created by yuxiaolu on 9/21/16.
  */
package object sub {

}

package sub {
  class PackageTest {
    private[seven] def foo(param: String): String = {
      param
    }
  }
}