import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SolveEqTest {
    // Написать тест, который проверяет, что для уравнения x^2+1 = 0 корней нет (возвращается пустой массив)
    @Test
    fun `x^2+1 no roots test`() {
        assertTrue(SolveEq().solve(a = 1.0, b = 0.0, c = 1.0).isEmpty())
    }

    // Написать тест, который проверяет, что для уравнения x^2-1 = 0 есть два корня кратности 1 (x1=1, x2=-1)
    @Test
    fun `x^2-1 two roots multiplicity 1 test`() {
        val roots = SolveEq().solve(a = 1.0, b = 0.0, c = -1.0)

        assertTrue(roots.size == 2)
        assertTrue(roots.containsAll(listOf(1.0, -1.0)))
    }

    // Написать тест, который проверяет, что для уравнения x^2+2x+1 = 0 есть один корень кратности 2 (x1= x2 = -1)
    @Test
    fun `x^2+2x+1 = 0 one root multiplicity 2 test`() {
        val roots = SolveEq().solve(a = 1.0, b = 2.0, c = 1.0)

        assertTrue(roots.size == 1)
        assertTrue(roots.contains(-1.0))
    }

    // Написать тест, который проверяет, что коэффициент a не может быть равен 0. В этом случае solve выбрасывает исключение.
    @Test
    fun `'a' cannot be zero test`() {
        val exception = assertThrows<IllegalArgumentException> { SolveEq().solve(a = 0.0, b = 1.0, c = 1.0) }

        assertEquals(CheckArgumentsDelegate.aZeroErrorMessage, exception.message)
    }

    // С учетом того, что дискриминант тоже нельзя сравнивать с 0 через знак равенства,
    // подобрать такие коэффициенты квадратного уравнения для случая одного корня кратности два,
    // чтобы дискриминант был отличный от нуля, но меньше заданного эпсилон.
    @Test
    fun `nearly zero D test`() {
        //D = 1.0004E-6

        val solver = SolveEq()
        solver.epsilon = 0.00001

        assertTrue(solver.solve(0.00001, 0.001, -0.00001).size == 1)
    }

    // Посмотреть какие еще значения могут принимать числа типа double, кроме числовых и написать тест с их использованием на все коэффициенты.
    // solve должен выбрасывать исключение.
    @Test
    fun `NaN tests`() {
        var exception = assertThrows<IllegalArgumentException> { SolveEq().solve(a = Double.NaN, b = 1.0, c = 1.0) }
        assertEquals("[a] ${CheckArgumentsDelegate.nanErrorMessage}", exception.message)

        exception = assertThrows { SolveEq().solve(a = 1.0, b = Double.NaN, c = 1.0) }
        assertEquals("[b] ${CheckArgumentsDelegate.nanErrorMessage}", exception.message)

        exception = assertThrows { SolveEq().solve(a = 1.0, b = 1.0, c = Double.NaN) }
        assertEquals("[c] ${CheckArgumentsDelegate.nanErrorMessage}", exception.message)
    }

    @Test
    fun `Infinity tests`() {
        var exception = assertThrows<IllegalArgumentException> { SolveEq().solve(a = Double.POSITIVE_INFINITY, b = 1.0, c = 1.0) }
        assertEquals("[a] ${CheckArgumentsDelegate.nanErrorMessage}", exception.message)

        exception = assertThrows { SolveEq().solve(a = 1.0, b = Double.NEGATIVE_INFINITY, c = 1.0) }
        assertEquals("[b] ${CheckArgumentsDelegate.nanErrorMessage}", exception.message)

        exception = assertThrows { SolveEq().solve(a = 1.0, b = 1.0, c = Double.POSITIVE_INFINITY) }
        assertEquals("[c] ${CheckArgumentsDelegate.nanErrorMessage}", exception.message)
    }
}