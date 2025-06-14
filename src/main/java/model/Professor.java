/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Professor {
    private String nomeProfessor;
    private Formacao grauFormacao;

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public Formacao getGrauFormacao() {
        return grauFormacao;
    }

    public void setGrauFormacao(Formacao grauFormacao) {
        this.grauFormacao = grauFormacao;
    }
    public Professor(){

    }
    public Professor(String n,Formacao f){
        this.nomeProfessor = n;
        this.grauFormacao = f;
    }
}
