package com.example.plugins

import com.example.routes.customerRouting
import com.example.routes.getOrderRoute
import com.example.routes.listOfOrdersRoute
import com.example.routes.totalizeOrder
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        this.customerRouting()
        this.listOfOrdersRoute()
        this.getOrderRoute()
        this.totalizeOrder()
    }
}
