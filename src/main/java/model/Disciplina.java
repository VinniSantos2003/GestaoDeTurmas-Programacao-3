/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author -
 */
public class Disciplina {
    private String nomeDisciplina;
    private int creditos;

    public String getNomeDisciplina() {
        return nomeDisciplina;
    }

    public void setNomeDisciplina(String nomeDisciplina) {
        this.nomeDisciplina = nomeDisciplina;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
    public Disciplina(){

    }
    public Disciplina(String nomeDisciplina,int creditos){
        this.nomeDisciplina = nomeDisciplina;
        this.creditos = creditos;
    }

}
