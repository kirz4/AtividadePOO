public class Pessoa {
    public Pessoa(String nome){
        this.nome = nome;
    }
    private String nome;

    public void setNome(String nome){
        this.nome = nome;
    }
    public String getNome(){
        return this.nome;
    }
}
