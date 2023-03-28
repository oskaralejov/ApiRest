package com.example.apirest.Controller;


import com.example.apirest.Model.Task;
import com.example.apirest.Repository.TodoRepository;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController //controlador de tipo Rest
public class TodoController {

    //atributos
    private final Logger log= LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping(value="/")
    public String holaMundo(){
        return "Hola Mundooo!!!";
    }
    //CRUD sobre la entidad task
    //buscar todos las tareas

    /**
     *http://localhost:8087/tasks
     * @return
     */


    @GetMapping(value="/tasks")
    @ApiOperation("buscar todos las tareas")
    public List<Task> getTask(){
        return todoRepository.findAll();
    }

    //buscar tarea por su id

    /**
     *http://localhost:8087/tasks/{id}
     * @return
     */

    @GetMapping(value="tasks/{id}")
    @ApiOperation("buscar tareas por su id")
    public ResponseEntity<Task> findOneById(@PathVariable Long id) {

        Optional<Task> taskBYId = todoRepository.findById(id);

        //opcion 1
        if (taskBYId.isPresent()) {
            return ResponseEntity.ok(taskBYId.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //crear nueva tarea

    /**
     *http://localhost:8087/savetask
     * @return
     */

    @PostMapping(value = "/savetask")
    @ApiOperation("Crear nueva tarea")
    public String saveTask(@RequestBody Task task){

        todoRepository.save(task);
        return "save task";

    }
    //actualizar tarea existente en la base de datos
    /**
     *http://localhost:8087/updatetask/{id}
     * @return
     */


    @PutMapping(value="updatetask/{id}")
    @ApiOperation("Actualizar tarea existente en la base de datos")
    public String updateTask(@PathVariable Long id, @RequestBody Task task){
        Task updateTask = todoRepository.findById(id).get();
        updateTask.setTitle(task.getTitle());
        updateTask.setDescription(task.getDescription());
        updateTask.setEstado(task.getEstado());

        todoRepository.save(updateTask);

        return "update task";
    }

    //eliminar tarea por su id
    /**
     *http://localhost:8087/delete/{id}
     * @return
     */
    @ApiIgnore
    @DeleteMapping(value="delete/{id}")
    @ApiOperation("eliminar una tarea por su id")
        //public String deleteTask(@PathVariable Long id)
        public ResponseEntity<Task> deleteTask(@PathVariable Long id){
            if(!todoRepository.existsById(id)){

                log.warn("tryikng to update a non existent book");
                return ResponseEntity.notFound().build();
            }
            Task deleteTask=todoRepository.findById(id).get();
            todoRepository.delete(deleteTask);

            return ResponseEntity.notFound().build();
        }

    //eliminar todas las tareas
    /**
     *http://localhost:8087/deletetasks
     * @return
     */
    @ApiIgnore
    @DeleteMapping (value = "/deletetasks")
    @ApiOperation("eliminar una tarea por su id")
    public ResponseEntity<Task> deleteAll(){

        log.info("REST Request for delete all books");
        todoRepository.deleteAll();
        return ResponseEntity.noContent().build();

    }


    
}
