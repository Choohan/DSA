package Passes.classes;

import java.time.LocalDate;

public class Person {
    private String fullName;
    private String tel;
    private char gender;
    private String email;
    private String username;
    private LocalDate dob;
    private int age;
    private String password;

    public Person(){
        // empty constructor
    }

    public Person(String fullName, String tel, char gender, String email, String username, LocalDate dob, String password) {
        this.fullName = fullName;
        this.tel = tel;
        this.gender = gender;
        this.email = email;
        this.username = username;
        this.dob = dob;
        this.age = (LocalDate.now().getYear() - dob.getYear());
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
