package com.sistema.academia.config.excepition.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ErroValidacaoResponse {

    private LocalDateTime timestamp;
    private int status;
    private String mensagem;
    private String caminho;
    private List<ErroDetalhado> erros;

    public ErroValidacaoResponse() {
        this.timestamp = LocalDateTime.now();
        this.erros = new ArrayList<>();
    }

    public ErroValidacaoResponse(int status, String mensagem, String caminho) {
        this();
        this.status = status;
        this.mensagem = mensagem;
        this.caminho = caminho;
    }

    public void adicionarErro(String campo, String mensagem) {
        this.erros.add(new ErroDetalhado(campo, mensagem));
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

    public List<ErroDetalhado> getErros() {
        return erros;
    }

    public void setErros(List<ErroDetalhado> erros) {
        this.erros = erros;
    }

    // Classe aninhada para representar um erro detalhado
    public static class ErroDetalhado {
        private String campo;
        private String mensagem;

        public ErroDetalhado(String campo, String mensagem) {
            this.campo = campo;
            this.mensagem = mensagem;
        }

        public String getCampo() {
            return campo;
        }

        public void setCampo(String campo) {
            this.campo = campo;
        }

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }
    }
}
