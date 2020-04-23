package com.kkwonsy.spring.kotlin

// Jackson이 내부적으로 사용하는 ObjectMapper보다 kotlin JVM에 의해 컴파일 되는 data class가 더 나은 메소드를 생성한다.
data class ComplexObject(var object1: SimpleObject? = null)