package sissa.NotaZ.dto;

import java.io.Serial;
import java.io.Serializable;

public class ResumoFrequenciaResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long alunoId;
    private String alunoNome;
    private Long disciplinaId;
    private String disciplinaNome;
    private Integer totalAulas;
    private Integer totalPresencas;
    private Integer totalFaltas;
    private Double percentualFrequencia;
    private Double percentualFaltas;
    private Double frequenciaMinima;
    private String situacaoFrequencia;

    public ResumoFrequenciaResponseDTO(){
    }

    public ResumoFrequenciaResponseDTO(Long alunoId, String alunoNome, Long disciplinaId, String disciplinaNome, Integer totalAulas, Integer totalPresencas, Integer totalFaltas, Double percentualFrequencia, Double percentualFaltas, Double frequenciaMinima, String situacaoFrequencia) {
        this.alunoId = alunoId;
        this.alunoNome = alunoNome;
        this.disciplinaId = disciplinaId;
        this.disciplinaNome = disciplinaNome;
        this.totalAulas = totalAulas;
        this.totalPresencas = totalPresencas;
        this.totalFaltas = totalFaltas;
        this.percentualFrequencia = percentualFrequencia;
        this.percentualFaltas = percentualFaltas;
        this.frequenciaMinima = frequenciaMinima;
        this.situacaoFrequencia = situacaoFrequencia;
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

    public Integer getTotalAulas() {
        return totalAulas;
    }

    public void setTotalAulas(Integer totalAulas) {
        this.totalAulas = totalAulas;
    }

    public Integer getTotalPresencas() {
        return totalPresencas;
    }

    public void setTotalPresencas(Integer totalPresencas) {
        this.totalPresencas = totalPresencas;
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

    public Double getPercentualFaltas() {
        return percentualFaltas;
    }

    public void setPercentualFaltas(Double percentualFaltas) {
        this.percentualFaltas = percentualFaltas;
    }

    public Double getFrequenciaMinima() {
        return frequenciaMinima;
    }

    public void setFrequenciaMinima(Double frequenciaMinima) {
        this.frequenciaMinima = frequenciaMinima;
    }

    public String getSituacaoFrequencia() {
        return situacaoFrequencia;
    }

    public void setSituacaoFrequencia(String situacaoFrequencia) {
        this.situacaoFrequencia = situacaoFrequencia;
    }
}
