package tingeso.movimientos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso.movimientos.entities.MovimientosEntity;
import tingeso.movimientos.services.MovimientosService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientosController {
    @Autowired
    private MovimientosService movimientosService;

    @GetMapping
    public ResponseEntity<List<MovimientosEntity>> listarMovimientos(){
        List<MovimientosEntity> movimientos = movimientosService.listarMovimientos();
        return ResponseEntity.ok(movimientos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MovimientosEntity> getMovimientoId(@PathVariable("id") int id){
        MovimientosEntity movimiento = movimientosService.obtenerMovimientoId(id);
        return ResponseEntity.ok(movimiento);
    }
    @PostMapping
    public ResponseEntity<MovimientosEntity> createMovimiento(@RequestBody MovimientosEntity movimiento){
        MovimientosEntity movimientoDB = movimientosService.createMovimiento(movimiento);
        return ResponseEntity.ok(movimientoDB);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MovimientosEntity> deleteMovimiento(@PathVariable("id") int id){
        MovimientosEntity movimiento = movimientosService.deleteMovimiento(id);
        return ResponseEntity.ok(movimiento);
    }
    @GetMapping("/getEntreFechas/{fecha1}/{fecha2}")
    public ResponseEntity<List<MovimientosEntity>> getMovimientosEntreFechas(@PathVariable("fecha1") String fecha1, @PathVariable("fecha2") String fecha2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fechaInicio = LocalDate.parse(fecha1, formatter);
        LocalDate fechaFin = LocalDate.parse(fecha2, formatter);
        List<MovimientosEntity> movimientos = movimientosService.getEntreFecha(fechaInicio, fechaFin);
        return ResponseEntity.ok(movimientos);
    }
}
