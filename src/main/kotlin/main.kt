import kotlin.math.pow

fun main() {
    println("Here is: ${0.001.pow(2) - 4 * 0.00001 * -0.00001}")

    val solver = SolveEq()
    solver.epsilon = 0.00001

    println(solver.solve(0.00001, 0.001, -0.00001))
}