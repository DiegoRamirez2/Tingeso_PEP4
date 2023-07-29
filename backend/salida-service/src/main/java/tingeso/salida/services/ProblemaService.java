package tingeso.salida.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.salida.entities.ProblemaEntity;
import tingeso.salida.repositories.ProblemaRepository;

import java.util.List;

@Service
public class ProblemaService {
    @Autowired
    private ProblemaRepository problemaRepository;

    public List<ProblemaEntity> listarProblemas(){
        return problemaRepository.findAll();
    }
    public ProblemaEntity obtenerProblemaId(int id){
        return problemaRepository.findById(id).orElse(null);
    }
    public ProblemaEntity crearProblema(ProblemaEntity problema){
        return problemaRepository.save(problema);
    }
    public ProblemaEntity eliminarProblema(int id){
        ProblemaEntity problema = problemaRepository.findById(id).orElse(null);
        problemaRepository.deleteById(id);
        return problema;
    }
    public List<ProblemaEntity> obtenerProblemasByDificultad(int dificultad){
        return problemaRepository.findByDificultad(dificultad);
    }

    public List<ProblemaEntity> obtenerPruebaDificultad(int dificultad){
        return problemaRepository.findRandomByDificultad(dificultad, 4);
    }
}
