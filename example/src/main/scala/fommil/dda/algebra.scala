// Copyright: 2017 - 2018 Sam Halliday
// License: http://www.gnu.org/licenses/gpl-3.0.en.html

package fommil
package dda
package algebra

import prelude._, Z._
import time.Epoch

trait Drone[F[_]] {
  def getBacklog: F[Int]
  def getAgents: F[Int]
}
object Drone

@deriving(Order, Show)
final case class MachineNode(id: String)

trait Machines[F[_]] {
  def getTime: F[Epoch]
  def getManaged: F[NonEmptyList[MachineNode]]
  def getAlive: F[MachineNode ==>> Epoch]
  def start(node: MachineNode): F[Unit]
  def stop(node: MachineNode): F[Unit]
}
object Machine
