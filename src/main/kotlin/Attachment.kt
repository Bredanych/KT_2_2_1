interface Attachment {
    val type: String
    val id: Int
    val owner_id: Int
}

class Photo(
    override val type: String,
    override val id: Int,
    override val owner_id: Int,
    val album_id: Int,
    val text: String
) :
    Attachment {

}

class Video(
    override val type: String,
    override val id: Int,
    override val owner_id: Int,
    val title:String,
    val description:String,
    val duration: Int
) : Attachment {

}

class Audio(
    override val type: String,
    override val id: Int,
    override val owner_id: Int,
    val title:String,
    val description:String,
    val duration: Int
) : Attachment {

}

class Doc(
    override val type: String,
    override val id: Int,
    override val owner_id: Int,
    val title:String,
    val size:Int,
    val ext:String
) : Attachment {

}

class Graffiti(
    override val type: String,
    override val id: Int,
    override val owner_id: Int,
    val photo_130:String,
    val photo_604:String
) : Attachment {

}