import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertTrue

class SolveEqTest {
    //Написать тест, который проверяет, что для уравнения x^2+1 = 0 корней нет (возвращается пустой массив)
    @Test
    fun `x^2+1 no roots test`() {
        assertTrue(SolveEq().solve(a = 1.0, b = 0.0, c = 1.0).isEmpty())
    }

    //Написать тест, который проверяет, что для уравнения x^2-1 = 0 есть два корня кратности 1 (x1=1, x2=-1)
    @Test
    fun `x^2-1 two roots multiplicity 1 test`() {
        val roots = SolveEq().solve(a = 1.0, b = 0.0, c = -1.0)

        assertTrue(roots.size == 2)
        assertTrue(roots.containsAll(listOf(1.0, -1.0)))
    }

//    Написать тест, который проверяет, что для уравнения x^2+2x+1 = 0 есть один корень кратности 2 (x1= x2 = -1)
    @Test
    fun `x^2+2x+1 = 0 one root multiplicity 2 test`() {
        val roots = SolveEq().solve(a = 1.0, b = 2.0, c = 1.0)

        assertTrue(roots.size == 1)
        assertTrue(roots.contains(-1.0))
    }
//    Написать тест, который проверяет, что коэффициент a не может быть равен 0. В этом случае solve выбрасывает исключение.
//    Примечание. Учесть, что a имеет тип double и сравнивать с 0 через == нельзя.
    @Test
    fun `'a' cannot be zero test`() {
        assertThrows<IllegalArgumentException> { SolveEq().solve(a = 0.0, b = 0.0, c = 0.0) }
    }
//    С учетом того, что дискриминант тоже нельзя сравнивать с 0 через знак равенства,
//    подобрать такие коэффициенты квадратного уравнения для случая одного корня кратности два,
//    чтобы дискриминант был отличный от нуля, но меньше заданного эпсилон. Эти коэффициенты должны заменить коэффициенты в тесте из п. 7.
//    При необходимости поправить реализацию квадратного уравнения.

//    Посмотреть какие еще значения могут принимать числа типа double, кроме числовых и написать тест с их использованием на все коэффициенты. solve должен выбрасывать исключение.

}