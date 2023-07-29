package tingeso.movimientos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.movimientos.entities.MovimientosEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimientosRepository extends JpaRepository<MovimientosEntity, Integer> {
    @Query("select m from movimientos m where m.fecha between :fechaInicio and :fechaFin order by m.fecha asc")
    List<MovimientosEntity> getEntreFecha(@Param("fechaInicio") LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin);
}
