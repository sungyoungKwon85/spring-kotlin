package com.kkwonsy.spring.kotlin

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@SpringBootApplication
class DemoApplication {
    @Bean
    @ConditionalOnExpression("#{'\${service.message.type}' == 'simple'}")
    fun exampleService(): ServiceInterface = ExampleService()

    @Bean
    @ConditionalOnExpression("#{'\${service.message.type}' == 'advance'}")
    fun advancedService(): ServiceInterface = AdvanceService()
}


@Controller
class FirstController() {

    @Autowired
    lateinit var exampleService: ServiceInterface
    // lateinit을 선언하면 이 프로퍼티는 생성자 다음에 초기화 된다

    @RequestMapping(value = ["/user/{name}"], method = arrayOf(RequestMethod.GET))
    @ResponseBody
    fun hello(@PathVariable name: String) = exampleService.getHello(name)
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
