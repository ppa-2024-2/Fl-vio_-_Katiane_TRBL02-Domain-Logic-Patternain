package br.edu.ifrs.riogrande.tads.ppa.ligaa.domain;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Matricula.Situacao;
import br.edu.ifrs.riogrande.tads.ppa.ligaa.service.NotFoundException;

public class Turma {

    private String codigo; // ppa-2024-2

    private Disciplina disciplina;
    private Professor professor;

    private String semestre; // 2024-2
    private String sala;
    private int vagas;
    private boolean fechada;
    private List<Matricula> matriculas = new ArrayList<>();


    
    public Matricula matricular(Aluno aluno, Historico historico) {
        
        // validar se pode matricular
        validarSePodeMatricular(aluno, historico);
        
        Matricula matricula = new Matricula(aluno, Situacao.REGULAR);
        matriculas.add(matricula);

        return matricula;
        
    }

    private void validarSePodeMatricular(Aluno aluno, Historico historico) {
        
        // aluno existe?
        String cpf = aluno.getCpf();

        // turma existe?
        String codigoTurma = this.getCodigo();

        // turma já terminou o ciclo?
        // if (this.isFechada()) {
        //     throw new DomainException("Turma " + this.getCodigo() + " está fechada");
        // }
    
        // aluno já matriculado?
        // if (turma.getMatriculas().stream().anyMatch(m -> m.getAluno().equals(aluno))) {
        if (estáMatriculado(aluno)) {
            throw new DomainException("Aluno " + cpf + " já está matriculado na turma " + codigoTurma);
        }

        if (!temVagas(historico)) {
            throw new DomainException("Turma " + codigoTurma + " não tem vagas disponíveis");
        }     

        // aluno já fez essa disciplina?        
        if (historico.aprovadoEm(disciplina)) {
            throw new DomainException("Aluno " + cpf + " já aprovado na disciplina " + this.getDisciplina().getNome());
        }        

    }
    
    //Esse método retorna true se o aluno estiver matriculado na turma e false caso contrário.
    public boolean estáMatriculado(Aluno aluno) {
        return this.getMatriculas()
                .stream()
                .anyMatch(m -> m.getAluno().equals(aluno));
    }

    // Esse método retorna true se a turma tiver vagas disponíveis e false caso contrário.
    public boolean temVagas(Historico historico){
        if (matriculas.size() < vagas) {
            return true;            
        }

        return historico.reprovadoEm(disciplina);
    }

    //Getters e Setters

    public boolean isFechada() {
        return fechada;
    }

    public void setFechada(boolean fechada) {
        this.fechada = fechada;
    }


    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    @Override
    public String toString() {
        return "Turma [codigo=" + codigo + ", disciplina=" + disciplina + ", professor=" + professor + ", semestre="
                + semestre + ", sala=" + sala + ", vagas=" + vagas + ", matriculas=" + matriculas + "]";
    }

    

   

       
}
