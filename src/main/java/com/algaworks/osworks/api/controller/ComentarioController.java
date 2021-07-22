package com.algaworks.osworks.api.controller;

import com.algaworks.osworks.api.model.ComentarioDTO;
import com.algaworks.osworks.api.model.ComentarioRequest;
import com.algaworks.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.osworks.domain.model.Comentario;
import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.domain.repository.OrdemServicoRepository;
import com.algaworks.osworks.domain.service.GestaoOrdemServicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

	@Autowired
	private GestaoOrdemServicoService service;

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ModelMapper mapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioDTO criar(@PathVariable Long ordemServicoId, @Valid @RequestBody ComentarioRequest comentarioRequest) {
		Comentario comentario = service.adicionarComentario(ordemServicoId, comentarioRequest.getDescricao());
		return toComentarioDTO(comentario);
	}

	@GetMapping
	public List<ComentarioDTO> listar(@PathVariable Long ordemServicoId) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
			.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada."));

		return toListComentarioDTO(ordemServico.getComentarios());
	}

	private List<ComentarioDTO> toListComentarioDTO(List<Comentario> comentarios) {
		return comentarios.stream().map(comentario -> toComentarioDTO(comentario)).collect(Collectors.toList());
	}

	private ComentarioDTO toComentarioDTO(Comentario comentario) {
		return mapper.map(comentario, ComentarioDTO.class);
	}
}
