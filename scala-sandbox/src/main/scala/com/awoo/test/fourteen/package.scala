package com.awoo.test

/**
  * Created by yuxiaolu on 9/29/16.
  */
package object fourteen {
  //14.4
  sealed abstract class Item
  case class Article(description: String, price: Double) extends Item
  case class Bundle(description: String, discount: Double, items: Item*) extends Item
  case class Multiple(count: Int, items: Item) extends Item

  def price(it: Item): Double = it match {
    case Article(_, p) => p
    case Bundle(_, disc, its @ _*) => its.map(price).sum - disc
    case Multiple(count, item) => price(item) * count
  }

  //14.6
  sealed abstract class BinaryTree
  case class Leaf(value: Int) extends BinaryTree
  case class Node(left: BinaryTree, right: BinaryTree) extends BinaryTree

  def leafSumBinaryTree(tree: BinaryTree): Int = tree match {
    case l: Leaf => l.value
    case n: Node => leafSumBinaryTree(n.left) + leafSumBinaryTree(n.right)
  }

  //14.7
  sealed abstract class Tree
  case class LeafT(value: Int) extends Tree
  case class NodeT(trees: Tree*) extends Tree

  def leafSumTree(tree: Tree): Int = tree match {
    case LeafT(l) => l
    case NodeT(t @ _*) => t.map(leafSumTree).sum
  }

  //14.8
  sealed abstract class OpTree
  case class LeafOpT(value: Int) extends OpTree
  case class NodeOpT(op: (Seq[Int]) => Int, opTrees: OpTree*) extends OpTree

  def leafOpTree(opTree: OpTree): Int = opTree match {
    case LeafOpT(l) => l
    case NodeOpT(op, t @ _*) => op(t.map(leafOpTree))
  }
}
