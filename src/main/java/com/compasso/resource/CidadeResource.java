package com.compasso.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compasso.model.Cidade;
import com.compasso.model.Pessoa;
import com.compasso.respository.CidadeRepository;
import com.compasso.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Cidade> obterPorCodigo(@PathVariable Long codigo) {
		Cidade cidadeObtida = cidadeService.obterCidadePeloCodigo(codigo);
		
		return ResponseEntity.ok(cidadeObtida);		
	}
	
	@GetMapping("/estado/{codigo}")
	public List<Cidade> obterCidadesPorCodigoEstado(@PathVariable Long codigo) {
		return cidadeRepository.findByEstadoCodigo(codigo);
	}
	
	@GetMapping("/pesquisar/{nome}")
	public ResponseEntity<List<Cidade>> obterCidadePeloNome(@PathVariable String nome) {
		List<Cidade> lstCidades = cidadeRepository.findByNomeContainingIgnoreCase(nome);
		
		return ResponseEntity.ok(lstCidades);
	}
	
	@PostMapping
	public ResponseEntity<Cidade> cadastrarCidade(@Valid @RequestBody Cidade cidade, HttpServletResponse response) {
		Cidade cidadeSalvar = cidadeService.salvar(cidade);
		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalvar);
	}
}
