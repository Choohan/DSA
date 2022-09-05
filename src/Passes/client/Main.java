package Passes.client;

import Passes.adt.DoublyLinkedList;
import Passes.classes.*;
import static Passes.client.Utility.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        // data storage
        DoublyLinkedList<Account> accountList = new DoublyLinkedList<>(); // store accounts
        Account currentUser = new Account();
        // init
        menu(accountList, currentUser);
    }

    public static void menu(DoublyLinkedList<Account> accountList, Account currentUser) {
        do {
            System.out.println("\n--------------- Passes Main Menu ---------------");
            System.out.println("1. Login");
            System.out.println("2. Signup");
            switch (promptInt("\nSelect an option (-1 to exit): ")) {
                case -1 -> System.exit(1);
                case 1 -> loginMenu(accountList, currentUser);
                case 2 -> signupMenu(accountList);
                default -> System.out.println("Invalid choice entered...");
            }
            pressAnyKeyToContinue();
        }
        while (true);
    }

    public static void loginMenu(DoublyLinkedList<Account> accountList, Account currentUser) {
        do {
            System.out.println("--------------- Please login to enter the system ---------------");
            String username = promptString("Username: ");
            String password = promptString("Password: ");
            for (int i = 0; i < accountList.size(); i++) {
                if (accountList.get(i).getUser().getUsername().equals(username) &&
                        accountList.get(i).getUser().getPassword().equals(password)) {
                    currentUser = accountList.get(i);
                    System.out.println("Login successful!");
                    userMenu(accountList, currentUser);
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
                case 1 -> socialVisitPass(accountList, null);
                case 2 -> socialVisitPassSpouse(accountList, null);
                case 3 -> visitPassProfessional(accountList, null);
                case 4 -> secondHomePass(accountList, null);
                default -> System.out.println("Invalid choice entered...");
            }
            pressAnyKeyToContinue();
        }
        while (choices.contains(choice));
    }

    public static void userMenu(DoublyLinkedList<Account> accountList, Account currentUser) {
        boolean check;
        do {
            check = false;
            System.out.println("--------------- Passes User Menu ---------------");
            System.out.println("1. Passes");
            System.out.println("2. Profile");
            System.out.println("3. Logout");
            switch (promptInt("\nSelect an option : ")) {
                case 1 -> passesMenu(accountList, currentUser);
                case 2 -> profileMenu(accountList, currentUser);
                case 3 -> {
                    currentUser = null;
                    menu(accountList, currentUser);
                }
                default -> {
                    System.out.println("Invalid choice entered...");
                    check = true;
                }
            }
            pressAnyKeyToContinue();
        }
        while (check);
    }

    public static void passesMenu(DoublyLinkedList<Account> accountList, Account currentUser) {
        do {
            System.out.println("--------------- Passes Menu ---------------");
            System.out.println("1. Add Passes");
            System.out.println("2. View Passes");
            System.out.println("3. Edit Passes");
            System.out.println("4. Search Passes");
            System.out.println("5. Delete/Cancel Passes");
            switch (promptInt("\nSelect an option (-1 to return): ")) {
                case -1 -> { return; }
                case 1 -> viewPasses(accountList,currentUser);
                case 2 -> addPasses(accountList,currentUser);
                case 3 -> editPasses(accountList,currentUser);
                case 4 -> searchPasses(accountList,currentUser);
                case 5 -> terminatePasses(accountList,currentUser);
                default -> System.out.println("Invalid choice entered...");
            }
            pressAnyKeyToContinue();
        }
        while (true);
    }

    public static void profileMenu(DoublyLinkedList<Account> accountList, Account currentUser) {
        do {
            System.out.println("--------------- Passes Profile Menu ---------------");
            System.out.println("1. View Profile");
            System.out.println("2. Edit Profile");
            System.out.println("3. Terminate Account");
            switch (promptInt("\nSelect an option (-1 to return): ")) {
                case -1 -> { return; }
                case 1 -> viewProfile(accountList,currentUser);
                case 2 -> editProfile(accountList,currentUser);
                case 3 -> terminateAccount(accountList, currentUser);
                default -> System.out.println("Invalid choice entered...");
            }
            pressAnyKeyToContinue();
        }
        while (true);
    }

    public static int countPasses(DoublyLinkedList<Account> accountList) {
        int count = 0;
        for (int accountIndex = 0 ; accountIndex < accountList.size(); accountIndex++) {
            count += accountList.get(accountIndex).getPasses().size();
        }
        return count;
    }

    public static Individual createIndividual(String msgPassportNo, String msgNationality, DoublyLinkedList<Account> accountList) {
        return new Individual(String.valueOf(countPasses(accountList))+1 ,
                getPassport(msgPassportNo),
                getNationality(msgNationality), LocalDateTime.now());
    }

    public static Sponsor createSponsor(String msgIDNo, String msgNationality, DoublyLinkedList<Account> accountList) {
        return new Sponsor(String.valueOf(countPasses(accountList))+1,
                getNRIC(msgIDNo),
                getNationality(msgNationality), LocalDateTime.now());
    }

    public static void socialVisitPass(DoublyLinkedList<Account> accountList, Account currentUser) {
        boolean loop;
        Account account = currentUser == null ? createAccount("Full Name : ",
                "Telephone No : ",
                "Email : ",
                "Username : ",
                "Date of Birth : ",
                accountList) : currentUser;
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
                case 1 -> account.getPasses().add(createIndividual("Passport No : ", "Nationality : ", accountList));
                case 2 -> account.getPasses().add(createSponsor("Identification No : ", "Nationality : ", accountList));
                default -> {
                    System.out.println("Invalid Selection...");
                    loop = true;
                }
            }
        }
        while (loop);
        if (currentUser == null) {
            accountList.add(account);
        }
        System.out.println("\nApplication Submitted Successfully!");
    }

    public static void socialVisitPassSpouse(DoublyLinkedList<Account> accountList, Account currentUser) {
        System.out.println("\n\n--------------- Spouse Details ---------------");
        System.out.println("Type of Application : Social Visit Pass (Spouse of Malaysia Citizen)\n");
        Account account = currentUser == null ? createAccount("Spouse Full Name : ",
                "Telephone No : ",
                "Email : ",
                "Username : ",
                "Spouse Date of Birth : ",
                accountList) : currentUser;
        account.getPasses().add(createSpouse(accountList));
        if (currentUser == null) {
            accountList.add(account);
        }
        System.out.println("\nApplication Submitted Successfully!");
    }

    public static Professional createProfessional(DoublyLinkedList<Account> accountList, Category category) {
        return new Professional(String.valueOf(countPasses(accountList))+1,
                getCompanyID("Company Registration ID : "),
                category, promptString("Company Name : "), LocalDateTime.now());
    }

    public static Spouse createSpouse(DoublyLinkedList<Account> accountList) {
        return new Spouse(String.valueOf(countPasses(accountList))+1,
                getNRIC("Identification No (NRIC): "),
                getNationality("Applicant Nationality : "),
                getPassport("Applicant Passport No: "), LocalDateTime.now());
    }

    public static void visitPassProfessional(DoublyLinkedList<Account> accountList, Account currentUser) {
        System.out.println("\n\n--------------- Employer Detail ---------------");
        System.out.println("Type of Application : Visit Pass (Professional)\n");
        Category category = getCategory();
        if (category == null) {
            System.out.println("Application process terminated...");
            return;
        }
        Account account = currentUser == null ? createAccount("Full Name : ",
                "Telephone No : ",
                "Email : ",
                "Username : ",
                "Date of Birth : ",
                accountList) : currentUser;

        account.getPasses().add(createProfessional(accountList, category));
        if (currentUser == null) {
            accountList.add(account);
        }
        System.out.println("\nApplication Submitted Successfully!");
    }

    public static Category getCategory() {
        boolean loop;
        Category category = null;
        do {
            loop = false;
            System.out.println("\tCategory selection");
            System.out.println("  -------------------------");
            System.out.println("  1. Agency");
            System.out.println("  2. Organization");
            System.out.println("  3. Employer");
            switch (promptInt("  Please select a category (-1 to exit) : ")) {
                case -1 -> { return null; }
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
        return category;
    }

    public static void secondHomePass(DoublyLinkedList<Account> accountList, Account currentUser) {
        System.out.println("\n\n--------------- Applicant Details ---------------");
        System.out.println("Type of Application : Malaysia Second Home Programme Pass\n");
        System.out.println("*Note: Please Insert International Code before key-in Telephone Number. ( exp: +1-212-456-7890 )\n");
        Account account = currentUser == null ? createAccount("Full Name : ",
                "Telephone No : ",
                "Email : ",
                "Username : ",
                "Date of Birth : ",
                accountList) : currentUser;
        account.getPasses().add(createSecondHome("Passport No : ", "Old Passport No : ", "Nationality : ", accountList));
        if (currentUser == null) {
            accountList.add(account);
        }
        System.out.println("\nApplication Submitted Successfully!");
    }

    public static SecondHome createSecondHome(String msgPassport, String msgOldPassport, String msgNationality, DoublyLinkedList<Account> accountList) {
        return new SecondHome(String.valueOf(countPasses(accountList))+1,
                getPassport(msgPassport),
                getPassport(msgOldPassport),
                getNationality(msgNationality), LocalDateTime.now());
    }

    public static Account createAccount(String msgName,
                                        String msgTel,
                                        String msgEmail,
                                        String msgUsername,
                                        String msgDOB,
                                        DoublyLinkedList<Account> accountList) {
        return new Account(new Person(getName(msgName),
                getTel(msgTel),
                getGender(),
                getEmail(msgEmail),
                getUsername(accountList, msgUsername),
                getDOB(msgDOB), getPassword()), new DoublyLinkedList<>(), accountList.size()+1);
    }

    public static void addPasses(DoublyLinkedList<Account> accountList, Account currentUser) {
        boolean flag;
        do {
            flag = false;
            System.out.println("--------------- Application Type ---------------");
            System.out.println("1. Social Visit Pass");
            System.out.println("2. Social Visit Pass (Spouse of Malaysia Citizen)");
            System.out.println("3. Visit Pass (Professional)");
            System.out.println("4. Malaysia Second Home Programme Pass");
            switch (promptInt("\nSelect an application type (-1 to return): ")) {
                case -1 -> { return; }
                case 1 -> socialVisitPass(accountList, currentUser);
                case 2 -> socialVisitPassSpouse(accountList, currentUser);
                case 3 -> visitPassProfessional(accountList, currentUser);
                case 4 -> secondHomePass(accountList, currentUser);
                default -> {
                    System.out.println("Invalid choice entered...");
                    flag = true;
                }
            }
            pressAnyKeyToContinue();
        }
        while (flag);
    }

    public static void viewPasses(DoublyLinkedList<Account> accountList, Account currentUser) {
        for (int i = 0; i < currentUser.getPasses().size() ; i++) {
            System.out.printf("%s.\t%s",i+1 ,currentUser.getPasses().get(i));
        }
    }

    public static void editPasses(DoublyLinkedList<Account> accountList, Account currentUser) {
        for (int i = 0; i < currentUser.getPasses().size() ; i++) {
            System.out.printf("%d. %s %s", i+1, currentUser.getPasses().get(i).getApplyTimestamp().toString(),
                    formatPasses(currentUser.getPasses().get(i)));
        }
        int choice = promptInt("Select a pass to edit : ");
        VisitPass pass = currentUser.getPasses().get(choice-1);
        boolean flag;
        if (pass instanceof Individual) {
            do {
                flag = false;
                System.out.println("1. Passport No.");
                System.out.println("2. Nationality");
                switch (promptInt("Select a element to modify (-1 to return) : ")) {
                    case -1 -> { return; }
                    case 1 -> ((Individual) pass).setPassportNo(getPassport("New Passport No : "));
                    case 2 -> ((Individual) pass).setNationality(getNationality("New Nationality : "));
                    default -> {
                        System.out.println("Invalid choice entered...");
                        flag = true;
                    }
                }
            }
            while (flag);
        }
        else if (pass instanceof Professional) {
            do {
                flag = false;
                System.out.println("1. Company ID");
                System.out.println("2. Category");
                System.out.println("3. Company Name");
                switch (promptInt("Select a element to modify (-1 to return) : ")) {
                    case -1 -> { return; }
                    case 1 -> ((Professional) pass).setCompanyID(getPassport("New Company ID : "));
                    case 2 -> ((Professional) pass).setCategory(getCategory());
                    case 3 -> ((Professional) pass).setCompanyName(promptString("New Company Name : "));
                    default -> {
                        System.out.println("Invalid choice entered...");
                        flag = true;
                    }
                }
            }
            while (flag);
        }
        else if (pass instanceof SecondHome) {
            do {
                flag = false;
                System.out.println("1. Passport No.");
                System.out.println("2. Old Passport No.");
                System.out.println("3. Nationality");
                switch (promptInt("Select a element to modify (-1 to return) : ")) {
                    case -1 -> { return; }
                    case 1 -> ((SecondHome) pass).setPassportNo(getPassport("New Passport No : "));
                    case 2 -> ((SecondHome) pass).setOldPassportNo(getPassport("New Previous Passport No : "));
                    case 3 -> ((SecondHome) pass).setNationality(getNationality("New Nationality : "));
                    default -> {
                        System.out.println("Invalid choice entered...");
                        flag = true;
                    }
                }
            }
            while (flag);
        }
        else if (pass instanceof Sponsor) {
            do {
                flag = false;
                System.out.println("1. Identification No.");
                System.out.println("2. Nationality");
                switch (promptInt("Select a element to modify (-1 to return) : ")) {
                    case -1 -> { return; }
                    case 1 -> ((Sponsor) pass).setIDNo(getNRIC("New Identification No : "));
                    case 2 -> ((Sponsor) pass).setNationality(getNationality("New Nationality : "));
                    default -> {
                        System.out.println("Invalid choice entered...");
                        flag = true;
                    }
                }
            }
            while (flag);
        }
        else if (pass instanceof Spouse) {
            do {
                flag = false;
                System.out.println("1. NRIC");
                System.out.println("2. Nationality");
                System.out.println("3. Passport No.");
                switch (promptInt("Select a element to modify (-1 to return) : ")) {
                    case -1 -> { return; }
                    case 1 -> ((Spouse) pass).setNRIC(getNRIC("New Identification No : "));
                    case 2 -> ((Spouse) pass).setNationality(getNationality("New Nationality : "));
                    case 3 -> ((Spouse) pass).setPassportNo(getPassport("New Passport No : "));
                    default -> {
                        System.out.println("Invalid choice entered...");
                        flag = true;
                    }
                }
            }
            while (flag);
        }
        System.out.println("Pass Modified Successfully!");
    }

    public static String formatPasses(VisitPass pass) {
        if (pass instanceof Individual) {
            return String.format("%s %s %s", pass.getApplyTimestamp().toString(),
                    "Social Visit Pass (Individual) " ,
                    ((Individual) pass).getNationality());
        }
        else if (pass instanceof Professional) {
            return String.format("%s %s %s", pass.getApplyTimestamp().toString(),
                    "Social Visit Pass (Professional) " ,
                    ((Professional) pass).getCompanyName());
        }
        else if (pass instanceof SecondHome) {
            return String.format("%s %s %s %s", pass.getApplyTimestamp().toString(),
                    "Malaysia Second Home Programme Pass" ,
                    ((SecondHome) pass).getOldPassportNo(),
                    ((SecondHome) pass).getPassportNo());
        }
        else if (pass instanceof Sponsor) {
            return String.format("%s %s %s %s", pass.getApplyTimestamp().toString(),
                    "Social Visit Pass (Sponsor)" ,
                    ((Sponsor) pass).getIDNo(),
                    ((Sponsor) pass).getNationality());
        }
        else if (pass instanceof Spouse) {
            return String.format("%s %s %s %s %s", pass.getApplyTimestamp().toString(),
                    "Social Visit Pass (Spouse of Malaysia Citizen)" ,
                    ((Spouse) pass).getPassportNo(),
                    ((Spouse) pass).getNRIC(),
                    ((Spouse) pass).getNationality());
        }
        return "";
    }

    public static void searchPasses(DoublyLinkedList<Account> accountList, Account currentUser) {
        int count = 0;
        boolean flag;
        do {
            flag = false;
            System.out.println("------ Type of Passes ------");
            System.out.println("1. Social Visit Pass (Individual)");
            System.out.println("2. Social Visit Pass (Sponsor)");
            System.out.println("3. Social Visit Pass (Spouse of Malaysia Citizen)");
            System.out.println("4. Visit Pass (Professional)");
            System.out.println("5. Malaysia Second Home Programme Pass");
            switch (promptInt("Select type of pass to search for (-1 to return) : ")) {
                case -1 -> { return; }
                case 1 -> {
                    System.out.println("------ Type of Attribute ------");
                    System.out.println("1. Passport No. ");
                    System.out.println("2. Nationality. ");
                    switch (promptInt("Select type of attribute to search for (-1 to return) : ")) {
                        case -1 -> { return; }
                        case 1 -> {
                            String passportNo = getPassport("Passport No. : ");
                            for (int i = 0 ; i < currentUser.getPasses().size() ; i++) {
                                if (((Individual) currentUser.getPasses().get(i)).getPassportNo().equals(passportNo)) {
                                    System.out.println(currentUser.getPasses().get(i));
                                    count++;
                                }
                            }
                        }
                        case 2 -> {
                            Country nationality = getNationality("Nationality : ");
                            for (int i = 0 ; i < currentUser.getPasses().size() ; i++) {
                                if (((Individual) currentUser.getPasses().get(i)).getNationality().equals(nationality)) {
                                    System.out.println(currentUser.getPasses().get(i));
                                    count++;
                                }
                            }
                        }
                        default -> System.out.println("Invalid choice selected...");
                    }
                }
                case 2 -> {
                    System.out.println("------ Type of Attribute ------");
                    System.out.println("1. Identification No.");
                    System.out.println("2. Nationality ");
                    switch (promptInt("Select type of attribute to search for (-1 to return) : ")) {
                        case -1 -> { return; }
                        case 1 -> {
                            String IDNo = getNRIC("Identification No. : ");
                            for (int i = 0 ; i < currentUser.getPasses().size() ; i++) {
                                if (((Sponsor) currentUser.getPasses().get(i)).getIDNo().equals(IDNo)) {
                                    System.out.println(currentUser.getPasses().get(i));
                                    count++;
                                }
                            }
                        }
                        case 2 -> {
                            Country nationality = getNationality("Nationality : ");
                            for (int i = 0 ; i < currentUser.getPasses().size() ; i++) {
                                if (((Individual) currentUser.getPasses().get(i)).getNationality().equals(nationality)) {
                                    System.out.println(currentUser.getPasses().get(i));
                                    count++;
                                }
                            }
                        }
                        default -> System.out.println("Invalid choice selected...");
                    }
                }
                case 3 -> {
                    System.out.println("------ Type of Attribute ------");
                    System.out.println("1. NRIC");
                    System.out.println("2. Nationality ");
                    System.out.println("3. Passport No. ");
                    switch (promptInt("Select type of attribute to search for (-1 to return) : ")) {
                        case -1 -> { return; }
                        case 1 -> {
                            String NRIC = getNRIC("Identification No. : ");
                            for (int i = 0 ; i < currentUser.getPasses().size() ; i++) {
                                if (((Spouse) currentUser.getPasses().get(i)).getNRIC().equals(NRIC)) {
                                    System.out.println(currentUser.getPasses().get(i));
                                    count++;
                                }
                            }
                        }
                        case 2 -> {
                            Country nationality = getNationality("Nationality : ");
                            for (int i = 0 ; i < currentUser.getPasses().size() ; i++) {
                                if (((Spouse) currentUser.getPasses().get(i)).getNationality().equals(nationality)) {
                                    System.out.println(currentUser.getPasses().get(i));
                                    count++;
                                }
                            }
                        }
                        case 3 -> {
                            String passportNo = getPassport("Passport No. : ");
                            for (int i = 0 ; i < currentUser.getPasses().size() ; i++) {
                                if (((Spouse) currentUser.getPasses().get(i)).getPassportNo().equals(passportNo)) {
                                    System.out.println(currentUser.getPasses().get(i));
                                    count++;
                                }
                            }
                        }
                        default -> System.out.println("Invalid choice selected...");
                    }
                }
                case 4 -> {
                    System.out.println("------ Type of Attribute ------");
                    System.out.println("1. Company Name");
                    System.out.println("2. Category ");
                    System.out.println("3. Company ID ");
                    switch (promptInt("Select type of attribute to search for (-1 to return)")) {
                        case -1 -> { return; }
                        case 1 -> {
                            String companyName = promptString("Company Name : ");
                            for (int i = 0 ; i < currentUser.getPasses().size() ; i++) {
                                if (((Professional) currentUser.getPasses().get(i)).getCompanyName().equals(companyName)) {
                                    System.out.println(currentUser.getPasses().get(i));
                                    count++;
                                }
                            }
                        }
                        case 2 -> {
                            Category category = getCategory();
                            for (int i = 0 ; i < currentUser.getPasses().size() ; i++) {
                                if (((Professional) currentUser.getPasses().get(i)).getCategory().equals(category)) {
                                    System.out.println(currentUser.getPasses().get(i));
                                    count++;
                                }
                            }
                        }
                        case 3 -> {
                            String companyID = getCompanyID("Company ID : ");
                            for (int i = 0 ; i < currentUser.getPasses().size() ; i++) {
                                if (((Professional) currentUser.getPasses().get(i)).getCompanyID().equals(companyID)) {
                                    System.out.println(currentUser.getPasses().get(i));
                                    count++;
                                }
                            }
                        }
                        default -> System.out.println("Invalid choice selected...");
                    }
                }
                case 5 -> {
                    System.out.println("------ Type of Attribute ------");
                    System.out.println("1. Passport No.");
                    System.out.println("2. Old Passport No.");
                    System.out.println("3. Nationality");
                    switch (promptInt("Select type of attribute to search for (-1 to return)")) {
                        case -1 -> { return; }
                        case 1 -> {
                            String passportNo = getPassport("Passport No : ");
                            for (int i = 0 ; i < currentUser.getPasses().size() ; i++) {
                                if (((SecondHome) currentUser.getPasses().get(i)).getPassportNo().equals(passportNo)) {
                                    System.out.println(currentUser.getPasses().get(i));
                                    count++;
                                }
                            }
                        }
                        case 2 -> {
                            String oldPassportNo = getPassport("Old Passport No : ");
                            for (int i = 0 ; i < currentUser.getPasses().size() ; i++) {
                                if (((SecondHome) currentUser.getPasses().get(i)).getOldPassportNo().equals(oldPassportNo)) {
                                    System.out.println(currentUser.getPasses().get(i));
                                    count++;
                                }
                            }
                        }
                        case 3 -> {
                            Country nationality = getNationality("Nationality : ");
                            for (int i = 0 ; i < currentUser.getPasses().size() ; i++) {
                                if (((SecondHome) currentUser.getPasses().get(i)).getNationality().equals(nationality)) {
                                    System.out.println(currentUser.getPasses().get(i));
                                    count++;
                                }
                            }
                        }
                        default -> System.out.println("Invalid choice selected...");
                    }
                }
                default -> {
                    System.out.println("Invalid choice entered...");
                    flag = true;
                }
            }
        } while(flag);
        System.out.println("------------------------------------------------");
        System.out.printf("\n %d results found...", count);
        pressAnyKeyToContinue();
    }

    public static void terminatePasses(DoublyLinkedList<Account> accountList, Account currentUser) {
        for (int i = 0; i < currentUser.getPasses().size() ; i++) {
            System.out.printf("%d. %s %s", i+1, currentUser.getPasses().get(i).getApplyTimestamp().toString(),
                    formatPasses(currentUser.getPasses().get(i)));
        }
        int choice = promptInt("Select a pass to terminate : ");
        VisitPass pass = currentUser.getPasses().get(choice-1);
        if (Character.toLowerCase(
                promptChar(
                        String.format("Are you sure you want to terminate #%s %s", pass.getApplicationID(), pass.getTitle()))) == 'y') {
            currentUser.getPasses().remove(pass);
            System.out.println("Pass Terminated Successfully!");
            return;
        }
        System.out.println("Canceled Terminate Operation...");
    }

    public static void viewProfile(DoublyLinkedList<Account> accountList, Account currentUser) {
        System.out.println("--------------- Passes Profile ---------------");
        System.out.printf("Name          : %s", currentUser.getUser().getFullName());
        System.out.printf("\nTelephone No  : %s", currentUser.getUser().getTel());
        System.out.printf("\nGender        : %c", currentUser.getUser().getGender() == 'M' ? 'M' : 'F');
        System.out.printf("\nEmail         : %s", currentUser.getUser().getEmail());
        System.out.printf("\nUsername      : %s", currentUser.getUser().getUsername());
        System.out.printf("\nDate of Birth : %s", currentUser.getUser().getDob().format(DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.ROOT)));
        System.out.print("\nPassword      : ");
        for (int i = 0 ; i < currentUser.getUser().getPassword().length() ; i++)
            System.out.print("*");
        System.out.print("\n\n");
    }

    public static void editProfile(DoublyLinkedList<Account> accountList, Account currentUser) {
        do {
            System.out.println("--------------- Passes Edit Profile ---------------");
            System.out.println("1. Name");
            System.out.println("2. Telephone No");
            System.out.println("3. Gender");
            System.out.println("4. Email");
            System.out.println("5. Username");
            System.out.println("6. Date of Birth");
            System.out.println("7. Password");
            switch (promptInt("\nSelect an option (-1 to return): ")) {
                case -1 -> System.exit(1);
                case 1 -> currentUser.getUser().setFullName(getName("New Full Name : "));
                case 2 -> currentUser.getUser().setTel(getTel("New Telephone No : "));
                case 3 -> currentUser.getUser().setGender(getGender());
                case 4 -> currentUser.getUser().setEmail(getEmail("New Email : "));
                case 5 -> currentUser.getUser().setUsername(getUsername(accountList, "New Username : "));
                case 6 -> currentUser.getUser().setDob(getDOB("New Date of Birth : "));
                case 7 -> currentUser.getUser().setPassword(getPassword());
                default -> {
                    System.out.println("Invalid choice entered...");
                    pressAnyKeyToContinue();
                    continue;
                }
            }
            System.out.println("Profile edited successfully!");
            return;
        }
        while (true);
    }

    public static void terminateAccount(DoublyLinkedList<Account> accountList, Account currentUser) {
        switch (promptChar("\nAre you sure you want to terminate your account (Y/N) ?   \n\t(All Passes will be lost)\n\n >")) {
            case 'y', 'Y' -> {
                accountList.remove(currentUser);
                System.out.println("Account terminated successfully!");
                menu(accountList, currentUser);
            }
        }
    }

    public static String getCompanyID(String msg) {
        String companyID;
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
        String passport;
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
        String NRIC;
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
        String nationality_str;
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
        String name;
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
        String email;
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
        String tel;
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
        String username;
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
