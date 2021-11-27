/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isep.dei.lapr2.controller;

import isep.dei.lapr2.model.Freelancer;
import isep.dei.lapr2.model.Organization;
import isep.dei.lapr2.model.Platform;
import isep.dei.lapr2.model.RegistryClasses.RegistryFreelancers;
import isep.dei.lapr2.model.RegistryClasses.RegistryOrganizations;
import isep.dei.lapr2.model.RegistryClasses.RegistryTransactions;
import isep.dei.lapr2.model.Transaction;
import java.util.Comparator;
import java.util.List;

/**
 * Controller Class for Stats about Organization Freelancers Scene
 */
public class StatsAboutFreelancerInOrganizationController {
    /**
     * Platform
     */
      private Platform platform;
    /**
     * Registry Transaction Class
     */
    private RegistryTransactions regTrans;
    /**
     * Registry Freelancer Class
     */
    private RegistryFreelancers regFrl;
    /**
     * Organization Registry Class
     */
    private RegistryOrganizations  m_RegistryOrganizations;
    /**
     * Email of the current user logged
     */
    private String email;
    /**
     * Organization Currently Logged
     */
    private Organization  org;

    /**
     * Constructor that initiates all necessary variable
     */
    public StatsAboutFreelancerInOrganizationController() {
        this.platform = MainController.getPlatform();
        this.regTrans = platform.getRegistryTransaction();
        this.regFrl = platform.getRegistryFreelancer();
        this.m_RegistryOrganizations=platform.getRegistryOrganizations();
        this.email=MainController.getLoginSystem().getLoggedUserEmail();
        this.org= m_RegistryOrganizations.getOrgByUserEmail(email);
    }

    /**
     * Method that return Freelancers working for the Organization logged.
     * @return list of Freelancers that work for the organization logged.
     */
    public List<Freelancer> getListFreelancersByOrg() {
        return regFrl.getListFreelancersByOrg(org);
    }

    /**
     * Method that return Freelancer by the ID
     * @param id ID of the Freelancer
     * @return Freelancer with the ID
     */
    public Freelancer getFreelancer(String id) {
        return regFrl.getFreelancerByID(id);
    }

    /**
     * Method that returns the Mean Delays of the Organization.
     * @return value of the Mean Delays
     */
     public double getMeanDelays() {
        return regTrans.getMeanDelays(regTrans.getListOrganizationTransactions(org));
    }

    /**
     * Method that returns the deviation of the delays
     * @return double of deviation of the delays
     */
    public double getDeviationDelays() {
        return regTrans.getDeviationDelays(regTrans.getListOrganizationTransactions(org));
    }

    /**
     * Returns an integer array with the information to the delays histogram
     * @return an integer array with the information to the delays histogram
     */
    public int[] getHistogramDataDelays() {
        return regTrans.getHistogramDataDelays(regTrans.getListOrganizationTransactions(org));
    }

    /**
     * Returns the mean of payments related to the organization
     * @return the mean of payments related to the organization
     */
    public double getMeanPays() {
        return regTrans.getMeanPays(regTrans.getListOrganizationTransactions(org));
    }

    /**
     * Returns the standard deviation of payments related to the organization
     * @return the standard deviation of payments related to the organization
     */
    public double getDeviationPays() {
        return regTrans.getDeviationPays(regTrans.getListOrganizationTransactions(org));
    }

    /**
     * Returns an integer array with the information to the payments histogram
     * @return an integer array with the information to the payments histogram
     */
    public int[] getHistogramDataPays() {
        return regTrans.getHistogramDataPays(regTrans.getListOrganizationTransactions(org));
    }

    /**
     * Returns the mean of delays of a specific freelancer, related to an organization
     * @param frl the freelancer wanted
     * @return the mean of delays of the specific freelancer
     */
    public double getMeanDelaysOfFrl(Freelancer frl) {
        return regTrans.getMeanDelaysOfFreelancer(frl,regTrans.getListOrganizationTransactions(org));
    }

    /**
     * Returns the standard deviation of delays of a specific freelancer, related to an organization
     * @param frl the freelancer wanted
     * @return the mean of delays of the specific freelancer
     */
    public double getDeviationDelaysOfFrl(Freelancer frl) {
        return regTrans.getDeviationDelaysOfFreelancer(frl,regTrans.getListOrganizationTransactions(org));
    }

    /**
     * Returns an array with the data to the histogram, related to the delays of a freelancer
     * @param frl the freelancer wanted
     * @return an array with the data to the histogram, related to the delays of a freelancer
     */
    public int[] getHistogramDataDelaysOfFrl(Freelancer frl) {
        return regTrans.getHistogramDataDelaysOfFreelacer(frl,regTrans.getListOrganizationTransactions(org));
    }

    /**
     * Returns the mean of payments of a specific freelancer, related to an organization
     * @param frl the freelancer wanted
     * @return the payments of delays of the specific freelancer
     */
    public double getMeanPaysOfFrl(Freelancer frl) {
        return regTrans.getMeanPaysOfFreelancer(frl,regTrans.getListOrganizationTransactions(org));
    }

    /**
     * Returns the standard deviation of payments of a specific freelancer, related to an organization
     * @param frl the freelancer wanted
     * @return the mean of payments of the specific freelancer
     */
    public double getDeviationPaysOfFrl(Freelancer frl) {
        return regTrans.getDeviationPaysOfFreelancer(frl,regTrans.getListOrganizationTransactions(org));
    }

    /**
     * Returns an array with the data to the histogram, related to the payments of a freelancer
     * @param frl the freelancer wanted
     * @return an array with the data to the histogram, related to the payments of a freelancer
     */
    public int[] getHistogramDataPaysOfFrl(Freelancer frl) {
        return regTrans.getHistogramDataPaysOfFreelancer(frl,regTrans.getListOrganizationTransactions(org));
    }

    /**
     * Returns a list of the different comparators to freelancer
     * @return a list of the different comparators to freelancer
     */
    public List<Comparator<Freelancer>> getCriterios() {
        return regFrl.getCriterios();
    }
}
