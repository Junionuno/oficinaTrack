import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        MenuService menu = new MenuService();

        menu.iniciar();

        ServiceOrder ordemEncontrada = service.searchById(3);
        if (ordemEncontrada != null){
            System.out.println("Ordem encontrada: " + ordemEncontrada);
        }else {
            System.out.println("Ordem não encontrada.");
        }

        service.exportCompletedOrders();

        service.saveData();


    }
}
