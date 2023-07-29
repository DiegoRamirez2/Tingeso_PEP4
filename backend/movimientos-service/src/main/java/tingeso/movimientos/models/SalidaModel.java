package tingeso.movimientos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalidaModel {

    private Integer idSalida;
    private LocalDate fecha;
    private String tipo;
    private String numDoc;
    private String motivo;
    private float salida;
}
