package visualize;

import model.Taxpayer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import persistence.Database;
import utils.ApplicationConstants;

import java.awt.*;
import java.text.DecimalFormat;

public class TaxpayerDataVisualizer {

    public void createTaxpayerReceiptsPieJFreeChart(int taxpayerIndex){
        DefaultPieDataset receiptPieChartDataset = new DefaultPieDataset();
        Taxpayer taxpayer = Database.getDatabaseInstance().getTaxpayerFromArrayList(taxpayerIndex);

        receiptPieChartDataset.setValue("Basic", taxpayer.getReceiptsTotalAmountByType(ApplicationConstants.BASIC_RECEIPT));
        receiptPieChartDataset.setValue("Entertainment", taxpayer.getReceiptsTotalAmountByType(ApplicationConstants.ENTERTAINMENT_RECEIPT));
        receiptPieChartDataset.setValue("Travel", taxpayer.getReceiptsTotalAmountByType(ApplicationConstants.TRAVEL_RECEIPT));
        receiptPieChartDataset.setValue("Health", taxpayer.getReceiptsTotalAmountByType(ApplicationConstants.HEALTH_RECEIPT));
        receiptPieChartDataset.setValue("Other", taxpayer.getReceiptsTotalAmountByType(ApplicationConstants.OTHER_RECEIPT));

        JFreeChart receiptPieJFreeChart = ChartFactory.createPieChart("Receipt Pie Chart", receiptPieChartDataset);
        PiePlot piePlot = (PiePlot) receiptPieJFreeChart.getPlot();
        PieSectionLabelGenerator generator = new StandardPieSectionLabelGenerator("{0}: {1}$ ({2})", new DecimalFormat("0.00"), new DecimalFormat("0.00%"));
        piePlot.setLabelGenerator(generator);

        ChartFrame receiptPieChartFrame = new ChartFrame(Database.getDatabaseInstance().getTaxpayerNameAfmValuesPairList(taxpayerIndex), receiptPieJFreeChart);
        receiptPieChartFrame.pack();
        receiptPieChartFrame.setResizable(false);
        receiptPieChartFrame.setLocationRelativeTo(null);
        receiptPieChartFrame.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        receiptPieChartFrame.setVisible(true);
    }

    public void createTaxpayerTaxAnalysisBarJFreeChart(int taxpayerIndex){
        DefaultCategoryDataset taxAnalysisBarChartDataset = new DefaultCategoryDataset();
        Taxpayer taxpayer = Database.getDatabaseInstance().getTaxpayerFromArrayList(taxpayerIndex);

        String taxVariationType = taxpayer.getTaxIncrease()!=0? "Tax Increase" : "Tax Decrease";
        double taxVariationAmount = taxpayer.getTaxIncrease()!=0? taxpayer.getTaxIncrease() : taxpayer.getTaxDecrease()*(-1);

        taxAnalysisBarChartDataset.setValue(taxpayer.getTax(), "Tax", "Basic Tax");
        taxAnalysisBarChartDataset.setValue(taxVariationAmount, "Tax", taxVariationType);
        taxAnalysisBarChartDataset.setValue(taxpayer.getFinalTax(), "Tax", "Total Tax");

        JFreeChart taxAnalysisJFreeChart = ChartFactory.createBarChart("Tax Analysis Bar Chart", "",  "Tax Analysis in $", taxAnalysisBarChartDataset, PlotOrientation.VERTICAL, true, true, false);

        ChartFrame receiptPieChartFrame = new ChartFrame(Database.getDatabaseInstance().getTaxpayerNameAfmValuesPairList(taxpayerIndex), taxAnalysisJFreeChart);
        receiptPieChartFrame.pack();
        receiptPieChartFrame.setResizable(false);
        receiptPieChartFrame.setLocationRelativeTo(null);
        receiptPieChartFrame.setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        receiptPieChartFrame.setVisible(true);
    }
}
