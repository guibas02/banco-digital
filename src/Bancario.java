public class Bancario {
    private String nome;
    private String identificacao;

    public Bancario(String nome, String identificacao) {
        this.nome = nome;
        this.identificacao = identificacao;
    }

    public void exibirTodasAsContas(Banco banco){
        banco.listarConta();
    }

    public String getNome() {
        return nome;
    }

    public String getIdentificacao() {
        return identificacao;
    }
}
