package phonebook;

public class Person extends MenuBar {
    private String name;
    private String phone;

    public Person(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Person() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        String[] isNameMatch = validator(name, "^\\w+$", "Please pay attention max length is 20 characters!\n Please try again, enter a valid name", "The process was closed after 3 wrong attempts");
        if (isNameMatch[0].equals("true".toLowerCase())) {
            this.name = isNameMatch[1];
        } else {
            System.out.println(isNameMatch[2]);
            System.exit(0);
        }
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        String[] isPhoneMatch = validator(phone, "^0\\d([\\d]{0,1})([-]{0,1})\\d{7}$", "Invalid number! May contain only digits and '-'. \n for example : 052-1111111 \n Please try again to enter a valid phone", "The process was closed after 3 wrong attempts");
        if (isPhoneMatch[0].equals("true".toLowerCase())) {
            this.phone = isPhoneMatch[1];
        } else {
            System.out.println(isPhoneMatch[2]);
            System.exit(0);
        }
    }


    public String toString() {
        return getName() + getPhone();
    }
}


