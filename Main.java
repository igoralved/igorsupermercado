package Main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//TODO arrumar a baixa do estoque para quando nao tiver estoque o suficiente (bug)
//TODO criar metodo para receber o valor pago e calcular o troco (funcionalidade)
//TODO criar metodo que defina a menor quantidade de notas possivel para o troco (funcionalidade dificil)
//      Notas possiveis = 100,50,20,10,5,2,1,0.5,0.25,0.1,0.05 e 0.01;
        //Menu.controleMenu();
        Pedido.trocoEmNotas(1.5);
        Pedido.trocoEmNotas(250.01);
        Pedido.trocoEmNotas(0.25);
        Pedido.trocoEmNotas(25.25);
    }
}