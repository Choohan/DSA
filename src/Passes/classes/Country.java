package Passes.classes;

public class Country {
    private final String ISO;
    private final String CODE;
    private final String NAME;

    public Country(String iso, String code, String name) {
        this.ISO = iso;
        this.CODE = code;
        this.NAME = name;
    }
    public String getIso() {
        return ISO;
    }

    public String getCode() {
        return CODE;
    }

    public String getName() {
        return NAME;
    }

    public String toString() {
        return ISO + " - " + CODE + " - " + NAME.toUpperCase();
    }
}
