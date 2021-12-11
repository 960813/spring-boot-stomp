package study.springbootstomp.domain.chat.interfaces

import study.springbootstomp.domain.chat.entity.ChatRoom
import study.springbootstomp.utils.toEpochMillis
import java.time.LocalDateTime

data class ChatRoomDto(
    val id: String
)

data class ChatDto(
    val type: Type,
    val sender: String,
    val message: String
) {

    val createdAt: Long = LocalDateTime.now().toEpochMillis()

    enum class Type {
        ENTER, COMMENT
    }
}


internal fun ChatRoom.toDto() = ChatRoomDto(
    id = id
)
