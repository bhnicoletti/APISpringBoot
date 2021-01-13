package br.com.bhnicoletti.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ServicoDTO {

    @NotBlank(message = "{campo.descricao.obrigatorio}")
    private String descricaoServico;

    @NotNull(message = "{campo.valor.obrigatorio}")
    private BigDecimal valorServico;

    @NotNull(message = "{campo.cliente.obrigatorio}")
    private Integer idCliente;
}
