package tingeso.salida.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tingeso.salida.entities.SalidaEntity;
import tingeso.salida.repositories.SalidaRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalidaService {
    @Autowired
    private SalidaRepository salidaRepository;


    public List<SalidaEntity> listarSalidas(){
        return salidaRepository.findAll();
    }
    public SalidaEntity getSalida(int id){
        return salidaRepository.findById(id).orElse(null);
    }
    public SalidaEntity createSalida(SalidaEntity salida){
        return salidaRepository.save(salida);
    }
    public SalidaEntity deleteSalida(int id){
        SalidaEntity salida = salidaRepository.findById(id).orElse(null);
        salidaRepository.deleteById(id);
        return salida;
    }
    public List<SalidaEntity> getEntreFecha(LocalDate fechaInicio, LocalDate fechaFin){
        return salidaRepository.getEntreFecha(fechaInicio, fechaFin);
    }



}
