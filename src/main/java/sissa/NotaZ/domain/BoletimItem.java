package sissa.NotaZ.domain;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class BoletimItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "boletim_id")
    private Boletim boletim;
    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;
    private Double media;
    private Integer totalAulas;
    private Integer totalFaltas;
    private Double percentualFrequencia;
    private String situacao;

    public BoletimItem() {
    }

    public BoletimItem(Boletim boletim, Disciplina disciplina, Double media, Integer totalAulas, Integer totalFaltas, Double percentualFrequencia, String situacao) {
        this.boletim = boletim;
        this.disciplina = disciplina;
        this.media = media;
        this.totalAulas = totalAulas;
        this.totalFaltas = totalFaltas;
        this.percentualFrequencia = percentualFrequencia;
        this.situacao = situacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boletim getBoletim() {
        return boletim;
    }

    public void setBoletim(Boletim boletim) {
        this.boletim = boletim;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BoletimItem that = (BoletimItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
