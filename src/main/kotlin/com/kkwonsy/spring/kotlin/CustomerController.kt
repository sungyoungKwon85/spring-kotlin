package com.kkwonsy.spring.kotlin

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController(val customerService: CustomerService) {

//    @Autowired
//    lateinit var customers: ConcurrentHashMap<Int, Customer>

    @GetMapping(value = ["/customer/{id}"])
    fun getCustomer(@PathVariable id: Int): ResponseEntity<Customer> {
        val customer = customerService.getCustomer(id)
        val status = if (customer == null) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(customer, status)
    }

    @GetMapping(value = ["/customers"])
    fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
            customerService.searchCustomers(nameFilter)

    @RequestMapping(value = ["/customer/"], method = [RequestMethod.POST])
    fun createCustomer(@RequestBody customer: Customer): ResponseEntity<Unit> {
        customerService.createCustomer(customer)
        return ResponseEntity(Unit, HttpStatus.CREATED)
//        return ResponseEntity(null, HttpStatus.CREATED) // `````````````````````````````````````````````````````````````````````````````````````````````````````필요하면 null로 반환하던가
    }

    @RequestMapping(value = arrayOf("/customer/"), method = arrayOf(RequestMethod.DELETE))
    fun deleteCustomer(@PathVariable id: Int): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (customerService.getCustomer(id) != null) {
            customerService.deleteCustomer(id)
            status = HttpStatus.OK
        }
        return ResponseEntity(Unit, status)
    }

    @PutMapping(value = ["/customer/{id}"])
    fun updateCustomer(@PathVariable id: Int, @RequestBody customer: Customer): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if (customerService.getCustomer(id) != null) {
            customerService.updateCustomer(id, customer)
            status = HttpStatus.ACCEPTED
        }
        return ResponseEntity(Unit, status)
    }
}