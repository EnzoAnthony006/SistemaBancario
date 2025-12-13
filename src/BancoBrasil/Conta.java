public class Conta {
    private Cliente titular;
    private double saldo;

    public Conta(Cliente titular) {
        this.titular = titular;
        this.saldo = 0.0;
    }

    public Cliente getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) throws IllegalArgumentException {
        if(valor <= 0) {
            throw new IllegalArgumentException("O valor do Depósito deve ser Positivo.");
        }
        saldo += valor;
    }

    public void sacar(double valor) throws SaldoInsuficienteException {
        if(valor <= 0) {
            throw new IllegalArgumentException("O valor do Saque deve ser Positivo.");
        }
        if(valor > saldo) {
            throw new SaldoInsuficienteException("Saldo Insuficiente!");
        }
        saldo -= valor;
    }

    @Override
    public String toString() {
        return titular + " | Saldo: " + saldo;
    }
}
