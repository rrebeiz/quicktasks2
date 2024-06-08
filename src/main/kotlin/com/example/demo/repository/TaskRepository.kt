package com.example.demo.repository

import com.example.demo.data.Task
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository: JpaRepository<Task, Long> {

    fun findTaskById(id: Long): Task

    @Query(value = "SELECT * from task where done = TRUE", nativeQuery = true)
    fun getAllOpenTasks(): List<Task>

    @Query(value = "SELECT * from task where done = FALSE", nativeQuery = true)
    fun getAllClosedTasks(): List<Task>

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE task set description = :description, done = :done, version = version + 1 where id = :id and version = :version", nativeQuery = true)
    fun updateTaskById(
        @Param(value = "description") description: String,
        @Param(value = "done") done: Boolean,
        @Param(value = "id") id: Long,
        @Param(value = "version") version: Int
    )
}