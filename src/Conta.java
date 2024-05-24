import javax.swing.*;

public class Conta implements IConta {
    protected String agencia;
    protected String numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(String agencia, String numero, Cliente cliente) {
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = 0.0;
        this.cliente = cliente;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    @Override
    public void sacar(double valor) {
        if (valor > saldo){
            JOptionPane.showMessageDialog(null,"Saldo insuficiente.");
        }else{
            saldo -= valor;
            JOptionPane.showMessageDialog(null,"Saque de " + valor + " realizado com sucesso." +
                    "\n" + "Seu saldo disponível ainda é de: " + saldo);
        }
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;

        JOptionPane.showMessageDialog(null,"Deposito de " + valor + " realizado com sucesso." +
                "\n" + "Seu saldo disponível ainda é de: " + saldo);
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        if (valor > saldo){
            JOptionPane.showMessageDialog(null,"Saldo insuficiente.");
        }else{
            sacar(valor);
            contaDestino.depositar(valor);
            JOptionPane.showMessageDialog(null,"Transferencia realizada com sucesso.");
        }
    }

    @Override
    public void exibirExtrato() {
        System.out.println("Titular: " + cliente.getNome());
        System.out.println("Numero da conta: " + numero);
        System.out.println("Agencia:" + agencia);
        System.out.println("Saldo: " + saldo);
    }
}
