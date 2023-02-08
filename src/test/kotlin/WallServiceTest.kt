import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {

    @Test
    fun add() {
        val service = WallService
        val result = service.add(Post(3,4,"AthName", "cont", 25,13))
         assertNotEquals( 0, result.id)
    }

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun updateExisting() {
        val service = WallService
        service.add(Post(3,4,"AthName", "content", 20,10))
        val result = service.update(Post(3,4,"AthName", "cont", 25,13))
        assertTrue(result)
    }


    @Test
    fun updateNotExisting() {
        val service = WallService

        val result = service.update(Post(3,4,"AthName", "cont", 25,13))
        assertFalse(result)
    }
}