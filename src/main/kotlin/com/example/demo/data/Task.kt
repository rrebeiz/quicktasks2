package com.example.demo.data

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

@Entity
@Table(name = "task")
class Task {
    @Id
    @GeneratedValue(generator = "task_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "task_sequence", sequenceName = "task_sequence", allocationSize = 1)
    val id: Long = 0

    @NotBlank
    @Column(name = "description", nullable = false, unique = true)
    var description: String = ""


    @Column(name = "done", nullable = false)
    var done: Boolean = false

    @Column(name = "created_at", columnDefinition = "timestamp(0) without time zone not null default now()")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_at", columnDefinition = "timestamp(0) without time zone not null default now()")
    var updatedAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "version", columnDefinition = "integer not null default 1")
    var version: Int = 1
}