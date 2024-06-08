package com.example.demo.data.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime

data class TaskFetchRequest(
    val id: Long,
    val description: String,
    val done: Boolean,
    @JsonIgnore
    val createdAt: LocalDateTime,
    @JsonIgnore
    val updatedAt: LocalDateTime,
    @JsonIgnore
    val version: Int
    )
