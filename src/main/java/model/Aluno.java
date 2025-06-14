/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.UUID;


public class Aluno {
    private String nomeAluno;
    private int matriculaAluno;

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public int getMatriculaAluno() {
        return matriculaAluno;
    }

    public void setMatriculaAluno(int matriculaAluno) {
        this.matriculaAluno = matriculaAluno;
    }

    public Aluno(){
        //matriculaAluno = Integer.parseInt(UUID.randomUUID().toString());
    }
    public Aluno(String nomeAluno,int matriculaAluno){
        this.matriculaAluno = matriculaAluno;
        this.nomeAluno = nomeAluno;
    }
}
