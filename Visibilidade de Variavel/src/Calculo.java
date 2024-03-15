public class Calculo{
    public void calcularIss(){
        Servico iss = new Servico();
        iss.precoServico = 20.05f;
        iss.calcularIssTotalServico();
        
    }
    public void calcularIcmsProduto(){
        Produto icmsP = new Produto();
        icmsP.precoProduto = 14.50f;
        icmsP.calcularIcmsTotalProduto();
    }
    public void calcularIcmsServico(){
        Servico icmsS = new Servico();
        icmsS.precoServico = 20.05f;
        icmsS.calcularIcmsTotalServico();
        }
    public void calcularIpi(){
        Produto ipi = new Produto();
        ipi.precoProduto = 14.50f;
        ipi.calcularIpiTotalProduto();
    }
}