import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static Banco banco = new Banco("Banco do Brasil");
    public static void main(String[] args) {

        menu();
        boolean sair = false;
        int opcao;

        while(!sair){

            System.out.println("Digite sua opção (7 - para ver opções)");
            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao){
                case 0:
                    sair = true;
                    break;

                case 1:
                    cadastraCliente();
                    break;

                case 2:
                    removeCliente();
                    break;

                case 3:
                    alteraCliente();
                    break;

                case 4:
                    depositar();
                    break;

                case 5:
                    transferir();
                    break;

                case 6:
                    banco.listarClientesETransferencias();
                    break;

                case 7:
                    menu();
                    break;
            }
        }
    }

    public static void cadastraCliente(){

        System.out.println("Digite o nome: ");
        String nome = sc.nextLine();

        System.out.println("Digite o CPF: ");
        String cpf = sc.nextLine();

        Cliente cliente = new Cliente(nome, cpf);
        banco.cadastraCliente(cliente);
    }

    public static void alteraCliente(){

        System.out.println("Digite o CPF do cliente a ser alterado: ");
        String cpf = sc.nextLine();

        Cliente clienteAntigo = banco.encontraCliente(cpf);

        System.out.println("Digite o novo nome: ");
        String nomeNovo = sc.nextLine();

        System.out.println("Digite o novo CPF: ");
        String cpfNovo = sc.nextLine();

        Cliente clienteNovo = new Cliente(nomeNovo, cpfNovo);
        banco.alteraCliente(clienteAntigo, clienteNovo);
    }

    public static void removeCliente(){

        System.out.println("Digite o CPF: ");
        String cpf = sc.nextLine();

        Cliente cliente = banco.encontraCliente(cpf);
        banco.removeCliente(cliente);
    }

    public static void depositar(){

        System.out.println("Digite o CPF: ");
        String cpf = sc.nextLine();

        System.out.println("Digite o valor a ser depositado: ");
        double valor = sc.nextDouble();

        Cliente cliente = banco.encontraCliente(cpf);
        banco.depositar(cliente, valor);
    }

    public static void transferir(){

        System.out.println("Digite o CPF de quem vai transferir: ");
        String cpfRemetente = sc.nextLine();
        Cliente remetente = banco.encontraCliente(cpfRemetente);

        System.out.println("Digite o CPF de quem vai receber: ");
        String cpfDestinatario = sc.nextLine();
        Cliente destinatario = banco.encontraCliente(cpfDestinatario);

        System.out.println("Digite o valor da transferência: ");
        double valor = sc.nextDouble();
        banco.transferir(remetente, destinatario, valor);
    }

    public static void menu(){

        System.out.println("Pressione \n" +
                "0 - para sair\n" +
                "1 - para cadastrar cliente\n" +
                "2 - para remover um cliente\n" +
                "3 - para alterar dados de um cliente\n" +
                "4 - para depositar\n" +
                "5 - para transferir\n" +
                "6 - para listar cliente, depositos e transferências\n" +
                "7 - para ver opções");
    }


}