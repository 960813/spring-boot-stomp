package study.springbootstomp.domain.chat.repository

import org.springframework.stereotype.Repository
import study.springbootstomp.domain.chat.entity.ChatRoom

@Repository
class ChatRoomRepository {

    private val chatRooms = LinkedHashMap<String, ChatRoom>()

    fun findAllRoom(): List<ChatRoom> {
        return chatRooms.values.toList()
    }

    fun findById(id: String): ChatRoom {
        return chatRooms[id]!!
    }

    fun createRoom(name: String): ChatRoom {
        return ChatRoom(
            name = name
        ).also {
            chatRooms[it.name] = it
        }
    }
}
