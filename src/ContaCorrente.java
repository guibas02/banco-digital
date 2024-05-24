public class ContaCorrente extends Conta {

    public ContaCorrente(String agencia, String numero, Cliente cliente) {
        super(agencia, numero, cliente);
    }

    @Override
    public void exibirExtrato() {
        System.out.println("=======Extrato de Conta Corrente=======");
        super.exibirExtrato();
    }
}
