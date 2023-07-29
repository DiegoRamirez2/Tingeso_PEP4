package tingeso.salida.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "problema")
public class ProblemaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_problema", unique = true, nullable = false)
    private Integer idProblema;
    private String problema;
    private String codigo;
    private String respuesta;
    // Basico = 0, Intermedio = 1, Avanzado = 2
    private Integer dificultad;
}
