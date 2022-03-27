package com.hyuuny.kotlinpractice.controller.exception

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.hyuuny.kotlinpractice.model.http.UserRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.util.LinkedMultiValueMap


@WebMvcTest
@AutoConfigureMockMvc
class ExceptionApiControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun helloTest() {
        mockMvc.perform(
            get("/api/exception/hello")
        ).andExpect(
            status().isOk
        ).andExpect(
            content().string("hello")
        ).andDo(print())
    }

    @Test
    fun getTest() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "hyuuny")
        queryParams.add("age", "30")

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect(
            status().isOk
        ).andExpect(
            content().string("hyuuny 30")
        ).andDo(print())
    }

    @Test
    fun getFailTest() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "hyuuny")
        queryParams.add("age", "5")

        mockMvc.perform(
            get("/api/exception").queryParams(queryParams)
        ).andExpect(
            status().isBadRequest
        ).andExpect(
            content().contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
            jsonPath("$.result_code").value("FAIL")
        ).andExpect(
            jsonPath("$.errors[0].field").value("age")
        ).andExpect(
            jsonPath("$.errors[0].value").value("5")
        ).andDo(print())
    }

    @Test
    fun postTest() {
        val userRequest = UserRequest().apply {
            this.name = "hyuuny"
            this.age = 30
            this.phoneNumber = "010-1324-1324"
            this.address = "경기도 광명시"
            this.email = "201876@naver.com"
            this.createdAt = "2022-03-27 23:15:00"
        }
        val json = jacksonObjectMapper().writeValueAsString(userRequest)

        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
            status().isOk
        ).andExpect(
            jsonPath("$.name").value(userRequest.name)
        ).andExpect(
            jsonPath("$.age").value(userRequest.age)
        ).andExpect(
            jsonPath("$.phoneNumber").value(userRequest.phoneNumber)
        ).andExpect(
            jsonPath("$.email").value(userRequest.email)
        ).andExpect(
            jsonPath("$.createdAt").value(userRequest.createdAt)
        ).andDo(print())
    }

    @Test
    fun postFailTest() {
        val userRequest = UserRequest().apply {
            this.name = "hyuuny"
            this.age = -1
            this.phoneNumber = "010-1324-1324"
            this.address = "경기도 광명시"
            this.email = "201876@naver.com"
            this.createdAt = "2022-03-27 23:15:00"
        }
        val json = jacksonObjectMapper().writeValueAsString(userRequest)

        mockMvc.perform(
            post("/api/exception")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
            status().isBadRequest
        ).andExpect(
            jsonPath("$.result_code").value("FAIL")
        ).andExpect(
            jsonPath("$.http_status").value(HttpStatus.BAD_REQUEST.value())
        ).andExpect(
            jsonPath("$.errors[0].field").value("age")
        ).andExpect(
            jsonPath("$.errors[0].value").value(userRequest.age)
        ).andDo(print())
    }

}