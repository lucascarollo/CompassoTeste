package com.compasso.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.compasso.model.Pessoa;
import com.compasso.respository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
		
	
	public Pessoa salvar (Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
		//Copiando de pessoa para pessoaSalva ignorando o código para que o código continue o mesmo;
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		return pessoaRepository.save(pessoaSalva);
	}
	
	public Pessoa buscarPessoaPeloCodigo(Long codigo) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(codigo);
		if(!pessoa.isPresent()) {
			 throw new EmptyResultDataAccessException(1);		 
		}
		return pessoa.get();
	}
	
	public void atualizarPropriedadeNomeCompeto(Long codigo, String nomeCompleto) {
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
		
		pessoaSalva.setNomeCompleto(nomeCompleto);
		pessoaRepository.save(pessoaSalva);
	}
}
