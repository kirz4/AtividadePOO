public class Aluno extends Pessoa{
    private String curso;
    public void setCurso(String curso){
        this.curso = curso;
    }
    public String getCurso(){
        return this.curso;
    }

    public Aluno (String nome, String curso){
        super(nome);
        this.setCurso(curso);
    }

}