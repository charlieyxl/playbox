package com.awoo.test.fourteen

/**
  * Created by yuxiaolu on 9/29/16.
  */
object FourteenMain {
  def main(args: Array[String]) {
    //14.2
//    println(swap((1, 2)))

    //14.3
    //can't give Array[Int](1, 2, 3), because Array does not have :: method even though
    // it is built from ArraySeq which extends AbstractSeq, AbstractSeq with Seq; so always goes to case _
//    println(swapArray(List(1, 2, 3)))

    //14.4
//    val arti1 = Article("item1", 10)
//    val arti2 = Article("item2", 20)
//    val bundle = Bundle("item1 and item2", 3, arti1, arti2)
//    val mul = Multiple(10, bundle)
//    println(price(mul))

    //14.5
//    println(leafSumFun(List(List(3, 8), 2, List(5))))

    //14.6
//    val node0 = Node(Node(Leaf(3), Leaf(8)), Leaf(2))
//    println(leafSumBinaryTree(node0))

    //14.7
//    val tree = NodeT(NodeT(LeafT(3), LeafT(8)), LeafT(2), NodeT(LeafT(5)))
//    println(leafSumTree(tree))

    //14.8
//    val add = (s: Seq[Int]) => s.sum
//    val product = (s: Seq[Int]) => s.product
//    val minus = (s: Seq[Int]) => s.foldLeft(0)(_ - _)
//    val tree = NodeOpT(add, NodeOpT(product, LeafOpT(3), LeafOpT(8)), LeafOpT(2), NodeOpT(minus, LeafOpT(5)))
//    println(leafOpTree(tree))

    //14.9
//    println(optionListSum(List(Some(1), None, Some(2), None, Some(3))))

    //14.10
    println(h(2))
  }

  //14.2
  def swap(input: (Int, Int)): (Int, Int) = input match {
    case (a, b) => (b, a)
  }

  //14.3
  def swapArray[A](arr: Seq[A]): Seq[A] = arr match {
    case Nil => Nil
    case a :: b :: rest => b :: a :: rest
    case _ => arr
  }

  //14.5
  def leafSum(list: List[Any]): Int = {
    var sum = 0
    for (i <- list) i match {
      case l: List[Any] => sum += leafSum(l)
      case n: Int => sum += n
      case _ => 0
    }
    sum
  }
  def leafSumFun(list: Any): Int = list match {
    case l: List[Any] => l.map(leafSumFun).sum
    case n: Int => n
    case _ => 0
  }

  //14.9
  def optionListSum(list: List[Option[Int]]): Int = {
    //foldLeft would ignore None, sum actually calling foldLeft
    list.flatMap(x => x).sum
  }

  //14.10
  type Foo = Double => Option[Double]
  def compose(f: Foo, g: Foo): Foo = {
    x: Double => g(x) match {
      case None => None
      case Some(y) => f(y)
    }
  }

  def _f(x: Double) = if (x >= 0) Some(math.sqrt(x)) else None
  def _g(x: Double) = if (x != 1) Some(1 / (x - 1)) else None
  def h = compose(_f, _g)
}
