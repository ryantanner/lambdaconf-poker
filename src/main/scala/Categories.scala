import PokerCards._
import RankLTEq._
import shapeless.Nat.{apply => _, _}
import shapeless.ops.nat.Diff
import shapeless.test.illTyped
import shapeless.{=:!=, Sized}

object Categories {
  import PokerCards._

  sealed trait HoleCards[C1 <: Card, C2 <: Card]

  class Pair[C1 <: Card, C2 <: Card]
    (implicit ev: C1#S =:!= C2#S, ev1: C1#R =:= C2#R) extends HoleCards[C1, C2]

  case class BigPair[C1 <: Card, C2 <: Card]
    (implicit ev: Big[C1#R], ev1: C1#S =:!= C2#S, ev2: C1#R =:= C2#R) extends Pair[C1, C2]

  case class MediumPair[C1 <: Card, C2 <: Card]
    (implicit ev: Medium[C1#R], ev1: C1#S =:!= C2#S, ev2: C1#R =:= C2#R) extends Pair[C1, C2]

  case class SmallPair[C1 <: Card, C2 <: Card]
    (implicit ev: Small[C1#R], ev1: C1#S =:!= C2#S, ev2: C1#R =:= C2#R) extends Pair[C1, C2]


  class Suited[C1 <: Card, C2 <: Card]
    (implicit ev: C1#S =:= C2#S, ev1: C1#R =:!= C2#R) extends HoleCards[C1, C2]

  case class SuitedBigCards[C1 <: Card, C2 <: Card]
    (implicit ev: Big[C1#R], ev1: Big[C2#R], ev2: C1#S =:= C2#S, ev3: C1#R =:!= C2#R) extends Suited[C1, C2]
  case class SuitedMediumCards[C1 <: Card, C2 <: Card]
    (implicit ev: Medium[C1#R], ev1: Medium[C2#R], ev2: C1#S =:= C2#S, ev3: C1#R =:!= C2#R) extends Suited[C1, C2]
  case class SuitedSmallCards[C1 <: Card, C2 <: Card]
    (implicit ev: Small[C1#R], ev1: Small[C2#R], ev2: C1#S =:= C2#S, ev3: C1#R =:!= C2#R) extends Suited[C1, C2]


  class Connector[C1 <: Card, C2 <: Card]
    (implicit ev: ConnectedRanks[C1#R, C2#R]) extends HoleCards[C1, C2]

  case class SuitedConnector[C1 <: Card, C2 <: Card]
    (implicit ev: C1#S =:= C2#S, ev1: ConnectedRanks[C1#R, C2#R]) extends Connector[C1, C2]

  class SuitedCard[C1 <: Card, C2 <: Card]
    (implicit ev: C1#S =:= C2#S, ev1: C1#R =:!= C2#R) extends HoleCards[C1, C2]

  case class SuitedAce[C1 <: Card, C2 <: Card]
    (implicit ev: C1#R =:= Ace, ev1: C1#S =:= C2#S, ev2: C1#R =:!= C2#R) extends SuitedCard[C1, C2]

  case class SuitedKing[C1 <: Card, C2 <: Card]
    (implicit ev: C1#R =:= King, ev1: C1#S =:= C2#S, ev2: C1#R =:!= C2#R) extends SuitedCard[C1, C2]

  case class SuitedFaceTrash[C1 <: Card, C2 <: Card]
    (implicit ev: JackOrQueen[C1#R], ev1: MediumOrSmall[C2#R], ev2: C1#S =:= C2#S, ev3: C1#R =:!= C2#R) extends SuitedCard[C1, C2]


  case class TwoGap[C1 <: Card, C2 <: Card]()
    (implicit ev: TwoGapRank[C1#R, C2#R]) extends HoleCards[C1, C2]

  case class ThreeGap[C1 <: Card, C2 <: Card]
    (implicit ev: ThreeGapRank[C1#R, C2#R]) extends HoleCards[C1, C2]

  case class PsuedoHigh[C1 <: Card, C2 <: Card]
    (implicit ev: High[C1#R], ev1: Low[C2#R]) extends HoleCards[C1, C2]


  case class SuitedTrash[C1 <: Card, C2 <: Card]
    (implicit ev: C1#S =:= C2#S, ev1: Low[C1#R], ev2: Low[C2#R], ev3: C1#R =:!= C2#R) extends HoleCards[C1, C2]

  case class OffsuitTrash[C1 <: Card, C2 <: Card]
    (implicit ev: C1#S =:!= C2#S, ev1: Low[C1#R], ev2: Low[C2#R], ev3: C1#R =:!= C2#R) extends HoleCards[C1, C2]

}

object Usage {
  import Categories._
  import TwoGapRank._


}