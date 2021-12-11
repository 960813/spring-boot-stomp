package study.springbootstomp.domain.chat.interfaces

import org.springframework.http.MediaType
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.web.bind.annotation.*
import study.springbootstomp.domain.chat.entity.ChatRoom
import study.springbootstomp.domain.chat.service.ChatRoomService
import study.springbootstomp.domain.chat.ws.dto.Chat


@RestController
class ChatRoomRestController(
    private val sendingOperations: SimpMessageSendingOperations,
    private val chatRoomService: ChatRoomService
) {

    @MessageMapping("/chat/message")
    fun message(chat: Chat) {
        val message: String = if (chat.type == Chat.Type.ENTER) {
            "${chat.sender} 님이 입장했습니다."
        } else {
            chat.message
        }
        sendingOperations.convertAndSend("/sub/chat/room/${chat.roomId}", message)
    }

    @PostMapping(
        value = ["/api/v1/chat/room"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun createRoom(@RequestParam name: String): ChatRoomDto {
        return chatRoomService.createRoom(name).toDto()
    }

    @GetMapping(
        value = ["/api/v1/chat/room"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun findAllRoom(): List<ChatRoomDto> {
        return chatRoomService.findAllRoom().map {
            it.toDto()
        }
    }

    @GetMapping("/room/{roomId}")
    fun roomInfo(
        @PathVariable roomId: String
    ): ChatRoom {
        return chatRoomService.findById(roomId)
    }
}
