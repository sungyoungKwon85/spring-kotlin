package com.kkwonsy.spring.kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@SpringBootApplication
class DemoApplication

@Controller
class FirstController {
    @RequestMapping
    @ResponseBody
    fun hello() = "hello world"
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
