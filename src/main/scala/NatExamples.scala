

/**
  * Created by ryantanner on 5/21/16.
  */
class NatExamples {

  // Code here is all borrowed from Shapeless for explanatory purposes

  // Natural numbers are whole, non-negative numbers (integers >= 0): 0, 1, 2 and so on

  // All Shapeless natural numbers are subtypes of the Nat trait
  trait Nat {
    type N <: Nat
  }

  // Shapeless only explicitly defines the first natural number, 0
  class _0 extends Nat with Serializable {
    type N = _0
  }

  // All subsequent natural numbers are encoded using a successor function
  case class Succ[P <: Nat]() extends Nat {
    type N = Succ[P]
  }

  // Poke around https://github.com/milessabin/shapeless/blob/master/core/src/main/scala/shapeless/nat.scala
  // _1, _2, etc. aren't there!

  // Note that _0 is defined as a class, not an object
  // So if we say...
  // val Zero = _0
  // What are we saying?

  object NatOps {
    type _0 = shapeless._0
    val _0: _0 = new _0
  }

  // Now we can actually reference one of these numbers!
  import NatOps._

  val Zero = _0

  // But we still can't reference _1
  // val One = _1 <- does not compile yet

  // So how is that successor function ever invoked?

  // Macros!
  // This is the point where I get over my head, as I can't fully explain the macro magic going on here.

  // Huh, and you thought I was some sort of expert?  Bwahaha.

  // Essentially, Shapeless provides a macro which, in combination with some code generation, generates the
  // natural numbers up to 22.  So you can't (currently) encode any arbitrary natural number, but we only need 13
  // (The above sentence is a bit of a lie, you can witness arbitrary numbers but above a certain threshold operations are quite limited)

  // The limit is that each number is encoded as the succession of all previous numbers, so at some point you just blow up the compiler

}

object MoreExamples {

  import shapeless._
  import Nat._
  import ops.nat._
  import LTEq._
  import test._

  // What can we do with these numbers?

  // Comparisons
  implicitly[_0 <= _1]

  implicitly[Min.Aux[_0, _1, _0]]
  illTyped { """implicitly[Min.Aux[_0, _1, _1]]""" }

  implicitly[Sum[_2, _3]]

  // Basic Operations
  implicitly[Sum.Aux[_3, _4, _7]]
  illTyped { """implicitly[Sum.Aux[_3, _4, _10]]""" }

  val fivePlusFive = implicitly[Sum.Aux[_5, _5, _10]]
  val tenMinusTen = implicitly[Diff.Aux[fivePlusFive.Out, _10, _0]]
  val zeroEqualsZero = implicitly[tenMinusTen.Out =:= _0]
  val zeroNeqOne = implicitly[tenMinusTen.Out =:!= _1]

  // Fibonacci example is fantastic:
  // https://github.com/milessabin/shapeless/blob/master/examples/src/main/scala/shapeless/examples/fibonacci.scala

}
