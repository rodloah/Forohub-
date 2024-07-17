package med.forohub.api.domain.topic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import med.forohub.api.domain.usuario.Usuario;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Topico")
@Table(name = "topico")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String title;
    @Setter
    private String message;
    private LocalDateTime creation;
    @Enumerated(EnumType.STRING)
    private TopicoEstado estado;
    @JoinColumn(name = "author_id")
    @ManyToOne
    private Usuario author;
    private String course;

    public Topico(Usuario user, DatosTopico topic) {
        this.title = topic.title();
        this.message = topic.message();
        this.creation = LocalDateTime.now();
        this.estado = TopicoEstado.CREADO;
        this.author = user;
        this.course = topic.course();
    }

    public void delete() {
        this.estado = TopicoEstado.ELIMINADO;
    }

    public void update() {
        this.estado = TopicoEstado.MODIFICADO;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreation() {
        return creation;
    }

    public TopicoEstado getEstado() {
        return estado;
    }

    public Usuario getAuthor() {
        return author;
    }

    public String getCourse() {
        return course;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreation(LocalDateTime creation) {
        this.creation = creation;
    }

    public void setEstado(TopicoEstado estado) {
        this.estado = estado;
    }

    public void setAuthor(Usuario author) {
        this.author = author;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
