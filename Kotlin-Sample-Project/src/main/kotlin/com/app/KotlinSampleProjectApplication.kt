package com.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSampleProjectApplication

fun main(args: Array<String>) {
	runApplication<KotlinSampleProjectApplication>(*args)
}
