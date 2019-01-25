package library.assistant.util;

import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AlertMaker {

	public static void showSimpleAlert(String title, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		styleAlert(alert);
		alert.showAndWait();
	}

	public static void showErrorMessage(String title, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(title);
		alert.setContentText(content);
		styleAlert(alert);
		alert.showAndWait();
	}

	public static void showErrorMessage(Exception ex) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error occured");
		alert.setHeaderText("Error Occured");
		alert.setContentText(ex.getLocalizedMessage());

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("The exception stacktrace was:");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		alert.getDialogPane().setExpandableContent(expContent);

		styleAlert(alert);
		alert.showAndWait();
	}

	public static void showErrorMessage(Exception ex, String title, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error occured");
		alert.setHeaderText(title);
		alert.setContentText(content);

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("The exception stacktrace was:");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		alert.getDialogPane().setExpandableContent(expContent);
		alert.showAndWait();
	}

//	public static void showMaterialDialog(StackPane root, Node nodeToBeBlurred, List<JFXButton> controls, String header, String body) {
//		BoxBlur blur = new BoxBlur(3, 3, 3);
//		if (controls.isEmpty()) {
//			controls.add(new JFXButton("Kapat"));
//		}
//		JFXDialogLayout dialogLayout = new JFXDialogLayout();
//		JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.TOP);
//
//		controls.forEach(controlButton -> {
//			controlButton.getStyleClass().add("dialog-button");
//			controlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
//				System.err.println(mouseEvent.getButton());
//				dialog.close();
//			});
//		});
//
//		dialogLayout.setHeading(new Label(header));
//		dialogLayout.setBody(new Label(body));
//		dialogLayout.setActions(controls);
//		dialog.show();
//		dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
//			nodeToBeBlurred.setEffect(null);
//		});
//		nodeToBeBlurred.setEffect(blur);
//	}
	
	public static JFXDialog showMaterialDialog(StackPane root, Node nodeToBeBlurred, List<JFXButton> controls, String header, String body) {
		BoxBlur blur = new BoxBlur(3, 3, 3);
		
		if (!controls.isEmpty()) {
			controls.forEach(controlButton -> {
				controlButton.getStyleClass().add("dialog-button");
			});
		}
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.TOP);
		
		JFXButton close = new JFXButton("Kapat");
		controls.add(close);

		close.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
			dialog.close();
		});
		
		dialogLayout.setHeading(new Label(header));
		dialogLayout.setBody(new Label(body));
		dialogLayout.setActions(controls);
		dialog.show();
		dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
			nodeToBeBlurred.setEffect(null);
		});
		nodeToBeBlurred.setEffect(blur);
		
		return dialog;
	}
	
	public static JFXDialog showImage(StackPane root, Node nodeToBeBlurred, ImageView imageView) {
		BoxBlur blur = new BoxBlur(3, 3, 3);
		
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.TOP);
		
//		dialogLayout.setHeading(new Label(header));
		dialogLayout.setBody(imageView);
//		dialogLayout.setActions(controls);
		dialog.show();
		dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
			nodeToBeBlurred.setEffect(null);
		});
		nodeToBeBlurred.setEffect(blur);
		
		return dialog;
	}

	public static void showTrayMessage(String title, String message) {
		try {
			SystemTray tray = SystemTray.getSystemTray();
			BufferedImage image = ImageIO.read(AlertMaker.class.getResource("images/close.png"));
			TrayIcon trayIcon = new TrayIcon(image, "Assistant");
			trayIcon.setImageAutoSize(true);
			trayIcon.setToolTip("Assistant");
			tray.add(trayIcon);
			trayIcon.displayMessage(title, message, MessageType.INFO);
			tray.remove(trayIcon);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	private static void styleAlert(Alert alert) {
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image("images/close.png"));
//		FXUtils.setStageIcon(stage);

		DialogPane dialogPane = alert.getDialogPane();
		
		System.err.println("dasd" + AlertMaker.class.getResource("/css/dark-theme.css"));
		
		String path = AlertMaker.class.getResource("/css/dark-theme.css").toExternalForm();
//		File file = new File(path);
//		Image image = new Image(file.toURI().toString());
		
		
		
		dialogPane.getStylesheets().add(path);
		dialogPane.getStyleClass().add("custom-alert");
	}
}
