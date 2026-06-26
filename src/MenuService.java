import java.util.Scanner;

public class MenuService {
    Scanner sc = new Scanner(System.in);
    void iniciar(){
        ServiceManager service = new ServiceManager();
        service.loadData();

        int numMenu = sc.nextInt();
        while(numMenu != 0){
            System.out.println("ORDEM DE SERVIÇO");
            System.out.println("\n[1] Cadastrar novo veículo");
            System.out.println("[2] Cadastrar ordem de serviço");
            System.out.println("[3] Pesquisar ordem de serviço por ID");
            System.out.println("[4] Exportar ordens");
            System.out.println("[5] Salvar arquivo");
            System.out.println("[0] Encerrar");

            switch (numMenu){
                case 1 : service.registerVehicle(); break;
                case 2 : service.
            }
        }
    }
}
