import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

public class LineChart extends JFrame {

	private static final long serialVersionUID = 1L;

	public LineChart() {

        initUI();
    }

    private void initUI() {

        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        
        add(chartPanel);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDataset() {

        var series1 = new XYSeries("Iterative");
        for(int i = 1; i < 25; i++) {
        	long startTime = System.nanoTime();
            fibonacciIter(i);
            long stopTime = System.nanoTime();
            series1.add(i, stopTime - startTime);
        }
        
        var series2 = new XYSeries("Recursive");
        for(int i = 1; i < 25; i++) {
        	long startTime = System.nanoTime();
            fibonacciRecur(i);
            long stopTime = System.nanoTime();
            series2.add(i, stopTime - startTime);
        }

        var dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }
  //Fibonacci Iterative
	
  	public static long fibonacciIter(long n){
  		   long v1=0,v2=1,v3=0;
  		       for(long i=2 ;i <= n;i++){
  		           v3 = v1 + v2;
  		           v1 = v2;
  		           v2 = v3;
  		       }
  		       return v3;
  		   }
  	//Recursive
  	public static long fibonacciRecur(long n){
  	       if(n == 0) return 0;
  	       if(n == 1) return 1;
  	       return fibonacciRecur(n-1) + fibonacciRecur(n-2);
  	   }
    private JFreeChart createChart(final XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Fibonacci Time",
                "Input",
                "Time",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        var renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Fibonacci Time",
                        new Font("Serif", Font.BOLD, 18)
                )
        );

        return chart;
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new LineChart();
            ex.setVisible(true);
        });
    }
}