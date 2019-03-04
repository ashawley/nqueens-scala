N-Queens problem in Scala
============================

[![AppVeyor](https://ci.appveyor.com/api/projects/status/github/ashawley/nqueens-scala?svg=true)](https://ci.appveyor.com/project/ashawley/nqueens-scala)
[![Circle](https://circleci.com/gh/ashawley/nqueens-scala.svg?style=svg)](https://circleci.com/gh/ashawley/nqueens-scala)
[![Travis](https://img.shields.io/travis/ashawley/nqueens-scala.svg)](https://travis-ci.org/ashawley/nqueens-scala)

Problem: Place ''N'' queens on a board so no queen threatens another queen.

    _|_|_|_|_|Q|_|_
    _|_|_|Q|_|_|_|_
    _|_|_|_|_|_|Q|_
    Q|_|_|_|_|_|_|_
    _|_|_|_|_|_|_|Q
    _|Q|_|_|_|_|_|_
    _|_|_|_|Q|_|_|_
    _|_|Q|_|_|_|_|_
    
Solving the problem for a 8 queens:

```scala
NQueens.solve(8)
```

It's possible to solve for other board sizes:
decimal.

```scala
NQueens.solve(10) // 10 queens on a 10-by-10 board.
```

### Overview

This solution is written in [Scala] 2.12 and only depends on the Scala
standard library, and [Java 8].

The primary routine is called `solve`.  It can give the set of all
solutions for the problem in decimal:
	  
```scala
NQueens.solve(6).foreach(println)
Vector(1, 3, 5, 0, 2, 4)
Vector(2, 5, 1, 4, 0, 3)
Vector(3, 0, 4, 1, 5, 2)
Vector(4, 2, 0, 5, 3, 1)
```

Alternatively, an application entry point, `main`, is provided:

```scala
NQueens.main(Array())
```

Gives a listing of all the solutions for 8 queens:

```
Solution #1
Q|_|_|_|_|_|_|_
_|_|_|_|Q|_|_|_
_|_|_|_|_|_|_|Q
_|_|_|_|_|Q|_|_
_|_|Q|_|_|_|_|_
_|_|_|_|_|_|Q|_
_|Q|_|_|_|_|_|_
_|_|_|Q|_|_|_|_
Solution #2
Q|_|_|_|_|_|_|_
_|_|_|_|_|Q|_|_
_|_|_|_|_|_|_|Q
_|_|Q|_|_|_|_|_
_|_|_|_|_|_|Q|_
_|_|_|Q|_|_|_|_
_|Q|_|_|_|_|_|_
_|_|_|_|Q|_|_|_
Solution #3
Q|_|_|_|_|_|_|_
_|_|_|_|_|_|Q|_
_|_|_|Q|_|_|_|_
_|_|_|_|_|Q|_|_
_|_|_|_|_|_|_|Q
_|Q|_|_|_|_|_|_
_|_|_|_|Q|_|_|_
_|_|Q|_|_|_|_|_
Solution #4
Q|_|_|_|_|_|_|_
_|_|_|_|_|_|Q|_
_|_|_|_|Q|_|_|_
_|_|_|_|_|_|_|Q
_|Q|_|_|_|_|_|_
_|_|_|Q|_|_|_|_
_|_|_|_|_|Q|_|_
_|_|Q|_|_|_|_|_
[...]
Solution #89
_|_|_|_|_|_|_|Q
_|Q|_|_|_|_|_|_
_|_|_|Q|_|_|_|_
Q|_|_|_|_|_|_|_
_|_|_|_|_|_|Q|_
_|_|_|_|Q|_|_|_
_|_|Q|_|_|_|_|_
_|_|_|_|_|Q|_|_
Solution #90
_|_|_|_|_|_|_|Q
_|Q|_|_|_|_|_|_
_|_|_|_|Q|_|_|_
_|_|Q|_|_|_|_|_
Q|_|_|_|_|_|_|_
_|_|_|_|_|_|Q|_
_|_|_|Q|_|_|_|_
_|_|_|_|_|Q|_|_
Solution #91
_|_|_|_|_|_|_|Q
_|_|Q|_|_|_|_|_
Q|_|_|_|_|_|_|_
_|_|_|_|_|Q|_|_
_|Q|_|_|_|_|_|_
_|_|_|_|Q|_|_|_
_|_|_|_|_|_|Q|_
_|_|_|Q|_|_|_|_
Solution #92
_|_|_|_|_|_|_|Q
_|_|_|Q|_|_|_|_
Q|_|_|_|_|_|_|_
_|_|Q|_|_|_|_|_
_|_|_|_|_|Q|_|_
_|Q|_|_|_|_|_|_
_|_|_|_|_|_|Q|_
_|_|_|_|Q|_|_|_
Found 92 solutions
```

### Getting started

Use [sbt] to interact with the build.

```
$ sbt
```

You can run Scala from the sbt console:

```
sbt> console
scala> Main.main(Array("4"))
```

```
Solution #1
_|Q|_|_
_|_|_|Q
Q|_|_|_
_|_|Q|_
Solution #2
_|_|Q|_
Q|_|_|_
_|_|_|Q
_|Q|_|_
Found 2 solutions
```

Alternatively, you can interact with program as if it was built as a
command-line application but from sbt:

```
sbt> run 6
[info] Running Main 6
Solution #1
_|Q|_|_|_|_
_|_|_|Q|_|_
_|_|_|_|_|Q
Q|_|_|_|_|_
_|_|Q|_|_|_
_|_|_|_|Q|_
Solution #2
_|_|Q|_|_|_
_|_|_|_|_|Q
_|Q|_|_|_|_
_|_|_|_|Q|_
Q|_|_|_|_|_
_|_|_|Q|_|_
Solution #3
_|_|_|Q|_|_
Q|_|_|_|_|_
_|_|_|_|Q|_
_|Q|_|_|_|_
_|_|_|_|_|Q
_|_|Q|_|_|_
Solution #4
_|_|_|_|Q|_
_|_|Q|_|_|_
Q|_|_|_|_|_
_|_|_|_|_|Q
_|_|_|Q|_|_
_|Q|_|_|_|_
Found 4 solutions
[success] Total time: 8 s, completed Mar 4, 2019 9:30:06 AM
```

### Caveats

Passing the empty string as an argument:

```
scala> Main.main(Array(""))
java.lang.NumberFormatException: For input string: ""
```

Passing an argument that is something other than an Integer:

```
scala> Main.main(Array("f"))
java.lang.NumberFormatException: For input string: "f"
```

Passing an argument that isn't a valid Integer:

```
scala> Int.MaxValue
res1: Int = 2147483647

scala> Main.main(Array("2147483648"))
java.lang.NumberFormatException: For input string: "2147483648"
```

### References

1. <https://en.wikipedia.org/wiki/Eight_queens_puzzle>
2. <https://rosettacode.org/wiki/N-queens_problem#Scala>

[Java 8]: http://docs.oracle.com/javase/8/docs/api/
[sbt]: http://scala-sbt.org
[Scala]: http://scala-lang.org
