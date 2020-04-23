package com.kkwonsy.spring.kotlin

import com.kkwonsy.spring.kotlin.Customer.*
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomerServiceImpl: CustomerService {

    companion object {

        val initialCustomers = arrayOf(
                Customer(0, "Kotlin")
                , Customer(1, "Java", Telephone("+44", "1234523232"))
                , Customer(2, "Python", Telephone("+82", "5645124544"))
                , Customer(3, "Javascript", Telephone("+81", "1212315222"))
                , Customer(4, "Ruby", Telephone("+48", "0191237489")))
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
            customers.filter { it.value.name.contains(nameFilter, true) }
                    .map { it.value }.toList()
}