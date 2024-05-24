import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String nome;
    private List<Conta> contas;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    public void adicionarConta(Conta conta){
        contas.add(conta);
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void listarConta(){
        for (Conta conta : contas){
            System.out.println("Agencia: " + conta.agencia);
            System.out.println("Numero: " + conta.numero);
            System.out.println("Cliente: " + conta.cliente.getNome());
            System.out.println("-------------------------------------");
        }
    }
}
