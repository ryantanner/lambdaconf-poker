import shapeless._
import Nat._

sealed trait Suit

sealed trait Rank {
  type Val <: Nat
}

object Rank {
  type Aux[N <: Nat] = Rank { type Val = N }
}

//class Card[R <: Rank, S <: Suit]
trait Card extends Serializable {
  type R <: Rank
  type S <: Suit
}

object Card {
  type Aux[R1 <: Rank, S1 <: Suit] = Card { type R = R1; type S = S1 }
}

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


sealed trait TwoOfClubs extends Card { type Rank = Two; type Suit = Clubs }
sealed trait ThreeOfClubs extends Card { type Rank = Three; type Suit = Clubs }
sealed trait FourOfClubs extends Card { type Rank = Four; type Suit = Clubs }
sealed trait FiveOfClubs extends Card { type Rank = Five; type Suit = Clubs }
sealed trait SixOfClubs extends Card { type Rank = Six; type Suit = Clubs }
sealed trait SevenOfClubs extends Card { type Rank = Seven; type Suit = Clubs }
sealed trait EightOfClubs extends Card { type Rank = Eight; type Suit = Clubs }
sealed trait NineOfClubs extends Card { type Rank = Nine; type Suit = Clubs }
sealed trait TenOfClubs extends Card { type Rank = Ten; type Suit = Clubs }
sealed trait JackOfClubs extends Card { type Rank = Jack; type Suit = Clubs }
sealed trait QueenOfClubs extends Card { type Rank = Queen; type Suit = Clubs }
sealed trait KingOfClubs extends Card { type Rank = King; type Suit = Clubs }
sealed trait AceOfClubs extends Card { type Rank = Ace; type Suit = Clubs}

sealed trait TwoOfHearts extends Card { type Rank = Two; type Suit = Hearts }
sealed trait ThreeOfHearts extends Card { type Rank = Three; type Suit = Hearts }
sealed trait FourOfHearts extends Card { type Rank = Four; type Suit = Hearts }
sealed trait FiveOfHearts extends Card { type Rank = Five; type Suit = Hearts }
sealed trait SixOfHearts extends Card { type Rank = Six; type Suit = Hearts }
sealed trait SevenOfHearts extends Card { type Rank = Seven; type Suit = Hearts }
sealed trait EightOfHearts extends Card { type Rank = Eight; type Suit = Hearts }
sealed trait NineOfHearts extends Card { type Rank = Nine; type Suit = Hearts }
sealed trait TenOfHearts extends Card { type Rank = Ten; type Suit = Hearts }
sealed trait JackOfHearts extends Card { type Rank = Jack; type Suit = Hearts }
sealed trait QueenOfHearts extends Card { type Rank = Queen; type Suit = Hearts }
sealed trait KingOfHearts extends Card { type Rank = King; type Suit = Hearts }
sealed trait AceOfHearts extends Card { type Rank = Ace; type Suit = Hearts }

sealed trait TwoOfDiamonds extends Card { type Rank = Two; type Suit = Diamonds }
sealed trait ThreeOfDiamonds extends Card { type Rank = Three; type Suit = Diamonds }
sealed trait FourOfDiamonds extends Card { type Rank = Four; type Suit = Diamonds }
sealed trait FiveOfDiamonds extends Card { type Rank = Five; type Suit = Diamonds }
sealed trait SixOfDiamonds extends Card { type Rank = Six; type Suit = Diamonds }
sealed trait SevenOfDiamonds extends Card { type Rank = Seven; type Suit = Diamonds }
sealed trait EightOfDiamonds extends Card { type Rank = Eight; type Suit = Diamonds }
sealed trait NineOfDiamonds extends Card { type Rank = Nine; type Suit = Diamonds }
sealed trait TenOfDiamonds extends Card { type Rank = Ten; type Suit = Diamonds }
sealed trait JackOfDiamonds extends Card { type Rank = Jack; type Suit = Diamonds }
sealed trait QueenOfDiamonds extends Card { type Rank = Queen; type Suit = Diamonds }
sealed trait KingOfDiamonds extends Card { type Rank = King; type Suit = Diamonds }
sealed trait AceOfDiamonds extends Card { type Rank = Ace; type Suit = Diamonds }

sealed trait TwoOfSpades extends Card { type Rank = Two; type Suit = Spades }
sealed trait ThreeOfSpades extends Card { type Rank = Three; type Suit = Spades }
sealed trait FourOfSpades extends Card { type Rank = Four; type Suit = Spades }
sealed trait FiveOfSpades extends Card { type Rank = Five; type Suit = Spades }
sealed trait SixOfSpades extends Card { type Rank = Six; type Suit = Spades }
sealed trait SevenOfSpades extends Card { type Rank = Seven; type Suit = Spades }
sealed trait EightOfSpades extends Card { type Rank = Eight; type Suit = Spades }
sealed trait NineOfSpades extends Card { type Rank = Nine; type Suit = Spades }
sealed trait TenOfSpades extends Card { type Rank = Ten; type Suit = Spades }
sealed trait JackOfSpades extends Card { type Rank = Jack; type Suit = Spades }
sealed trait QueenOfSpades extends Card { type Rank = Queen; type Suit = Spades }
sealed trait KingOfSpades extends Card { type Rank = King; type Suit = Spades }
sealed trait AceOfSpades extends Card { type Rank = Ace; type Suit = Spades }