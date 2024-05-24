public class ContaPoupanca extends Conta {

    public ContaPoupanca(String agencia, String numero, Cliente cliente) {
        super(agencia, numero, cliente);
    }

    @Override
    public void exibirExtrato() {
        System.out.println("=======Extrato de Conta Corrente=======");
        super.exibirExtrato();
    }
}
