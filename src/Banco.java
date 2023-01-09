import java.util.ArrayList;
import java.util.List;

public class Banco {

    private final String nome;
    private final List<Cliente> clientes;

    public Banco(String nome) {
        this.nome = nome;
        this.clientes = new ArrayList<>();
    }

    public boolean cadastraCliente(Cliente cliente){
        Cliente c = encontraCliente(cliente.getCpf());
        if(c == null){
            this.clientes.add(new Cliente(cliente.getNome(), cliente.getCpf()));
            System.out.println("Cliente com nome " + cliente.getNome() + " e CPF " + cliente.getCpf() + " cadastrado com sucesso");
            return true;
        }else{
            System.out.println("Cliente com CPF " + cliente.getCpf() + " já existe");
            return false;
        }
    }

    public boolean alteraCliente(Cliente clienteAntigo, Cliente clienteNovo){
        Cliente c = encontraCliente(clienteAntigo.getCpf());
        int indice = clientes.indexOf(c);

        if((c != null) && (clienteNovo != null)){
            this.clientes.set(indice, clienteNovo);
            clienteNovo.setSaldo(c.getSaldo());
            return true;
        }else{
            System.out.println("Cliente não encontrado");
            return false;
        }
    }

    public boolean removeCliente(Cliente cliente){
        Cliente c = encontraCliente(cliente.getCpf());
        int indice = clientes.indexOf(c);
        if(c != null){
            this.clientes.remove(indice);
            return true;
        }else{
            System.out.println("Cliente não encontrado");
            return false;
        }
    }

    public boolean depositar(Cliente cliente, double valor){

        Cliente c = encontraCliente(cliente.getCpf());
        if(c != null){
            return c.depositar(valor);
        }
        return false;
    }

    public boolean transferir(Cliente remetente, Cliente destinatario, double valor){

        Cliente r = encontraCliente(remetente.getCpf());
        Cliente d = encontraCliente(destinatario.getCpf());

        if((r != null) && (d != null) && (valor > 0) && (valor <=r.getSaldo())){
            Transferencia t = new Transferencia(valor, r, d);
            r.transferencia(valor, t);
            d.transferencia(valor, t);
            if(valor > r.getSaldo()){
                System.out.println("Saldo insuficiente para realizar a transferência");
            }
            return true;
        }
        return false;
    }

    public void listarClientesETransferencias(){
        for(int i=0; i<this.clientes.size(); i++){
            Cliente cliente = this.clientes.get(i);
            System.out.println("\n################################################");
            System.out.println("Nome: " + cliente.getNome() + ", CPF: " + cliente.getCpf());
            System.out.println("Saldo: " + cliente.getSaldo());
            List<Double> depositos = cliente.getDepositos();
            List<Transferencia> transferenciasEnviadas = cliente.getTransferenciasEnviadas();
            List<Transferencia> transferenciasRecebidas = cliente.getTransferenciasRecebidas();

            if(depositos.size() > 0){
                System.out.println("\nDepositos: ");
            }
            for(int j=0; j<depositos.size(); j++){
                System.out.println((j+1) + ": " + depositos.get(j));
            }

            if(transferenciasEnviadas.size() > 0){
                System.out.println("\nTransferências Enviadas: ");
            }
            for(int j=0; j<transferenciasEnviadas.size(); j++){
                System.out.println((j+1) + ": " + transferenciasEnviadas.get(j).getValor());
                System.out.println("Destinatário: " + transferenciasEnviadas.get(j).getDestinatario().getNome());
            }

            if(transferenciasRecebidas.size() > 0){
                System.out.println("\nTransferências Recebidas: ");
            }
            for(int j=0; j<transferenciasRecebidas.size(); j++){
                System.out.println((j+1) + ": " + transferenciasRecebidas.get(j).getValor());
                System.out.println("Remetente: " + transferenciasRecebidas.get(j).getRemetente().getNome());
            }
        }
    }

    public Cliente encontraCliente(String cpf){
        for(int i=0; i< clientes.size(); i++){
            Cliente cliente = this.clientes.get(i);
            if(cliente.getCpf().equals(cpf)){
                return cliente;
            }
        }
        return null;
    }

}
