package Main;

import Utils.Inputs;
import java.util.ArrayList;

public class Pedido {

    private static ArrayList<Item> listaDeItens = new ArrayList();
    private static double valorTotalDoPedido = 0;

    public static void calculaValorTotal() {
        double subTotal = 0;
        for (Item item : listaDeItens) {
            subTotal += item.getValorDoItem();
        }
        valorTotalDoPedido = subTotal;
    }

    public static boolean adicionaItemNaLista(Produto produto, int quantidade) {
        for (Item item : listaDeItens) {
            if (item.getProduto().getNome().equalsIgnoreCase(produto.getNome())) {
                if (Estoque.darBaixaEmEstoque(item.getProduto().getId(), quantidade)) {
                    item.setQuantidade(item.getQuantidade() + quantidade);
                    item.defineValorTotal();
                    System.out.println("Foi adicionada a quantidade ao item já existente.");
                } else {
                    System.out.println("Não tem");
                }
                return false;
            }
        }
        if(Estoque.darBaixaEmEstoque(produto.getId(), quantidade)) {
            listaDeItens.add(new Item(produto, quantidade));
            System.out.println("Foi adicionado o produto na lista de compras.");
        }else{
            System.out.println("nao tem");
        }
        return true;
    }

    public static void imprimePedido() {
        System.out.println("                              NOTA FISCAL");
        System.out.printf("ID       |NOME            |PRECO UN           |QUANTIDADE   |PRECO ITEM \n");
        for (Item item : listaDeItens) {
            System.out.printf("%-8d | %-14s | R$%-15.2f | %-10d  | R$%.2f\n"
                    , item.getProduto().getId(), item.getProduto().getNome(), item.getProduto().getPreco(), item.getQuantidade(), item.getValorDoItem());
        }
        imprimeValorTotal();
    }

    private static void imprimeValorTotal() {
        System.out.println();
        System.out.printf("Total: R$%.2f", valorTotalDoPedido);
        System.out.println("________________________________________________________________________");
        System.out.println();
        System.out.println();
    }

    public static void adicionaItem(){
        String nome = recebeNomeDoTeclado();
        int quantidade = recebeQuantidadeDoTeclado();
        Produto produto = Estoque.encontraProduto(nome);
        if(produto != null){
            adicionaItemNaLista(produto,quantidade);
            calculaValorTotal();
        } else {
            System.out.println("Produto nao encontrado");
        }

    }

    public static String recebeNomeDoTeclado(){
        System.out.print("Digite o nome: ");
        return Inputs.inputString();
    }


    public static int recebeQuantidadeDoTeclado(){
        System.out.print("Digite a quantidade: ");
        return Inputs.inputInt();
    }

    public void limparCarrinho() {
        listaDeItens.clear();
    }

    public static ArrayList<Item> getListaDeItens() {
        return listaDeItens;
    }

    public void setListaDeItens(ArrayList<Item> listaDeItens) {
        Pedido.listaDeItens = listaDeItens;
    }

    public double getValorTotalDoPedido() {
        return valorTotalDoPedido;
    }

    public void setValorTotalDoPedido(double valorTotalDoPedido) {
        Pedido.valorTotalDoPedido = valorTotalDoPedido;
    }

    private static double valorTroco(double valorPago){
        if(valorPago - valorTotalDoPedido >= 0){
            System.out.println("Pedido feito com sucesso");
            return valorPago - valorTotalDoPedido;
        }else{
            System.out.println("Sem troco");
            return 0;
        }
    }

    public static int trocoEmNotas(double valorDoTroco){
        double resultadodotroco = valorTroco(valorDoTroco);
        if(valorDoTroco > 0){
            System.out.println("Troco: " + resultadodotroco);
            // Notas possiveis = 100,50,20,10,5,2,1,0.5,0.25,0.1,0.05 e 0.01;
            resultadodotroco = resultadodotroco * 100;
            int totalNotas = 0;
            //notas de 100
            int notas100 = (int) (resultadodotroco) / 10000;
            System.out.println("Notas de 100: " + notas100);
            totalNotas += notas100;
            resultadodotroco = ((resultadodotroco) % 10000);
            //notas de 50
            int notas50 = (int) (resultadodotroco) / 5000;
            System.out.println("Notas de 50: " + notas50);
            totalNotas += notas50;
            resultadodotroco = ((resultadodotroco) % 5000);
            //notas de 20
            int notas20 = (int) (resultadodotroco) / 2000;
            System.out.println("Notas de 20: " + notas20);
            totalNotas += notas20;
            resultadodotroco = ((resultadodotroco) % 2000);
            //notas de 10
            int notas10 = (int) (resultadodotroco) / 1000;
            System.out.println("Notas de 10: " + notas10);
            totalNotas += notas10;
            resultadodotroco = ((resultadodotroco) % 1000);
            //notas de 5
            int notas5 = (int) ((resultadodotroco)) / 500;
            System.out.println("Notas de 5: " + notas5);
            totalNotas += notas5;
            resultadodotroco = ((resultadodotroco) % 500);
            //notas de 2
            int notas2 = (int) (resultadodotroco) / 200;
            System.out.println("Notas de 2: " + notas2);
            totalNotas += notas2;
            resultadodotroco = ((resultadodotroco) % 200);
            //notas de 1
            int notas1 = (int) (resultadodotroco / 100);
            System.out.println("Notas de 1: " + notas1);
            totalNotas += notas1;
            resultadodotroco = (resultadodotroco % 100);
            //notas de 0.5;
            int notas1half = (int) (resultadodotroco / 50);
            System.out.println("Notas de 0.5: " + notas1half);
            totalNotas += notas1half;
            resultadodotroco = (resultadodotroco % 50);
            //notas de 0.25
            int notas1quarter = (int)(resultadodotroco / 25);
            System.out.println("Notas de 0.25: " + notas1quarter);
            totalNotas += notas1quarter;
            resultadodotroco = (resultadodotroco % 25);
            //notas de 0.1
            int notas1decimo =(int)  (resultadodotroco / 10);
            System.out.println("Notas de 0.1: " + notas1decimo);
            totalNotas += notas1decimo;
            resultadodotroco = (resultadodotroco % 10);
            //notas de 0.05
            int notas0ponto05 = (int) (resultadodotroco / 5);
            System.out.println("Notas de 0.05: " + notas0ponto05);
            totalNotas += notas0ponto05;
            resultadodotroco = (resultadodotroco % 5);
            //notas de 0.01
            int notas1ponto01 = (int) (resultadodotroco);
            System.out.println("Notas de 0.01: " + notas1ponto01);
            totalNotas += notas1ponto01;
            resultadodotroco = (resultadodotroco );
            System.out.println("Com notas");
            return totalNotas;
        }System.out.println("Sem notas");
        return 0;
    }

}
