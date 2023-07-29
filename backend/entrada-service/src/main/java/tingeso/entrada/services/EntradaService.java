package tingeso.entrada.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.entrada.entities.EntradaEntity;
import tingeso.entrada.repositories.EntradaRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class EntradaService {
    @Autowired
    private EntradaRepository entradaRepository;

    public List<EntradaEntity> listarEntradas(){
        return entradaRepository.findAll();
    }
    public EntradaEntity getEntradaId(int id){
        return entradaRepository.findById(id).orElse(null);
    }
    public EntradaEntity createEntrada(EntradaEntity entrada){
        return entradaRepository.save(entrada);
    }
    public EntradaEntity deleteEntrada(int id){
        EntradaEntity entrada = entradaRepository.findById(id).orElse(null);
        entradaRepository.deleteById(id);
        return entrada;
    }
    public List<EntradaEntity> getEntreFecha(LocalDate fechaInicio, LocalDate fechaFin){
        return entradaRepository.getEntreFecha(fechaInicio, fechaFin);
    }
}
