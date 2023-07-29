package tingeso.entrada.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tingeso.entrada.entities.ProblemaEntity;

import java.util.List;

@Repository
public interface ProblemaRepository extends JpaRepository<ProblemaEntity, Integer> {
    @Query("select p from ProblemaEntity p where p.dificultad = :dificultad")
    List<ProblemaEntity> findByDificultad(int dificultad);
    @Query(value = "select * from problema p where p.dificultad = :dificultad order by random() limit :cantidad", nativeQuery = true)
    List<ProblemaEntity> findRandomByDificultad(@Param("dificultad") int dificultad, @Param("cantidad") int cantidad);


}
