package model.staticModels;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import model.Aluno;
import model.Disciplina;
import model.Professor;
import model.Turma;

import java.util.ArrayList;
@JacksonXmlRootElement(localName = "Lista")
public class Lista {
    @JacksonXmlElementWrapper(localName = "listaAlunos")
    @JacksonXmlProperty(localName = "aluno")
    public static ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();
    @JacksonXmlElementWrapper(localName = "listaDisciplina")
    @JacksonXmlProperty(localName = "disciplina")
    public static ArrayList<Disciplina> listaDisciplina = new ArrayList<Disciplina>();
    @JacksonXmlElementWrapper(localName = "listaProfessor")
    @JacksonXmlProperty(localName = "professor")
    public static ArrayList<Professor> listaProfessor = new ArrayList<Professor>();
    @JacksonXmlElementWrapper(localName = "listaTurma")
    @JacksonXmlProperty(localName = "turma")
    public static ArrayList<Turma> listaTurma = new ArrayList<Turma>();
}
