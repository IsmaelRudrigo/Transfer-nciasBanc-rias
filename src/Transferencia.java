public class Transferencia{

    private final double valor;
    private final Cliente remetente;
    private final Cliente destinatario;

    public Transferencia(double valor, Cliente remetente, Cliente destinatario) {
        this.valor = valor;
        this.remetente = remetente;
        this.destinatario = destinatario;
    }

    public double getValor() {
        return valor;
    }

    public Cliente getRemetente() {
        return remetente;
    }

    public Cliente getDestinatario() {
        return destinatario;
    }
}
