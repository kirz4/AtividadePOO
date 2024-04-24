import java.util.ArrayList;
import java.util.List;

public class Ensalamento {
//  Aluno alunos[] = new Aluno[20];
//  Professor professores[] = new Professor[3];
    private int contadorTI= 0;
    private int contadorADM= 0;
    private List<Professor> professores1 = new ArrayList<Professor>();
    private List<Aluno> alunos1 = new ArrayList<Aluno>();

    
    public Ensalamento() {
        alunos1.add(new Aluno("Alfredo", "TI"));
        alunos1.add(new Aluno("Amélia", "TI"));
        alunos1.add(new Aluno("Ana", "ADM"));
        alunos1.add(new Aluno("Bruno", "TI"));
        alunos1.add(new Aluno("Bentinho", "ADM"));
        alunos1.add(new Aluno("Capitú", "TI"));
        alunos1.add(new Aluno("Debra", "TI"));
        alunos1.add(new Aluno("Ian", "ADM"));
        alunos1.add(new Aluno("Iracema", "TI"));
        alunos1.add(new Aluno("Joelmir", "ADM"));
        alunos1.add(new Aluno("Julieta", "TI"));
        alunos1.add(new Aluno("Luana", "ADM"));
        alunos1.add(new Aluno("Luciana", "TI"));
        alunos1.add(new Aluno("Majô", "ADM"));
        alunos1.add(new Aluno("Maria", "ADM"));
        alunos1.add(new Aluno("Norberto", "TI"));
        alunos1.add(new Aluno("Paulo", "ADM"));
        alunos1.add(new Aluno("Romeu", "ADM"));
        alunos1.add(new Aluno("Wendel", "TI"));
        alunos1.add(new Aluno("Zoey", "TI"));


        professores1.add(new Professor("Mia", "POO"));
        professores1.add(new Professor("Saulo", "Estrutura de Dados"));
        professores1.add(new Professor("Paula", "BI"));

        
    }
    private void EnsalarTurma1(List<Aluno> alunos1, List<Professor> professores1){
        System.out.println("Turma 1:\nDisciplina - " + professores1.get(0).getDisciplinaTitular() + " do Curso de TI");
        System.out.println("Professor(a): " + professores1.get(0).getNome());
        for (Aluno aluno : alunos1)
        {
            if(aluno.getCurso().equals("TI")){
                contadorTI += 1;
                //System.out.format("Aluno: %s - Curso: %s\n", aluno.getNome(), aluno.getCurso());
            }
        }
        System.out.format("Quantidade de Alunos - Turma 1: %d alunos\n", contadorTI);
    }
    private void EnsalarTurma2(List<Aluno> alunos1, List<Professor> professores1){
        System.out.println("\nTurma 2:\nDisciplina - " + professores1.get(1).getDisciplinaTitular() + " do Curso de TI");
        System.out.println("Professor(a): " + professores1.get(1).getNome());
        contadorTI = 0;
        for (Aluno aluno : alunos1)
        {
            if(aluno.getCurso().equals("TI")){
                contadorTI += 1;
                System.out.format("Aluno: %s - Curso: %s\n", aluno.getNome(), aluno.getCurso());
            }
        }
        System.out.format("Quantidade de Alunos - Turma 1: %d alunos\n", contadorTI);
    }
    private void EnsalarTurma3(List<Aluno> alunos1, List<Professor> professores1){
        System.out.println("\nTurma 3:\nDisciplina - " + professores1.get(2).getDisciplinaTitular() + " do Curso de ADM");
        System.out.println("Professor(a): " + professores1.get(2).getNome());
        for (Aluno aluno : alunos1)
        {
            if(aluno.getCurso().equals("ADM")){
                contadorADM += 1;
                System.out.format("Aluno: %s - Curso: %s\n", aluno.getNome(), aluno.getCurso());
            }
        }
        System.out.format("Quantidade de Alunos - Turma 3: %d alunos\n", contadorADM);
        
    }
    public void EnsalarTodasTurmas(){
        EnsalarTurma1(alunos1, professores1);
        EnsalarTurma2(alunos1, professores1);
        EnsalarTurma3(alunos1, professores1);
    }

}
