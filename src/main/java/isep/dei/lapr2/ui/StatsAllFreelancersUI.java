package isep.dei.lapr2.ui;

import isep.dei.lapr2.controller.StatsAllFreelancersController;
import isep.dei.lapr2.model.Freelancer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * UI Class for the Stats of ALL Freelancers Scene.
 */
public class StatsAllFreelancersUI implements Initializable {

    private StatsAllFreelancersController controller;
    private AdminMenuUI adminMenuUI;

    @FXML
    private TextField txtMeanDelays;

    @FXML
    private TextField txtProbability;

    @FXML
    private TextField txtDeviationDelays;

    @FXML
    private TextField txtMeanPays;

    @FXML
    private BarChart histogramDelays;

    @FXML
    private BarChart histogramPays;

    @FXML
    private TextField txtDeviationPays;

    @FXML
    private ListView<String> lvFrl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    /**
     * Method that initializes the Scene
     */
    public void start() {
        clear();
        this.controller = new StatsAllFreelancersController();
        lvFrl.getItems().add("All freelancers");
        List<Freelancer> lstFrl = controller.getListFreelancers();
        for (Freelancer frl: lstFrl) {
            lvFrl.getItems().add(frl.getId());
        }
        txtProbability.setText(String.format("%.4f", controller.getProbability()));
        showStatsAllFrl();
    }
    /**
     * Method that set-up the current Scene in the parent Scene.
     * @param adminMenuUI UI class of the parent Scene
     */
    public void openWindow(AdminMenuUI adminMenuUI){
        try{
            this.adminMenuUI = adminMenuUI;
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/fxml/StatsAllFreelancersScene.fxml"));
            loader.setController(this);
            this.adminMenuUI.setBorderCenter((Node)loader.load());
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
    void selectFrlAction(MouseEvent event) {
        int index = lvFrl.getSelectionModel().getSelectedIndex();
        if (index == 0) {
            showStatsAllFrl();
        }
        if (index > 0) {
            Freelancer frl = controller.getFreelancer(index - 1);
            showStatsFrl(frl);
        }
    }

    /**
     * Method that shows the stats of All Freelancers.
     */
    private void showStatsAllFrl() {
        double meanDelays = controller.getMeanDelays();
        double deviationDelays = controller.getDeviationDelays();
        txtMeanDelays.setText(String.format("%.4f", meanDelays));
        txtDeviationDelays.setText(String.format("%.4f", deviationDelays));
        int[] histDataDelays = controller.getHistogramDataDelays();
        fillChart(histogramDelays, histDataDelays, meanDelays, deviationDelays);
        double meanPays = controller.getMeanPays();
        double deviationPays = controller.getDeviationPays();
        txtMeanPays.setText(String.format("%.4f", meanPays));
        txtDeviationPays.setText(String.format("%.4f", deviationPays));
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
        txtMeanDelays.setText(String.format("%.4f", meanDelays));
        txtDeviationDelays.setText(String.format("%.4f", deviationDelays));
        int[] histDataDelays = controller.getHistogramDataDelaysOfFrl(frl);
        fillChart(histogramDelays, histDataDelays, meanDelays, deviationDelays);
        double meanPays = controller.getMeanPaysOfFrl(frl);
        double deviationPays = controller.getDeviationPaysOfFrl(frl);
        txtMeanPays.setText(String.format("%.4f", meanPays));
        txtDeviationPays.setText(String.format("%.4f", deviationPays));
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
        txtProbability.setText("0.0");
        lvFrl.getItems().clear();
        histogramDelays.getData().clear();
        histogramPays.getData().clear();
    }

}
