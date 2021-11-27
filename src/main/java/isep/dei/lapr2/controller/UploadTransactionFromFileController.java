/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isep.dei.lapr2.controller;

import isep.dei.lapr2.model.*;
import isep.dei.lapr2.model.Freelancer.Expertise;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import isep.dei.lapr2.model.RegistryClasses.ListTasks;
import isep.dei.lapr2.model.RegistryClasses.RegistryFreelancers;
import isep.dei.lapr2.model.RegistryClasses.RegistryOrganizations;
import isep.dei.lapr2.model.RegistryClasses.RegistryTransactions;
import isep.dei.lapr2.model.reader.CSVFileReader;
import isep.dei.lapr2.model.reader.FileReaderContext;
import isep.dei.lapr2.model.reader.TXTFileReader;
import javafx.stage.FileChooser;

/**
 * Controller Class that uploads the transaction File.
 */
public class UploadTransactionFromFileController {
    /**
     * Platform class
     */
    private Platform m_platform;

    /**
     * Constructor of the class that initiates the variables
     */
    public UploadTransactionFromFileController() {
        m_platform = MainController.getPlatform();
    }

    /**
     * Method that returns the File Selected by the user.
     * @return File Selected
     */
    public File uploadTransactionfromFile() {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.dir")));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"), new FileChooser.ExtensionFilter("TXT Files", "*.txt"));
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            return selectedFile;
        } else {
            return null;
        }
    }

    /**
     * Method that saves in the system the data from the file
     * @param file File Selected
     */
    public void savesDatafromFile(File file) {
        FileReaderContext ctx = new FileReaderContext();
        if (file.getName().endsWith("csv")) {
            ctx.setReaderStrategy(new CSVFileReader());
            ctx.readFile(file);
        } else if (file.getName().endsWith("txt")) {
            ctx.setReaderStrategy(new TXTFileReader());
            ctx.readFile(file);
        }
    }
}