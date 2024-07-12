package ar.com.demo.dux.controller;

import ar.com.demo.dux.dto.EquipoDTO;
import ar.com.demo.dux.dto.ErrorDTO;
import ar.com.demo.dux.request.EquipoRequest;
import ar.com.demo.dux.service.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipos")
@Slf4j
@Tag(name = "EquipoController")
public class EquipoController {

    private static final String NOTFOUND = "Equipo no encontrado";
    private final EquipoService service;

    public EquipoController(EquipoService service) {
        super();
        this.service = service;
    }

    @GetMapping("/")
    @Operation(summary = "Obtener listado de equipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = EquipoDTO.class))))
    })
    public ResponseEntity<Object> getAll(){
        log.debug("Petición para obtener listado de equipos.");
        List<EquipoDTO> result;
        try{
            result = service.getAll();
            return ResponseEntity.ok(result);
        } catch (Exception ex){
            log.error("Ocurrió un error al consultar listado de equipos");
            return new ResponseEntity<>(
                    ErrorDTO.builder().codigo(500).mensaje(ex.getMessage()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener equipo por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipoDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<Object> getById(@PathVariable("id") Long id){
        log.debug("Petición para obtener un equipo por id");
        EquipoDTO equipo;
        try{
            equipo = service.getById(id);
            if(equipo != null && equipo.getId() == null){
                return new ResponseEntity<>(ErrorDTO.builder().codigo(404).mensaje(NOTFOUND).build(), HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(equipo);
        } catch (Exception ex){
            log.error("Ocurrió un error al consultar el equipo por ID");
            return new ResponseEntity<>(
                    ErrorDTO.builder().codigo(500).mensaje(ex.getMessage()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/buscar")
    @Operation(summary = "Filtrar equipos cuyo nombre contengan valor del parámetro nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = EquipoDTO.class))))
    })
    public ResponseEntity<Object> getByNombre(@RequestParam("nombre") String nombre){
        log.debug("Petición para filtrar listado de equipos por nombre");
        List<EquipoDTO> result;
        try{
            result = service.getByNombre(nombre);
            return ResponseEntity.ok(result);
        } catch (Exception ex){
            log.error("Ocurrió un error al filtrar equipos por nombre");
            return new ResponseEntity<>(
                    ErrorDTO.builder().codigo(500).mensaje(ex.getMessage()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    @Operation(summary = "Insertar nuevo equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<Object> create(@Valid @RequestBody EquipoRequest body){
        log.debug("Petición para crear nuevo equipo");
        EquipoDTO created;
        try{
            created = this.service.create(body);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception ex){
            log.error("Ocurrió un error al insertar nuevo equipo");
            return new ResponseEntity<>(
                    ErrorDTO.builder().codigo(500).mensaje(ex.getMessage()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar equipo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<Object> update(@PathVariable("id") Long id,@Valid @RequestBody EquipoRequest body){
        log.debug("Petición para actualizar un equipo existente");
        EquipoDTO updated;
        try{
            updated = this.service.update(id, body);
            if(updated != null && updated.getId() == null){
                return new ResponseEntity<>(ErrorDTO.builder().codigo(404).mensaje(NOTFOUND).build(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception ex){
            log.error("Ocurrió un error al actualizar equipo");
            return new ResponseEntity<>(
                    ErrorDTO.builder().codigo(500).mensaje(ex.getMessage()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id){
        log.debug("Petición para eliminar un equipo");
        try{
            Integer rowsDeleted = this.service.deleteById(id);
            if(rowsDeleted == 0){
                return new ResponseEntity<>(ErrorDTO.builder().codigo(404).mensaje(NOTFOUND).build(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex){
            log.error("Ocurrió un error al actualizar equipo");
            return new ResponseEntity<>(
                    ErrorDTO.builder().codigo(500).mensaje(ex.getMessage()).build(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
