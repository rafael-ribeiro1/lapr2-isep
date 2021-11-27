/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isep.dei.lapr2.model;

import isep.dei.lapr2.controller.MainController;
import isep.dei.lapr2.model.RegistryClasses.ListTasks;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Class that represents Organization
 */
public class Organization implements Serializable {
    /**
     * Name of the Organization
     */
    private String orgName;
    /**
     * Nif of the Organization
     */
    private String orgNIF;
    /**
     * Value that represents the day of the Payments
     */
    private int paymentDay;
    /**
     * Value that represents the hour of the Payments
     */
    private int paymentHour;
    /**
     * Value that represents the minute of the Payments
     */
    private int paymentMinute;
    /**
     * Collaborator of the Organization
     */
    private Collaborator collaborator;
    /**
     * Manager of the Organization
     */
    private Manager manager;
    /**
     * List of tasks of the Organization
     */
    private ListTasks m_listTasks;
    /**
     * Default value of Day of the Payments
     */
    private static final int DEFAULT_DAY = 1;
    /**
     * Default value of the hour of Payments
     */
    private static final int DEFAULT_HOUR = 0;
    /**
     * Default value of minutes of Payments
     */
    private static final int DEFAULT_MINUTE = 0;

    /**
     * Constructor of the Organization
     * @param orgName Name of the Organization
     * @param nif NIF of the Organization
     * @param nameCollab Name of the collaborator
     * @param emailCollab Email of the COllaborator
     * @param nameMan Name of the Manager
     * @param emailMan Email of the manager
     */
    public Organization(String orgName, String nif, String nameCollab, String emailCollab, String nameMan, String emailMan) {
        setOrgName(orgName);
        setOrgNIF(nif);
        this.paymentDay = DEFAULT_DAY;
        this.paymentHour = DEFAULT_HOUR;
        this.paymentMinute = DEFAULT_MINUTE;
        this.collaborator = new Collaborator(nameCollab, emailCollab);
        this.manager = new Manager(nameMan, emailMan);
        this.m_listTasks = new ListTasks();
    }

    /**
     * Getter of the list of tasks of the organization.
     * @return List of the tasks of the organization.
     */
    public ListTasks getListTask(){return m_listTasks;}

    /**
     * Setter that validates and defines the name of the Organization.
     * @param orgName
     */
    public void setOrgName(String orgName) {
        if(orgName.trim().isEmpty()||orgName==null){
            throw new IllegalArgumentException("Empty Organization name !");
        }
        this.orgName = orgName;
    }

    /**
     * Setter that validates and defines the NIF of the organization
     * @param orgNIF Nif of the Organization
     */
    public void setOrgNIF(String orgNIF) {
        try {
            if (orgNIF.trim().isEmpty()|| Long.parseLong(orgNIF) >= 1000000000 || Long.parseLong(orgNIF) <= 99999999) {
                throw new IllegalArgumentException("Invalid Organization NIF  ! (A valid has 9 digits)");
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid Format for NIF ! (Introduce Numbers)");
        }
        this.orgNIF = orgNIF;
    }

    /**
     * Getter that return the Name of the organization.
     * @return Name of the organization.
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * Getter that returns the NIF of the organization.
     * @return NIF of the organization.
     */
    public String getOrgNIF() {
        return orgNIF;
    }

    /**
     * Getter that returns the Collaborator of the Organization
     * @return Collaborator of the Organization
     */
    public Collaborator getCollaborator() {
        return collaborator;
    }

    /**
     * Getter of the Manager of the Organization
     * @return Manager of the Organization
     */
    public Manager getManager() {
        return manager;
    }

    /**
     * Method that register the Payments Datetime of the Organization
     * @param day Day of the Payment
     * @param hour Hour of the Payment
     * @param min Minute of the Payment
     * @return true if its is a valid DateTime of the Payment
     */
    public boolean registerPaymentsDatetime(int day, int hour, int min) {
        if (!validDatetime(day, hour, min)) {
            return false;
        } else {
            setPaymentsDatetime(day, hour, min);
            MainController.getPlatform().getRegistryTransaction().scheduleOrgPayments(this);
            return true;
        }
    }

    /**
     * Method that validates a DateTime
     * @param day Day of DateTime
     * @param hour hour of the DateTime
     * @param min Minute of the DateTime
     * @return
     */
    public boolean validDatetime(int day, int hour, int min) {
        return (day >= 1 && day <= 28) && (hour >= 0 && hour < 24)
                && (min >= 0 && min < 60);
    }

    /**
     * Setter of the Payment DateTime
     * @param day Day of the DateTime
     * @param hour Hour of the DateTime
     * @param min Minute of the DateTime
     */
    public void setPaymentsDatetime(int day, int hour, int min) {
        this.paymentDay = day;
        this.paymentHour = hour;
        this.paymentMinute = min;
    }

    /**
     * Method of equals for Organization
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return orgName.equals(that.orgName) &&
                orgNIF.equals(that.orgNIF);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.orgName);
        hash = 23 * hash + Objects.hashCode(this.orgNIF);
        return hash;
    }

    /**
     * Method that return the Date of the next Payment
     * @return Date of the Next Payment
     */

    public Date getNextPaymentsDate() {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DAY_OF_MONTH) > this.paymentDay)
            calendar.add(Calendar.MONTH, 1);
        if ((calendar.get(Calendar.DAY_OF_MONTH) == this.paymentDay) && 
                ((calendar.get(Calendar.HOUR_OF_DAY) > this.paymentHour) ||
                ((calendar.get(Calendar.HOUR_OF_DAY) == this.paymentHour) &&
                (calendar.get(Calendar.MINUTE) >= this.paymentMinute))))
            calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, this.paymentDay);
        calendar.set(Calendar.HOUR_OF_DAY, this.paymentHour);
        calendar.set(Calendar.MINUTE, this.paymentMinute);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

}
