package ar.com.demo.dux.service.impl;

import ar.com.demo.dux.dto.EquipoDTO;
import ar.com.demo.dux.model.EquipoModel;
import ar.com.demo.dux.repository.EquipoRepository;
import ar.com.demo.dux.request.EquipoRequest;
import ar.com.demo.dux.service.EquipoService;
import ar.com.demo.dux.service.mapper.EquipoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository repository;

    public EquipoServiceImpl(EquipoRepository repository) {
        super();
        this.repository = repository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<EquipoDTO> getAll() {
        return EquipoMapper.toDtoList(this.repository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public EquipoDTO getById(Long id) {
        EquipoDTO response = new EquipoDTO();
        Optional<EquipoModel> optEquipo = this.repository.findById(id);
        if(optEquipo.isPresent()){
            response = EquipoMapper.toDto(optEquipo.get());
        }
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EquipoDTO> getByNombre(String nombre) {
        return EquipoMapper.toDtoList(this.repository.findByNombreContainingIgnoreCase(nombre));
    }

    @Override
    @Transactional
    public EquipoDTO create(EquipoRequest body) {
        EquipoModel newEquipo = new EquipoModel();
        newEquipo.setNombre(body.getNombre());
        newEquipo.setLiga(body.getLiga());
        newEquipo.setPais(body.getPais());
        return EquipoMapper.toDto(this.repository.save(newEquipo));
    }

    @Override
    @Transactional
    public EquipoDTO update(Long id, EquipoRequest body) {
        EquipoDTO response = new EquipoDTO();
        if(id != null && id > 0L){
            Optional<EquipoModel> opModel = this.repository.findById(id);
            if(opModel.isPresent()){
                EquipoModel entity = opModel.get();
                entity.setNombre(body.getNombre());
                entity.setPais(body.getPais());
                entity.setLiga(body.getLiga());
                response = EquipoMapper.toDto(this.repository.save(entity));
            }
        }
        return response;
    }

    @Override
    @Transactional
    public Integer deleteById(Long id) {
        if(id != null && id > 0L){
            Optional<EquipoModel> opModel = this.repository.findById(id);
            if(opModel.isPresent()){
                this.repository.deleteById(id);
                return 1;
            }
        }
        return 0;
    }

}
