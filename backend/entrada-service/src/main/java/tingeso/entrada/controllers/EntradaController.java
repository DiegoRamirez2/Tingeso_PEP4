package tingeso.entrada.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso.entrada.entities.EntradaEntity;
import tingeso.entrada.services.EntradaService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/entrada")
@CrossOrigin(origins = "*")
public class EntradaController {
    @Autowired
    private EntradaService entradaService;

    @GetMapping
    public ResponseEntity<List<EntradaEntity>> listarEntradas() {
        List<EntradaEntity> salidas = entradaService.listarEntradas();
        return ResponseEntity.ok(salidas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradaEntity> getEntradaId(@PathVariable("id") int id) {
        EntradaEntity entrada = entradaService.getEntradaId(id);
        return ResponseEntity.ok(entrada);
    }

    @PostMapping
    public ResponseEntity<EntradaEntity> createEntrada(@RequestBody EntradaEntity entrada) {
        EntradaEntity entradaDB = entradaService.createEntrada(entrada);
        return ResponseEntity.ok(entradaDB);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<EntradaEntity> deleteEntrada(@PathVariable("id") int id) {
        EntradaEntity entrada = entradaService.deleteEntrada(id);
        return ResponseEntity.ok(entrada);
    }
    @GetMapping("/entreFecha/{fecha1}/{fecha2}")
    public ResponseEntity<List<EntradaEntity>> getEntradasEntreFechas(@PathVariable("fecha1") String fecha1, @PathVariable("fecha2") String fecha2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaInicio = LocalDate.parse(fecha1, formatter);
        LocalDate fechaFin = LocalDate.parse(fecha2, formatter);
        List<EntradaEntity> entradas = entradaService.getEntreFecha(fechaInicio, fechaFin);
        return ResponseEntity.ok(entradas);
    }
}
