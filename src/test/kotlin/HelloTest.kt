import kotlin.test.Test
import kotlin.test.assertEquals

class HelloTest {
    @Test
    fun helloTest() {
        assertEquals(1, Hello.printHello())
    }
}