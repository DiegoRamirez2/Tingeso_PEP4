package tingeso.salida.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.salida.entities.SalidaEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SalidaRepository extends JpaRepository<SalidaEntity, Integer> {
    @Query("select s from salida s where s.fecha between :fechaInicio and :fechaFin order by s.fecha asc")
    List<SalidaEntity> getEntreFecha(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);
}
