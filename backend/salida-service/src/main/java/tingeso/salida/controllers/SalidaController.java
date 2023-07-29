package tingeso.salida.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso.salida.entities.SalidaEntity;
import tingeso.salida.services.SalidaService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/salida")
public class SalidaController {
    @Autowired
    private SalidaService salidaService;

    @GetMapping
    public ResponseEntity<List<SalidaEntity>> listarSalidas(){
        List<SalidaEntity> salidas = salidaService.listarSalidas();
        return ResponseEntity.ok(salidas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SalidaEntity> getSalidaId(@PathVariable("id") int id){
        SalidaEntity salida = salidaService.getSalida(id);
        return ResponseEntity.ok(salida);
    }
    @PostMapping
    public ResponseEntity<SalidaEntity> createSalida(@RequestBody SalidaEntity salida){
        SalidaEntity salidaDB = salidaService.createSalida(salida);
        return ResponseEntity.ok(salidaDB);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<SalidaEntity> deleteSalida(@PathVariable("id") int id){
        SalidaEntity salida = salidaService.deleteSalida(id);
        return ResponseEntity.ok(salida);
    }
    @GetMapping("/entreFecha/{fecha1}/{fecha2}")
    public ResponseEntity<List<SalidaEntity>> getSalidasEntreFechas(@PathVariable("fecha1") String fecha1, @PathVariable("fecha2") String fecha2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate fechaInicio = LocalDate.parse(fecha1, formatter);
        LocalDate fechaFin = LocalDate.parse(fecha2, formatter);
        List<SalidaEntity> salidas = salidaService.getEntreFecha(fechaInicio, fechaFin);
        return ResponseEntity.ok(salidas);
    }
}
