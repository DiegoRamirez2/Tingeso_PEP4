package tingeso.entrada.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tingeso.entrada.entities.ProblemaEntity;
import tingeso.entrada.services.ProblemaService;

import java.util.List;

@RestController
@RequestMapping("/problema")
@CrossOrigin(origins = "*")
public class ProblemaController {
    @Autowired
    private ProblemaService problemaService;

    @GetMapping
    public ResponseEntity<List<ProblemaEntity>> getProblemas(){
        List<ProblemaEntity> problemas = problemaService.listarProblemas();
        return ResponseEntity.ok(problemas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProblemaEntity> getProblemaId(@PathVariable("id") int id){
        ProblemaEntity problema = problemaService.obtenerProblemaId(id);
        return ResponseEntity.ok(problema);
    }
    @PostMapping
    public ResponseEntity<ProblemaEntity> createProblema(@RequestBody ProblemaEntity problema){
        ProblemaEntity problemaDB = problemaService.crearProblema(problema);
        return ResponseEntity.ok(problemaDB);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ProblemaEntity> deleteProblema(@PathVariable("id") int id){
        ProblemaEntity problema = problemaService.eliminarProblema(id);
        return ResponseEntity.ok(problema);
    }
    @GetMapping("/prueba/{dificultad}")
    public ResponseEntity<List<ProblemaEntity>> getPruebaDificultad(@PathVariable("dificultad") int dificultad){
        List<ProblemaEntity> problemas = problemaService.obtenerPruebaDificultad(dificultad);
        return ResponseEntity.ok(problemas);
    }

}
