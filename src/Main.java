import javax.swing.*;
import java.util.Scanner;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Banco do guibas");
        Object[] options = {"Cliente", "Bancario"};
        int opcao = JOptionPane.showInternalOptionDialog(null,
                "Você deseja acessar o sistema como cliente ou como bancario?", "Banco Digital do Guibas",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (opcao == 0) {
            Object[] opcaoCliente = {"Criar Conta", "Acessar Conta Existente"};
            int acessoCliente = JOptionPane.showInternalOptionDialog(null,
                    "Você deseja acessar utilizando uma conta existente ou quer criar uma conta?", "Bem vindo cliente!",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcaoCliente, opcaoCliente[0]);

            Conta conta = null;
            if (acessoCliente == 0) {
                String nome = JOptionPane.showInputDialog("Digite seu nome: ");
                int idade = Integer.parseInt(JOptionPane.showInputDialog("Digite sua idade"));
                if (idade < 18) {
                    JOptionPane.showMessageDialog(null,
                            "Você não pode criar uma conta.", "Alerta - Menor de idade",
                            JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                } else {
                    Cliente cliente = new Cliente(nome, idade);
                    Object[] opcaoConta = {"Conta Corrente", "Conta Poupança"};
                    int tipoConta = JOptionPane.showInternalOptionDialog(null,
                            "Que tipo de conta você deseja criar?", null,
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcaoConta, opcaoConta[0]);

                    if (tipoConta == 0) {
                        String agencia = JOptionPane.showInputDialog("Digite a agencia: ");
                        String numero = JOptionPane.showInputDialog("Digite o numero da conta:");
                        conta = new ContaCorrente(agencia, numero, cliente);
                        banco.adicionarConta(conta);
                        JOptionPane.showMessageDialog(null, "Conta criada com sucesso");
                    } else if (tipoConta == 1) {
                        String agencia = JOptionPane.showInputDialog("Digite a agencia: ");
                        String numero = JOptionPane.showInputDialog("Digite o numero da conta:");
                        conta = new ContaPoupanca(agencia, numero, cliente);
                        banco.adicionarConta(conta);
                        JOptionPane.showMessageDialog(null, "Conta criada com sucesso");
                    }
                }
            } else if (acessoCliente == 1) {
                String numeroConta = JOptionPane.showInputDialog("Digite o número da conta:");
                for (Conta c : banco.getContas()) {
                    if (c.getNumero().equals(numeroConta)) {
                        conta = c;
                        break;
                    }
                }

                if (conta == null) {
                    JOptionPane.showMessageDialog(null, "Conta não encontrada.");
                    System.exit(1);
                }
            }

            if (conta != null) {
                boolean clienteMenu = true;
                while (clienteMenu) {
                    Object[] listaOperacoes = {"Saque", "Deposito", "Transferencia", "Extrato", "Sair"};
                    int operacaoEscolhida = JOptionPane.showInternalOptionDialog(null,
                            "Escolha uma operação", "Operacoes Disponiveis",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, listaOperacoes, listaOperacoes[0]);

                    switch (operacaoEscolhida) {
                        case 0:
                            double valorSaque = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do saque:"));
                            conta.sacar(valorSaque);
                            break;
                        case 1:
                            double valorDeposito = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do depósito:"));
                            conta.depositar(valorDeposito);
                            break;
                        case 2:
                            String numeroContaDestino = JOptionPane.showInputDialog("Digite o número da conta destino:");
                            Conta contaDestino = null;
                            for (Conta c : banco.getContas()) {
                                if (c.getNumero().equals(numeroContaDestino)) {
                                    contaDestino = c;
                                    break;
                                }
                            }
                            if (contaDestino == null) {
                                JOptionPane.showMessageDialog(null, "Conta destino não encontrada.");
                            } else {
                                double valorTransferencia = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da transferência:"));
                                conta.transferir(valorTransferencia, contaDestino);
                            }
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(null, "Saldo atual: " + conta.getSaldo());
                            break;
                        case 4:
                            clienteMenu = false;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida.");
                            break;
                    }
                }
            }
        } else if (opcao == 1) {
            String nome = JOptionPane.showInputDialog("Digite seu nome: ");
            String identificacao = JOptionPane.showInputDialog("Digite sua identificação: ");
            Bancario bancario = new Bancario(nome, identificacao);
            StringBuilder sb = new StringBuilder();
            for (Conta conta : banco.getContas()) {
                sb.append("Agência: ").append(conta.getAgencia())
                        .append(", Número: ").append(conta.getNumero())
                        .append(", Cliente: ").append(conta.getCliente().getNome())
                        .append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Contas Cadastradas", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

