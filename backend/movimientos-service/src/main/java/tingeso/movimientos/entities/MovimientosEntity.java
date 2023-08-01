package tingeso.movimientos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "movimientos")
public class MovimientosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMovimiento;
    private LocalDate fecha;
    private String tipoDoc;
    private String numDoc;
    private String motivo;
    private float ingreso;
    private float salida;
    private float saldo;
}
