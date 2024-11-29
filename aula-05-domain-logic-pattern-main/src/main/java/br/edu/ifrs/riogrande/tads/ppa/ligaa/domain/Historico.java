package br.edu.ifrs.riogrande.tads.ppa.ligaa.domain;

import java.util.List;

import br.edu.ifrs.riogrande.tads.ppa.ligaa.domain.Matricula.Situacao;

// Record => Value Object => Objeto de Valor 
// DTO => VO => DTO
public record Historico(Aluno aluno, List<Turma> turmas) {

    public boolean aprovadoEm(Disciplina disciplina) {
        // FIXME: considerar a disciplina
        // Filtrar as turmas do histórico que pertencem à disciplina fornecida
        return turmas.stream()
            .filter(t -> t.getDisciplina().equals(disciplina)) // Apenas turmas da disciplina fornecida
            .flatMap(t -> t.getMatriculas().stream()) // Explodir as matrículas das turmas
            .anyMatch(m -> m.getAluno().equals(aluno) && m.getSituacao().equals(Situacao.APROVADO)); // Verificar se o aluno foi aprovado
    }

    // FIXME: Implementar o método para verificar se o aluno já cursou uma disciplina e foi reprovado
    public boolean reprovadoEm(Disciplina disciplina) {
        return turmas.stream()
            .filter(t -> t.getDisciplina().equals(disciplina))
            .flatMap(t -> t.getMatriculas().stream())
            .anyMatch(m -> m.getAluno().equals(aluno) && m.getSituacao().equals(Matricula.Situacao.REPROVADO)); // Verificar se o aluno foi reprovado
    }

    // FIXME: Implementar o método para verificar se o aluno já cursou uma disciplina e foi aprovado (Aproveitamento de Estudos)
    public boolean AproveitamentoEm(Disciplina disciplina) {
        return turmas.stream()
            .filter(t -> t.getDisciplina().equals(disciplina))
            .flatMap(t -> t.getMatriculas().stream())
            .anyMatch(m -> m.getAluno().equals(aluno) && m.getSituacao().equals(Matricula.Situacao.APROVEITAMENTO)); // Verificar se o aluno foi aprovado
    }

    // FIXME: Implementar o método para verificar se o aluno cancelou uma disciplina (Cancelamento)
    public boolean cancelamentoEm(Disciplina disciplina) {
        return turmas.stream()
            .filter(t -> t.getDisciplina().equals(disciplina))
            .flatMap(t -> t.getMatriculas().stream())
            .anyMatch(m -> m.getAluno().equals(aluno) && m.getSituacao().equals(Matricula.Situacao.CANCELADO)); // Verificar se o aluno foi aprovado
    }

    //FIXME: Implementar o método para verificar se o aluno está regular em uma disciplina (Matriculado)
    public boolean matriculadoEm(Disciplina disciplina) {
        return turmas.stream()
            .filter(t -> t.getDisciplina().equals(disciplina))
            .flatMap(t -> t.getMatriculas().stream())
            .anyMatch(m -> m.getAluno().equals(aluno) && m.getSituacao().equals(Matricula.Situacao.REGULAR)); // Verificar se o aluno foi aprovado
    }

}
