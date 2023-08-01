package tingeso.movimientos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntradaModel {

    private Integer idEntrada;
    private LocalDate fecha;
    private String tipoDoc;
    private String numDoc;
    private String motivo;
    private float ingreso;
}
