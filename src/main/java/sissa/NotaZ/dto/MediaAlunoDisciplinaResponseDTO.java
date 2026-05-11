package sissa.NotaZ.dto;

import java.io.Serial;
import java.io.Serializable;

public class MediaAlunoDisciplinaResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long alunoId;
    private String alunoNome;
    private Long disciplinaId;
    private String disciplinaNome;
    private Double media;
    private Double totalPeso;
    private Integer quantidadeNotas;

    public MediaAlunoDisciplinaResponseDTO() {
    }

    public MediaAlunoDisciplinaResponseDTO(Long alunoId, String alunoNome, Long disciplinaId, String disciplinaNome, Double media, Double totalPeso, Integer quantidadeNotas) {
        this.alunoId = alunoId;
        this.alunoNome = alunoNome;
        this.disciplinaId = disciplinaId;
        this.disciplinaNome = disciplinaNome;
        this.media = media;
        this.totalPeso = totalPeso;
        this.quantidadeNotas = quantidadeNotas;
    }

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
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

    public Double getTotalPeso() {
        return totalPeso;
    }

    public void setTotalPeso(Double totalPeso) {
        this.totalPeso = totalPeso;
    }

    public Integer getQuantidadeNotas() {
        return quantidadeNotas;
    }

    public void setQuantidadeNotas(Integer quantidadeNotas) {
        this.quantidadeNotas = quantidadeNotas;
    }
}
