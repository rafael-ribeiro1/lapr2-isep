/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isep.dei.lapr2.ui;

import isep.dei.lapr2.controller.StatsAboutFreelancerInOrganizationController;
import isep.dei.lapr2.model.Freelancer;
import isep.dei.lapr2.model.RegistryClasses.RegistryTransactions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import isep.dei.lapr2.model.Transaction;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
/**
 * UI class for the Stats of a Organization Scene.
 */
public class StatsAboutFreelancerInOrganizationUI implements Initializable  {
    private StatsAboutFreelancerInOrganizationController controller;
    private CollaboratorMenuUI collaboratorMenuUI;
    private ManagerMenuUI managerMenuUI;
    private RegistryTransactions rt;

    //comboBox Items
    private SortedList personSortedList;
    private ObservableList<Comparator<Freelancer>> personComparators;
    private Image image;
    //introduzir esta nova classe
    //private GlyphCheckBox sortDirectionCheckBox;

    @FXML
    private TextField txtMeanDelays;

    @FXML
    private TextField txtDeviationDelays;

    @FXML
    private TextField txtMeanPays;

    @FXML
    private BarChart histogramDelays;

    @FXML
    private BarChart histogramPays;

    @FXML
    private ImageView ordenarLstView;
    @FXML
    private ImageView ordenarLstView1;

    @FXML
    private TextField txtDeviationPays;
    @FXML
    private ComboBox<Comparator<Freelancer>> cmbSort;

    @FXML
    private ListView<String> lvFrl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Method that initiates the Scene
     */
    public void start() {
        clear();
        this.controller = new StatsAboutFreelancerInOrganizationController();
        lvFrl.getItems().add("All freelancers");

        List<Freelancer> lstFrl = controller.getListFreelancersByOrg();
        for (Freelancer frl: lstFrl) {
            lvFrl.getItems().add(frl.getId());
        }
        showStatsAllFrl();

        cmbSort.getItems().addAll(controller.getCriterios());
    }

    /**
     * Method that set-up the current Scene in the parent Scene.
     * @param collaboratorMenuUI UI class of the parent Scene
     */
    public void openWindow(CollaboratorMenuUI collaboratorMenuUI){
        try{
            this.collaboratorMenuUI=collaboratorMenuUI;
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/StatsAboutFreelancerInOrganizationScene.fxml"));
            loader.setController(this);
            this.collaboratorMenuUI.setBorderCenter((Node)loader.load());
            start();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Method that set-up the current Scene in the parent Scene.
     * @param managerMenuUI UI class of the parent Scene
     */
      public void openWindow(ManagerMenuUI managerMenuUI){
        try{
            this.managerMenuUI=managerMenuUI;
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/StatsAboutFreelancerInOrganizationScene.fxml"));
            loader.setController(this);
            this.managerMenuUI.setBorderCenter((Node)loader.load());
            start();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Method that sets the current freelancer.
     * @param event Clicking a Freelancer in the list View
     */
    @FXML
    private void selectFreeAction(MouseEvent event) {
        int index = lvFrl.getSelectionModel().getSelectedIndex();
        if (index == 0) {
            showStatsAllFrl();
        }
        if (index > 0) {
            Freelancer frl = controller.getFreelancer(lvFrl.getSelectionModel().getSelectedItem());
            showStatsFrl(frl);
        }
    }

    /**
     * Method that sorts the list
     */
    @FXML
    private void sortList() {
        try{
            List<Freelancer> lista =controller.getListFreelancersByOrg();
            Collections.sort(lista,cmbSort.getSelectionModel().getSelectedItem());
            addItems(lista);
        }catch (NullPointerException e){
            AlertBox.createAlert(Alert.AlertType.ERROR,"Error","Select a sort method first !").show();
        }

    }
    /**
     * Method that changes the current order of the Sort
     * @param event Clicking Image of Sort
     */
    @FXML
    private void changeOrder(MouseEvent event){
        try{
            List<Freelancer> lista =controller.getListFreelancersByOrg();
            Collections.sort(lista,cmbSort.getSelectionModel().getSelectedItem().reversed());
            addItems(lista);
        }catch (NullPointerException e){
            AlertBox.createAlert(Alert.AlertType.ERROR,"Error","Select a sort method first !").show();
        }
    }
    /**
     * Method that changes the current order of the Sort
     * @param event Clicking Image of Sort
     */
    @FXML
    void changeOrdemNormal(MouseEvent event) {
        try {
            List<Freelancer> lista =controller.getListFreelancersByOrg();
            Collections.sort(lista,cmbSort.getSelectionModel().getSelectedItem());
            addItems(lista);
        }catch (ClassCastException e){
            AlertBox.createAlert(Alert.AlertType.ERROR,"Error","Select a sort method first !").show();
        }
    }

    /**
     * Method that add in the Scene the necessary Items
     * @param lista Necessary Items
     */
    public void addItems(List<Freelancer> lista){
        lvFrl.getItems().clear();
        lvFrl.getItems().add("All Freelancers");
        for(Freelancer f:lista){
        lvFrl.getItems().add(f.getId());
        }
    }

    /**
     * Method that shows the stats of All Freelancers.
     */
    private void showStatsAllFrl() {
         double meanDelays = controller.getMeanDelays();
        double deviationDelays = controller.getDeviationDelays();
        txtMeanDelays.setText(String.format("%.4f",meanDelays));
        txtDeviationDelays.setText(String.format("%.4f",deviationDelays));
        int[] histDataDelays = controller.getHistogramDataDelays();
        fillChart(histogramDelays, histDataDelays, meanDelays, deviationDelays);
        double meanPays = controller.getMeanPays();
        double deviationPays = controller.getDeviationPays();
        txtMeanPays.setText(String.format("%.4f",meanPays));
        txtDeviationPays.setText(String.format("%.4f",deviationPays));
        int[] histDataPays = controller.getHistogramDataPays();
        fillChart(histogramPays, histDataPays, meanPays, deviationPays);
    }

    /**
     * Method that shows the stats of a selected Freelancer
     * @param frl Selected Freelancer by User.
     */
    private void showStatsFrl(Freelancer frl) {
       double meanDelays = controller.getMeanDelaysOfFrl(frl);
        double deviationDelays = controller.getDeviationDelaysOfFrl(frl);
        txtMeanDelays.setText(String.format("%.4f",meanDelays));
        txtDeviationDelays.setText(String.format("%.4f",deviationDelays));
        int[] histDataDelays = controller.getHistogramDataDelaysOfFrl(frl);
        fillChart(histogramDelays, histDataDelays, meanDelays, deviationDelays);
        double meanPays = controller.getMeanPaysOfFrl(frl);
        double deviationPays = controller.getDeviationPaysOfFrl(frl);
        txtMeanPays.setText(String.format("%.4f",meanPays));
        txtDeviationPays.setText(String.format("%.4f",deviationPays));
        int[] histDataPays = controller.getHistogramDataPaysOfFrl(frl);
        fillChart(histogramPays, histDataPays, meanPays, deviationPays);
    }

    /**
     * Method that fill the data of the Chart
     * @param barChart Chart
     * @param histData Data for the Chart
     * @param mean Value of mean for the chart
     * @param deviation Deviation Value for the Chart
     */
    private void fillChart(BarChart barChart, int[] histData, double mean, double deviation) {
        XYChart.Series data = new XYChart.Series();
        data.setName("Number of tasks");
        double infLim = mean - deviation;
        double supLim = mean + deviation;
        data.getData().add(new XYChart.Data(String.format("]-∞;%.2f]",infLim),histData[0]));
        data.getData().add(new XYChart.Data(String.format("]%.2f;%.2f[",infLim,supLim),histData[1]));
        data.getData().add(new XYChart.Data(String.format("[%.2f;+∞[",supLim),histData[2]));
        barChart.getData().clear();
        barChart.getData().add(data);
    }

    /**
     * Method that clears the date from the Scene.
     */
    private void clear() {
        txtMeanDelays.setText("0.0");
        txtDeviationDelays.setText("0.0");
        txtMeanPays.setText("0.0");
        txtDeviationPays.setText("0.0");
        lvFrl.getItems().clear();
        histogramDelays.getData().clear();
        histogramPays.getData().clear();
    }
}
