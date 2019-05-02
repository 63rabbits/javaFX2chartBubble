package javaFX2chartBubble;

import java.net.URL;
import java.util.Iterator;
import java.util.TreeMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ChartBubbleController {

	@FXML
	private VBox vbx;
	@FXML
	private Button btn;

	enum BubbleStyle {
		TWO_DATA, THREE_DATA;
	}

	private BubbleStyle style = BubbleStyle.TWO_DATA;

	private XYChart.Series<Number, Number> series21 = new XYChart.Series<>();
	private XYChart.Series<Number, Number> series22 = new XYChart.Series<>();
	// NOTE : Set NumberAxis parameter to use setLowerBound etc. .
	private NumberAxis xAxis2 = new NumberAxis(0, 100, 1);
	private NumberAxis yAxis2 = new NumberAxis();
	private BubbleChart<Number, Number> bubble2c = new BubbleChart<>(xAxis2, yAxis2);

	private XYChart.Series<Number, Number> series31 = new XYChart.Series<>();
	private XYChart.Series<Number, Number> series32 = new XYChart.Series<>();
	// NOTE : Set NumberAxis parameter to use setLowerBound etc. .
	private NumberAxis xAxis3 = new NumberAxis(0, 100, 1);
	private NumberAxis yAxis3 = new NumberAxis();
	private BubbleChart<Number, Number> bubble3c = new BubbleChart<>(xAxis3, yAxis3);

	private boolean flag = true;
	private double xAxisMin = 0.0d;
	private double xAxisMax = 0.0d;

	@FXML
	void initialize() {
		assert vbx != null : "fx:id=\"vbx\" was not injected: check your FXML file 'ChartBubble.fxml'.";
		assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'ChartBubble.fxml'.";

		//////////////////////////////
		// Bubble Chart (1)
		bubble2c.prefWidthProperty().bind(vbx.widthProperty());
		bubble2c.prefHeightProperty().bind(vbx.heightProperty());
		vbx.getChildren().add(bubble2c);

		bubble2c.setTitle("Bubble Chart Sample (1)");
		xAxis2.setLabel("X-Axis label");
		yAxis2.setLabel("Y-Axis label");

		//////////////////////////////
		// Bubble Chart (2)
		bubble3c.prefWidthProperty().bind(vbx.widthProperty());
		bubble3c.prefHeightProperty().bind(vbx.heightProperty());
		vbx.getChildren().add(bubble3c);

		bubble3c.setTitle("Bubble Chart Sample (2)");
		xAxis3.setLabel("X-Axis label");
		yAxis3.setLabel("Y-Axis label");

		//////////////////////////////
		// set Data
		series21.setName("data01");
		series31.setName("data01");
		{
			URL url = this.getClass().getResource("res/data01forBubble.csv");
			OpCsv csv = new OpCsv(url);

			TreeMap<Integer, String[]> map = csv.getNumberSortedCsv(0);
			Iterator<Integer> it = map.keySet().iterator();
			while (it.hasNext()) {
				int no = it.next();
				String[] words = map.get(no);
				Double d01 = Double.parseDouble(words[0]);
				Double d02 = Double.parseDouble(words[1]);
				Double d03 = Double.parseDouble(words[2]);

				series21.getData().add(new XYChart.Data<Number, Number>(d01, d02));
				series31.getData().add(new XYChart.Data<Number, Number>(d01, d02, d03));

				if (flag) {
					flag = false;
					xAxisMin = d01;
					xAxisMax = d01;
				}
				else {
					if (d01 < xAxisMin) {
						xAxisMin = d01;
					}
					if (d01 > xAxisMax) {
						xAxisMax = d01;
					}
				}
			}
		}
		bubble2c.getData().add(series21);
		bubble3c.getData().add(series31);

		series22.setName("data02");
		series32.setName("data02");
		{
			URL url = this.getClass().getResource("res/data02forBubble.csv");
			OpCsv csv = new OpCsv(url);

			TreeMap<Integer, String[]> map = csv.getNumberSortedCsv(0);
			Iterator<Integer> it = map.keySet().iterator();
			while (it.hasNext()) {
				int no = it.next();
				String[] words = map.get(no);
				Double d01 = Double.parseDouble(words[0]);
				Double d02 = Double.parseDouble(words[1]);
				Double d03 = Double.parseDouble(words[2]);

				series22.getData().add(new XYChart.Data<Number, Number>(d01, d02));
				series32.getData().add(new XYChart.Data<Number, Number>(d01, d02, d03));

				if (flag) {
					flag = false;
					xAxisMin = d01;
					xAxisMax = d01;
				}
				else {
					if (d01 < xAxisMin) {
						xAxisMin = d01;
					}
					if (d01 > xAxisMax) {
						xAxisMax = d01;
					}
				}
			}
		}
		bubble2c.getData().add(series22);
		bubble3c.getData().add(series32);

		xAxis2.setLowerBound(xAxisMin);
		xAxis2.setUpperBound(xAxisMax);
		//		xAxis2.setTickUnit(1);
		//		xAxis2.setMinorTickCount(2);

		xAxis3.setLowerBound(xAxisMin);
		xAxis3.setUpperBound(xAxisMax);
		//		xAxis3.setTickUnit(1);
		//		xAxis3.setMinorTickCount(2);

		setStyleBubble2();
	}

	@FXML
	void btnOnAction(ActionEvent event) {
		switch (style) {
		case TWO_DATA:
			setStyleBubble3();
			break;
		case THREE_DATA:
			setStyleBubble2();
			break;
		default:
			break;
		}
	}

	private void setStyleBubble2() {
		style = BubbleStyle.TWO_DATA;
		vbx.getChildren().clear();
		vbx.getChildren().add(bubble2c);
	}

	private void setStyleBubble3() {
		style = BubbleStyle.THREE_DATA;
		vbx.getChildren().clear();
		vbx.getChildren().add(bubble3c);
	}
}
