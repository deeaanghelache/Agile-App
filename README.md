# Agile App - _Java Web Programming Course_

## TECHNOLOGIES
- **Backend:** Java, Spring Boot, Lombok
- **Database:** MySQL
- **Frontend:** Angular 
- **Tests:** JUnit, Mockito

## PROJECT REQUIRMENTS

I. Define a system of your choice.
- [ ] [Jump To Section](#rest-endpoints---business-requirements) Define 10 business requirements for the chosen business domain
- [x] [Jump To Section](#detailed-business-requirements) Prepare a document based on the 10 business requirements containing a description of 5 main features your project should contain for the MVP (minimum viable product) phase.
- [x] [Jump To Section](#) Create a repository for your project and commit the document for review.

II. The project should consist of a Spring Boot Application containing:
- [x] [Jump To Section](#rest-endpoints) REST endpoints for all the features defined for the MVP. You should define at least 5 endpoints.
- [x] [Jump To Section](#services) Beans for defining services (implementing business logic). One service per feature.
- [x] [Jump To Section](#repositories) Beans for defining repositories One repository per entity.
- [x] [Jump To Section](#testing) Write unit tests for all REST endpoints and services and make sure all passed.
- [x] [Jump To Section](#database-structure) The data within the application should be persisted in a database. Define at least 6 entities that will be persisted in the database database, and at least 4 relations between them.
- [x] [Jump To Section](#entities) You should validate the POJO classes. You can use the existing validation constraints or create your own annotations if you need a custom constraint.
- [x] [Jump To Section](#swagger-documentation) Document the functionalities in the application such that anyone can use it after reading the document. Every API will be documented by Swagger. You can also export the visual documentation and add it to your main document presentation.
- [x] [Jump To Section](#frontend) The functionality of the application will be demonstrated using Postman or GUI (Thymeleaf,Angular, etc).

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

## Main features for minimum viable product phase (MVP)

1. User can create and account and the password should be encrypted when saved in the database. After registration, users can log in the app using the email they provided and the password. You can get details about a user based on the userId or a list of all the users in the database. Users can also be deleted by id.

2. Roles can be added in the database, by providing one of the role names: admin, user or scrum master. You can get information about roles and you can delete them by id.

3. Projects can be created, by providing the name and a short description. You also get information about a certain project, by providing the projectId. Projects cand be deleted by id.

4. A user can have multiple roles (max 3, because at any time in the database, there are only 3 roles). A user-role is created by providing the userId and the role name.

5. You can add users to projects and you can get a list of all the projects a given user is part of.

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

In the controllers, there are defined CRUD endpoints, as presented in the next sections. 

I used *@RequestBody* and *@PathVariable* to send information with the request.

The *@Parameter* annotation is only used for Swagger.

##### Create

```java
    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestBody @Parameter(description = "User data provided by the register form") User user) {
        return ResponseEntity.ok(userService.create(user));
    }
```

##### Read

```java
    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") @Parameter(description = "The uuid of the user you want to get information about") UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
```

##### Update

```java
    @PostMapping("/changePassword/{id}")
    public ResponseEntity<?> changePassword(@PathVariable("id") @Parameter(description = "The uuid of the user") UUID id, @RequestBody String password){
        userService.changePassword(id, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }
```

##### Delete

```java
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") @Parameter(description = "The uuid of the user") UUID id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
```

### Exception Handling

I have created custom exceptions, such as **UserNotFound** or **UserAlreadyExists**. 

```java
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User not found!");
    }
}
```

They are thrown in the services methods. For example, if the user with a given id is not found, the method will throw the UserNotFound exception.

```java 
    public void delete(UUID uuid) {
        Optional<User> user = userRepository.findById(uuid);
        if (user.isPresent()) {
            userRepository.deleteById(uuid);
        } else {
            throw new UserNotFoundException();
        }
    }
```

Then, they are caught by the GlobalExceptionHandler, which is annotated with *@ControllerAdvice*.

```java
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<?> handle(UserNotFoundException userNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFoundException.getMessage());
    }
```

### Testing

#### Unit Testing

I have tested the methods in the controllers and services. For this, I used JUnit and Mockito.

*Example Test Of a Controller Method*

```java
    @Test
    public void createProject() throws Exception {
        UUID uuid = UUID.randomUUID();
        ProjectRequest projectRequest = new ProjectRequest("Project1", "Description");
        Project project = new Project(uuid, "Project1", "Description");

        when(projectService.create(any())).thenReturn(project);

        mockMvc.perform(post("/project/createProject")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(projectRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value(projectRequest.getDescription()))
                .andExpect(jsonPath("$.name").value(projectRequest.getName()));
    }
```

*Example Test of a Service Method*

```java
    @Test
    void create() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("password123");

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());

        User savedUser = new User();
        savedUser.setUserId(UUID.randomUUID());
        savedUser.setFirstName(user.getFirstName());
        savedUser.setLastName(user.getLastName());
        savedUser.setEmail(user.getEmail());
        savedUser.setPassword("encodedPassword");
        when(userRepository.save(user)).thenReturn(savedUser);

        doNothing().when(userRoleService).addRoleForUser(user);

        User result = userService.create(user);

        assertNotNull(result);
        assertEquals(savedUser.getUserId(), result.getUserId());
        assertEquals(savedUser.getFirstName(), result.getFirstName());
        assertEquals(savedUser.getLastName(), result.getLastName());
        assertEquals(savedUser.getEmail(), result.getEmail());
        assertEquals(savedUser.getPassword(), result.getPassword());

        verify(userRepository).save(user);
        verify(bCryptPasswordEncoder).encode(any(CharSequence.class));
        verify(userRoleService).addRoleForUser(user);
    }
```


#### Test Coverage



## Frontend
For the frontend part I chose to use Angular. You can find the frontend files in the AppFrontend folder.

## Swagger Documentation

URL: http://localhost:8080/swagger-ui.html

![](/ReadmePhotos/Swagger.png)

To enable Swagger, I used the SpringDoc OpenApi dependency. 

Each endpoint from each controller is annotated with *@Operation* and *@ApiResponse*. Additionally, the method's parameters are annotated with *@Parameter*. 

```java
    @Operation(summary = "Get information about an user", description = "Get information about a certain user by providing their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User was found in the database"),
            @ApiResponse(responseCode = "404", description = "User was NOT found in the database")
    })
    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") @Parameter(description = "The uuid of the user you want to get information about") UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
```

