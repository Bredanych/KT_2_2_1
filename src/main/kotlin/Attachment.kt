interface Attachment { //Задача 2

    val type: String
}

data class AudioAttachment(val audio: Audio) : Attachment {
    override val type: String = "Audio"
}

data class Audio(
    val id: Int,
    val duration: Int,
    val artist: String
)

data class PhotoAttachment(val audio: Photo) : Attachment {
    override val type: String = "Photo"
}

data class Photo(
    val id: Int,
    val ownerId: Int,
    val photo_130: String,
    val photo_604: String,
    val description: String
)

data class VideoAttachment(val audio: Video) : Attachment {
    override val type: String = "Video"
}

data class Video(
    val id: Int,
    val ownerId: Int,
    val duration: Int,
    val title: String,
    val description: String
)


