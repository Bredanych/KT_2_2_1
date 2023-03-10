data class Post(
    val id: Int,
    val authorId: Int,
    val authorName: String,
    val content: String,
    val published: Long,
    val likes: Int,
    val comments: Comments = Comments(),
    val copyright: Copyright = Copyright()
)

object WallService {

    private var posts = emptyArray<Post>()


    private var postId: Int = 0

    fun clear() {
        posts = emptyArray()
        postId = 0;
    }

    fun add(post: Post): Post {

        posts += post.copy(id = ++postId)// Вот тут я испытываю когнитивный диссонанс - да мы конечно не делаем никаких проверок, но какой бы я не прислал готовый айди, он заменится на порядковый. отчего я чуть не облысел пока ковырял автотест!
        return posts.last()
    }

    fun likeById(id: Int) {
        for ((index, post) in posts.withIndex()) {
            if (post.id == id) {
                posts[index] = post.copy(likes = post.likes + 1)
            }
        }
    }

    fun update(post: Post): Boolean {
        for ((index, sPost) in posts.withIndex()) {
            if (sPost.id == post.id) {
                posts[index] = post.copy(
                    content = "updated comment",
                    likes = post.likes + 1,
                    published = post.published + 1
                ) //не понятно откуда берутся обновленные данные, но как пример нарисовал вот так...
                return true
            }
        }
        return false
    }
}

fun main() {
    val post = Post(1, 1, "author", "content", likes = 0, published = 0)
    val liked = post.copy(likes = post.likes + 1)
    val (id, authorId, _, content) = post
    println(liked)
    println("$id,$authorId, $content")
}

data class Comments(
    val count: Int = 0,
    val can_post: Int = 1,
    val groups_can_post: Boolean = true,
    val can_close: Boolean = true,
    val can_open: Boolean = true
)

data class Copyright(
    val id: Long = 0,
    val link: String? = null,
    val name: String? = null,
    val type: String? = null,
)