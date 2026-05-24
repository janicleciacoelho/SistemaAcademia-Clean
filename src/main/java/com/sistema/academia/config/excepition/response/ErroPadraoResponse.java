package com.sistema.academia.config.excepition.response;

import java.time.LocalDateTime;

public class ErroPadraoResponse {

    private LocalDateTime timestamp;
    private int status;
    private String mensagem;
    private String caminho;

    public ErroPadraoResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ErroPadraoResponse(int status, String mensagem, String caminho) {
        this();
        this.status = status;
        this.mensagem = mensagem;
        this.caminho = caminho;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }
}
