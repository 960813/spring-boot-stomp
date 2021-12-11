package study.springbootstomp.domain.chat.interfaces

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import study.springbootstomp.domain.chat.service.ChatRoomService

@RestController
class ChatRoomRestController(
    private val chatRoomService: ChatRoomService
) {

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
}
