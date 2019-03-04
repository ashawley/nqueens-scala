/**
 * NQueens.scala --- N-queens problem
 *
 * Copyright (C) 2019  Aaron S. Hawley
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import scala.Boolean
import scala.Int
import scala.Iterator
import scala.math.Numeric
import scala.StringContext
// import scala.Tuple2
import scala.collection.immutable.Seq
import scala.Predef.intWrapper

import java.lang.IllegalArgumentException

/**
 * Problem: Place ''N'' queens on a board so no queen threatens another.
 *
 * <pre>
 * _|_|_|_|_|Q|_|_
 * _|_|_|Q|_|_|_|_
 * _|_|_|_|_|_|Q|_
 * Q|_|_|_|_|_|_|_
 * _|_|_|_|_|_|_|Q
 * _|Q|_|_|_|_|_|_
 * _|_|_|_|Q|_|_|_
 * _|_|Q|_|_|_|_|_
 * </pre>
 *
 * The queens can't occupy the same row, column or diagonal.
 *
 * Example: {{{
 * NQueens.solve(8)
 * }}}
 *
 * A brute-force heuristic is used to search the possibilities.
 * Complexity severely limits the capability to solve bases larger than 13.
 */
object NQueens {

  // FIXME: value safe is not a member of (Int, Int)
  //        (x,y).safe(v(x),v(y))
  //              ^
  // private implicit class RichPair[T <: Numeric[T]](
  private implicit class RichPair[T](
    pair: (T,T))(
    implicit num: Numeric[T]
  ) {
    import num._

    def safe(x: T, y: T): Boolean =
      pair._1 - pair._2 != abs(x - y)
  }
  
  /**
    * Solve N-queens for `n`.
    *
    * Generates all permutations of the distinct range 0 to `n`, or ''n!''.
    *
    *  1. For 8 queens, there are 40,320 cases.
    *  1. For 10 queens,there are 3,628,800 values.
    *  1. For 12 queens, there are 479,001,600 values.
    *  1. For 13 queens, there are 6,227,020,800 values.
    *
    * @param  n Number of queens
    * @return Solutions
    * @throws java.lang.IllegalArgumentException When n less than zero
    */
  def solve(n: Int): Iterator[Seq[Int]] = {
    if (n < 0) {
      throw new IllegalArgumentException(s"Number was less than 0: $n")
    }
    (0 to n-1)
      .permutations
      .filter { v =>
        (0 to n-1).forall { y =>
          (y+1 to n-1).forall { x =>
            (x,y).safe(v(x),v(y))
          }
        }
      }
  }
}
