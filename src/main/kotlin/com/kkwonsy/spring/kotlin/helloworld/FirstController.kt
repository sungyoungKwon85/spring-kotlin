package com.kkwonsy.spring.kotlin.helloworld

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

class FirstController {
    @Controller
    class FirstController() {

        @Autowired
        lateinit var exampleService: ServiceInterface
        // lateinit을 선언하면 이 프로퍼티는 생성자 다음에 초기화 된다

        @RequestMapping(value = ["helloworld/{name}"], method = arrayOf(RequestMethod.GET))
        @ResponseBody
        fun hello(@PathVariable name: String) = exampleService.getHello(name)
    }
}