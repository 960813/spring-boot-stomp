package study.springbootstomp.domain.chat.interfaces

import study.springbootstomp.domain.chat.ws.dto.ChatRoom

data class ChatRoomDto(
    val id: String
)

internal fun ChatRoom.toDto() = ChatRoomDto(
    id = id
)
