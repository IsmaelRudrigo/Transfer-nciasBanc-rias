import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cliente {

    private final String nome;
    private final String cpf;
    private double saldo;
    private final List<Transferencia> transferenciasEnviadas;
    private final List<Transferencia> transferenciasRecebidas;
    private final List<Double> depositos;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.transferenciasEnviadas = new ArrayList<>();
        this.transferenciasRecebidas = new ArrayList<>();
        this.depositos = new ArrayList<>();
        this.saldo = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<Transferencia> getTransferenciasEnviadas() {
        return Collections.unmodifiableList(transferenciasEnviadas);
    }

    public List<Transferencia> getTransferenciasRecebidas() {
        return Collections.unmodifiableList(transferenciasRecebidas);
    }



    public List<Double> getDepositos() {
        return Collections.unmodifiableList(depositos);
    }

    public boolean depositar(double valor){
        if(valor >0){
            this.saldo += valor;
            this.depositos.add(valor);
            return true;
        }
        return false;
    }

    public boolean transferencia(double valor, Transferencia transferencia){
        if(transferencia.getRemetente().getNome().equals(this.getNome())){
            this.transferenciasEnviadas.add(transferencia);
            this.saldo -= valor;
            return true;
        }else if(transferencia.getDestinatario().getNome().equals(this.getNome())){
            this.transferenciasRecebidas.add(transferencia);
            this.saldo += valor;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }

        if(this.getClass() != obj.getClass()){
            return false;
        }

        String objCpf = ((Cliente) obj).getCpf();
        return this.getCpf().equals(objCpf);
    }

    @Override
    public int hashCode() {
        return this.getCpf().hashCode() + 35;
    }
}
