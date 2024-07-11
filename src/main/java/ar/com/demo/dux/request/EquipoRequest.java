package ar.com.demo.dux.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipoRequest {

    @NotBlank(message = "Parámetro nombre requerido")
    private String nombre;
    @NotBlank(message = "Parámetro liga requerido")
    private String liga;
    @NotBlank(message = "Parámetro pais requerido")
    private String pais;

}
