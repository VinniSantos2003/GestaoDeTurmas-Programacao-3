package utilities;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import model.Aluno;
import model.Disciplina;
import model.Professor;
import model.Turma;
import model.staticModels.Lista;

import java.util.ArrayList;
@JacksonXmlRootElement(localName = "TempXMLList")
public class TempXMLList {

    @JacksonXmlElementWrapper(localName = "listaAlunos")
    @JacksonXmlProperty(localName = "aluno")
    public  ArrayList<Aluno> listaAlunos = Lista.listaAlunos;
    @JacksonXmlElementWrapper(localName = "listaDisciplina")
    @JacksonXmlProperty(localName = "disciplina")
    public  ArrayList<Disciplina> listaDisciplina = Lista.listaDisciplina;
    @JacksonXmlElementWrapper(localName = "listaProfessor")
    @JacksonXmlProperty(localName = "professor")
    public  ArrayList<Professor> listaProfessor =Lista.listaProfessor;
    @JacksonXmlElementWrapper(localName = "listaTurma")
    @JacksonXmlProperty(localName = "turma")
    public  ArrayList<Turma> listaTurma = Lista.listaTurma;


    protected void importMode(){
        Lista.listaAlunos = listaAlunos;
        Lista.listaProfessor = listaProfessor;
        Lista.listaDisciplina = listaDisciplina;
        Lista.listaTurma = listaTurma;
    }
    protected void exportMode(){
        listaAlunos = Lista.listaAlunos;
        listaDisciplina = Lista.listaDisciplina;
        listaProfessor = Lista.listaProfessor;
        listaTurma = Lista.listaTurma;
    }
}
