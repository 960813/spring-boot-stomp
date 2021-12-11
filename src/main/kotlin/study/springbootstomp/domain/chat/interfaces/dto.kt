package study.springbootstomp.domain.chat.interfaces

import study.springbootstomp.domain.chat.entity.ChatRoom

data class ChatRoomDto(
    val id: String
)

internal fun ChatRoom.toDto() = ChatRoomDto(
    id = roomId
)
