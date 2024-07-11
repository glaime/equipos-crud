package ar.com.demo.dux.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorDTO {

    private Integer codigo;
    private String mensaje;

}
