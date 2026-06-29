import java.util.Scanner;

public class MenuService {
    Scanner sc = new Scanner(System.in);
    void iniciar(){
        ServiceManager service = new ServiceManager();
        service.loadData();
        int numMenu = 0;
        while(numMenu != 6){
            System.out.println("\n[1] Cadastrar novo veículo");
            System.out.println("[2] Cadastrar ordem de serviço");
            System.out.println("[3] Pesquisar ordem de serviço por ID");
            System.out.println("[4] Salvar arquivo");
            System.out.println("[5] Encerrar");
            numMenu = sc.nextInt();

            switch (numMenu){

                case 1 : service.registerVehicle(); break;
                case 2 : service.writeOrder(); break;
                case 3 :
                    System.out.print("Digite o ID que deseja procurar: ");
                    int id = sc.nextInt();
                    ServiceOrder foundOrder = service.searchById(3);
                    if (foundOrder != null){
                        System.out.println("Ordem encontrada: " + foundOrder);
                    }else {
                        System.out.println("Ordem não encontrada.");
                    }
                    service.searchById(id);
                    break;
                case 4: service.saveData(); break;
                case 5:
                    System.out.println("Até logo..."); break;
                default:
                    while (numMenu > 5 || numMenu < 5){
                        System.out.println("\n[1] Cadastrar novo veículo");
                        System.out.println("[2] Cadastrar ordem de serviço");
                        System.out.println("[3] Pesquisar ordem de serviço por ID");
                        System.out.println("[4] Exportar ordens");
                        System.out.println("[5] Salvar arquivo");
                        System.out.println("[0] Encerrar");
                    }
            }
        }
    }
}
