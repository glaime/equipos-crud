package ar.com.demo.dux;

import ar.com.demo.dux.dto.EquipoDTO;
import ar.com.demo.dux.model.EquipoModel;
import ar.com.demo.dux.repository.EquipoRepository;
import ar.com.demo.dux.request.EquipoRequest;
import ar.com.demo.dux.service.EquipoService;
import ar.com.demo.dux.service.impl.EquipoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DuxApplicationTests {

	@Mock
	private EquipoRepository repository;

	@InjectMocks
	private EquipoServiceImpl service;

	@Test
	void getAllTest() {
		when(this.repository.findAll()).thenReturn(Arrays.asList(
				new EquipoModel(1L,"Boca","LPF","Arg"),
				new EquipoModel(2L,"River","LPF","Arg")));

		List<EquipoDTO> result = service.getAll();

		assertEquals(2, result.size());
		assertEquals(1L, result.get(0).getId());
		assertEquals("Boca", result.get(0).getNombre());
		assertEquals(2L, result.get(1).getId());
		assertEquals("River", result.get(1).getNombre());

		verify(this.repository, times(1)).findAll();
	}

	@Test
	void getByIdTest() {
		when(this.repository.findById(any())).thenReturn(Optional.of(new EquipoModel(1L,"Boca","LPF","Arg")));

		EquipoDTO result = service.getById(1L);

		assertEquals(1L, result.getId());
		assertEquals("Boca", result.getNombre());
		assertEquals("LPF", result.getLiga());
		assertEquals("Arg", result.getPais());

		verify(this.repository, times(1)).findById(any());
	}

	@Test
	void crearEquipoTest(){
		when(this.repository.save(any())).thenReturn(new EquipoModel(1L,"Rosario Central","LPF","Arg"));

		EquipoDTO result = service.create(new EquipoRequest("Rosario Central","LPF","Arg"));

		assertEquals(1L, result.getId());
		assertEquals("Rosario Central", result.getNombre());
		assertEquals("LPF", result.getLiga());
		assertEquals("Arg", result.getPais());

		verify(this.repository, times(1)).save(any());
	}

}