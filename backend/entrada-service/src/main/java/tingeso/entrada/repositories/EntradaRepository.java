package tingeso.entrada.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.entrada.entities.EntradaEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EntradaRepository extends JpaRepository<EntradaEntity, Integer> {
    @Query("select e from entrada e where e.fecha between :fechaInicio and :fechaFin order by e.fecha asc")
    List<EntradaEntity> getEntreFecha(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);
}
