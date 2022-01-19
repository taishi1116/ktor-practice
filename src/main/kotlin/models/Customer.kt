


package models
import kotlinx.serialization.Serializable

// 今回はデータベースなどを利用しない
val customerStorage = mutableListOf<Customer>()

// 顧客データクラス
// @Serializable APIレスポンスに必要なjson表現を自動的に生成してくれる
@Serializable
data class Customer(val id:String, val firstName: String, val lastName: String,val email: String)
