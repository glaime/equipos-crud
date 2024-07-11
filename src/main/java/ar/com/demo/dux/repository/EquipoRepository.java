package ar.com.demo.dux.repository;

import ar.com.demo.dux.model.EquipoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<EquipoModel, Long> {

    List<EquipoModel> findByNombreContainingIgnoreCase(String nombre);

}
