package tingeso.entrada.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "entrada")
public class EntradaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEntrada;
    private LocalDate fecha;
    private String tipo;
    private String numDoc;
    private String motivo;
    private float ingreso;
}
