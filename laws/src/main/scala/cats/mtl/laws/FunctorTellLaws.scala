package cats
package mtl
package laws

import cats.syntax.functor._

class FunctorTellLaws[F[_], L]()(implicit val tellInstance: FunctorTell[F, L]) {
  implicit val functor: Functor[F] = tellInstance.functor

  import tellInstance._

  // internal laws
  def writerIsTellAndMap[A](a: A, l: L): IsEq[F[A]] = {
    (tell(l) as a) <-> writer(a, l)
  }

  def tupleIsWriterFlipped[A](a: A, l: L): IsEq[F[A]] = {
    writer(a, l) <-> tuple((l, a))
  }

}

object FunctorTellLaws {
  def apply[F[_], L](implicit tell: FunctorTell[F, L]): FunctorTellLaws[F, L] = {
    new FunctorTellLaws[F, L]()(tell)
  }
}