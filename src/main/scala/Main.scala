/**
 * Main.scala --- Command-line application
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

import scala.Array
import scala.Console
import scala.Int
import scala.Seq
import scala.StringContext
import scala.Unit
import scala.util.Try

import scala.Predef.augmentString
import scala.Predef.genericArrayOps
import scala.Predef.String

import java.lang.NumberFormatException

/**
 * Command-line driver of [[NQueens]] problem.
 */
object Main {

  /**
    * Display a solution in the terminal screen.
    *
    * @param  n     Number of queens
    * @param  board Queen positions on an ''n''-by-''n'' board
    * @return Nothing
    */
  def output(n: Int)(board: Seq[Int]): Unit = {
    board.foreach { queen =>
      val row = 
        "_|" * queen.toInt + "Q" + "|_" * (n-queen.toInt-1)
      Console.out.println(row)
    }
  }

  /**
    * Solve N-queens problem for ''n'' in first argument,
    * else use 8 for ''n''.
    *
    * @param  args Arguments
    * @return Nothing
    * @throws java.lang.NumberFormatException
    *         If arg isn't an integer
    * @throws java.lang.IllegalArgumentException 
    *         For n argument to [[NQueens.solve]]
    */
  def main(args: Array[String]): Unit = {
    val n = args.headOption.getOrElse("8").toInt
    val (solns1, solns2) = NQueens.solve(n).duplicate
    solns1.zipWithIndex.foreach { case (soln, i) =>
      Console.out.println(s"Solution #${i+1}")
      output(n)(soln)
    }
    val n_solns = solns2.size
    if (n_solns == 1) {
      Console.out.println("Found 1 solution")
    } else {
      Console.out.println(s"Found $n_solns solutions")
    }
  }

  /**
    * Same as [[main]], but silently quit if failure or no argument.
    *
    * @param  args Arguments
    * @return Nothing
    */
  def main2(args: Array[String]): Unit = {
    for (arg <- args.headOption if arg.trim.nonEmpty)
      for (n <- Try(arg.toInt).filter(0 <= _).filter(_ < 14))
        yield {
          val (solns1, solns2) = NQueens.solve(n).duplicate
          solns1.zipWithIndex.foreach { case (soln, i) =>
            Console.out.println(s"Solution #${i+1}")
            output(n)(soln)
          }
          val n_solns = solns2.size
          if (n_solns == 1) {
            Console.out.println("Found 1 solution")
          } else {
            Console.out.println(s"Found $n_solns solutions")
          }
        }
  }

  /**
    * Same as [[main]], but fail if no argument provided.
    *
    * @param  args Arguments
    * @return Nothing
    */
  def require(args: Array[String]): Unit = {
    args.headOption
      .filter(_.trim.nonEmpty)
      .map {
        _.toInt
      }
      .map { n =>
        val (solns1, solns2) = NQueens.solve(n).duplicate
        solns1.zipWithIndex.foreach { case (soln, i) =>
          Console.out.println(s"Solution #${i+1}")
          output(n)(soln)
        }
        val n_solns = solns2.size
        if (n_solns == 1) {
          Console.out.println("Found 1 solution")
        } else {
          Console.out.println(s"Found $n_solns solutions")
        }
      }
      .getOrElse {
        Console.err.println("Error: expected argument")
      }
  }

  /**
    * Same as [[require]], but fail if invalid argument provided.
    *
    * @param  args Arguments
    * @return Nothing
    * @throws java.lang.IllegalArgumentException 
    *         For n argument to [[NQueens.solve]]
    */
  def require2(args: Array[String]): Unit = {
    args.headOption
      .filter(_.trim.nonEmpty)
      .map { arg: String =>
        Try(arg.toInt)
          .recover {
            case _: NumberFormatException =>
              Console.err.println(
                s"Error: Failed to convert '$arg' to integer"
              )
          }
          .foreach {
            case n: Int =>
              val (solns1, solns2) = NQueens.solve(n).duplicate
              solns1.zipWithIndex.foreach { case (soln, i) =>
                Console.out.println(s"Solution #${i+1}")
                output(n)(soln)
              }
              val n_solns = solns2.size
              if (n_solns == 1) {
                Console.out.println("Found 1 solution")
              } else {
                Console.out.println(s"Found $n_solns solutions")
              }
          }
      }
      .getOrElse {
        Console.err.println("Error: expected argument")
      }
  }
}
