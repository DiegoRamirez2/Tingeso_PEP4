package tingeso.movimientos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tingeso.movimientos.entities.MovimientosEntity;
import tingeso.movimientos.models.MovimientoModel;
import tingeso.movimientos.models.SalidaModel;
import tingeso.movimientos.repositories.MovimientosRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MovimientosService {
    @Autowired
    private MovimientosRepository movimientosRepository;
    @Autowired
    RestTemplate restTemplate;

    public List<MovimientosEntity> listarMovimientos(){
        return movimientosRepository.findAll();
    }
    public MovimientosEntity obtenerMovimientoId(int id){
        return movimientosRepository.findById(id).orElse(null);
    }
    public MovimientosEntity createMovimiento(MovimientosEntity movimiento){
        return movimientosRepository.save(movimiento);
    }
    public MovimientosEntity deleteMovimiento(int id){
        MovimientosEntity movimiento = movimientosRepository.findById(id).orElse(null);
        movimientosRepository.deleteById(id);
        return movimiento;
    }

    public List<MovimientoModel> getMovimientoEntreFecha(LocalDate fechaInicio, LocalDate fechaFin, String tipo){
        if(tipo.equals("entrada")){
            return restTemplate.getForObject("http://localhost:8080/" + tipo +"/entreFecha/"+fechaInicio+"/"+fechaFin, List.class);
        }
        List<MovimientoModel> movimientos = restTemplate.getForObject("http://localhost:8080/" + tipo +"/entreFecha/"+fechaInicio+"/"+fechaFin, List.class);
        if(movimientos != null){
            for(MovimientoModel movimiento : movimientos){
                movimiento.setCantidad(movimiento.getCantidad()*-1);
            }
            return movimientos;
        }
        return null;
    }


    public void CalcularEntreFecha(LocalDate fechaInicio, LocalDate fechaFin){
        List<MovimientoModel> entradas = getMovimientoEntreFecha(fechaInicio, fechaFin, "entrada");
        List<MovimientoModel> salidas = getMovimientoEntreFecha(fechaInicio, fechaFin, "salida");
        List<MovimientoModel> movimientos = Stream.concat(entradas.stream(), salidas.stream())
                .sorted(Comparator.comparing(MovimientoModel::getFecha))
                .collect(Collectors.toList());
        float saldo = 0;
        for(MovimientoModel movimiento : movimientos){
            MovimientosEntity movimientoActual = new MovimientosEntity();
            saldo += movimiento.getCantidad();
            movimientoActual.setFecha(movimiento.getFecha());
            movimientoActual.setTipoDoc(movimiento.getTipoDoc());
            movimientoActual.setNumDoc(movimiento.getNumDoc());
            movimientoActual.setMotivo(movimiento.getMotivo());
            movimientoActual.setSaldo(saldo);
            if(movimiento.getCantidad() > 0){
                movimientoActual.setIngreso(movimiento.getCantidad());
                movimientoActual.setSalida(0);
            }else{
                movimientoActual.setIngreso(0);
                movimientoActual.setSalida(movimiento.getCantidad()*-1);
            }
        }

    }
    public List<MovimientosEntity> getEntreFecha(LocalDate fechaInicio, LocalDate fechaFin){
        CalcularEntreFecha(fechaInicio, fechaFin);
        return movimientosRepository.getEntreFecha(fechaInicio, fechaFin);
    }

}
