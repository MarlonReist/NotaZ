package sissa.NotaZ.dto;

import sissa.NotaZ.domain.Boletim;
import sissa.NotaZ.domain.enums.CursoEnum;
import sissa.NotaZ.domain.enums.PeriodoEnum;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class BoletimResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String periodo;
    private LocalDate dataFechamento;
    private Double mediaGeral;
    private Double percentualFrequencia;
    private String situacao;
    private Long alunoId;
    private String alunoMatricula;
    private String alunoNome;
    private Long turmaId;
    private PeriodoEnum turmaPeriodo;
    private CursoEnum turmaCurso;

    public BoletimResponseDTO(){
    }

    public BoletimResponseDTO(Boletim boletim){
        id = boletim.getId();
        periodo = boletim.getPeriodo();
        dataFechamento = boletim.getDataFechamento();
        mediaGeral = boletim.getMediaGeral();
        percentualFrequencia = boletim.getPercentualFrequencia();
        situacao = boletim.getSituacao();
        alunoId = boletim.getAluno().getId();
        alunoMatricula = boletim.getAluno().getMatricula();
        alunoNome = boletim.getAluno().getUsuario().getNome();
        turmaId = boletim.getTurma().getId();
        turmaPeriodo = boletim.getTurma().getPeriodo();
        turmaCurso = boletim.getTurma().getCurso();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Double getMediaGeral() {
        return mediaGeral;
    }

    public void setMediaGeral(Double mediaGeral) {
        this.mediaGeral = mediaGeral;
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

    public Long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }

    public String getAlunoMatricula() {
        return alunoMatricula;
    }

    public void setAlunoMatricula(String alunoMatricula) {
        this.alunoMatricula = alunoMatricula;
    }

    public String getAlunoNome() {
        return alunoNome;
    }

    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    public Long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(Long turmaId) {
        this.turmaId = turmaId;
    }

    public PeriodoEnum getTurmaPeriodo() {
        return turmaPeriodo;
    }

    public void setTurmaPeriodo(PeriodoEnum turmaPeriodo) {
        this.turmaPeriodo = turmaPeriodo;
    }

    public CursoEnum getTurmaCurso() {
        return turmaCurso;
    }

    public void setTurmaCurso(CursoEnum turmaCurso) {
        this.turmaCurso = turmaCurso;
    }
}
