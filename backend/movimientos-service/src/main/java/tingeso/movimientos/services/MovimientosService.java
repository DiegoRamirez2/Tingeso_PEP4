package tingeso.movimientos.services;

import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tingeso.movimientos.entities.MovimientosEntity;
import tingeso.movimientos.models.EntradaModel;
import tingeso.movimientos.models.MovimientoModel;
import tingeso.movimientos.models.SalidaModel;
import tingeso.movimientos.repositories.MovimientosRepository;

import java.time.LocalDate;
import java.util.ArrayList;
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
        if(obtenerMovimientoNumDoc(movimiento.getNumDoc()) == null){
            return movimientosRepository.save(movimiento);
        }
        else{
            return null;
        }
    }
    public MovimientosEntity deleteMovimiento(int id){
        MovimientosEntity movimiento = movimientosRepository.findById(id).orElse(null);
        movimientosRepository.deleteById(id);
        return movimiento;
    }

    public List<MovimientoModel> getMovimientoEntreFecha(LocalDate fechaInicio, LocalDate fechaFin, String tipo){
        List<MovimientoModel> movimientos = new ArrayList<MovimientoModel>();
        if(tipo.equals("entrada")){
            ParameterizedTypeReference<List<EntradaModel>> typeRef = new ParameterizedTypeReference<>() {};
            ResponseEntity<List<EntradaModel>> responseEntity = restTemplate.exchange("http://entrada-service:8083/" + tipo + "/entreFecha/" + fechaInicio + "/" + fechaFin, HttpMethod.GET, null, typeRef);
            List<EntradaModel> entradas = responseEntity.getBody();
            for(EntradaModel entrada : entradas){
                MovimientoModel movimiento = new MovimientoModel();
                movimiento.setFecha(entrada.getFecha());
                movimiento.setTipoDoc(entrada.getTipoDoc());
                movimiento.setNumDoc(entrada.getNumDoc());
                movimiento.setMotivo(entrada.getMotivo());
                movimiento.setCantidad(entrada.getIngreso());
                movimientos.add(movimiento);
            }
        }
        else if(tipo.equals("salida")){
            ParameterizedTypeReference<List<SalidaModel>> typeRef = new ParameterizedTypeReference<>() {};
            ResponseEntity<List<SalidaModel>> responseEntity = restTemplate.exchange("http://salida-service:8084/" + tipo + "/entreFecha/" + fechaInicio + "/" + fechaFin, HttpMethod.GET, null, typeRef);
            List<SalidaModel> salidas = responseEntity.getBody();
            for(SalidaModel salida : salidas){
                MovimientoModel movimiento = new MovimientoModel();
                movimiento.setFecha(salida.getFecha());
                movimiento.setTipoDoc(salida.getTipoDoc());
                movimiento.setNumDoc(salida.getNumDoc());
                movimiento.setMotivo(salida.getMotivo());
                movimiento.setCantidad(salida.getSalida()*-1);
                movimientos.add(movimiento);
            }
        }
        return movimientos;
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
                createMovimiento(movimientoActual);
        }
    }
    @Transactional
    public List<MovimientosEntity> getEntreFecha(LocalDate fechaInicio, LocalDate fechaFin){
        movimientosRepository.eliminarEntreFecha(fechaInicio, fechaFin);
        CalcularEntreFecha(fechaInicio, fechaFin);
        return movimientosRepository.getEntreFecha(fechaInicio, fechaFin);
    }

    public MovimientosEntity obtenerMovimientoNumDoc(String numDoc) {
        return movimientosRepository.findByNumDoc(numDoc);
    }
}
