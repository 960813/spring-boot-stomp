package study.springbootstomp.domain.chat.entity

data class ChatRoom(
    val name: String
) {
    val roomId: String
        get() = name
}
