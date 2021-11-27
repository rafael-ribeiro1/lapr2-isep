package isep.dei.lapr2.model.reader;

import isep.dei.lapr2.controller.MainController;
import isep.dei.lapr2.model.*;
import isep.dei.lapr2.model.RegistryClasses.ListTasks;
import isep.dei.lapr2.model.RegistryClasses.RegistryFreelancers;
import isep.dei.lapr2.model.RegistryClasses.RegistryOrganizations;
import isep.dei.lapr2.model.RegistryClasses.RegistryTransactions;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Class that reads a csv file
 */
public class CSVFileReader implements FileReader {
    /**
     * Reads the csv file
     * @param file the file to read
     */
    @Override
    public void readFile(File file) {
        Scanner sc = null;
        Platform m_platform = MainController.getPlatform();
        RegistryOrganizations m_RegistryOrganizations = m_platform.getRegistryOrganizations();
        RegistryFreelancers m_Freelancer = m_platform.getRegistryFreelancer();
        String email = MainController.getLoginSystem().getLoggedUserEmail();
        Organization org = m_RegistryOrganizations.getOrgByUserEmail(email);
        ListTasks ls = org.getListTask();
        RegistryTransactions m_Transaction = m_platform.getRegistryTransaction();
        String line = "";
        String[] data;
        SimpleDateFormat dataFormat1 = new SimpleDateFormat("dd-mm-yyyy");
        SimpleDateFormat dataFormat2 = new SimpleDateFormat("dd/mm/yyyy");
        try {
            if (file == null) throw new NullPointerException("Invalid File");
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
        }
        if (sc != null) {
            while (sc.hasNextLine()) {
                try {
                    line = sc.nextLine();
                    data = line.split(";");
                    if (data.length != 17) throw new IllegalArgumentException();
                    Freelancer free = m_Freelancer.newFreelancer(data[9], data[10], Freelancer.Expertise.valueOf(data[11]), data[12], data[13], data[14], data[15], data[16]);

                    Task task = ls.newTask(data[1], Integer.parseInt(data[3]), Double.parseDouble(data[4]), data[5], data[2]);

                    if (data[6].split("-").length == 3) {
                        m_Transaction.registerTransaction(new Transaction(org, data[0], dataFormat1.parse(data[6]), Integer.parseInt(data[7]), data[8], false, task, free));
                    } else if (data[6].split("/").length == 3) {
                        m_Transaction.registerTransaction(new Transaction(org, data[0], dataFormat2.parse(data[6]), Integer.parseInt(data[7]), data[8], false, task, free));
                    } else {
                        throw new IllegalArgumentException();
                    }
                    if (m_Freelancer.validate(free)) {
                        m_Freelancer.registerFreelancer(free);
                    }
                    if (ls.validate(task)) {
                        ls.registerTask(task);
                    }
                    task.setTaskHasTransactionTrue();
                } catch (IllegalArgumentException | ParseException e) {
                    continue;
                }
            }
            sc.close();
        }
    }
}
