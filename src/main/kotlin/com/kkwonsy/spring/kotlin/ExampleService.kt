package com.kkwonsy.spring.kotlin

import org.springframework.stereotype.Service

@Service
class ExampleService {

    fun getHello(name: String) = "hello $name"
}