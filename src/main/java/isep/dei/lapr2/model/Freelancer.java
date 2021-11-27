package isep.dei.lapr2.model;
import java.io.Serializable;

/**
 * Class that represents the Freelancer
 */
public class Freelancer implements Serializable {
    /**
     * Id of the Freelancer
     */
    private String id;
    /**
     * Name of the Freelancer
     */
    private String name;
    /**
     * Expertise of the Freelancer.
     */
    private Expertise expertise;
    /**
     * Email of the Freelancer
     */
    private String email;
    /**
     * NIF of the Freelancer
     */
    private String nif;
    /**
     * Bank account of the Freelancer.
     */
    private String bankAccount;
    /**
     * Address of the Freelancer.
     */
    private String address;
    /**
     * Country of the Freelancer
     */
    private String country;

    /**
     * Constructor of a Freelancer with No predefined ID
     * @param Name Name of the Freelancer.
     * @param expertise Expertise of the Freelancer.
     * @param email Email of the Freelancer.
     * @param nif NIF of the Freelancer.
     * @param bankAccount Bank account of the Freelancer.
     * @param address Address of the Freelancer.
     * @param country Country of the Freelancer.
     */
    public Freelancer(String Name, Expertise expertise, String email, String nif, String bankAccount, String address, String country) {
        setName(Name);
        setEmail(email);
        setNif(nif);
        setBankAccount(bankAccount);
        setAddress(address);
        setCountry(country);
        setExpertise(expertise);
    }

    /**
     * Constructor of a Freelancer with a Defined ID
     * @param id ID of  Freelancer.
     * @param Name name of the Freelancer.
     * @param expertise Expertise of the Freelancer.
     * @param email Email of the Freelancer.
     * @param nif NIF of the Freelancer.
     * @param bankAccount Bank account of the Freelancer.
     * @param address Address of the Freelancer.
     * @param country Country of the Freelancer
     */
    public Freelancer(String id, String Name, Expertise expertise, String email, String nif, String bankAccount, String address, String country) {
        setId(id);
        setName(Name);
        setEmail(email);
        setNif(nif);
        setBankAccount(bankAccount);
        setAddress(address);
        setCountry(country);
        setExpertise(expertise);
    }
    /**
     * Expertise of the Freelancer
     */
    public enum Expertise {
        Junior{
            @Override
            public String toString() {
                return "Junior";
            }
        },
        Senior{
            @Override
            public String toString() {
                return "Senior";
            }
        }

    }

    /**
     * Setter that validates and defines the name of the Freelancer
     * @param name Name of the Freelancer
     */
    public void setName(String name) {
        if(name.trim().isEmpty() || name ==null) throw new IllegalArgumentException("Invalid name!");
        this.name = name;
    }

    /**
     * Setter that validates and defines the Email of the Freelancer
     * @param email email of the Freelancer.
     */
    public void setEmail(String email) {
        if(email.trim().isEmpty()|| email ==null) throw new IllegalArgumentException("Invalid Email!");
        this.email = email;
    }

    /**
     * Setter that validates and defines the NIF of the Freelancer.
     * @param nif NIF of the Freelancer
     */
    public void setNif(String nif) {
        try {
            if (nif.trim().isEmpty()|| Long.parseLong(nif) >= 1000000000 || Long.parseLong(nif) <= 99999999) {
                throw new IllegalArgumentException("Invalid NIF ! (A valid has 9 numbers)");
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid Format for NIF !");
        }
        this.nif = nif;
    }

    /**
     * Setter that validates and defines the validates the bank account of the freelancer
     * @param bankAccount Bank account of the freelancer.
     */
    public void setBankAccount(String bankAccount) {
        if (bankAccount.trim().isEmpty() ) {
            throw new IllegalArgumentException("Empty Bank Account !");
        }
        this.bankAccount = bankAccount;
    }

    /**
     * Setter that validates and defines the address of the Freelancer.
     * @param address Address of the Freelancer.
     */
    public void setAddress(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Address !");
        }
        this.address = address;
    }

    /**
     * Setter that validates and defines the Country of the Freelancer
     * @param country country of the freelancer.
     */
    public void setCountry(String country) {
        if (country == null || country.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid Country !");
        }
        this.country = country;
    }

    /**
     * Setter that validates and defines the Expertise of the Freelancer
     * @param expertise Expertise of the freelancer.
     */
    public void setExpertise(Expertise expertise) {
        if(expertise ==null){
            throw new IllegalArgumentException("Empty Expertise !");
        }
        this.expertise = expertise;
    }

    /**
     * Setter that validates and defines the ID of the Freelancer.
     * @param id ID of the Freelancer.
     */
    public void setId(String id) {
        if(id ==null){
            new IllegalArgumentException("ID Freelancer is Empty");
        }
        this.id = id;
    }

    /**
     * Getter of the name of the Freelancer
     * @return name of the Freelancer
     */
    public String getName() {
        return name;
    }

    /**
     * Getter of the NIF of the Freelancer
     * @return NIF of the Freelancer
     */
    public String getNif() {
        return nif;
    }

    /**
     * Getter of the Bank account of the Freelancer.
     * @return Bank account of the Freelancer.
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * Getter of available Expertise values
     * @return list of the available Expertise values.
     */
    public Expertise[] getValuesExpertise(){
        return Expertise.values();
    }

    /**
     * Getter of the expertise of the Freelancer.
     * @return Expertise of the Freelancer.
     */
    public Expertise getExpertise() {
        return expertise;
    }

    /**
     * Getter of the ID of the Freelancer
     * @return ID of the Freelancer.
     */
    public String getId() {
        return id;
    }

    /**
     * Getter of the Country of the Freelancer.
     * @return Country of the Freelancer.
     */
    public String getCountry() {
        return this.country;
    }

    /**
     * Getter of the email of the Freelancer
     * @return Email of the Freelancer.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Method that gives information of the Freelancer.
     * @return String of the Information about the Freelancer.
     */
    @Override
    public String toString() {
        return "ID: "+ id + "\nName: "+ name;
    }

    /**
     * Equals method of the Freelancer
     * @param o Object
     * @return True if its the same object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Freelancer that = (Freelancer) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                expertise == that.expertise &&
                email.equals(that.email) &&
                nif.equals(that.nif) &&
                bankAccount.equals(that.bankAccount) &&
                address.equals(that.address) &&
                country.equals(that.country);
    }

}
