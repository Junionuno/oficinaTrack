import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ServiceManager {
    List<ServiceOrder> orders = new ArrayList<ServiceOrder>();
    List<Vehicle> vehicles = new ArrayList<Vehicle>();
    Scanner sc = new Scanner(System.in);

    void registerVehicle(){
        System.out.print("Modelo do veículo: ");
        String model = sc.nextLine();
        System.out.print("Placa do veículo: ");
        String licensePlate = sc.nextLine();
        System.out.print("Dono do veículo: ");
        String owner = sc.nextLine();

        Vehicle vehicle = new Vehicle(model, licensePlate, owner);
        vehicles.add(vehicle);
        System.out.println("Veículo cadastrado com sucesso!");
    }

    ServiceOrder writeOrder(){
        System.out.print("ID: ");
        int id = sc.nextInt();

        System.out.print("Digite a placa do veículo: ");
        sc.nextLine();
        String licensePlate = sc.nextLine();

        Vehicle foundVehicle = null;
        Collections.sort(vehicles);
        for(Vehicle vehicle : vehicles){
            if (vehicle.getLicensePlate().equals(licensePlate)){
                foundVehicle = vehicle;
                break;
            }
        }
        if (foundVehicle == null){
            System.out.println("Erro: veículo com a placa " + licensePlate + " não cadastrado!");
            return null;
        }

        System.out.print("Descrição: ");
        String description = sc.nextLine();

        System.out.print("Serviço já feito? (s/n)");
        char completed = sc.next().charAt(0);
        boolean completedB = false;
        if (completed == 's') {
            completedB = true;
        }else if(completed == 'n'){
            completedB = false;
        }

        ServiceOrder order = new ServiceOrder(id, foundVehicle, description, completedB);
        registerOrder(order);
        return order;
    }

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
        ServiceOrder foundOrder = new ServiceOrder(id, null, null, false);
        int index = Collections.binarySearch(orders, foundOrder);
        if (index >= 0){
            return orders.get(index);
        }
        return null;
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
