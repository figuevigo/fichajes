package com.jcromero.fichajes.fichaje;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
class FichajeServiceImpl implements FichajeService {
    
    @Autowired
    FichajeRepository repository;

    @Override
    public Long addFichaje(FichajeDTO fichaje) throws FichajeException {
        if (fichaje==null) {
            throw new FichajeException();
        }
        Fichaje fichajeNuevo = new Fichaje(); 
        fichajeNuevo.setBusinessId(fichaje.getBusinessId());
        fichajeNuevo.setDate(fichaje.getDate());
        fichajeNuevo.setEmployeeId(fichaje.getEmployeeId());
        fichajeNuevo.setRecordType(fichaje.getRecordType());
        fichajeNuevo.setServiceId(fichaje.getServiceId());
        fichajeNuevo.setType(fichaje.getType());
        
        Fichaje fichajeSalvado = repository.save(fichajeNuevo);
        
        return fichajeSalvado.getId();
    }

    @Override
    public List<FichajeDTO> getAllFichajes() {
        return repository.findAll().stream().map(this::convertFichajeTofichajeDTO).collect(Collectors.toList());
    }

    private FichajeDTO convertFichajeTofichajeDTO(Fichaje fichaje) {
        FichajeDTO fichajeNuevo = new FichajeDTO(); 
        fichajeNuevo.setBusinessId(fichaje.getBusinessId());
        fichajeNuevo.setDate(fichaje.getDate());
        fichajeNuevo.setEmployeeId(fichaje.getEmployeeId());
        fichajeNuevo.setRecordType(fichaje.getRecordType());
        fichajeNuevo.setServiceId(fichaje.getServiceId());
        fichajeNuevo.setType(fichaje.getType()); 
        return fichajeNuevo;
    }

    @Override
    public List<FichajeDTO> getAllFichajesEmpleado(String empleado) {
        Fichaje fichaje = new Fichaje();
        fichaje.setEmployeeId(empleado);
        Example<Fichaje> example = Example.of(fichaje);
        return repository.findAll(example).stream().map(this::convertFichajeTofichajeDTO).collect(Collectors.toList());
    }

    @Override
    public Semana getFichajesSemanaEnCursoEmpleado(String empleado) {
        return getFichajesSemanaEmpleado(empleado, LocalDate.now());
    }

    @Override
    public Semana getFichajesSemanaEmpleado(String empleado, LocalDate diaSemana) {
        Semana semana = Semana.buildFor(diaSemana);
        List<Fichaje> fichajesSemanalesEmpleado = repository.findByEmployeeIdAndDate(empleado, semana.getFechaInicio(), semana.getFechaFin());
        semana.fillSemanaWithFichajes(fichajesSemanalesEmpleado);
        return semana;
    }
}
