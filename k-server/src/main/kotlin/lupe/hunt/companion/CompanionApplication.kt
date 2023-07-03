package lupe.hunt.companion

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CompanionApplication

fun main(args: Array<String>) {
	runApplication<CompanionApplication>(*args)
}
