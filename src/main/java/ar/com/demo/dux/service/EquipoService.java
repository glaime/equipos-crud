package ar.com.demo.dux.service;

import ar.com.demo.dux.dto.EquipoDTO;
import ar.com.demo.dux.request.EquipoRequest;

import java.util.List;

public interface EquipoService {

    List<EquipoDTO> getAll();

    EquipoDTO getById(Long id);

    List<EquipoDTO> getByNombre(String nombre);

    EquipoDTO create(EquipoRequest body);

    EquipoDTO update(Long id, EquipoRequest body);

    Integer deleteById(Long id);

}
