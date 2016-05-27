import scala.annotation.implicitNotFound

/**
  * Created by ryantanner on 5/22/16.
  */
object CakeCards {

  import shapeless._
  import Nat._
  import ops.nat._
  import LTEq._
  import GTEq._
  import RankGTEq._
  import RankLTEq._

  sealed trait Suit
  sealed trait Rank {
    type Val <: Nat
  }

  class Card { self: Suit with Rank => }

  sealed trait Clubs extends Suit
  sealed trait Hearts extends Suit
  sealed trait Diamonds extends Suit
  sealed trait Spades extends Suit

  trait Two    extends Rank { type Val = _0 }
  trait Three  extends Rank { type Val = _1 }
  trait Four   extends Rank { type Val = _2 }
  trait Five   extends Rank { type Val = _3 }
  trait Six    extends Rank { type Val = _4 }
  trait Seven  extends Rank { type Val = _5 }
  trait Eight  extends Rank { type Val = _6 }
  trait Nine   extends Rank { type Val = _7 }
  trait Ten    extends Rank { type Val = _8 }
  trait Jack   extends Rank { type Val = _9 }
  trait Queen  extends Rank { type Val = _10 }
  trait King   extends Rank { type Val = _11 }
  trait Ace    extends Rank { type Val = _12 }

  case object TwoOfClubs extends Card with Two with Clubs
  case object ThreeOfClubs extends Card with Three with Clubs
  case object FourOfClubs extends Card with Four with Clubs
  case object FiveOfClubs extends Card with Five with Clubs
  case object SixOfClubs extends Card with Six with Clubs
  case object SevenOfClubs extends Card with Seven with Clubs
  case object EightOfClubs extends Card with Eight with Clubs
  case object NineOfClubs extends Card with Nine with Clubs
  case object TenOfClubs extends Card with Ten with Clubs
  case object JackOfClubs extends Card with Jack with Clubs
  case object QueenOfClubs extends Card with Queen with Clubs
  case object KingOfClubs extends Card with King with Clubs
  case object AceOfClubs extends Card with Ace with Clubs

  case object TwoOfHearts extends Card with Two with Hearts
  case object ThreeOfHearts extends Card with Three with Hearts
  case object FourOfHearts extends Card with Four with Hearts
  case object FiveOfHearts extends Card with Five with Hearts
  case object SixOfHearts extends Card with Six with Hearts
  case object SevenOfHearts extends Card with Seven with Hearts
  case object EightOfHearts extends Card with Eight with Hearts
  case object NineOfHearts extends Card with Nine with Hearts
  case object TenOfHearts extends Card with Ten with Hearts
  case object JackOfHearts extends Card with Jack with Hearts
  case object QueenOfHearts extends Card with Queen with Hearts
  case object KingOfHearts extends Card with King with Hearts
  case object AceOfHearts extends Card with Ace with Hearts

  case object TwoOfDiamonds extends Card with Two with Diamonds
  case object ThreeOfDiamonds extends Card with Three with Diamonds
  case object FourOfDiamonds extends Card with Four with Diamonds
  case object FiveOfDiamonds extends Card with Five with Diamonds
  case object SixOfDiamonds extends Card with Six with Diamonds
  case object SevenOfDiamonds extends Card with Seven with Diamonds
  case object EightOfDiamonds extends Card with Eight with Diamonds
  case object NineOfDiamonds extends Card with Nine with Diamonds
  case object TenOfDiamonds extends Card with Ten with Diamonds
  case object JackOfDiamonds extends Card with Jack with Diamonds
  case object QueenOfDiamonds extends Card with Queen with Diamonds
  case object KingOfDiamonds extends Card with King with Diamonds
  case object AceOfDiamonds extends Card with Ace with Diamonds

  case object TwoOfSpades extends Card with Two with Spades
  case object ThreeOfSpades extends Card with Three with Spades
  case object FourOfSpades extends Card with Four with Spades
  case object FiveOfSpades extends Card with Five with Spades
  case object SixOfSpades extends Card with Six with Spades
  case object SevenOfSpades extends Card with Seven with Spades
  case object EightOfSpades extends Card with Eight with Spades
  case object NineOfSpades extends Card with Nine with Spades
  case object TenOfSpades extends Card with Ten with Spades
  case object JackOfSpades extends Card with Jack with Spades
  case object QueenOfSpades extends Card with Queen with Spades
  case object KingOfSpades extends Card with King with Spades
  case object AceOfSpades extends Card with Ace with Spades

  sealed trait HoleCards

  case class BigPair[S1 <: Suit, S2 <: Suit, R <: Rank : Big](c1: Card with R with S1, c2: Card with R with S2)(implicit ev: S1 =:!= S2) extends HoleCards
  case class MediumPair[S1 <: Suit, S2 <: Suit, R <: Rank : Medium](c1: Card with R with S1, c2: Card with R with S2)(implicit ev: S1 =:!= S2) extends HoleCards
  case class SmallPair[S1 <: Suit, S2 <: Suit, R <: Rank : Small](c1: Card with R with S1, c2: Card with R with S2)(implicit ev: S1 =:!= S2) extends HoleCards

  case class SuitedBigCards[S <: Suit, R <: Rank : Big, R1 <: Rank : Big](c1: Card with R with S, c2: Card with R1 with S)(implicit ev: R =:!= R1) extends HoleCards
  case class SuitedMediumCards[S <: Suit, R <: Rank : Medium, R1 <: Rank : Medium](c1: Card with R with S, c2: Card with R1 with S)(implicit ev: R =:!= R1) extends HoleCards
  case class SuitedSmallCards[S <: Suit, R <: Rank : Small, R1 <: Rank : Small](c1: Card with R with S, c2: Card with R1 with S)(implicit ev: R =:!= R1) extends HoleCards

  case class SuitedConnector[S <: Suit, R1 <: Rank, R2 <: Rank](c1: Card with R1 with S, c2: Card with R2 with S)(implicit ev: ConnectedRanks[R1, R2]) extends HoleCards
  case class Connector[S1 <: Suit, R1 <: Rank, S2 <: Suit, R2 <: Rank](c1: Card with R1 with S1, c2: Card with R2 with S2)(implicit ev: ConnectedRanks[R1, R2], ev1: S1 =:!= S2) extends HoleCards

  case class SuitedAce[S <: Suit, R <: Rank](c1: Card with Ace with S, c2: Card with R with S) extends HoleCards
  case class SuitedKing[S <: Suit, R <: Rank](c1: Card with King with S, c2: Card with R with S) extends HoleCards

  case class SuitedFaceTrash[S <: Suit, R1 <: Rank : JackOrQueen, R2 <: Rank : MediumOrSmall](c1: Card with R1 with S, c2: Card with R2 with S) extends HoleCards

  case class TwoGap[S1 <: Suit, R1 <: Rank, S2 <: Suit, R2 <: Rank](c1: Card with R1 with S1, c2: Card with R2 with S2)(implicit ev: TwoGapRank[R1, R2]) extends HoleCards
  case class ThreeGap[S1 <: Suit, R1 <: Rank, S2 <: Suit, R2 <: Rank](c1: Card with R1 with S1, c2: Card with R2 with S2)(implicit ev: ThreeGapRank[R1, R2]) extends HoleCards

  case class PsuedoHigh[S1 <: Suit, R1 <: Rank : High, S2 <: Suit, R2 <: Rank : Low](c1: Card with R1 with S1, c2: Card with R2 with S2)(implicit ev: R2 <== Ten) extends HoleCards

  case class RandomSuited[S <: Suit, R1 <: Rank : Low, R2 <: Rank : Low](c1: Card with R1 with S, c2: Card with R2 with S) extends HoleCards

  case class RandomOffsuit[S1 <: Suit, S2 <: Suit, R1 <: Rank : Low, R2 <: Rank : Low](c1: Card with R1 with S1, c2: Card with R2 with S2)(implicit ev: S1 =:!= S2) extends HoleCards

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

}
