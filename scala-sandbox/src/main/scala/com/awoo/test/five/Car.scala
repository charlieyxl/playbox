package com.awoo.test.five

/**
  * Created by yuxiaolu on 9/21/16.
  */
class Car (val _manufacturer: String, val _model: String) {
  private var _yearOfModel = -1
  private var _carPlate = ""

  def this(manufacturer: String, model: String, yearOfModel: Int) {
    this(manufacturer, model)
    this._yearOfModel = yearOfModel
  }

  def this(manufacturer: String, model: String, carPlate: String) {
    this(manufacturer, model)
    this._carPlate = carPlate
  }

  def this(manufacturer: String, model: String, yearOfModel: Int, carPlate: String) {
    this(manufacturer, model,yearOfModel)
    this._carPlate = carPlate
  }

  def manufacturer() = _manufacturer

  def model() = _model

  def yearOfModel() = _yearOfModel

  def carPlate() = _carPlate
}
