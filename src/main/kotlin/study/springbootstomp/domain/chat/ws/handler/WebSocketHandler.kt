package study.springbootstomp.domain.chat.ws.handler

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import study.springbootstomp.domain.chat.service.ChatRoomService
import study.springbootstomp.domain.chat.ws.dto.Chat

@Component
class WebSocketHandler(
    private val chatRoomService: ChatRoomService,
    private val objectMapper: ObjectMapper
) : TextWebSocketHandler() {

    override fun handleMessage(session: WebSocketSession, message: WebSocketMessage<*>) {
        val payload = message.payload.toString()
        log.info("payload: $payload")

        val chat: Chat = objectMapper.readValue(payload, Chat::class.java)
        val chatRoom = chatRoomService.findById(chat.roomId)

        chatRoom.handleActions(session, chat, chatRoomService)
    }

    companion object {

        private val log: Logger = LoggerFactory.getLogger(WebSocketHandler::class.java)
    }
}
