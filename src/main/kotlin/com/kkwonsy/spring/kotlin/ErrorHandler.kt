package com.kkwonsy.spring.kotlin

import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest


// 하지만 이런 컨트롤러 어드바이스는 GO-TO 명령과 동등한 수준으로 사용할 수 있어
// 흐름을 통제할 수 없게 되고, 메인 로직 바깥에서 어떤 일이 일어나게 만든다
// 특별한 상황을 처리해야 하는 경우만 사용돼야 한다
// 가령 Controller에서 로직으로 풀 수 있지
// if (customer == null) ResponseEntity(ErrorResponse(....), HttpStatus.NOT_FOUND)
@ControllerAdvice
class ErrorHandler {
    @ExceptionHandler(JsonParseException::class)
    fun JsonParseExceptionHandler(servletRequest: HttpServletRequest, exception: Exception)
            : ResponseEntity<ErrorResponse> {
        return ResponseEntity(
                ErrorResponse("JSON Error", exception.message ?: "invalid json")
                , HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(CustomerNotFoundException::class)
    fun CustomerNotFoundException(servletRequest: HttpServletRequest, exception: Exception)
            : ResponseEntity<ErrorResponse> {
        return ResponseEntity(
                ErrorResponse("Customer Not Found", exception.message ?: "invalid json")
                , HttpStatus.NOT_FOUND)
    }
}