package study.springbootstomp.domain.chat.ws.dto

import org.springframework.web.socket.WebSocketSession
import study.springbootstomp.domain.chat.service.ChatRoomService

data class ChatRoom(
    val id: String
) {

    private val sessions = mutableSetOf<WebSocketSession>()

    fun handleActions(session: WebSocketSession, chat: Chat, chatRoomService: ChatRoomService) {
        val message: String = if (chat.type == Chat.Type.ENTER) {
            sessions.add(session)
            "${chat.sender} 님이 입장했습니다."
        } else {
            chat.message
        }
        sendMessage(
            message = message,
            chatRoomService = chatRoomService
        )
    }

    private fun <T> sendMessage(message: T, chatRoomService: ChatRoomService) {
        sessions.parallelStream().forEach { session ->
            chatRoomService.sendMessage(session, message)
        }
    }
}
