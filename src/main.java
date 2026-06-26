public class main {
    public static void main(String[] args) {
        ServiceManager service = new ServiceManager();
        service.loadData();

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
