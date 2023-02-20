import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {

    @Test
    fun add() {
        val service = WallService
        val result = service.add(Post(3,4,"AthName", "cont", 25,13, original = null))
         assertNotEquals( 0, result.id)
    }

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun updateExisting() {
        val service = WallService
        service.add(Post(3,4,"AthName", "content", 20,10, original = null))
        val result = service.update(Post(1,4,"AthName", "cont", 25,13, original = null))
        assertTrue(result)
    }


    @Test
    fun updateNotExisting() {
        val service = WallService

        val result = service.update(Post(3,4,"AthName", "cont", 25,13, original = null))
        assertFalse(result)
    }
}