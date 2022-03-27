package com.hyuuny.kotlinpractice.controller.page

import com.hyuuny.kotlinpractice.model.http.UserRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HomeController {

    // http://localhost:8080/main
    @GetMapping("/main")
    fun main(): String {
        println("init main")
        return "main.html"
    }

    @ResponseBody
    @GetMapping("/test")
    fun responseBody(): UserRequest {
        return UserRequest().apply {
            this.name = "hyuuny"
        }
//        return "main.html"
    }

}