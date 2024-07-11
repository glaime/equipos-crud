package ar.com.demo.dux.service.mapper;

import ar.com.demo.dux.dto.EquipoDTO;
import ar.com.demo.dux.model.EquipoModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.ArrayList;
import java.util.List;

public class EquipoMapper {

    EquipoMapper(){ }

    public static EquipoDTO toDto(EquipoModel model){
        if(model != null){
            ModelMapper mapper = new ModelMapper();
            return mapper.map(model, EquipoDTO.class);
        }else{
            return null;
        }
    }

    public static List<EquipoDTO> toDtoList(List<EquipoModel> list){
        List<EquipoDTO> result = new ArrayList<>();
        if(!list.isEmpty()){
            list.forEach( item -> result.add(EquipoMapper.toDto(item)));
        }
        return result;
    }

}
