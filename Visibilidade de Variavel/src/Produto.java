public class Produto{
    float precoProduto;

    void calcularIcmsTotalProduto(){
        System.out.println("O valor total(ICMS) do produto é de: " + (this.precoProduto+(this.precoProduto *0.17)));
    }
    void calcularIpiTotalProduto(){
        System.out.println("O valor total(IPI) do produto é de: " + (this.precoProduto+(this.precoProduto *0.25)));
    }

}