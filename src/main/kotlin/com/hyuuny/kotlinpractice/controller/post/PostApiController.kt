package com.hyuuny.kotlinpractice.controller.post

import com.hyuuny.kotlinpractice.model.http.UserRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PostApiController {

    @PostMapping("/post-mapping")
    fun postMapping(): String {
        return "post-mapping"
    }

    @RequestMapping(method = [RequestMethod.POST], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    // object mapper
    // json -> object
    // object -> json
    @PostMapping("/post-mapping/object")
    fun postMappingObject(@RequestBody userRequest: UserRequest): UserRequest {
        // json -> object
        println(userRequest)

        // object -> json
        return userRequest
    }

}