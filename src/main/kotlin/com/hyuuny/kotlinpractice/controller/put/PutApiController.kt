package com.hyuuny.kotlinpractice.controller.put

import com.hyuuny.kotlinpractice.model.http.Result
import com.hyuuny.kotlinpractice.model.http.UserRequest
import com.hyuuny.kotlinpractice.model.http.UserResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping("/put-mappping")
    fun putMapping(): String {
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], value = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping - put method"
    }

    @PutMapping(path = ["/put-mapping/object"])
    fun putMethodObject(@RequestBody userRequest: UserRequest): UserResponse {
        // 0. Response
        return UserResponse().apply {
            // 1. result
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }
        }.apply {
            // 2. description
            this.description = "~~~~~~~~~~"
        }.apply {
            // 3. user mutable list
            val userList = mutableListOf<UserRequest>()
            userList.add(userRequest)
            userList.add(UserRequest().apply {
                this.name = "a"
                this.age = 10
                this.email = "a@gamil.com"
                this.address = "a address"
                this.phoneNumber = "010-1234-1234"
            })
            userList.add(UserRequest().apply {
                this.name = "b"
                this.age = 20
                this.email = "b@gamil.com"
                this.address = "b address"
                this.phoneNumber = "010-4567-4567"
            })
            this.userRequest = userList
        }
    }

}
