/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author -
 */
public class Turma {
    private Professor professorTitular;
    private Disciplina disciplina;
    private ArrayList<Aluno> listaAlunos;
    private Curso curso;
    private int capacidadeAlunos;

    public Professor getProfessorTitular() {
        return professorTitular;
    }

    public void setProfessorTitular(Professor professorTitular) {
        this.professorTitular = professorTitular;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public ArrayList<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public void setListaAlunos(ArrayList<Aluno> listaAlunos) {
        this.listaAlunos = listaAlunos;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getCapacidadeAlunos() {
        return capacidadeAlunos;
    }

    public void setCapacidadeAlunos(int capacidadeAlunos) {
        this.capacidadeAlunos = capacidadeAlunos;
    }
    public Turma(){
        this.listaAlunos = new ArrayList<>();
    }
    public Turma(
        Professor p,
        Disciplina d,
        ArrayList<Aluno> lA,
        Curso c,
        int cA
    ){
        this.listaAlunos = new ArrayList<>();
        this.professorTitular = p;
        this.disciplina = d;
        this.listaAlunos = lA;
        this.curso = c;
        this.capacidadeAlunos = cA;
    }
}
