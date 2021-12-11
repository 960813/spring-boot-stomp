package study.springbootstomp.domain.chat.interfaces

import org.springframework.http.MediaType
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.*
import study.springbootstomp.domain.chat.entity.ChatRoom
import study.springbootstomp.domain.chat.service.ChatRoomService


@RestController
class ChatRoomRestController(
    private val chatRoomService: ChatRoomService
) {

    @MessageMapping("/pub/chat/room/{roomId}")
    @SendTo("/sub/chat/room/{roomId}")
    fun message(@DestinationVariable roomId: String, dto: ChatDto): ChatDto {
        return dto
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

    @GetMapping(
        value = ["/api/v1/chat/room/{roomId}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun roomInfo(
        @PathVariable roomId: String
    ): ChatRoom {
        return chatRoomService.findById(roomId)
    }
}
