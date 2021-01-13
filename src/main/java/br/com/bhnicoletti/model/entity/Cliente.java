package br.com.bhnicoletti.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(nullable = false, length = 150, name = "nome_cliente")
    @NotBlank(message = "{campo.nome.obrigatorio}")
    private String nomeCliente;

    @Column(nullable = false, length = 11, name = "cpf_cliente")
    @NotBlank(message = "{campo.cpf.obrigatorio}")
    @CPF(message = "{campo.cpf.invalido}")
    private String cpfCliente;

    @Column(name = "data_cadastro_cliente", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastroCliente;

    @PrePersist
    public void prePersist(){
        this.setDataCadastroCliente(LocalDate.now());
    }

}
