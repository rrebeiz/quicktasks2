package com.example.demo.controller

import com.example.demo.data.model.TaskCreateRequest
import com.example.demo.data.model.TaskFetchRequest
import com.example.demo.data.model.TaskUpdateRequest
import com.example.demo.exception.InternalServerErrorException
import com.example.demo.service.TaskService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
class TaskController(private val taskService: TaskService) {
    @GetMapping("/tasks")
    fun getAllTasks(@RequestParam("status") status: String?): List<TaskFetchRequest> =
        when (status) {
            "open" -> taskService.getAllOpenTasks()
            "closed" -> taskService.getAllClosedTasks()
            else -> taskService.getAllTasks()
        }

    @PostMapping("/tasks")
    fun createTask(@RequestBody taskRequest: TaskCreateRequest): TaskFetchRequest =
        taskService.saveTask(taskRequest)
            ?: throw InternalServerErrorException("Internal Server Error")


    @GetMapping("/tasks/{id}")
    fun getTaskById(@PathVariable("id") taskId: Long): TaskFetchRequest =
        taskService.findTaskById(taskId)
            ?: throw InternalServerErrorException("Internal Server Error")

    @DeleteMapping("/tasks/{id}")
    fun deleteTaskById(@PathVariable("id") taskId: Long) {
        taskService.findTaskById(taskId)
            ?: throw InternalServerErrorException("Internal Server Error")

        taskService.deleteTaskById(taskId)
    }

    @PatchMapping("/tasks/{id}")
    fun updateTaskById(
        @PathVariable("id") taskId: Long,
        @RequestBody taskUpdateRequest: TaskUpdateRequest
    ): TaskFetchRequest =
        taskService.updateTask(taskId, taskUpdateRequest) ?: throw InternalServerErrorException("Internal Server Error")

}