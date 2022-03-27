package com.hyuuny.kotlinpractice.controller.get

import com.hyuuny.kotlinpractice.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class GetApiController {

    @GetMapping("/hello")
    fun hello(): String {
        return "hello world"
    }

    @RequestMapping(method = [RequestMethod.GET], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    @GetMapping("/get-mapping/path-variable/{name}/{age}")
    fun pathVariable(@PathVariable name: String, @PathVariable age: Int): String {
        println("$name, $age")
        return "$name $age"
    }


    @GetMapping("/get-mapping/path-variable2/{name}/{age}")
    fun pathVariable2(
        @PathVariable(value = "name") _name: String,
        @PathVariable(name = "age") age: Int
    ): String {
        val name = "kotlin"
        println("$_name, $age")
        return "$_name $age"
    }

    // http://localhost:8080/api/page?key=value&key=value
    @GetMapping("/get-mapping/query-param") // name=hyuuny&age=30
    fun queryParam(@RequestParam name: String, @RequestParam age: Int): String {
        println("$name, $age")
        return "$name $age"
    }

    // name, age, address, email
    @GetMapping("/get-mapping/query-param/object")
    fun queryObject(userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }

    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map: Map<String, Any>): Map<String, Any> {
        println(map)
        val phoneNumber = map["phone-number"]
        println(phoneNumber)
        return map
    }

}