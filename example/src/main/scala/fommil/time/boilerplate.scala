// Copyright: 2017 - 2018 Sam Halliday
// License: http://www.gnu.org/licenses/gpl-3.0.en.html

package fommil
package time

import prelude._
import Z._

private[time] abstract class LocalClockBoilerplate {
  this: LocalClock.type =>

  def liftErr[E <: Cpr: Injt] (f: LocalClock[Task]) =
    new LocalClock[IO[E, ?]] {
      def now: IO[E, Epoch] = f.now.liftErr[E]
    }

}

private[time] abstract class SleepBoilerplate {
  this: Sleep.type =>

  def liftErr[E <: Cpr: Injt](f: Sleep[Task]): Sleep[IO[E, ?]] =
    new Sleep[IO[E, ?]] {
      def sleep(time: FiniteDuration): IO[E, Unit] = f.sleep(time).liftErr[E]
    }
}
