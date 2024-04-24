public class Professor extends Pessoa{
    private String disciplinaTitular;
    public void setDisciplinaTitular(String disciplinaTitular){
        this.disciplinaTitular = disciplinaTitular;
    }
    public String getDisciplinaTitular(){
        return this.disciplinaTitular;
    }
    public Professor (String nome, String disciplinaTitular){
        super(nome);
        this.setDisciplinaTitular(disciplinaTitular);
        
    }
}
