package com.example.demo.data.model

import jakarta.validation.constraints.NotBlank

data class TaskCreateRequest(
    @NotBlank(message = "description cannot be blank")
    val description: String,
    val done: Boolean,
    )
