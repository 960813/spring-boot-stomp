package study.springbootstomp.domain.chat.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import study.springbootstomp.domain.chat.ws.dto.ChatRoom
import study.springbootstomp.domain.chat.ws.handler.WebSocketHandler
import java.io.IOException

@Service
class ChatRoomService(
    private val objectMapper: ObjectMapper
) {

    private val chatRooms = LinkedHashMap<String, ChatRoom>()

    fun findAllRoom(): List<ChatRoom> {
        return chatRooms.values.toList()
    }

    fun findById(id: String): ChatRoom {
        return chatRooms[id]!!
    }

    fun createRoom(name: String): ChatRoom {
        return ChatRoom(
            id = name
        ).also {
            chatRooms[it.id] = it
        }
    }

    fun <T> sendMessage(session: WebSocketSession, message: T) {
        try {
            session.sendMessage(
                TextMessage(objectMapper.writeValueAsBytes(message))
            )
        } catch (e: IOException) {
            log.error(e.message, e)
        }
    }

    companion object {

        private val log: Logger = LoggerFactory.getLogger(WebSocketHandler::class.java)
    }
}
