import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.sign
import kotlin.math.sqrt

class SolveEq {
    fun solve(a: Double = 0.0, b: Double, c: Double): List<Double> {
        if (a.isActuallyZero()) {
            throw IllegalArgumentException("This is not a quadratic equation: 'a' cannot be zero.")
        }

        val discriminant = searchD(a, b, c)

        return if (discriminant.sign < 0.0) {
            emptyList()
        } else {
            getRoots(a, b, discriminant)
        }
    }

    private fun searchD(a: Double, b: Double, c: Double): Double {
        return b.pow(2) - 4 * a * c
    }

    private fun getRoots(a: Double, b: Double, discriminant: Double): List<Double> {
        return if (discriminant.isActuallyZero()) {
            listOf(-b / 2 * a)
        } else {
            listOf((-b + sqrt(discriminant))/ 2 * a, (-b - sqrt(discriminant))/ 2 * a)
        }
    }

    fun Double.isActuallyZero(): Boolean {
        return this.absoluteValue < 2 * Double.MIN_VALUE
    }
}