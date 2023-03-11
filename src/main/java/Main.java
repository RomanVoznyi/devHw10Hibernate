
import features.hibernate.HibernateUtil;
import features.initDB.DBInitAndPopulateService;
import features.spaceTravel.Client;
import features.services.ClientCrudService;
import features.spaceTravel.Planet;
import features.services.PlanetCrudService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        DBInitAndPopulateService.getInstance().runMigration();
        runClientServicesTests();
        runPlanetServicesTests();

        HibernateUtil.getInstance().close();
        System.gc();
    }

    private static void runClientServicesTests() {
        ClientCrudService clientService = new ClientCrudService();

        //Add clients
        System.out.println("-------------Adding new clients-------------");
        clientService.addClient(new Client("John Dou 1"));
        clientService.addClient(new Client("John Dou 2"));
        clientService.addClient(new Client("Mary Jane"));

        //Get clients info
        printItem(clientService.getClientById(11L), "Get client by ID '11'");
        printList(clientService.getClientByName("John Dou"), "Get clients by name 'John Dou'");
        printList(clientService.getAllClients(), "Get all clients");

        //Update clients info
        System.out.println("-------------Updating clients-------------");
        clientService.updateName(2, "Boris Johnson");
        clientService.updateName(11, "Bruce Willis");
        printItem(clientService.getClientById(2), "Get modified client with id '2'");
        printItem(clientService.getClientById(11), "Get modified client with id '11'");

        //Remove clients
        System.out.println("-------------Removing clients-------------");
        clientService.removeUser(1);
        clientService.removeUser(11);
        printItem(clientService.getClientById(1), "Trying to find a user with id '1' after removing");
        printItem(clientService.getClientById(11), "Trying to find a user with id '11' after removing");
        printList(clientService.getAllClients(), "Final updated list of clients");
    }

    private static void runPlanetServicesTests() {
        PlanetCrudService planetService = new PlanetCrudService();

        //Add planets
        System.out.println("-------------Adding new planets-------------");
        planetService.addPlanet(new Planet("05JUP", "Jupiter"));
        planetService.addPlanet(new Planet("06SAT", "Saturn"));
        planetService.addPlanet(new Planet("333NAB", "Naboo"));

        //Get planets info
        printItem(planetService.getPlanetById("03EAR"), "Get planet by ID '03EAR'");
        printList(planetService.getPlanetByName("Naboo"), "Get planets by name 'Naboo'");
        printList(planetService.getPlanetByName("vulcan"), "Get planets by name 'vulcan'");
        printList(planetService.getAllPlanets(), "Get all planets");

        //Update planets info
        System.out.println("-------------Updating planets-------------");
        planetService.updatePlanet("04MAR", "Barsoom");
        planetService.updatePlanet("03EAR", "Jasoom");
        planetService.updatePlanet("02VEN", "Cosoom");
        printItem(planetService.getPlanetById("03EAR"), "Get modified planet with id '03EAR'");
        printItem(planetService.getPlanetById("04MAR"), "Get modified planet with id '04MAR'");

        //Remove planets
        System.out.println("-------------Removing planets-------------");
        planetService.removePlanet("06SAT");
        planetService.removePlanet("222NAB");
        planetService.removePlanet("333NAB");
        printItem(planetService.getPlanetById("06SAT"), "Trying to find a planet with id '06SAT' after removing");
        printItem(planetService.getPlanetById("333NAB"), "Trying to find a planet with id '333NAB' after removing");
        printList(planetService.getAllPlanets(), "Final updated list of planets");
    }

    private static <T> void printItem(T item, String topic) {
        System.out.println("-------------" + topic + "-------------");
        System.out.println(item + "\n");
    }

    private static <T> void printList(List<T> list, String topic) {
        System.out.println("-------------" + topic + "-------------");
        if (list.size() == 0) {
            System.out.println("The list of data is empty");
        } else {
            for (T item : list) {
                System.out.println(item);
            }
        }
        System.out.println();
    }
}
