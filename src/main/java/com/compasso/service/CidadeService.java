package com.compasso.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.compasso.model.Cidade;
import com.compasso.model.Pessoa;
import com.compasso.respository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cidade salvar(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}
	
	public Cidade obterCidadePeloCodigo(Long codigo) {
		Optional<Cidade> cidade = cidadeRepository.findById(codigo);
		if(!cidade.isPresent()) {
			 throw new EmptyResultDataAccessException(1);		 
		}
		return cidade.get();
	}
}
