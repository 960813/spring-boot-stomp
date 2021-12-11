package study.springbootstomp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootStompApplication

fun main(args: Array<String>) {
	runApplication<SpringBootStompApplication>(*args)
}
