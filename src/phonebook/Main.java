package phonebook;

public class Main {
    public static void main(String[] args) {

        MenuBar m = new MenuBar();
        int choice = m.menu();

        while (choice <= 11) {

            switch (choice) {
                case 1 -> {
                    m.addContact();
                    choice = m.menu();
                }
                case 2 -> {
                    m.deleteContact();
                    choice = m.menu();
                }
                case 3 -> {
                    m.showInfo();
                    choice = m.menu();
                }
                case 4 -> {
                    m.findByName();
                    choice = m.menu();
                }
                case 5 -> {
                    m.sortByName();
                    choice = m.menu();
                }
                case 6 -> {
                    m.sortByPhone();
                    choice = m.menu();
                }
                case 7 -> {
                    m.reverse();
                    choice = m.menu();
                }
                case 8 -> {
                    m.deleteDuplicate();
                    choice = m.menu();
                }
                case 9 -> {
                    m.exportToFile();
                    choice = m.menu();
                }
                case 10 -> {
                    m.importFile();
                    choice = m.menu();
                }

                case 11 -> m.exit();
            }
        }
    }

}