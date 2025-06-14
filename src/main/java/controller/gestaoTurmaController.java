package controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import model.staticModels.*;

import java.net.URL;
import java.util.ResourceBundle;



public class gestaoTurmaController implements Initializable {
    public gestaoTurmaController(){

    }
    //PERMITIR A CRIAÇÃO DA CLASSE TURMA SE ALUNOS
    //Cadastro Curso
    @FXML private TextField fieldClassStudentsCapacity;
    @FXML private TextField fieldClassTeacherName;
    @FXML private TextField fieldClassDiscipline;
    @FXML private ComboBox<Curso> comboBoxCurso;
    @FXML private TableView selectedStudents;
    @FXML private TableView registredTeachers;
    @FXML private TableView registredStudents;
    @FXML private TableView registredDiscipline;
    @FXML private Button saveClassButton;
    @FXML private Button saveEditedClassButton;
    @FXML private Button removeStudentFromClassButton;
    //Professor
    @FXML private TextField fieldTeacherName;
    @FXML private ComboBox<Formacao> comboboxFormacao;
    @FXML private TableView tableTeachers;
    @FXML private TableColumn columnTeacherNameClass;
    @FXML private TableColumn columnTeacherFormationClass;
    @FXML private TableColumn columnTeacherName;
    @FXML private TableColumn columnTeacherFormation;
    @FXML private Button saveEditedTeacherButton;
    @FXML private Button saveTeacherButton;
    //Aluno
    @FXML private TextField fieldStudentName;
    @FXML private TextField fieldStudentRegistration;
    @FXML private TableView tableStudent;
    @FXML private TableColumn columnStudentNameClass;
    @FXML private TableColumn columnStudentRegistrationClass;
    @FXML private TableColumn columnSelectedStudentNameClass;
    @FXML private TableColumn columnSelectedStudentRegistrationClass;
    @FXML private TableColumn columnStudentName;
    @FXML private TableColumn columnStudentRegistration;
    @FXML private Button saveStudentButton;
    @FXML private Button saveEditedStudentButton;
    //Disciplina
    @FXML private TextField  fieldDisciplineName;
    @FXML private TextField fieldDisciplineCredits;
    @FXML private TableView tableDiscipline;
    @FXML private TableColumn columnDisciplineNameClass;
    @FXML private  TableColumn columnDisciplineCreditsClass;
    @FXML private TableColumn columnDisciplineName;
    @FXML private  TableColumn columnDisciplineCredits;
    @FXML private Button saveEditedDisciplineButton;
    @FXML private Button saveDisciplineButton;
    //Turma - Edição Turma
    @FXML TableView registeredClass;
    @FXML TableColumn<Turma,String> registeredClassDisciplineColumn;
    @FXML TableColumn<Turma,String> registeredClassTeacherColumn;
    @FXML TableColumn<Turma,String> registeredClassCourseColumn;
    @FXML TableColumn<Turma,String> registeredClassStudentsCapacityColumn;
    @FXML Button editClass;
    @FXML Button deleteClass;
    @FXML Button saveEditedClass;
    // Variaveis auxiliares
    private Professor waitingTeacher;
    private Aluno waitingStudent;
    private Disciplina waitingDiscipline;
    private Turma waitingClass = null;
    private Disciplina disciplineToBeEdit;
    private Professor teacherToBeEdit;
    private Aluno studentToBeEdit;
    private Turma classToBeEdit;
    private ObservableList<Aluno> OLSelectedStudents = FXCollections.observableArrayList();
    private ObservableList<Professor> OLTeacher = FXCollections.observableArrayList();
    private ObservableList<Aluno> OLStudent = FXCollections.observableArrayList();
    private ObservableList<Disciplina> OLDiscipline = FXCollections.observableArrayList();
    private ObservableList<Turma> OLClass = FXCollections.observableArrayList();
    @FXML private TabPane mainTabPane;

    //Methods
    @FXML public void saveClass(){
        try{
            if(waitingClass == null){
                waitingClass = new Turma();
            }
            if(waitingTeacher == null || waitingDiscipline == null){
                showAlert("Aviso",
                        "Erro ao registrar turma",
                        "Por favor, selecione um professor e uma disciplina",
                        Alert.AlertType.WARNING);
            }else{
            /*
            - Antes de tudo, verificar se o campo de capacidade de alunos foi preenchido de maneira correta ( somente números)
            - É possível salvar uma turma sem alunos
            - Alunos são adicionados em setStudents
            - Fazer um check em fieldClassStudentsCapacity para verificar se está vazio ou não, se estiver,
            setar a capacidade de alunos da turma para um máximo de 50
            */
                //waitingClass.setCapacidadeAlunos(50);
                if(OLSelectedStudents.size() > Integer.parseInt(fieldClassStudentsCapacity.getText())){
                    showAlert("Aviso",
                            "Erro ao registrar turma",
                            "Por favor, aumente a capacidade da turma ou reduza a quantidade de alunos inscritos",
                            Alert.AlertType.WARNING);
                }else{
                    waitingClass.setCurso(comboBoxCurso.getValue());
                    waitingClass.setProfessorTitular(waitingTeacher);
                    waitingClass.setDisciplina(waitingDiscipline);
                    waitingClass.setCapacidadeAlunos(Integer.parseInt(fieldClassStudentsCapacity.getText()));
                    for (int i = 0; i < OLSelectedStudents.size(); i++) {
                        waitingClass.getListaAlunos().add(OLSelectedStudents.get(i));
                    }
                    if(!listaTurma.listaTurma.contains(waitingClass)){
                        listaTurma.listaTurma.add(waitingClass);
                    }
                    saveClassButton.setDisable(false);
                    saveEditedClassButton.setDisable(true);
                    clearAllField();
                    buildClassTable();
                    waitingClass = null;
                    System.out.println("Class Saved");
                }
            }
        } catch (ArithmeticException e) {
            showAlert("Aviso",
                    "Erro ao gravar capacidade de alunos",
                    "O campo de capacidade de alunos só aceita números",
                    Alert.AlertType.ERROR);
        }
    }
    @FXML public void editClass(){
        try{
            clearAllField();
            waitingClass = listaTurma.listaTurma.get(registeredClass.getSelectionModel().getSelectedIndex());
            waitingDiscipline = waitingClass.getDisciplina();
            waitingTeacher = waitingClass.getProfessorTitular();
            mainTabPane.getSelectionModel().selectFirst();
            clearMainScreen();
            OLSelectedStudents.addAll(waitingClass.getListaAlunos());
            fieldClassStudentsCapacity.setText(String.valueOf(waitingClass.getCapacidadeAlunos()));
            fieldClassTeacherName.setText(waitingClass.getProfessorTitular().getNomeProfessor());
            fieldClassDiscipline.setText(waitingClass.getDisciplina().getNomeDisciplina());
            comboBoxCurso.getSelectionModel().select(waitingClass.getCurso());
            saveClassButton.setDisable(true);
            saveEditedClassButton.setDisable(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML public void deletedClass(){
        if(registeredClass.getSelectionModel().getSelectedIndex() ==-1){
            showAlert("Aviso",
                    "Item selecionado inválido",
                    "Por favor, selecione uma turma para deleção",
                    Alert.AlertType.WARNING);
        }else{
            listaTurma.listaTurma.remove(registeredClass.getSelectionModel().getSelectedIndex());
            buildClassTable();

        }

    }

    @FXML public void saveTeacher(){
        if(fieldTeacherName.getText().isEmpty() || comboboxFormacao.getSelectionModel().getSelectedIndex() == -1){
            showAlert("Aviso",
                    "Campos incompletos",
                    "Por favor, preencha todos os campos",
                    Alert.AlertType.WARNING);
        }else{
            Professor p = new Professor();
            p.setNomeProfessor(fieldTeacherName.getText());
            p.setGrauFormacao(comboboxFormacao.getSelectionModel().getSelectedItem());
            listaProfessor.listaProfessor.add(p);
            clearAllField();
            buildTeacherTable();
        }
    }
    @FXML public void setTeacher(){
        int index = registredTeachers.getSelectionModel().getSelectedIndex();
        waitingTeacher = listaProfessor.listaProfessor.get(index); // Tenho que testar
        fieldClassTeacherName.setText(waitingTeacher.getNomeProfessor());
        System.out.println("Teacher selected");
    }
    @FXML public void editTeacher(){
        teacherToBeEdit = listaProfessor.listaProfessor.get(tableTeachers.getSelectionModel().getSelectedIndex());
        saveTeacherButton.setDisable(true);
        saveEditedTeacherButton.setDisable(false);
        fieldTeacherName.setText(teacherToBeEdit.getNomeProfessor());
        comboboxFormacao.getSelectionModel().select(teacherToBeEdit.getGrauFormacao());
    }
    @FXML public void saveEditedTeacher(){
        teacherToBeEdit.setGrauFormacao(comboboxFormacao.getSelectionModel().getSelectedItem());
        teacherToBeEdit.setNomeProfessor(fieldTeacherName.getText());
        saveTeacherButton.setDisable(false);
        saveEditedTeacherButton.setDisable(true);
        buildTeacherTable();
    }
    @FXML public void deleteTeacher(){
        if(tableTeachers.getSelectionModel().getSelectedIndex() == -1){
            showAlert("Aviso",
                    "Item selecionado inválido",
                    "Por favor, selecione um professor para deleção",
                    Alert.AlertType.WARNING);
        }else{
            listaProfessor.listaProfessor.remove(tableTeachers.getSelectionModel().getSelectedIndex());
            buildTeacherTable();
        }
    }

    @FXML public void saveStudent(){
        try{
            if(fieldStudentName.getText().isEmpty() || fieldStudentRegistration.getText().isEmpty()) {
                showAlert("Aviso",
                        "Campos incompletos",
                        "Por favor, preencha todos os campos ",
                        Alert.AlertType.WARNING);
            }else{
                Aluno aluno = new Aluno();
                aluno.setMatriculaAluno(Integer.parseInt(fieldStudentRegistration.getText()));
                aluno.setNomeAluno(fieldStudentName.getText());
                listaAlunos.listaAlunos.add(aluno);
                clearAllField();
                buildStudentTable();
            }
        }catch (NumberFormatException e){
            showAlert("Erro",
                    "Preencha o(s) campo(s) corretamente",
                    "O campo de matricula só aceita números",
                    Alert.AlertType.ERROR);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML public void setStudents(){
        int i = registredStudents.getSelectionModel().getSelectedIndex();
        if(OLSelectedStudents.contains(OLStudent.get(i))){
            showAlert("Aviso",
                    "Aluno duplicado",
                    "O aluno selecionado já está inserido na turma, por favor selecione outro",
                    Alert.AlertType.WARNING);
        }else {
            OLSelectedStudents.add(OLStudent.get(i));
        }
        System.out.println("Student selected");
    }
    @FXML public void editStudent(){
        studentToBeEdit = listaAlunos.listaAlunos.get(tableStudent.getSelectionModel().getSelectedIndex());
        saveStudentButton.setDisable(true);
        saveEditedStudentButton.setDisable(false);
        fieldStudentName.setText(studentToBeEdit.getNomeAluno());
        fieldStudentRegistration.setText(String.valueOf(studentToBeEdit.getMatriculaAluno()));
    }
    @FXML public void saveEditedStudent(){
        studentToBeEdit.setNomeAluno(fieldStudentName.getText());
        studentToBeEdit.setMatriculaAluno(Integer.parseInt(fieldStudentRegistration.getText()));
        saveStudentButton.setDisable(false);
        saveEditedStudentButton.setDisable(true);
        buildStudentTable();
    }
    @FXML public void deleteStudent(){
        if(tableStudent.getSelectionModel().getSelectedIndex() == -1){
            showAlert("Aviso",
                    "Item selecionado inválido",
                    "Por favor, selecione um aluno para deleção",
                    Alert.AlertType.WARNING);
        }else{
            listaAlunos.listaAlunos.remove(tableStudent.getSelectionModel().getSelectedIndex());
            buildStudentTable();
        }
    }

    @FXML public void saveDiscipline(){
        try{
            if(fieldDisciplineName.getText().isEmpty() || fieldDisciplineCredits.getText().isEmpty()){
                showAlert("Aviso",
                        "Campos incompletos",
                        "Por favor, preencha todos os campos ",
                        Alert.AlertType.WARNING);
            }else{
                Disciplina d = new Disciplina();
                d.setNomeDisciplina(fieldDisciplineName.getText());
                d.setCreditos(Integer.parseInt(fieldDisciplineCredits.getText()));
                listaDisciplina.listaDisciplina.add(d);
                clearAllField();
                buildDisciplineTable();
            }
        } catch (NumberFormatException e) {
            showAlert("Erro",
                    "Preencha o(s) campo(s) corretamente",
                    "O campo de créditos só aceita números",
                    Alert.AlertType.ERROR);
        }
    }
    @FXML public void setDiscipline(){
        int i = registredDiscipline.getSelectionModel().getSelectedIndex();
        waitingDiscipline = listaDisciplina.listaDisciplina.get(i);
        fieldClassDiscipline.setText(waitingDiscipline.getNomeDisciplina());
        System.out.println("Course selected");
    }
    @FXML public void editDiscipline(){
        disciplineToBeEdit = listaDisciplina.listaDisciplina.get(tableDiscipline.getSelectionModel().getSelectedIndex());
        saveEditedDisciplineButton.setDisable(false);
        registredDiscipline.setDisable(true);
        fieldDisciplineName.setText(disciplineToBeEdit.getNomeDisciplina());
        fieldDisciplineCredits.setText(String.valueOf(disciplineToBeEdit.getCreditos()));
    }
    @FXML public void saveEditedDiscipline(){
        disciplineToBeEdit.setNomeDisciplina(fieldDisciplineName.getText());
        disciplineToBeEdit.setCreditos(Integer.parseInt(fieldDisciplineCredits.getText()));
        saveEditedDisciplineButton.setDisable(true);
        registredDiscipline.setDisable(false);
        buildDisciplineTable();
    }
    @FXML public void deleteDiscipline(){
        if(tableDiscipline.getSelectionModel().getSelectedIndex() == -1){
            showAlert("Aviso",
                    "Item selecionado inválido",
                    "Por favor, selecione uma disciplina para deleção",
                    Alert.AlertType.WARNING);
        }else{
            listaDisciplina.listaDisciplina.remove(tableDiscipline.getSelectionModel().getSelectedIndex());
            buildDisciplineTable();
        }
    }
    @FXML public void setStudentsCapacity(){
        try{
            if(fieldClassStudentsCapacity.getText() == ""){
                waitingClass.setCapacidadeAlunos(0);
            }else{
                waitingClass.setCapacidadeAlunos(Integer.parseInt(fieldClassStudentsCapacity.getText()));
            }
            System.out.println("Students capacity set");
        } catch (ArithmeticException e) {
            Alert a  = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Erro ao gravar capacidade de estudantes");
            a.setContentText("O campo de capacidade só aceita caracteres numericos");
            a.show();
        }catch ( Exception e){
            e.printStackTrace();
        }
    }

    @FXML public void removeStudentFromClass(){
        if(selectedStudents.getSelectionModel().getSelectedIndex() == -1){
            showAlert("Aviso",
                    "Erro ao selecionar aluno",
                    "Selecione um aluno para a remoção do mesmo de uma turma",
                    Alert.AlertType.WARNING);
        }else {
            OLSelectedStudents.remove(selectedStudents.getSelectionModel().getSelectedIndex());
        }
    }
    //Utility - FXML
    @FXML public void clearAllField(){
        if(mainTabPane.getSelectionModel().getSelectedIndex() == 0){
            //Class Register
            fieldClassStudentsCapacity.clear();
            waitingTeacher = null;
            waitingDiscipline = null;
            OLSelectedStudents.clear();
            comboBoxCurso.getSelectionModel().select(-1);
            fieldClassTeacherName.setText("");
            fieldClassDiscipline.setText("");
        }else if(mainTabPane.getSelectionModel().getSelectedIndex() == 1){
            //Edit Class
        }else if(mainTabPane.getSelectionModel().getSelectedIndex() == 2){
            //Teacher
            fieldTeacherName.clear();
        }else if(mainTabPane.getSelectionModel().getSelectedIndex() == 3){
            //Discipline
            fieldDisciplineName.clear();
            fieldDisciplineCredits.clear();
        }else if(mainTabPane.getSelectionModel().getSelectedIndex() == 4){
            //Student
            fieldStudentName.clear();
            fieldStudentRegistration.clear();
        }
        System.out.println("Fields cleared");
    }
    //Utility
    private void clearMainScreen(){
        //variação do metodo clearAllField sem o waintingTeacher e waitingDiscipline = null
        fieldClassStudentsCapacity.clear();
        OLSelectedStudents.clear();
        comboBoxCurso.getSelectionModel().select(-1);
        fieldClassTeacherName.setText("");
        fieldClassDiscipline.setText("");
    }
    private void showAlert(String title, String headerText, String contentText, Alert.AlertType alertType){
        Alert a = new Alert(alertType);
        a.setTitle(title);
        a.setHeaderText(headerText);
        a.setContentText(contentText);
        a.show();
    }
    private void buildTeacherTable(){
        OLTeacher.clear();
        OLTeacher.addAll(listaProfessor.listaProfessor);
    }
    private void buildStudentTable(){
        OLStudent.clear();
        OLStudent.addAll(listaAlunos.listaAlunos);
    }
    private void buildDisciplineTable(){
        OLDiscipline.clear();
        OLDiscipline.addAll(listaDisciplina.listaDisciplina);
    }
    private void buildClassTable(){
        OLClass.clear();
        OLClass.addAll(listaTurma.listaTurma);
    }
    private void setUpTables(){
        //Tables on TabPane index 0
        columnTeacherNameClass.setCellValueFactory(new PropertyValueFactory<>("nomeProfessor"));
        columnTeacherFormationClass.setCellValueFactory(new PropertyValueFactory<>("grauFormacao"));
        columnStudentNameClass.setCellValueFactory(new PropertyValueFactory<>("nomeAluno"));
        columnStudentRegistrationClass.setCellValueFactory(new PropertyValueFactory<>("matriculaAluno"));
        columnDisciplineNameClass.setCellValueFactory(new PropertyValueFactory<>("nomeDisciplina"));
        columnDisciplineCreditsClass.setCellValueFactory(new PropertyValueFactory<>("creditos"));
        columnSelectedStudentNameClass.setCellValueFactory(new PropertyValueFactory<>("nomeAluno"));
        columnSelectedStudentRegistrationClass.setCellValueFactory(new PropertyValueFactory<>("matriculaAluno"));
        selectedStudents.getColumns().clear();
        selectedStudents.getColumns().addAll(columnSelectedStudentNameClass,columnSelectedStudentRegistrationClass);
        registredStudents.getColumns().clear();
        registredStudents.getColumns().addAll(columnStudentNameClass,columnStudentRegistrationClass);
        registredDiscipline.getColumns().clear();
        registredDiscipline.getColumns().addAll(columnDisciplineNameClass,columnDisciplineCreditsClass);
        registredTeachers.getColumns().clear();
        registredTeachers.getColumns().addAll(columnTeacherNameClass, columnTeacherFormationClass);
        selectedStudents.setItems(OLSelectedStudents);
        registredDiscipline.setItems(OLDiscipline);
        registredTeachers.setItems(OLTeacher);
        registredStudents.setItems(OLStudent);
        //Tables on TabPane index 1
        registeredClassTeacherColumn.setCellValueFactory(cellData -> {
            Professor p = cellData.getValue().getProfessorTitular();
            return new SimpleStringProperty(p != null ? p.getNomeProfessor(): "");
        });
        registeredClassCourseColumn.setCellValueFactory(cellData->{
            Enum<Curso> c = cellData.getValue().getCurso();
            return new SimpleStringProperty(c != null ? String.valueOf(c) : "");
        });
        registeredClassDisciplineColumn.setCellValueFactory(cellData ->{
            Disciplina d = cellData.getValue().getDisciplina();
            return new SimpleStringProperty(d != null ? d.getNomeDisciplina(): "");
        });
        registeredClassStudentsCapacityColumn.setCellValueFactory(cellData ->{
            Integer i = cellData.getValue().getCapacidadeAlunos();
            return new SimpleStringProperty(String.valueOf(i));
        });
        registeredClass.getColumns().clear();
        registeredClass.getColumns().addAll(registeredClassDisciplineColumn,registeredClassCourseColumn,registeredClassTeacherColumn,registeredClassStudentsCapacityColumn);
        registeredClass.setItems(OLClass);
        //Tables on TabPane index 2
        columnTeacherName.setCellValueFactory(new PropertyValueFactory<>("nomeProfessor"));
        columnTeacherFormation.setCellValueFactory(new PropertyValueFactory<>("grauFormacao"));
        tableTeachers.getColumns().clear();
        tableTeachers.getColumns().addAll(columnTeacherName, columnTeacherFormation);
        tableTeachers.setItems(OLTeacher);
        //Tables on TabPane index 4
        columnStudentName.setCellValueFactory(new PropertyValueFactory<>("nomeAluno"));
        columnStudentRegistration.setCellValueFactory(new PropertyValueFactory<>("matriculaAluno"));
        tableStudent.getColumns().clear();
        tableStudent.getColumns().addAll(columnStudentName,columnStudentRegistration);
        tableStudent.setItems(OLStudent);
        //Tables on TabPane index 3
        columnDisciplineName.setCellValueFactory(new PropertyValueFactory<>("nomeDisciplina"));
        columnDisciplineCredits.setCellValueFactory(new PropertyValueFactory<>("creditos"));
        tableDiscipline.getColumns().clear();
        tableDiscipline.getColumns().addAll(columnDisciplineName,columnDisciplineCredits);
        tableDiscipline.setItems(OLDiscipline);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpTables();

        //Configuração da table view professor
        comboboxFormacao.setItems(FXCollections.observableArrayList(Formacao.values()));
        comboBoxCurso.setItems(FXCollections.observableArrayList(Curso.values()));
    }
}
