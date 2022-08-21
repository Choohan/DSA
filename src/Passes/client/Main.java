package Passes.client;

import Passes.adt.DoublyLinkedList;
import Passes.classes.*;

import static Passes.client.Utility.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Locale;


public class Main {
    public static void main(String[] args) {

        DoublyLinkedList<Account> accountList = new DoublyLinkedList<>(); // store accounts

        menu(accountList);
    }

    public static void menu(DoublyLinkedList<Account> accountList) {
        int choice;
        do {
            choice = 0;
            System.out.println("--------------- Passes Main Menu ---------------");
            System.out.println("1. Login");
            System.out.println("2. Signup");
            switch (promptInt("\nSelect an option (-1 to exit): ")) {
                case -1 -> System.exit(1);
                case 1 -> loginMenu(accountList);
                case 2 -> signupMenu(accountList);
                default -> System.out.println("Invalid choice entered...");
            }
            pressAnyKeyToContinue();
        }
        while (true);
    }

    public static void loginMenu(DoublyLinkedList<Account> accountList) {
        do {
            System.out.println("--------------- Please login to enter the system ---------------");
            String username = promptString("Username: ");
            String password = promptString("Password: ");
            for (int i = 0; i < accountList.size(); i++) {
                if (accountList.get(i).getUser().getUsername().equals(username) &&
                        accountList.get(i).getUser().getPassword().equals(password)) {
                    System.out.println("Login successful!");
                }
            }
            if(promptInt("Invalid username or password... (-1 to return)") == -1) {
                return;
            }
        }
        while(true);
    }

    public static void signupMenu(DoublyLinkedList<Account> accountList) {
        DoublyLinkedList<Integer> choices = new DoublyLinkedList<>(new Integer[]{1, 2, 3, 4}); // choices available
        int choice ;
        do {
            choice = 0;
            System.out.println("--------------- Application Type ---------------");
            System.out.println("1. Social Visit Pass");
            System.out.println("2. Social Visit Pass (Spouse of Malaysia Citizen)");
            System.out.println("3. Visit Pass (Professional)");
            System.out.println("4. Malaysia Second Home Programme Pass");
            choice = promptInt("\nSelect an application type (-1 to return): ");
            switch (choice) {
                case -1 -> { return; }
                case 1 -> socialVisitPass(accountList);
                case 2 -> socialVisitPassSpouse(accountList);
                case 3 -> visitPassProfessional(accountList);
                case 4 -> secondHomePass(accountList);
                default -> System.out.println("Invalid choice entered...");
            }
            pressAnyKeyToContinue();
        }
        while (choices.contains(choice));
    }

    public static int countPasses(DoublyLinkedList<Account> accountList) {
        int count = 0;
        for (int accountIndex = 0 ; accountIndex < accountList.size(); accountIndex++) {
            count += accountList.get(accountIndex).getPasses().size();
        }
        return count;
    }

    public static void socialVisitPass(DoublyLinkedList<Account> accountList) {
        boolean loop;
        Account account = new Account(new Person(getName("Full Name : "),
                getTel("Telephone No :"),
                getGender(),
                getEmail("Email :"),
                getUsername(accountList, "Username :"),
                getDOB("Date Of Birth :"), getPassword()), new DoublyLinkedList<>(), accountList.size()+1);
        do {
            loop = false;
            System.out.println("\n\n--------------- Applicant Details ---------------");
            System.out.println("Type of Application : Social Visit Pass");
            System.out.println("\nCategory selection");
            System.out.println("  -------------------------");
            System.out.println("  1. Individual");
            System.out.println("  2. Sponsor");
            int choice = promptInt("  Please select a category (-1 to exit) : ");

            switch (choice) {
                case -1 -> { return; }
                case 1 -> account.getPasses().add(new Individual(String.valueOf(countPasses(accountList))+1 ,
                        getPassport("Passport No : "),
                        getNationality("Nationality : ")));
                case 2 -> account.getPasses().add(new Sponsor(String.valueOf(countPasses(accountList))+1,
                        getNRIC("Identification No : "),
                        getNationality("Nationality : ")));
                default -> {
                    System.out.println("Invalid Selection...");
                    loop = true;
                }
            }
        }
        while (loop);
        accountList.add(account);
    }

    public static void socialVisitPassSpouse(DoublyLinkedList<Account> accountList) {
        System.out.println("\n\n--------------- Spouse Details ---------------");
        System.out.println("Type of Application : Social Visit Pass (Spouse of Malaysia Citizen)\n");
        Account account = new Account(new Person(getName("Spouse Full Name : "),
                getTel("Telephone No :"),
                getGender(),
                getEmail("Email :"),
                getUsername(accountList, "Username :"),
                getDOB("Spouse Date Of Birth :"), getPassword()), new DoublyLinkedList<>(), accountList.size()+1);
        account.getPasses().add(new Spouse(String.valueOf(countPasses(accountList))+1,
                getNRIC("Identification No (NRIC): "),
                getNationality("Applicant Nationality : "),
                getPassport("Applicant Passport No: ")));
        accountList.add(account);
    }

    public static void visitPassProfessional(DoublyLinkedList<Account> accountList) {
        boolean loop;
        Category category = null;
        Account account = new Account(new Person(getName("Full Name : "),
                getTel("Telephone No :"),
                getGender(),
                getEmail("Email :"),
                getUsername(accountList, "Username :"),
                getDOB("Date Of Birth :"), getPassword()), new DoublyLinkedList<>(), accountList.size()+1);
        do {
            loop = false;
            System.out.println("\n\n--------------- Employer Detail ---------------");
            System.out.println("Type of Application : Visit Pass (Professional)\n");
            System.out.println("\tCategory selection");
            System.out.println("  -------------------------");
            System.out.println("  1. Agency");
            System.out.println("  2. Organization");
            System.out.println("  3. Employer");
            int choice = promptInt("  Please select a category (-1 to exit) : ");
            switch (choice) {
                case -1 -> { return; }
                case 1 -> category = Category.AGENCY;
                case 2 -> category = Category.ORGANIZATION;
                case 3 -> category = Category.EMPLOYER;
                default -> {
                    System.out.println("Invalid Selection...");
                    loop = true;
                }
            }
        }
        while(loop);
        account.getPasses().add(new Professional(String.valueOf(countPasses(accountList))+1,
                getCompanyID("Company Registration ID : "),
                category, promptString("Company Name : ")));
        accountList.add(account);
    }

    public static void secondHomePass(DoublyLinkedList<Account> accountList) {
        System.out.println("\n\n--------------- Applicant Details ---------------");
        System.out.println("Type of Application : Malaysia Second Home Programme Pass\n");
        System.out.println("*Note: Please Insert International Code before key-in Telephone Number. ( exp: +1-212-456-7890 )\n");
        Account account = new Account(new Person(getName("Full Name : "),
                getTel("Telephone No :"),
                getGender(),
                getEmail("Email :"),
                getUsername(accountList, "Email :"),
                getDOB("Date Of Birth :"), getPassword()), new DoublyLinkedList<>(), accountList.size()+1);
        account.getPasses().add(new SecondHome(String.valueOf(countPasses(accountList))+1,
                getPassport("Passport No : "),
                getPassport("Old Passport No : "),
                getNationality("Nationality : ")));
        accountList.add(account);
    }

    public static String getCompanyID(String msg) {
        String companyID = "";
        do {
            companyID = promptString(msg);
            if (companyID.length() == 12) {
                return companyID;
            }
            System.out.println("Invalid Company ID...");
        }
        while(true);
    }

    public static String getPassport(String msg) {
        String passport = "";
        do {
            passport = promptString(msg);
            if (validateRegex(passport, "^(?!^0+$)[a-zA-Z0-9]{3,20}$")) {
                return passport;
            }
            System.out.println("Invalid Passport No...");
        }
        while(true);
    }

    public static String getNRIC(String msg) {
        String NRIC = "";
        do {
            NRIC = promptString(msg);
            if (validateRegex(NRIC, "/^\\d{6}-\\d{2}-\\d{4}$/")) {
                return NRIC;
            }
            System.out.println("Invalid NRIC...");
        }
        while(true);
    }

    public static Country getNationality(String msg) {
        DoublyLinkedList<Country> countries = getCountries();
        String nationality_str = "";
        boolean found = false;
        do {
            nationality_str = promptString(msg).toLowerCase(Locale.ROOT);
            for (int i = 0; i < countries.size(); i++) {
                if (countries.get(i).getName().toLowerCase(Locale.ROOT).equals(nationality_str)) {
                    return countries.get(i);
                }
            }
            System.out.println("Invalid country name...");
        } while(true);
    }

    public static String getName(String msg) {
        String name = "";
        do {
            name = promptString(msg);
            if (validateRegex(name, "[a-zA-Z][a-zA-Z ]+")) {
                return name;
            }
            System.out.println("Invalid Name...");
        }
        while(true);
    }

    public static LocalDate getDOB(String msg) {
        LocalDate dob;
        do {
            try {
                System.out.println(msg);
                dob = LocalDate.of(promptInt("  Year  > "), promptInt("  Month > "), promptInt("  Day   > "));
                // if date entered is in the future or is before year 1905
                if (dob.isAfter(LocalDate.now().minusYears(16)) || dob.isBefore(LocalDate.of(1905, 1, 1))) {
                    System.out.println("  Invalid date entered...");
                    continue;
                }
                break;
            } catch (DateTimeException e) {
                System.out.println("  Invalid date entered...");
            }
        }
        while (true);
        return dob;
    }

    public static char getGender() {
        char gender = Character.MIN_VALUE;
        boolean genderFlag;// wait user response to continue
        do {
            genderFlag = false;
            System.out.println("  Gender selection");
            System.out.println("----------------------");
            System.out.println("1. Male");
            System.out.println("2. Female");
            switch (promptInt("  Please select a gender > ")) {
                case 1 -> gender = 'M';
                case 2 -> gender = 'F';
                default -> {
                    System.out.println("  Invalid Selection...");
                    genderFlag = true; // continue loop
                }
            }
        }
        while (genderFlag);
        return gender;
    }

    public static String getEmail(String msg) {
        String email = "";
        do {
            email = promptString(msg);
            if (validateRegex(email, "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
                return email;
            }
            System.out.println("Invalid Email...");
        }
        while(true);
    }

    public static String getTel(String msg) {
        String tel = "";
        do {
            tel = promptString(msg);
            if (validateRegex(tel, "^(\\+?6?01)[02-46-9]-*[0-9]{7}$|^(\\+?6?01)[1]-*[0-9]{8}$")) {
                return tel;
            }
            System.out.println("Invalid Telephone No...");
        }
        while(true);
    }

    public static String getUsername(DoublyLinkedList<Account> accountList, String msg) {
        String username = "";
        do {
            username = promptString(msg);
            for (int i = 0; i < accountList.size(); i++) {
                if (username.equals(accountList.get(i).getUser().getUsername())) {
                    System.out.println("Invalid Username...");
                    break;
                }
            }
        }
        while (username.equals(""));
        return username;
    }

    public static String getPassword() {
        String password, confirmPassword;
        boolean passwordFlag = true;
        do {
            System.out.println("\n  +--------------------------------------------------------------------+");
            System.out.println("  |                   *** VALID  PASSWORD  RULES ***                   |");
            System.out.println("  |                                                                    |");
            System.out.println("  |         - Password must have at least 7 character                  |");
            System.out.println("  |         - Password consist of only letters and digits              |");
            System.out.println("  |         - Password must contain at least one letter and at         |");
            System.out.println("  |           least one digit                                          |");
            System.out.println("  |                                                                    |");
            System.out.println("  +--------------------------------------------------------------------+");
            password = promptString("\n  Enter your desired password   > ");
            confirmPassword = promptString("  Confirm your desired password > ");

            // If input password not equals to input confirm password
            if (!(password.equals(confirmPassword))) {
                System.out.println("Both password does not match...");
                continue;
            }

            // If password is in correct format
            if (userpassValidation(password)) {
                passwordFlag = false;
            }
            // If not in correct format
            else {
                System.out.println("Password format invalid...");
            }
        }
        while (passwordFlag);
        return password;
    }

    public static boolean userpassValidation(String userpass) {
        boolean letter = false, digit = false;
        if (userpass.length() >= 7) {
            for (int index = 0; index < userpass.length(); index++) {
                if (Character.isLetter(userpass.charAt(index))) {
                    letter = true;
                } else if (Character.isDigit(userpass.charAt(index))) {
                    digit = true;
                }
                // not letter or digit
                else return false;
            }
            // after iteration
            return letter && digit;
        }
        // not within length
        return false;
    }

    public static DoublyLinkedList<Country> getCountries() {
        // A collection to store our country object
        DoublyLinkedList<Country> countries = new DoublyLinkedList<>();

        // Get ISO countries, create Country object and
        // store in the collection.
        String[] isoCountries = Locale.getISOCountries();
        for (String country : isoCountries) {
            Locale locale = new Locale("en", country);
            String iso = locale.getISO3Country();
            String code = locale.getCountry();
            String name = locale.getDisplayCountry();

            if (!"".equals(iso) && !"".equals(code) && !"".equals(name)) {
                countries.add(new Country(iso, code, name));
            }
        }
        return countries;
    }
}
