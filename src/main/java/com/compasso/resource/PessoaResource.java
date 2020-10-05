package com.compasso.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.compasso.model.Pessoa;
import com.compasso.respository.PessoaRepository;
import com.compasso.service.PessoaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private PessoaService pessoaService;
	
	//@PathVariable = Serve para obter variáveis de uma URL
	//@RequestBody = Para pega o conteúdo todo de uma URL e formar um objeto
	
	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {	
		Pessoa pessoaSalva = pessoaService.salvar(pessoa);
			
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);	
	}
	
	@GetMapping
	public List<Pessoa> listarTodas() {
		return pessoaRepository.findAll();
	}
	
	@GetMapping("/pesquisar/{nome}")
	public ResponseEntity<List<Pessoa>> obterPessoaPeloNome(@PathVariable String nome) {
		List<Pessoa> lstPessoas = pessoaRepository.findByNomeCompletoContainingIgnoreCase(nome);
		
		return ResponseEntity.ok(lstPessoas);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {
		Pessoa pessoa = pessoaService.buscarPessoaPeloCodigo(codigo);
		
		return ResponseEntity.ok(pessoa);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		pessoaRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);
		return ResponseEntity.ok(pessoaSalva);
	}
	
	@PutMapping("/{codigo}/alterarNome")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeNomeCompleto(@PathVariable Long codigo, @RequestBody String nomeCompleto) {
		try {
			JsonNode nodeNome = new ObjectMapper().readTree(nomeCompleto).findValue("nomeCompleto");
			pessoaService.atualizarPropriedadeNomeCompeto(codigo, nodeNome.asText());
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}		
	}
}
