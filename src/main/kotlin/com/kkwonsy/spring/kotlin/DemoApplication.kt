package com.kkwonsy.spring.kotlin

import com.kkwonsy.spring.kotlin.helloworld.AdvanceService
import com.kkwonsy.spring.kotlin.helloworld.ExampleService
import com.kkwonsy.spring.kotlin.helloworld.ServiceInterface
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
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class DemoApplication {

//    companion object {
//        val initialCustomers = arrayOf(
//                Customer(0, "Kotlin")
//                , Customer(1, "Java")
//                , Customer(2, "Python")
//                , Customer(3, "Javascript")
//                , Customer(4, "Ruby"))
//    }
//
//    // 서로 다른 request가 맵의 동일한 요소에 엑세스시 동기화 문제가 있으므로 ConcurrentHashMap을 사용
//    @Bean
//    fun customers() = ConcurrentHashMap(initialCustomers.associateBy(Customer::id))

    @Bean
    @ConditionalOnExpression("#{'\${service.message.type}' == 'simple'}")
    fun exampleService(): ServiceInterface = ExampleService()

    @Bean
    @ConditionalOnExpression("#{'\${service.message.type}' == 'advance'}")
    fun advancedService(): ServiceInterface = AdvanceService()
}

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}
