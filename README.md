# QuickTasks
## Welcome to QuickTasks

### About QuickTasks
QuickTasks is a fullstack app which allows you to create and manage your own tasks. <br>
This is a rewrite of the app this time using Kotlin, which will allow us to develop the app for web, desktop & mobile. 

### Setup
For the easiest and quickest setup, please install Docker then run `docker-compose up -d`. <br>
This will start 3 containers, a frontend running Vue, a backend running Go and a database container running postgres. The database schema will be automatically migrated. and the app will be up and ready to go on http://localhost:8080 this can be changed inside the docker-compose.yml


## GET
`/api/v1/tasks` returns all tasks. <br>
`/api/v1/tasks/:id` returns a single task. <br>

## POST
`/api/v1/tasks` Creates a new task. <br>

## PATCH
`/api/v1/tasks/:id` Updates a task. <br>

## DELETE
`/api/v1/tasks/:id` Deletes a task. <br>


## Endpoints
### Show Tasks
Returns json data about all tasks.
* URL: `/v1/tasks`
* Method: GET
* URL Params: None
* Body Params: None
* Success Response:
    * Code: 200
    * Content: {"tasks":[{"id": 1, "title":"test", "description":"test"...}]}
* Error Response:
    * Code: 500
    * Content: {"error": "internal server error"}

### Show Task
Returns json data about a single task.
* URL: `/v1/tasks/:id`
* Method: GET
* URL Params:
    * Required: id=[int]
* Success Response:
    * Code: 200
    * Content: {"tasks":[{"id": 1, "title":"test", "description":"test"...}]}
* Error Response:
    * Code: 404
    * Content: {"error":"the requested resource could not be found"}
    * Code: 500
    * Content: {"error": "internal server error"}

### Create Task
Creates a new Task.
* URL: `/v1/tasks`
* Method: POST
* URL Params: None
* Body Params:
    * Required:
        * `{"title": "test", "description":"test"}`
* Success Response:
    * Code: 200
    * Content: {"task":{"id":1, "title":"test", "description":"test"...}}
* Error Response:
    * Code: 422
    * Content: {"error": {"title":"should not be empty","description":"should not be empty"}}
    * Code: 500
    * Content: {"error": "internal server error"}

### Update Task
Updates a task.
* URL: `/v1/tasks/:id`
* Method: PATCH
* URL Params:
    * Required: id=[int]
* Body Params:
    * Optional:
        * `{"title": "test", "description":"test@test.com", "completed":true}`
* Success Response:
    * Code: 200
    * Content: {"tasks":[{"id": 1, "title":"test", "description":"test"...}]}
* Error Response:
    * Code: 404
    * Content: {"error":"the requested resource could not be found"}
    * Code: 422
    * Content: {"error": {"title":"should not be empty","description":"should not be empty"}}
    * Code: 500
    * Content: {"error": "internal server error"}

### Delete Task
Deletes a task.
* URL: `/v1/tasks/:id`
* Method: DELETE
* URL Params:
    * Required: id=[int]
* Body Params: None
* Success Response:
    * Code: 200
    * Content: {"message":"task with id: 3 has been deleted"}
* Error Response:
    * Code: 404
    * Content: {"error":"the requested resource could not be found"}
    * Code: 500
    * Content: {"error": "internal server error"}