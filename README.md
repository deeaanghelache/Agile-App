# Agile App - _Java Web Programming Course_

## Project Description

**Agile App** is a web application for organizing software development projects. 
It is used for projects developed using Agile Methodologies, providing a user-friendly interface for organizing sprints and tasks.

Users can keep track of all their projects, tasks and sprints. 

## Database Structure

![](/ReadmePhotos/ERD.png)

### Database Explanation
Users have one or two roles (Admin, Scrum Master or User);

Users can be a part of the development team of one or more projects;

Users have one or more tasks assigned to them;

Each project has one or more tasks and developers assigned to it. Each task is a part of a Sprint.

## App's functionalities

Users can create accounts and login. 

Each type of user has access to different functionalities of the app. 

|  Endpoint / Type of user    | Regular User (The Software Developer) | Scrum Master     | Admin |
| :----:       |    :----:   |          :----: | :----: |
| **Get**      | <ul><li>See all their projects and project's details on Profile page </li> <li> See all tasks assigned to them </li>  <li>See all tasks that are a part of the current project and who is assigned to them</li> </ul>     | <ul><li>See all projects, tasks and sprints</li></ul>  | <ul><li>See all data stored in the database (users, projects, tasks, subtasks, sprints);</li></ul> |
| **Create**   | <ul><li>Create subtasks from the existing tasks;</li> <li>Assign other users or itself to any task.</li></ul>        |  <ul><li>Create new projects</li> <li>Create new tasks or subtasks</li> <li>Create new sprints</li> <li>Assign users to tasks</li> </ul>       |  <ul><li>Create subtasks from the existing tasks;</li> <li>Assign other users or itself to any task.</li> </ul>  |
| **Update**   |  <ul><li>Update subtasks;</li> <li>Update task's and subtask's assignee;</li> <li>Move tasks in between the _To Do_, _In Progress_, _Done_ and _Nice to Have_ columns.</li></ul>         |  <ul><li> Update tasks, subtasks, projects or sprints;</li> <li>Update task's and subtask's assignee;</li> <li>Move tasks in between the _To Do_, _In Progress_, _Done_ and _Nice to Have_ columns.</li></ul>       |  <ul><li>Update user information. </li></ul>  |
| **Delete**   |  <ul><li>Delete subtasks</li></ul>         |  <ul><li>Delete tasks, subtasks, projects or sprints;</li></ul>       |  <ul><li>Delete users, tasks, subtasks, projects or sprints;</li></ul>  |

## Backend 

### Entities


### DTOs

### Interfaces

### Services

### Controllers

#### REST Endpoints

##### Create

##### Read

##### Update

##### Delete

### Exception Handling

### Testing

#### Unit Testing

#### Integration Testing

#### Test Coverage

## Frontend

## Swagger

