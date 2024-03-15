public class Main {
    public static void main(String[] args) {
        Calculo issTotal = new Calculo();
        issTotal.calcularIss();

        Calculo icmsTotalProduto = new Calculo();
        icmsTotalProduto.calcularIcmsProduto();

        Calculo icmsTotalServico = new Calculo();
        icmsTotalServico.calcularIcmsServico();

        Calculo ipitotal = new Calculo();
        ipitotal.calcularIpi();
        
    }
}
