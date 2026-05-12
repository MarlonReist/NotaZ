package sissa.NotaZ.dto;

import sissa.NotaZ.domain.BoletimItem;

import java.io.Serial;
import java.io.Serializable;

public class BoletimItemResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long boletimId;
    private String boletimPeriodo;
    private Long disciplinaId;
    private String disciplinaNome;
    private Double media;
    private Integer totalAulas;
    private Integer totalFaltas;
    private Double percentualFrequencia;
    private String situacao;

    public BoletimItemResponseDTO(){
    }

    public BoletimItemResponseDTO(BoletimItem boletimItem){
        id = boletimItem.getId();
        boletimId = boletimItem.getBoletim().getId();
        boletimPeriodo = boletimItem.getBoletim().getPeriodo();
        disciplinaId = boletimItem.getDisciplina().getId();
        disciplinaNome = boletimItem.getDisciplina().getNome();
        media = boletimItem.getMedia();
        totalAulas = boletimItem.getTotalAulas();
        totalFaltas = boletimItem.getTotalFaltas();
        percentualFrequencia = boletimItem.getPercentualFrequencia();
        situacao = boletimItem.getSituacao();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBoletimId() {
        return boletimId;
    }

    public void setBoletimId(Long boletimId) {
        this.boletimId = boletimId;
    }

    public String getBoletimPeriodo() {
        return boletimPeriodo;
    }

    public void setBoletimPeriodo(String boletimPeriodo) {
        this.boletimPeriodo = boletimPeriodo;
    }

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public String getDisciplinaNome() {
        return disciplinaNome;
    }

    public void setDisciplinaNome(String disciplinaNome) {
        this.disciplinaNome = disciplinaNome;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public Integer getTotalAulas() {
        return totalAulas;
    }

    public void setTotalAulas(Integer totalAulas) {
        this.totalAulas = totalAulas;
    }

    public Integer getTotalFaltas() {
        return totalFaltas;
    }

    public void setTotalFaltas(Integer totalFaltas) {
        this.totalFaltas = totalFaltas;
    }

    public Double getPercentualFrequencia() {
        return percentualFrequencia;
    }

    public void setPercentualFrequencia(Double percentualFrequencia) {
        this.percentualFrequencia = percentualFrequencia;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
