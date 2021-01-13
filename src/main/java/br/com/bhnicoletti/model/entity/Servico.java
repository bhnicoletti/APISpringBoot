package br.com.bhnicoletti.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servico")
    private Integer idServico;

    @Column(nullable = false, length = 250, name = "descricao_servico")
    @NotBlank(message = "{campo.descricao.obrigatorio}")
    private String descricaoServico;

    @Column(name = "valor_servico")
    @NotNull(message = "{campo.valor.obrigatorio}")
    private BigDecimal valorServico;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @NotNull(message = "{campo.cliente.obrigatorio}")
    private Cliente idCliente;


}
