package route

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import models.Customer
import models.customerStorage




// ルーディングの登録
fun Application.registerCustomerRoutes(){
    routing {
        customerRouting()
    }
}


fun Route.customerRouting(){
    route("/customer"){
        get {
            if(customerStorage.isNotEmpty()){
                call.respond(customerStorage)
            } else {
                call.respondText("no customers found", status = HttpStatusCode.NotFound)
            }
        }
        get("{id}"){
            // エルビス演算子 {左辺} ?: {右辺}　左辺が正の時は左辺をリターン。異なる場合は右をリターン
            val id = call.parameters["id"] ?: return@get call.respondText("パスパラメータの参照エラー", status = HttpStatusCode.BadRequest)
            val customer = customerStorage.find {it.id === id} ?: return@get  call.respondText("顧客ストレージから同じIdを取得不可", status = HttpStatusCode.NotFound)

            call.respond(customer)
        }
        post {
            val customer = call.receive<Customer>()
            customerStorage.add(customer)
        }
        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.NotFound)
            if(customerStorage.removeIf {it.id === id} ){
                call.respondText("合致した顧客リストを削除しました", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("見つかりませんでした", status = HttpStatusCode.NotFound)
            }
        }
    }
}



