package phonebook;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MenuBar {

    private final PhoneBook phoneBook = new PhoneBook();

    /**
     * Create new Person and added it to the phoneBook.
     */
    public void addContact() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter your name");
        String name = s.nextLine().toLowerCase();
        String[] isNameMatch = validator(name, "^\\w+$", "Please pay attention max length is 20 characters!\n Please try again, enter a valid name", "The process was closed after 3 wrong attempts");
        if (isNameMatch[0].equals("true".toLowerCase())) {
            System.out.println("Please enter your phone");
            String phone = s.nextLine();
            String[] isPhoneMatch = validator(phone, "^0\\d([\\d]{0,1})([-]{0,1})\\d{7}$", "Invalid number! May contain only digits and '-'. \n for example : 052-1111111 \n Please try again to enter a valid phone", "The process was closed after 3 wrong attempts");
            if (isPhoneMatch[0].equals("true".toLowerCase())) {
                Person p = new Person(name, phone);
                phoneBook.contacts.add(p);
                System.out.println("The contact has added to the phoneBook");
            } else {
                System.out.println(isPhoneMatch[2]);
                System.exit(0);
            }
        } else {
            System.out.println(isNameMatch[2]);
            System.exit(0);
        }
        System.out.println("Please click enter to go back to menu");
        s.nextLine();
    }

    /**
     * run on the arrayList and searching for person his name equal to the name the user entered if there is a match the person
     * will be deleted else the user will receive message.
     */
    public void deleteContact() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter name of the contact to be deleted:");
        String name = s.nextLine();
        int size = phoneBook.contacts.size();
        String[] isNameMatch = validator(name, "^\\w+$", "Please pay attention the name may contain characters and numbers max length is 20!\n Please try again, enter a valid name", "The process was closed after 3 wrong attempts");
        for (Person person : phoneBook.contacts) {
            if (person.getName().equals(isNameMatch[1])) {
                phoneBook.contacts.remove(person);
                System.out.println("The contact has been deleted from the phoneBook");
                break;
            }
        }
        if (phoneBook.contacts.size() == size) {
            System.out.println("Sorry, name not found in the phoneBook");
        }
        System.out.println("Please click enter to proceed");
        s.nextLine();
    }

    /**
     * The function run on the arrayList with for each loop and print to the console all the Persons.
     */
    public void showInfo() {
        for (Person person : phoneBook.contacts) {
            System.out.printf("The name is : %s and the phone is : %s%n", person.getName(), person.getPhone());
        }
        System.out.println("Please click enter to proceed");
        scanner();
    }

    /**
     * The function ask for the user to enter a name after receiving it the function run on the arrayList and check
     * if the name the user entered found on the arrayList if it is the function will pint the details.
     */
    public void findByName() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter name of the contact to view");
        String name = s.nextLine();
        String[] isNameMatch = validator(name, "^\\w+$", "Please pay attention the name may contain characters and numbers max length is 20!\n Please try again, enter a valid name", "The process was closed after 3 wrong attempts");
        boolean isFound = false;
        for (Person person : phoneBook.contacts) {
            if (person.getName().equals(isNameMatch[1])) {
                System.out.printf("%s %s", "\n" + person.getName(), person.getPhone());
                isFound = true;
            } else {
                System.out.println(isNameMatch[2]);
                System.exit(0);
            }
        }
        if (!isFound) {
            System.out.printf("The name %s is not exist in the phonebook", name);
        }
        System.out.println("\n" + "Please click enter to proceed");
        s.nextLine();
    }

    /**
     * The function run Comparator and sort the arrayList by name.
     */
    public void sortByName() {
        phoneBook.contacts.sort(Comparator.comparing(Person::getName));
        for (Person person : phoneBook.contacts) {
            System.out.println(person.getName() + " " + person.getPhone());
        }
        System.out.println("Please click enter to proceed");
        scanner();
    }

    /**
     * The function run Comparator and sort the arrayList by phone.
     */
    public void sortByPhone() {
        phoneBook.contacts.sort(Comparator.comparing(Person::getPhone));
        for (Person person : phoneBook.contacts) {
            System.out.println(person.getName() + "  " + person.getPhone());
        }
        System.out.println("Please click enter to proceed");
        scanner();
    }

    /**
     * The function run Comparator.reverse and sort the arrayList inversely.
     */
    public void reverse() {
        System.out.println("Before sorting :");
        phoneBook.contacts.forEach(System.out::println);
        Collections.reverse(phoneBook.contacts);
        System.out.println("After sorting : ");
        phoneBook.contacts.forEach(System.out::println);
        System.out.println("Please click enter to proceed");
        scanner();
    }

    /**
     * The function run on two for loop and check if there is a match between the names if it is the person will be deleted
     * else the user will receive message.
     */
    public void deleteDuplicate() {
        for (int i = 0; i < phoneBook.contacts.size(); i++) {
            String name = phoneBook.contacts.get(i).getName();
            String phone = phoneBook.contacts.get(i).getPhone();
            for (int j = i; j < phoneBook.contacts.size(); j++) {
                if (phoneBook.contacts.get(j).getName().equals(name) && phoneBook.contacts.get(j).getPhone().equals(phone) && i != j) {
                    phoneBook.contacts.remove(j);
                    j--;
                } else {
                    System.out.println("There is no contact named like this is the phoneBook");
                }
            }
        }
        System.out.println("Please click enter to proceed");
        scanner();
    }

    /**
     * using bufferWriter to export the arrayList into file (into the project folder).
     */
    public void exportToFile() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
        String formattedDate = myDateObj.format(myFormatObj);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Contacts " + formattedDate + ".txt"));
            for (Person person : phoneBook.contacts) {
                writer.write(person.getName() + ":" + person.getPhone() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Cannot export please check again " + e);
        }
        System.out.println("Please click enter to proceed");
        scanner();
    }

    /**
     * using bufferReader to import file to the arrayList (from the project folder).
     */
    public void importFile() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please enter the file name from the project file");
        String fileName = s.nextLine();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            List<String> collect = reader.lines().toList();
            if (collect.size() > 0) {
                for (String string : collect) {
                String[] split = string.split(":");
                String[] isNameMatch = validator(split[0], "^\\w+$", "Please pay attention max length is 20 characters!\n Please try again, enter a valid name", "The process was closed after 3 wrong attempts");
                if (isNameMatch[0].equals("true".toLowerCase())) {
                    String[] isPhoneMatch = validator(split[1], "^0\\d([\\d]{0,1})([-]{0,1})\\d{7}$", "Invalid number! May contain only digits and '-'. \n for example : 052-1111111 \n Please try again to enter a valid phone", "The process was closed after 3 wrong attempts");
                    if (isPhoneMatch[0].equals("true".toLowerCase())) {
                        phoneBook.contacts.add(new Person(split[0], split[1]));
                    } else {
                        System.out.println(isPhoneMatch[2]);
                        System.exit(0);
                    }
                } else {
                    System.out.println(isNameMatch[2]);
                    System.exit(0);
                }
            }
            } else {
                System.out.println("The file has no content");
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.printf("Cannot find the file specified %s " , fileName + "\n" + e);

        } catch (IOException e) {
            System.err.println("Cannot import the file, please try again \n" + e);
        }
        System.out.println("\n Please click enter to proceed");
        s.nextLine();
    }

    /**
     * close the program.
     */
    public void exit() {
        System.exit(0);
    }

    /**
     * In this function the whole menu managed after the user entered a number the function go to another function called Validator
     * and check if the number the user entered is valid if it is the function changed the String to int using ParseInt
     * @return the number the user entered.
     */
    public int menu() {

        System.out.println("Please choose your choice : ");
        System.out.println("1. " + "Add contact");
        System.out.println("2. " + "Delete contact");
        System.out.println("3. " + "Show info");
        System.out.println("4. " + "Find by name");
        System.out.println("5. " + "Sort phoneBook by name");
        System.out.println("6. " + "Sort phoneBook by phone");
        System.out.println("7. " + "Sort phoneBook inversely");
        System.out.println("8. " + "Delete duplicate");
        System.out.println("9. " + "Export to documents");
        System.out.println("10. " + "Import to documents");
        System.out.println("11. " + "Exit");

        Scanner s = new Scanner(System.in);
        String choice = s.nextLine();
        int returnChoice = 0;
        String[] isMatch = validator(choice, "^([1-9]|1[011])$", "Please select number from the menu", "The process was closed after 3 wrong attempts");
        if (isMatch[0].equals("true".toLowerCase())) {
            returnChoice = Integer.parseInt(isMatch[1]);
        } else {
            System.out.println(isMatch[2]);
            System.exit(0);
        }
        return returnChoice;
    }

    /**
     * Allows the user to read a String from System.in.
     */
    public void scanner() {
        Scanner s = new Scanner(System.in);
        s.nextLine();
    }

    /**
     * This function will check if the input is match the regex if not the user has 3 attempts.
     * @param choice The parameter the user entered and needed to validate with regex.
     * @param regex The condition.
     * @param errorMessage If the user entered a not valid input the function will print an error Msg.
     * @param exception Msg when the Operation closed.
     * @return array of String
     */
    public String[] validator(String choice, String regex, String errorMessage, String exception) {
        int number = 0;
        String[] arr = new String[3];
        arr[0] = "false";
        Scanner s = new Scanner(System.in);

        while (number < 2) {
            if (!choice.matches(regex)) {
                System.err.println(errorMessage);
                choice = s.nextLine();
                arr[2] = exception;
                number++;
            } else {
                arr[1] = choice;
                arr[0] = "true".toLowerCase();
                break;
            }
        }
        return arr;
    }
}

