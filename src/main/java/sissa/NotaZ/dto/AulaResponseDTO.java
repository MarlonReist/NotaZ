package sissa.NotaZ.dto;

import sissa.NotaZ.domain.Aula;
import sissa.NotaZ.domain.enums.CursoEnum;
import sissa.NotaZ.domain.enums.PeriodoEnum;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

public class AulaResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDate data;
    private Integer quantidadeAulas;
    private Long disciplinaId;
    private String disciplinaNome;
    private Long turmaId;
    private PeriodoEnum turmaPeriodo;
    private CursoEnum turmaCurso;

    public AulaResponseDTO(){
    }

    public AulaResponseDTO(Aula aula){
        id = aula.getId();
        data = aula.getData();
        quantidadeAulas = aula.getQuantidadeAulas();
        disciplinaId = aula.getDisciplina().getId();
        disciplinaNome = aula.getDisciplina().getNome();
        turmaId = aula.getTurma().getId();
        turmaPeriodo = aula.getTurma().getPeriodo();
        turmaCurso = aula.getTurma().getCurso();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getQuantidadeAulas() {
        return quantidadeAulas;
    }

    public void setQuantidadeAulas(Integer quantidadeAulas) {
        this.quantidadeAulas = quantidadeAulas;
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
