package com.example.demo.service

import com.example.demo.data.Task
import com.example.demo.data.model.TaskCreateRequest
import com.example.demo.data.model.TaskFetchRequest
import com.example.demo.data.model.TaskUpdateRequest
import com.example.demo.exception.TaskNotFoundException
import com.example.demo.repository.TaskRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TaskService(private val repository: TaskRepository) {

    // Java way
    private fun mappingEntityToDTO(task: Task): TaskFetchRequest {
        return TaskFetchRequest(
            task.id,
            task.description,
            task.done,
            task.createdAt,
            task.updatedAt,
            task.version,
        )
    }

    // Java way
    private fun assignValuesToEntity(task: Task, request: TaskCreateRequest) {
        task.description = request.description
        task.done = request.done
    }

    fun getAllTasks(): List<TaskFetchRequest> = repository.findAll().map (Task::toResponse)

    fun getAllOpenTasks(): List<TaskFetchRequest> = repository.getAllOpenTasks().map (Task::toResponse)

    fun getAllClosedTasks(): List<TaskFetchRequest> = repository.getAllClosedTasks().map (Task::toResponse)

    fun saveTask(taskRequest: TaskCreateRequest): TaskFetchRequest? =
        repository.save(taskRequest.toModel())
            .toResponse()

    fun updateTask(id: Long, taskRequest: TaskUpdateRequest): TaskFetchRequest?  {
        val task = repository.findByIdOrNull(id) ?: throw TaskNotFoundException("the requested task with id $id not found")
        val updatedTask = taskRequest.toModel(task)
        repository.updateTaskById(updatedTask.description, updatedTask.done,  updatedTask.id, updatedTask.version)
        return  repository.findTaskById(id).toResponse()
    }
    fun findTaskById(taskId: Long): TaskFetchRequest? =
        repository.findByIdOrNull(taskId)
            ?.toResponse()
            ?: throw TaskNotFoundException("the requested task with id $taskId was not found")

    fun deleteTaskById(taskId: Long) = repository.deleteById(taskId)

}

fun Task.toResponse(): TaskFetchRequest = TaskFetchRequest(
    id = this.id,
    description = this.description,
    done = this.done,
    createdAt = this.createdAt,
    updatedAt = this.updatedAt,
    version = this.version,
)

fun TaskCreateRequest.toModel(): Task {
    val task = Task()
    task.description = this.description
    task.done = false
    return task
}

fun TaskUpdateRequest.toModel(task: Task): Task {
    this.description?.let { task.description = it }
    this.done?.let { task.done = it }
    return task
}
