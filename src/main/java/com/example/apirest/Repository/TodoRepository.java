package com.example.apirest.Repository;

import com.example.apirest.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Task, Long> {



}
