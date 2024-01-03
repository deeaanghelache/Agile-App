# Agile App - _Java Web Programming Course_

## TECHNOLOGIES
- **Backend:** Java, Spring Boot, Lombok
- **Database:** MySQL
- **Frontend:** Angular 
- **Tests:** JUnit, Mockito

## PROJECT DESCRIPTION

**Agile App** is a web application for organizing software development projects. 
It is used for projects developed using Agile Methodologies, providing a user-friendly interface for organizing sprints and tasks.

Users can keep track of all their projects, tasks and sprints. 

## DATABASE STRUCTURE

![](/ReadmePhotos/ERD.png)

### DATABASE EXPLANATION
Users have one or two roles (Admin, Scrum Master or User);

Users can be a part of the development team of one or more projects;

Users have one or more tasks assigned to them;

Each project has one or more tasks and developers assigned to it. Each task is a part of a Sprint.

## REST ENDPOINTS - BUSINESS REQUIREMENTS

Users can create accounts and login. 

Each type of user has access to different functionalities of the app. 

|  Endpoint / Type of user    | Regular User (The Software Developer) | Scrum Master     | Admin |
| :----:       |    :----:   |          :----: | :----: |
| **Get**      | <ul><li>See all their projects and project's details on Profile page </li> <li> See all tasks assigned to them </li>  <li>See all tasks that are a part of the current project and who is assigned to them</li> </ul>     | <ul><li>See all projects, tasks and sprints</li></ul>  | <ul><li>See all data stored in the database (users, projects, tasks, subtasks, sprints);</li></ul> |
| **Create**   | <ul><li>Create subtasks from the existing tasks;</li> <li>Assign other users or itself to any task.</li></ul>        |  <ul><li>Create new projects</li> <li>Create new tasks or subtasks</li> <li>Create new sprints</li> <li>Assign users to tasks</li> </ul>       |  <ul><li>Create subtasks from the existing tasks;</li> <li>Assign other users or itself to any task.</li> </ul>  |
| **Update**   |  <ul><li>Update subtasks;</li> <li>Update task's and subtask's assignee;</li> <li>Move tasks in between the _To Do_, _In Progress_, _Done_ and _Nice to Have_ columns.</li></ul>         |  <ul><li> Update tasks, subtasks, projects or sprints;</li> <li>Update task's and subtask's assignee;</li> <li>Move tasks in between the _To Do_, _In Progress_, _Done_ and _Nice to Have_ columns.</li></ul>       |  <ul><li>Update user information. </li></ul>  |
| **Delete**   |  <ul><li>Delete subtasks</li></ul>         |  <ul><li>Delete tasks, subtasks, projects or sprints;</li></ul>       |  <ul><li>Delete users, tasks, subtasks, projects or sprints;</li></ul>  |

### DETAILED BUSINESS REQUIREMENTS

#### CREATE

1. Add new user (Create account / Register)
2. Create new projects
3. Create new tasks 
4. Create new subtasks
5. Create new sprints
6. Assign users to tasks
7. Assign users to subtasks

#### READ

1. See all user projects and project's details on Profile page
2. See all tasks assigned to the current user
3. See all tasks that are a part of the current project and who is assigned to them
4. See all user information (for admins only)

#### UPDATE

1. Update subtasks information
2. Update tasks information
3. Update project information
4. Update sprint information
5. Update task's and subtask's assignee
6. Move tasks in between the **To Do**, **In Progress**, **Done** and **Nice to Have columns**
7. Update user information

#### DELETE

1. Delete subtasks
2. Delete tasks
3. Delete projects
4. Delete sprints
5. Delete users


## Backend 

### Entities

There are 8 entities (*User, UserRole, Role, Project, UserProject, Task, Subtask, Sprint*). 

Each entity is annotated with **@Entity** and other Lombok annotations (used mainly for writing less and cleaner code, but providing the constructors, getters, setters and toString, hashcode and equals functions) 

Each entity has a unique identifier, generated automatically by Spring when the object is saved into the database. All ids are UUID format.



Two entities (*UserRole* and *UserProject*) have embedded ids, as these tables are the result of Many-to-Many relationships, therefore needing a composed primary key (the two primary keys of the main entities).



Each embedded id is a separated class, which can be found in the embeddedIds package.



### REPOSITORIES
Each java class has a corresponding Interface class which extends JpaRepository.

- ProjectRepository
- RoleRepository
- SprintRepository
- SubtaskRepository
- TaskAssignedRepository
- UserRepository
- UserProjectRepository
- UserRoleRepository

### SERVICES
Each java class has a corresponding Service class. 

- ProjectService
- RoleService
- SprintService
- SubtaskService
- TaskAssignedService
- UserService
- UserProjectService
- UserRoleService

### Controllers

Each java class has a corresponding Controller class. 

- ProjectController ( */project* )
- RoleController ( */role* )
- SprintController ( */sprint* )
- SubtaskController ( */subtask* )
- TaskAssignedController ( */task* )
- UserController ( */user* )
- UserProjectController ( */userProject* )
- UserRoleController ( */userRole* )

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

