package com.example.apirest.Model;



import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TasksCrud")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;
    @Column
    private Boolean estado;
    @Column
    private LocalDate fechaDeAsignacion;


    public Task(){}

    public Task(Long id, String title, String description, Boolean estado, LocalDate fechaDeAsignacion) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.estado=estado;
        this.fechaDeAsignacion=fechaDeAsignacion;
    }

    public Task(String title, String description, Boolean estado, LocalDate fechaDeAsignacion) {
        this.title = title;
        this.description = description;
        this.estado = estado;
        this.fechaDeAsignacion = fechaDeAsignacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFechaDeAsignacion() {
        return fechaDeAsignacion;
    }

    public void setFechaDeAsignacion(LocalDate fechaDeAsignacion) {
        this.fechaDeAsignacion = fechaDeAsignacion;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", estado=" + estado +
                ", fechaDeAsignacion=" + fechaDeAsignacion +
                '}';
    }
}
