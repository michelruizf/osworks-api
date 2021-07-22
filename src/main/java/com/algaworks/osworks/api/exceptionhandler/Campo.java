package com.algaworks.osworks.api.exceptionhandler;

public class Campo {
	private String nome;
	private String mensagem;

	public Campo(String nome, String mensagem) {
		this.nome = nome;
		this.mensagem = mensagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
