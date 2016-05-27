import scala.annotation.implicitNotFound

import shapeless._
import Nat._
import ops.nat._
import LTEq._
import GTEq._
import RankGTEq._
import RankLTEq._

import PokerCards._

trait JackOrQueen[R1 <: Rank] extends Serializable

object JackOrQueen {
  def rankIsJackOrQueen[R1 <: Rank](implicit ev: R1 <== Queen, ev1: R1 >== Jack) = new JackOrQueen[R1] {}
}

trait MediumOrSmall[R1 <: Rank] extends Serializable

object MediumOrSmall{
  def rankIsMediumOrSmall[R <: Rank](implicit ev: R <== Ten) = new MediumOrSmall[R] {}
}

@implicitNotFound("Ranks ${R1} and ${R2} are not two-gapped")
trait TwoGapRank[R1 <: Rank, R2 <: Rank] extends Serializable

object TwoGapRank {
  implicit def ranksAreTwoGap[R1 <: Rank, R2 <: Rank](implicit ev: Diff.Aux[R1#Val, R2#Val, _3]) = new TwoGapRank[R1, R2] {}
}

@implicitNotFound("Ranks ${R1} and ${R2} are not three-gapped")
trait ThreeGapRank[R1 <: Rank, R2 <: Rank] extends Serializable

object ThreeGapRank {
  implicit def ranksAreThreeGap[R1 <: Rank, R2 <: Rank](implicit ev: Diff.Aux[R1#Val, R2#Val, _4]) = new TwoGapRank[R1, R2] {}
}

@implicitNotFound("Rank ${A} is not High")
trait High[A <: Rank] extends Serializable

object High {
  implicit def rankIsHigh[R <: Rank](implicit ev: R <== King, ev1: R >== Ten) = new High[R] {}
}

@implicitNotFound("Rank ${A} is not Low")
trait Low[A <: Rank] extends Serializable

object Low {
  implicit def rankIsLow[R <: Rank](implicit ev: R <== Nine) = new Low[R] {}
}

@implicitNotFound("Rank ${A} is not Big")
trait Big[A <: Rank] extends Serializable

object Big {
  implicit def rankIsBig[R <: Rank](implicit ev: R >== Jack) = new Big[R] {}
}

@implicitNotFound("Rank ${A} is not Medium")
trait Medium[A <: Rank] extends Serializable

object Medium {
  implicit def rankIsMedium[R <: Rank](implicit ev1: R >== Seven, ev2: R <== Ten) = new Medium[R] {}
}

@implicitNotFound("Rank ${A} is not Small")
trait Small[A <: Rank] extends Serializable

object Small {
  implicit def rankIsSmall[R <: Rank](implicit ev: R <== Six) = new Small[R] {}
}

@implicitNotFound("Rank ${A} is not a face card")
trait Face[A <: Rank] extends Serializable

object Face {
  implicit def rankIsFace[R <: Rank](implicit ev: R >== Jack) = new Face[R] {}
}

trait ConnectedRanks[A <: Rank, B <: Rank]

object ConnectedRanks {

  type Predecessor[A <: Rank, B <: Rank] = Pred.Aux[A#Val, B#Val]

  def apply[A <: Rank, B <: Rank](implicit ev: Predecessor[B, A]) = new ConnectedRanks[A, B] { }

  implicit def conn[A <: Rank, B <: Rank](implicit ev: Pred.Aux[A#Val, B#Val]) = new ConnectedRanks[A, B] {}

}

trait GTEq[A <: Nat, B <: Nat] extends Serializable

object GTEq {
  def apply[A <: Nat, B <: Nat](implicit gteq: A >= B): GTEq[A, B] = gteq

  type >=[A <: Nat, B <: Nat] = GTEq[A, B]

  implicit def gtEq1 = new >=[_0, _0] {}
  implicit def gtEq2[B <: Nat] = new >=[Succ[B], _0] {}
  implicit def gtEq3[A <: Nat, B <: Nat](implicit gteq: A >= B) = new >=[Succ[A], Succ[B]] {}
}

trait NEq[A <: Nat, B <: Nat] extends Serializable

object NEq {
  def apply[A <: Nat, B <: Nat](implicit neq: A != B): NEq[A, B] = neq

  type !=[A <: Nat, B <: Nat] = NEq[A, B]

  implicit def neq1[B <: Nat] = new !=[Succ[B], _0] {}
  implicit def neq2[B <: Nat] = new !=[_0, Succ[B]] {}
}

trait RankGTEq[A <: Rank, B <: Rank] extends Serializable

object RankGTEq {
  def apply[A <: Rank, B <: Rank](implicit gteq: GTEq[A#Val, B#Val]): RankGTEq[A, B] = new RankGTEq[A, B] {}

  type >==[A <: Rank, B <: Rank] = RankGTEq[A, B]

  implicit def gtEq3[A <: Rank, B <: Rank](implicit gteq: A#Val >= B#Val): RankGTEq[A, B] = new >==[A, B] {}
}

trait RankLTEq[A <: Rank, B <: Rank] extends Serializable

object RankLTEq {
  def apply[A <: Rank, B <: Rank](implicit lteq: LTEq[A#Val, B#Val]): RankLTEq[A, B] = new RankLTEq[A, B] {}

  type <==[A <: Rank, B <: Rank] = RankLTEq[A, B]

  implicit def gtEq3[A <: Rank, B <: Rank](implicit lteq: A#Val <= B#Val): RankLTEq[A, B] = new <==[A, B] {}
}