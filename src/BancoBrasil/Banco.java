import java.util.*;


public class Banco {

    private List<Conta> contas;

    public Banco() {
        contas = new ArrayList<>();
    }

    public void adicionarConta(Conta conta) {
        if (!contas.contains(conta)) {
            contas.add(conta);
            System.out.println("Conta adicionada com sucesso: " +  conta.getTitular());

        } else {
            System.out.println("Conta já cadastrada!");
        }
    }

    public Conta buscarConta(String cpf) {
        return contas.stream()
                .filter(c -> c.getTitular().getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    public void transferir(String cpfOrigem, String cpfDestino, double valor)
            throws SaldoInsuficienteException {
        Conta origem = buscarConta(cpfOrigem);
        Conta destino = buscarConta(cpfDestino);

        if(origem == null || destino == null) {
            System.out.println("Uma das Contas não foi Encontrada!");
            return;
        }

        origem.sacar(valor);
        destino.depositar(valor);
        System.out.println("Transferência de " + valor + " Realizada com Sucesso!");

    }

    public void listarContas() {
        System.out.println("=== LISTA DE CONTAS ===");
        contas.forEach(System.out::println);
    }

    public void relatorioSaldoTotal() {
        double total = contas.stream()
                .mapToDouble(Conta::getSaldo)
                .sum();
        System.out.println("=== RELATÓRIO DE SALDO TOTAL ===");
        System.out.println("Número de contas: " + contas.size());
        System.out.println("Saldo Total no Banco: " + total);
    }

    public void relatorioContasNegativas() {
        System.out.println("=== RELATÓRIO DE CONTAS COM SALDO NEGATIVO ===");
        contas.stream()
                .filter(c -> c.getSaldo() < 0)
                .forEach(System.out::println);
    }
}
