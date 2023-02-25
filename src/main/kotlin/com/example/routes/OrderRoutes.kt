package com.example.routes

import com.example.models.orderStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.listOfOrdersRoute() {
    get("/order") {
        if (orderStorage.isNotEmpty()) {
            call.respond(orderStorage)
        }
    }
}

fun Route.getOrderRoute() {
    get("/order/{id?}") {
        val id = call.parameters["id"] ?: return@get call.respondText(
            "Order id was not provided",
            status = HttpStatusCode.BadRequest
        )

        val order = orderStorage.find { it.number == id } ?: return@get call.respondText(
            "Order not found",
            status = HttpStatusCode.NotFound
        )

        call.respond(order)
    }
}

fun Route.totalizeOrder() {
    get("/order/{id?}/total") {
        val id = call.parameters["id"] ?: return@get call.respondText(
            "Order id was not provided",
            status = HttpStatusCode.BadRequest
        )

        val order = orderStorage.find { it.number == id } ?: return@get call.respondText(
            "Order not found",
            status = HttpStatusCode.NotFound
        )

        val total = order.contents.sumOf { it.amount * it.price }
        call.respond(total)
    }
}