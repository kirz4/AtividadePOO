public class Servico {
    float precoServico;
    void calcularIssTotalServico(){
        System.out.println("O valor total(ISS) do Servico é de: " + (this.precoServico+(this.precoServico *0.046)));
    }
    void calcularIcmsTotalServico(){
        System.out.println("O valor total(ICMS) do Servico é de: " + (this.precoServico+(this.precoServico *0.17)));
    }
    
}
