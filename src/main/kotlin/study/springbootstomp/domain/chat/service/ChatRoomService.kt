package study.springbootstomp.domain.chat.service

import org.springframework.stereotype.Service
import study.springbootstomp.domain.chat.entity.ChatRoom
import study.springbootstomp.domain.chat.repository.ChatRoomRepository

@Service
class ChatRoomService(
    private val chatRoomRepository: ChatRoomRepository
) {

    fun findAllRoom(): List<ChatRoom> {
        return chatRoomRepository.findAllRoom()
    }

    fun findById(id: String): ChatRoom {
        return chatRoomRepository.findById(id)
    }

    fun createRoom(name: String): ChatRoom {
        return chatRoomRepository.createRoom(name)
    }
}
