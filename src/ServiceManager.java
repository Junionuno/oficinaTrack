import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServiceManager {
    List<ServiceOrder> orders = new ArrayList<>();

    void registerOrder(ServiceOrder order){
        for (ServiceOrder currentOrder : orders){
            if (currentOrder.getVehicle().equals(order.getVehicle()) && !currentOrder.isCompleted()){
                System.out.println("Erro: O veículo " + order.getVehicle().getModel() + "Já possui uma ordem de serviço aberta");
                return;
            }

            orders.add(order);
            System.out.println("Ordem de serviço registrada, veículo: " + order.getVehicle().getModel());
        }
    }

    ServiceOrder searchById(int id){

    }

    void exportCompletedOrders(){

    }

    void SaveData(){

    }

    void loadData(){

    }


}
