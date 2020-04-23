package com.kkwonsy.spring.kotlin.helloworld

import org.springframework.beans.factory.annotation.Value

//@Service
class ExampleService : ServiceInterface {

    @Value(value = "\${service.message.text:ola}")
    private lateinit var text: String

    @Value(value = "#{4+3}")
    private lateinit var result1: Number

    @Value(value = "#{ \${one.value} div \${another.value} }")
    private lateinit var result2: Number

    override fun getHello(name: String) = "$text $name $result1 $result2"
}