import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class WallServiceTest {

    @Test
    fun add() {
        val service = WallService
        val result = service.add(Post(3,4,"AthName", "cont", 25,13, original = null, attachment = null,  comments = null))
         assertNotEquals( 0, result.id)
    }

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun updateExisting() {
        val service = WallService
        service.add(Post(3,4,"AthName", "content", 20,10, original = null, attachment = null,  comments = null))
        val result = service.update(Post(1,4,"AthName", "cont", 25,13, original = null, attachment = null,  comments = null))
        assertTrue(result)
    }


    @Test
    fun updateNotExisting() {
        val service = WallService

        val result = service.update(Post(3,4,"AthName", "cont", 25,13, original = null, attachment = null,  comments = null))
        assertFalse(result)
    }

    // Тесты для комментариев
    @Test
    fun addCommentToExisting(){
        val service = WallService
        service.add(Post(5,4,"AthName", "content", 20,10, original = null, attachment = null,  comments = null))
        val comment = Comment(1,1,
            1,"Abrakadabra", 4,0)
        val result = service.createComment(1, comment)
        assertEquals(comment,result)
    }

    @Test
    fun addCommentToNotExisting(){
        fun shouldThrow() {
            val service = WallService

            val comment = Comment(1,1,1,"Abrakadabra", 4,0)
            val result = service.createComment(50, comment)
        }

    }
}