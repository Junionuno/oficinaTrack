import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServiceManager {
    List<ServiceOrder> orders = new ArrayList<ServiceOrder>();

    void registerOrder(ServiceOrder order){
        for (ServiceOrder currentOrder : orders){
            if (currentOrder.getVehicle().equals(order.getVehicle()) && !currentOrder.isCompleted()){
                System.out.println("Erro: O veículo " + order.getVehicle().getModel() + " já possui uma ordem de serviço aberta");
                return;
            }
        }
        orders.add(order);
        System.out.println("Ordem de serviço registrada, veículo: " + order.getVehicle().getModel());
    }

    ServiceOrder searchById(int id){
        Collections.sort(orders);
        ServiceOrder placeholder = new ServiceOrder(id, null, null, false);
        int index = Collections.binarySearch(orders, placeholder);
        if (index >= 0){
            return orders.get(index);
        }
        return null;
    }

    void exportCompletedOrders(){
        List<ServiceOrder> ordersCompleted = new ArrayList<>();
        for (ServiceOrder order : orders) {
            if (order.isCompleted()){
                ordersCompleted.add(order);
            }
        }

        ServiceOrder[] ordersToArray = ordersCompleted.toArray(new ServiceOrder[0]);
        sendToOldAPI(ordersToArray);
    }

    void sendToOldAPI(ServiceOrder[] orders){
        for (ServiceOrder order : orders){
            System.out.println("Transmitindo ordem de: " + order.getVehicle().getModel());
        }
    }

    void saveData(){
        File file = new File("ordens_servico.txt");
        try(FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw)){
            for (ServiceOrder order : orders) {
                bw.write(order.getId() + ", " + order.getVehicle().getModel() + ", " + order.getVehicle().getLicensePlate() + ", " + order.getVehicle().getOwnerName() + ", " + order.getDescription() + ", " + order.isCompleted());
                bw.newLine();
            }
        }catch(IOException e){
            System.out.println("Erro ao salvar" + e.getMessage());
        }
        System.out.println("Arquivo salvo com sucesso!");
    }

    void loadData(){
        File file = new File("ordens_servico.txt");
        try (FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr)){
            String linha;
            while ((linha = br.readLine()) != null){
                System.out.println(linha);
                String[] dados = linha.split(", ");
                String id = dados[0];
                int iD = Integer.parseInt(id);
                String vehicleName = dados[1];
                String vehiclePlate = dados[2];
                String vehicleOwner = dados[3];
                String description = dados[4];
                String completed = dados[5];
                boolean completedB = Boolean.parseBoolean(completed);

                Vehicle veiculo = new Vehicle(vehicleName, vehiclePlate, vehicleOwner);
                ServiceOrder order = new ServiceOrder(iD, veiculo, description, completedB);
                orders.add(order);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
