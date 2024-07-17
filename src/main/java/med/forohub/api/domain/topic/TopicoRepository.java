package med.forohub.api.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTitle(String title);

    boolean existsByMessage(String message);

    @Query("SELECT t FROM Topico t WHERE t.estado != 'ELIMINADO'")
    Page<Topico> findAll(Pageable page);

    @Query("SELECT t FROM Topico t WHERE t.estado != 'ELIMINADO' AND t.id=:id")
    Optional<Topico> findId(Long id);
}
