package br.com.bhnicoletti.rest;

import br.com.bhnicoletti.DTO.ServicoDTO;
import br.com.bhnicoletti.model.entity.Cliente;
import br.com.bhnicoletti.model.entity.Servico;
import br.com.bhnicoletti.model.repository.ClienteRepository;
import br.com.bhnicoletti.model.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/servicos")
@CrossOrigin("http://localhost:4200")
public class ServicoController {

    private final ServicoRepository repository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public ServicoController(ServicoRepository repository, ClienteRepository clienteRepository){
        this.repository = repository;
        this.clienteRepository = clienteRepository;
    }
/*
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Servico salvar(@RequestBody @Valid ServicoDTO servicoDTO){
        Servico servico = new Servico();
        return  repository.save(converterServico(servicoDTO, servico));
    }
*/

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Servico salvar(@RequestBody @Valid Servico servico){
        //Servico servico = new Servico();
        return  repository.save(servico);
    }

    @GetMapping("{id}")
    public Servico carregar(@PathVariable Integer id){
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id){
        repository.findById(id).map(
                servico -> {
                    repository.delete(servico);
                    return Void.TYPE;
                }).
                orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico não encontrado"));
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterar(@PathVariable Integer id, @RequestBody @Valid Servico servicoAtualizado){
        repository.findById(id)
                .map(servico -> {
                    servicoAtualizado.setIdServico(servico.getIdServico());
                    return repository.save(servicoAtualizado);
                }).
                orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico não encontrado"));
    }

    @GetMapping
    public List<Servico> listarTodos(){
        return repository.findAll();
    }

    public Servico converterServico(ServicoDTO servicoDTO, Servico servico){
        servico.setValorServico(servicoDTO.getValorServico());
        servico.setDescricaoServico(servicoDTO.getDescricaoServico());
        Cliente cliente = clienteRepository.findById(servicoDTO.getIdCliente()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        servico.setIdCliente(cliente);
        return servico;
    }
}
