package utilities;

import model.Aluno;
import model.Disciplina;
import model.Professor;
import model.Turma;
import model.staticModels.Lista;

import java.util.ArrayList;
import java.util.List;

public class TempJsonList {
    List<Aluno> aList = Lista.listaAlunos;
    List<Turma> aTurma = Lista.listaTurma;
    List<Disciplina> dList = Lista.listaDisciplina;
    List<Professor> pList = Lista.listaProfessor;

    protected void passToMainList(){
        Lista.listaAlunos = (ArrayList<Aluno>) aList;
        Lista.listaTurma = (ArrayList<Turma>) aTurma;
        Lista.listaDisciplina = (ArrayList<Disciplina>) dList;
        Lista.listaProfessor = (ArrayList<Professor>) pList;
    }
}

