import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();
        Scanner sc = new Scanner(System.in);
        boolean rodando = true;

        while (rodando) {
            System.out.println(" ");
            System.out.println("=== BANCO BRASIL ===");
            System.out.println(" ");
            System.out.println("1 - Adicionar Conta");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("4 - Transferir");
            System.out.println("5 - Listar Contas");
            System.out.println("6 - Relatório: Saldo Total");
            System.out.println("7 - Relatório: Contas Negativas");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine(); // consumir enter

            switch(opcao) {
                case 1:
                    System.out.print("Nome do cliente: ");
                    String nome = sc.nextLine();
                    System.out.print("CPF do cliente: ");
                    String cpf = sc.nextLine();
                    Cliente cliente = new Cliente(nome, cpf);
                    Conta conta = new Conta(cliente);
                    banco.adicionarConta(conta);
                    break;

                case 2:
                    System.out.print("CPF da conta: ");
                    cpf = sc.nextLine();
                    Conta contaDeposito = banco.buscarConta(cpf);
                    if(contaDeposito == null) {
                        System.out.println("Conta não encontrada!");
                        break;
                    }
                    System.out.print("Valor para depositar: ");
                    double valor = sc.nextDouble();
                    try {
                        contaDeposito.depositar(valor);
                        System.out.println("Depósito realizado com sucesso!");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("CPF da conta: ");
                    cpf = sc.nextLine();
                    Conta contaSaque = banco.buscarConta(cpf);
                    if(contaSaque == null) {
                        System.out.println("Conta não encontrada!");
                        break;
                    }
                    System.out.print("Valor para sacar: ");
                    valor = sc.nextDouble();
                    try {
                        contaSaque.sacar(valor);
                        System.out.println("Saque realizado com sucesso!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("CPF da conta de origem: ");
                    String origem = sc.nextLine();
                    System.out.print("CPF da conta de destino: ");
                    String destino = sc.nextLine();
                    System.out.print("Valor para transferir: ");
                    valor = sc.nextDouble();
                    try {
                        banco.transferir(origem, destino, valor);
                    } catch (SaldoInsuficienteException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    banco.listarContas();
                    break;

                case 6:
                    banco.relatorioSaldoTotal();
                    break;

                case 7:
                    banco.relatorioContasNegativas();
                    break;

                case 0:
                    rodando = false;
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
            sc.nextLine(); // consumir enter
        }

        sc.close();
    }
}
