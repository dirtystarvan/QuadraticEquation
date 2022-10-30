import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.sign
import kotlin.math.sqrt

class SolveEq {
    private var a: Double by CheckArgumentsDelegate {
        if (it.isActuallyZero()) throw IllegalArgumentException(CheckArgumentsDelegate.aZeroErrorMessage)
    }
    private var b: Double by CheckArgumentsDelegate()
    private var c: Double by CheckArgumentsDelegate()
    var epsilon: Double by CheckArgumentsDelegate(argument = DEFAULT_ACCURACY)

    fun solve(a: Double = 0.0, b: Double, c: Double): List<Double> {
        checkArguments(a, b, c)

        val discriminant = searchD(a, b, c)

        return if (discriminant.sign < 0.0) {
            emptyList()
        } else {
            searchRoots(a, b, discriminant)
        }
    }

    private fun searchD(a: Double, b: Double, c: Double): Double {
        return b.pow(2) - 4 * a * c
    }

    private fun searchRoots(a: Double, b: Double, discriminant: Double): List<Double> {
        return if (discriminant.isActuallyZero()) {
            listOf(-b / 2 * a)
        } else {
            listOf((-b + sqrt(discriminant))/ 2 * a, (-b - sqrt(discriminant))/ 2 * a)
        }
    }

    private fun checkArguments(a: Double, b: Double, c: Double) {
        this.a = a
        this.b = b
        this.c = c
    }

    private fun Double.isActuallyZero(): Boolean {
        return this.absoluteValue < epsilon
    }

    companion object {
        private const val DEFAULT_ACCURACY = 2 * Double.MIN_VALUE
    }
}