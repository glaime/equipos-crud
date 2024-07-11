package ar.com.demo.dux.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EquipoDTO {

    private Long id;
    private String nombre;
    private String liga;
    private String pais;

}
