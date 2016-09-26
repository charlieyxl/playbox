package com.awoo.test.eleven

/**
  * Created by yuxiaolu on 9/26/16.
  */
class RichFile(val dir: String, val file: String, val ext: String) {
  override def toString = dir + "/" + file + "/" + ext
}

object RichFile {
  def unapply(input: RichFile) = {
    Some((input.dir, input.file, input.ext))
  }
}