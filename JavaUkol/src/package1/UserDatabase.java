package package1;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;

public class UserDatabase {

    private ArrayList<User> users;
    public long age;
    Scanner scanner = new Scanner(System.in);

    public UserDatabase() {
        users = new ArrayList<>();
    }

    public void addUser(User user) {
        if (user.getName().isEmpty() || user.getSurname().isEmpty()) {
            System.out.println("Jmeno nesmi byt prazdne.");
            return;
        }
        for (User u : users) {
            if (u.getId() == user.getId()) {
                System.out.println("Osoba s RC jiz existuje.");
                return;
            }
        }
        users.add(user);
        System.out.println("Osoba pridana uspesne.");
    }

    public void deleteUser(long id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.remove(i);
                System.out.println("Osoba vymazana uspesne.");
                return;
            }
        }
        System.out.println("Osoba se nenasla.");
    }

    public User findUser(long id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                return users.get(i);
            }
        }
        System.out.println("Osoba se nenasla.");
        return null;
    }
    public long age(String rc){

        rc = rc.substring(0, rc.length() - 4);
        int den = Integer.parseInt(rc.substring(4, 6));
        int mesic = Integer.parseInt(rc.substring(2, 4));
        int rok = Integer.parseInt(rc.substring(0, 2));
        if (rok < 54) {
            rok += 2000;
        } else {
            rok += 1900;
        }
        LocalDate narozeniny = LocalDate.of(rok, mesic, den);
        LocalDate dnes = LocalDate.now();
        int vek = Period.between(narozeniny, dnes).getYears();

        return vek;
    }



    public static void main(String[] args) {
        UserDatabase database = new UserDatabase();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nCo chcete udelat?");
            System.out.println("1. Pridat uzivatele");
            System.out.println("2. Smazat uzivatele");
            System.out.println("3. Najit uzivatele");
            System.out.println("4. Ukoncit program");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Napiste detaily:");
                    System.out.print("Jmeno: ");
                    String name = scanner.nextLine().trim();
                    System.out.print("Prijmeni: ");
                    String surname = scanner.nextLine().trim();
                    //System.out.print("Rodne cislo: ");
                    long id;
                    // osetreni RC od lomitka a validace RC
                    while (true) {
                        System.out.print("Rodne cislo (musi mit 10 cisel s lomitkem i bez): ");
                        String idString = scanner.nextLine().trim();

                        // vymazani limitka z rodneho cisla
                        if (idString.length() > 9){
                            char deleteSlash = idString.charAt(6);
                            if(deleteSlash == '/'){
                            idString = idString.substring(0,6) + idString.substring(7);
                        } 
                        
                        if (idString.length() == 10 && idString.matches("\\d+")) {
                            id = Long.parseLong(idString);
                            break;
                        } else {
                            System.out.println("Rodne cislo musi mit 10 cisel s lomitkem i bez");
                        }
                    }
                }
                    User user = new User(name, surname, id);
                    database.addUser(user);
                    break;
                case 2:
                    System.out.print("Napiste rodne cislo osoby co chcete smazat: ");
                    long idToDelete = scanner.nextLong();
                    scanner.nextLine(); // consume the newline character
                    database.deleteUser(idToDelete);
                    break;
                case 3:
                    System.out.print("Napis rodne cislo osobne co chcete najit: ");
                    long idToFind = scanner.nextLong();
                    scanner.nextLine(); // consume the newline character
                    User foundUser = database.findUser(idToFind);


                    if (foundUser != null) {
                        long age = foundUser.getId();
                        String ager = String.valueOf(age);
                        System.out.println(foundUser.toString());
                        System.out.println("Osobě je " + database.age(ager));
                    }
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Nespravna volba.");
                    break;
            }
        }
    }
}