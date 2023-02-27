data class Post( //уже дата
    val id: Int,
    val authorId: Int,
    val authorName: String,
    val content: String,
    val published: Long,
    val likes: Int = 0,
    val comments: Comments = Comments(),
    val copyright: Copyright = Copyright(),
    val original: Post?, // Пример из лекции.
    val attachment: Array<Attachment>?
) {
    //Не совсем понял зачем мне предложили данный автокод. И собственно что случится если его не вставлять?
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (id != other.id) return false
        if (authorId != other.authorId) return false
        if (authorName != other.authorName) return false
        if (content != other.content) return false
        if (published != other.published) return false
        if (likes != other.likes) return false
        if (comments != other.comments) return false
        if (copyright != other.copyright) return false
        if (original != other.original) return false
        if (attachment != null) {
            if (other.attachment == null) return false
            if (!attachment.contentEquals(other.attachment)) return false
        } else if (other.attachment != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + authorId
        result = 31 * result + authorName.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + published.hashCode()
        result = 31 * result + likes
        result = 31 * result + comments.hashCode()
        result = 31 * result + copyright.hashCode()
        result = 31 * result + (original?.hashCode() ?: 0)
        result = 31 * result + (attachment?.contentHashCode() ?: 0)
        return result
    }
}


object WallService {

    private var posts = emptyArray<Post>() // и так в массиве.


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
    val post = Post(1, 1, "author", "content", 0, 0, original = null, attachment = null)
    val repost = Post(2, 1, "me", "repost", 0, 0, original = null, attachment = null)
    WallService.add(post)
    WallService.add(repost)
//    val liked = post.copy(likes = post.likes + 1)
//    val (id, authorId, _, content) = post
//    println(liked)
//    println("$id,$authorId, $content")

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