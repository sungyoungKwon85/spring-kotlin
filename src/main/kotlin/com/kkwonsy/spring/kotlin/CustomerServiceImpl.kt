package com.kkwonsy.spring.kotlin

import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomerServiceImpl: CustomerService {

    companion object {

        val initialCustomers = arrayOf(
                Customer(0, "Kotlin")
                , Customer(1, "Java")
                , Customer(2, "Python")
                , Customer(3, "Javascript")
                , Customer(4, "Ruby"))
    }

    // 서로 다른 request가 맵의 동일한 요소에 엑세스시 동기화 문제가 있으므로 ConcurrentHashMap을 사용
    private val customers = ConcurrentHashMap(initialCustomers.associateBy(Customer::id))

    override fun getCustomer(id: Int) = customers[id]

    override fun createCustomer(customer: Customer) {
        customers[customer.id] = customer
    }

    override fun deleteCustomer(id: Int) {
        customers.remove(id)
    }

    override fun updateCustomer(id: Int, customer: Customer) {
        customers.remove(id)
        customers[customer.id] = customer

    }

    override fun searchCustomers(nameFilter: String): List<Customer> =
            customers.filter { it.value.name.contains(nameFilter) }
                    .map { it.value }.toList()
}