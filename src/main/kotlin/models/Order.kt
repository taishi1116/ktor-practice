package models

import kotlinx.serialization.Serializable



// 注文オーダーのインメモリ
val orderStorage: List<Order> = listOf(Order("2022-01-19-01", listOf(
    OrderItem("lil peep", 1000, 1.3),
    OrderItem("han solo",2000,5.5),
    OrderItem("han holo",15000,6.5),
    )),
    Order("2020-04-03-01", listOf(
        OrderItem("Cheeseburger", 1, 8.50),
        OrderItem("Water", 2, 1.50),
        OrderItem("Coke", 2, 1.76),
        OrderItem("Ice Cream", 1, 2.35)
    )))

@Serializable
data class Order(val number:String,val contents: List<OrderItem>)

@Serializable
data class OrderItem(val item:String,val amount: Int,val price: Double)