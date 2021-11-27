package isep.dei.lapr2.model.RegistryClasses;


import isep.dei.lapr2.controller.EndYearEmailTask;
import isep.dei.lapr2.controller.MainController;
import isep.dei.lapr2.model.*;
import sun.applet.Main;

import java.io.Serializable;
import java.util.*;

/**
 * Class that register and keeps the freelancers
 */
public class RegistryFreelancers implements Serializable {

    /**
     * The list of freelancers
     */
    private List<Freelancer> m_lstFreelancer;

    /**
     * Initializes the list
     */
    public RegistryFreelancers() {

        this.m_lstFreelancer = new ArrayList<>();
    }

    /**
     * Returns the list of freelancers
     * @return the list of freelancers
     */
    public List<Freelancer> getListFreelancers() {
        return m_lstFreelancer;
    }

    /**
     * Returns the freelancers from an organization
     * @param org the organization to filter
     * @return the freelancers from the organization
     */
    public List<Freelancer> getListFreelancersByOrg(Organization org) {
        List<Freelancer> fr = new ArrayList<>();
        RegistryTransactions rt = MainController.getPlatform().getRegistryTransaction();
        List<Transaction> trans = rt.getListOrganizationTransactions(org);
        for (Transaction t : trans) {
            for (Freelancer f : m_lstFreelancer) {
                if (t.getFreelancer().getId().equalsIgnoreCase(f.getId()) && !freelancerNotInArray(f, fr)) {
                    fr.add(f);
                }
            }
        }
        return fr;
    }

    /**
     * Verifies if a freelancer is in the list
     * @param f the freelancer to verify
     * @param fr the list to verify if exists
     * @return true if the freelancer exists, false if not
     */
    private boolean freelancerNotInArray(Freelancer f, List<Freelancer> fr) {
        for (Freelancer f1 : fr) {
            if (f1.equals(f)) return true;
        }
        return false;
    }

    /**
     * Creates a new freelancer
     * @param name the name of the freelancer
     * @param expertise the expertise of the freelancer
     * @param email the email of the freelancer
     * @param nif the NIF of the freelancer
     * @param bankAccount the bank account of the freelancer
     * @param address the address of the freelancer
     * @param country the country of the freelancer
     * @return the freelancer created
     */
    public Freelancer newFreelancer(String name, Freelancer.Expertise expertise ,String email,String nif,String bankAccount, String address, String country) {
        return new Freelancer(name, expertise, email, nif, bankAccount, address, country);
    }

    /**
     * Creates a new freelancer
     * @param idFreelancer the ID of the freelancer
     * @param freelancerName the name of the freelancer
     * @param freelancerExpertise the expertise of the freelancer
     * @param freelancerEmail the email of the freelancer
     * @param freelancerNIF the NIF of the freelancer
     * @param freelancerBankAccount the bank account of the freelancer
     * @param freelancerAddress the address of the freelancer
     * @param freelancerCountry the country of the freelancer
     * @return the freelancer created
     */
    public Freelancer newFreelancer(String idFreelancer, String freelancerName, Freelancer.Expertise freelancerExpertise, String freelancerEmail, String freelancerNIF, String freelancerBankAccount, String freelancerAddress, String freelancerCountry) {
        return new Freelancer(idFreelancer, freelancerName, freelancerExpertise, freelancerEmail, freelancerNIF, freelancerBankAccount, freelancerAddress, freelancerCountry);
    }

    /**
     * Register a freelancer
     * @param free the freelancer to register
     * @return true if the freelancer was registered, false if not
     */
    public boolean registerFreelancer(Freelancer free) {
        if (validate(free)) {
            return m_lstFreelancer.add(free);
        } else {
            return false;
        }
    }

    /**
     * Validates a freelancer
     * @param free the freelancer to validate
     * @return true if there is no freelancer with the same NIF or the bank account, false if not
     */
    public boolean validate(Freelancer free) {
        if (existsNifInSystem(free)) return false;
        if (existsBankAccountInSystem(free)) return false;
        return true;
    }

    /**
     * Generates an ID to the freelancer
     * @param free the freelancer that needs an ID
     */
    public void generateFreelancerID(Freelancer free) {
        try {
            String id = "";
            String[] array = free.getName().split(" ");
            if (array.length <= 1) {
                throw new IllegalArgumentException("Please insert at least the first and Last Name of the Freelancer");
            }
            id = "" + array[0].charAt(0) + array[array.length - 1].charAt(0);
            id = id.toUpperCase();
            id = generateIndexID(id);
            free.setId(id);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Please insert at least the first and Last Name of the Freelancer");
        }
    }

    /**
     * Generates the index to the ID
     * @param id the id
     * @return the complete ID
     */
    public String generateIndexID(String id) {
        try {
            int index = 1;
            for (Freelancer f : m_lstFreelancer) {
                String idFreelancer = f.getId();
                if ((id + index).equals(idFreelancer)) {
                    index++;
                }
            }
            return id + index;
        } catch (NullPointerException e) {
            return id + "1";
        }

    }

    /**
     * Verifies if exists a freelancer with the same NIF registered
     * @param free the freelancer to verify
     * @return true if exists, false if not
     */
    public boolean existsNifInSystem(Freelancer free) {
        for (Freelancer f : m_lstFreelancer) {
            if (free.getNif().equals(f.getNif())) {
                if (free.equals(f)) {
                    return true;
                } else {
                    throw new IllegalArgumentException("NIF already registered on system !");

                }
            }
        }
        return false;
    }

    /**
     * Verifies if exists a freelancer with the same bank account registered
     * @param free the freelancer to verify
     * @return true if exists, false if not
     */
    public boolean existsBankAccountInSystem(Freelancer free) {
        for (Freelancer f : m_lstFreelancer) {
            if (free.getBankAccount().equals(f.getBankAccount())) {
                if (free.equals(f)) {
                    return true;
                } else {
                    throw new IllegalArgumentException("Bank Account already registered on system !");
                }

            }

        }
        return false;
    }

    /**
     * Schedule the timer to the automatic end-year email
     */
    public void scheduleAutomaticEmail() {
        EndYearEmailTask task = new EndYearEmailTask();
        Date definedDate = getDefinedDate();
        Timer t = new Timer();
        t.schedule(task,definedDate);
    }

    /**
     * Returns the date to the end-year email
     * @return the date to the end-year email
     */
    private Date getDefinedDate() {
        Calendar day = Calendar.getInstance();
        day.set(Calendar.MONTH, 11);
        day.set(Calendar.DAY_OF_MONTH, 31);
        day.set(Calendar.HOUR_OF_DAY, 23);
        day.set(Calendar.MINUTE, 50);
        day.set(Calendar.SECOND, 10);
        return day.getTime();
    }

    /**
     * Returns the average percentage of the delays
     * @return the average percentage of the delays
     */
    public double getAveragePercentageDelay() {
        Platform platform = MainController.getPlatform();
        RegistryTransactions registryTransactions = platform.getRegistryTransaction();
        List<Transaction> allTransactions = registryTransactions.getListTransaction();
        int currentYear =Calendar.getInstance().get(Calendar.YEAR);
        if (allTransactions.size() == 0) {
            return 0;
        }
        double counter = 0;
        for (Transaction t : allTransactions) {
            if (t.getDelay() > 0&&currentYear==t.getEndDate().getYear()) {
                counter++;
            }
        }

        return counter / allTransactions.size();
    }

    /**
     * Returns the sum of delays of a freelancer
     * @param f the freelancer calculate
     * @return the sum of delays of the freelancer
     */
    public double calculateSumDelayFreelancer(Freelancer f){
        double sumDelay =0;
        Platform platform = MainController.getPlatform();
        RegistryTransactions registryTransactions = platform.getRegistryTransaction();
        List<Transaction> allTransactions = registryTransactions.getListTransaction();
        int currentYear =Calendar.getInstance().get(Calendar.YEAR);
        for(Transaction t : allTransactions ){
            Freelancer freelancerTransaction = t.getFreelancer();
            if(freelancerTransaction.equals(f)&&t.getDelay()>0&&currentYear==t.getEndDate().getYear()){
                sumDelay += t.getDelay();
            }
        }
        return sumDelay;
    }

    /**
     * Returns the percentage of delays of a freelancer
     * @param f the freelancer to calculate
     * @return the percentage of delays of the freelancer
     */
    public double calculatePercentageFreelancerDelay(Freelancer f){
        double transactionsMadeByFreelancer = 0;
        double counterDelay = 0;
        Platform platform = MainController.getPlatform();
        RegistryTransactions registryTransactions = platform.getRegistryTransaction();
        List<Transaction> allTransactions = registryTransactions.getListTransaction();
        int currentYear =Calendar.getInstance().get(Calendar.YEAR);
        for(Transaction t : allTransactions){
            if (t.getFreelancer().equals(f)&&currentYear==t.getEndDate().getYear()) {
                transactionsMadeByFreelancer += 1;
                if (t.getDelay() > 0) {
                    counterDelay += 1;
                }
            }
        }
        return counterDelay / transactionsMadeByFreelancer;

    }

    /**
     * Returns a list of freelancers with high delays
     * @return a list of freelancers with high delays
     */
    public List<Freelancer> getFreelancersWithHighDelays() {
        List<Freelancer> freelancersWithHighDelays = new ArrayList<>();
        for (Freelancer f : m_lstFreelancer) {
            double sumHourDelay = calculateSumDelayFreelancer(f);
            double percentageFreelancerDelay = calculatePercentageFreelancerDelay(f);
            double averagePercentageDelay = getAveragePercentageDelay();
            if (sumHourDelay > 3 && percentageFreelancerDelay > averagePercentageDelay) {
                freelancersWithHighDelays.add(f);
            }
        }
        return freelancersWithHighDelays;
    }

    /**
     * Sends the end-year email to freelancers with high delays
     */
    public void sendEndofTheYearEmail() {
        for (Freelancer f : getFreelancersWithHighDelays()) {
           sendEmailWithInfo(f);
        }
    }

    /**
     * Sends email to the freelancer with the informations about high delays
     * @param f the freelancer that will receive the email
     */
    public void sendEmailWithInfo(Freelancer f){
        String email = f.getEmail();
        String header = String.format("Information about your Performance executing tasks during the current year");
        String content = String.format("Good Morning Mr/Mrs %s, T4J wanted to inform that you have a higher than average percentage of delays (Platform average of delays is %.2f percentage)(Your percentage is %.2f )\n and you have a total sum of delay hours higher than 3 hours (Your sum is %.0f)", f.getName(), getAveragePercentageDelay() * 100, calculatePercentageFreelancerDelay(f) * 100, calculateSumDelayFreelancer(f));
        ExternalEmailSender emailSender = MainController.getPlatform().getExternalEmailSender();
        emailSender.sendEmail(email, header, content);
    }

    /**
     * Sends email to the freelancers with high delays
     * @param paidTrans the list of freelancers to send email
     */
    public void sendEmails(List<Transaction> paidTrans) {
        ExternalConverterCurrency converter = MainController.getPlatform().getExternalConverterCurrency();
        for (Freelancer f: this.m_lstFreelancer) {
            double overall = 0;
            String country = f.getCountry();
            String amounts = "";
            for (Transaction payment: paidTrans) {
                if (!f.equals(payment.getFreelancer())) continue;
                double amountEUR = payment.getAmountToPay();
                overall += amountEUR;
                String amountConv = converter.convertCurrency(amountEUR, country);
                amounts += String.format("taskID=%s:%.2f€-%s;|||", payment.getTask().getTaskID(),amountEUR,amountConv);
            }
            if (overall != 0) {
                String overallConv = converter.convertCurrency(overall, country);
                String email = f.getEmail();
                String header = "Payments to freelancer " + f.getId();
                String content = amounts + String.format("overall:%.2f€-%s", overall, overallConv);
                ExternalEmailSender emailSender = MainController.getPlatform().getExternalEmailSender();
                emailSender.sendEmail(email, header, content);
            }
        }
    }

    /**
     * Returns a freelancer by is index in the list
     * @param index the index in the list
     * @return the freelancer in the index
     */
    public Freelancer getFreelancerByIndex(int index) {
        return m_lstFreelancer.get(index);
    }

    /**
     * Returns a freelancer with the specified ID
     * @param id the ID of the freelancer
     * @return the freelancer with the ID or null if does not exists
     */
    public Freelancer getFreelancerByID(String id) {
        for (Freelancer f:m_lstFreelancer) {
            if (id.equalsIgnoreCase(f.getId())) {
                return f;
            }
        }
        
        return null;
    }

    /**
     * Returns the comparators to sort the freelancers
     * @return the comparators to sort the freelancers
     */
    public List<Comparator<Freelancer>> getCriterios(){
        List<Comparator<Freelancer>> criterios = new ArrayList<>();
        Comparator<Freelancer> criterio1 = new Comparator<Freelancer>() {
            @Override
            public int compare(Freelancer t1, Freelancer t2) {
                String nome1 = t1.getName();
                String nome2 = t2.getName();

                if (nome1.compareToIgnoreCase(nome2)<0) {
                    return -1;
                } else if (nome1.compareToIgnoreCase(nome2)>0) {
                    return 1;
                } else {
                    return 0;
                }
            }
            @Override
            public String toString(){
                return "Sort by Name";
            }
        };

        Comparator<Freelancer> criterio2 = new Comparator<Freelancer>() {
            @Override
            public int compare(Freelancer t1, Freelancer t2) {
                RegistryTransactions registryTransactions = MainController.getPlatform().getRegistryTransaction();
                double payment1 = registryTransactions.sumPaymentesByFreelancer(t1);
                double payment2 = registryTransactions.sumPaymentesByFreelancer(t2);

                if ((payment1-payment2) <0) {
                    return -1;
                } else if ((payment1-payment2) >0) {
                    return 1;
                } else {
                    return 0;
                }
            }
            @Override
            public String toString(){
                return "Sort by Payment";
            }
        };
        criterios.add(criterio1);
        criterios.add(criterio2);

        return criterios;
    }
}