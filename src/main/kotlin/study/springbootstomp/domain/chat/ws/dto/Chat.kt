package study.springbootstomp.domain.chat.ws.dto

data class Chat(
    val type: Type,
    val roomId: String,
    val sender: String,
    var message: String
) {

    enum class Type {
        ENTER, COMMENT
    }
}
