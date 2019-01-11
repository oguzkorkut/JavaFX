package com.okorkut.camerafx.controller;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

public class CameraFXController implements Initializable {

//	@FXML
//	private JFXButton btnStartWebCam;
//
//	@FXML
//	private JFXButton btnTakePicture;
//
//	@FXML
//	private JFXButton btnCloseWebCam;

	@FXML
	private ImageView imgWebCamCapturedImage;

	// ------------------------------------------------------------------------------//
	private class WebCamInfo {

		private String webCamName;
		private int webCamIndex;

		public String getWebCamName() {
			return webCamName;
		}

		public void setWebCamName(String webCamName) {
			this.webCamName = webCamName;
		}

		public int getWebCamIndex() {
			return webCamIndex;
		}

		public void setWebCamIndex(int webCamIndex) {
			this.webCamIndex = webCamIndex;
		}

		@Override
		public String toString() {
			return webCamName;
		}
	}

	// ------------------------------------------------------------------------------//
	private String cameraListPromptText = "Kamera Seçiniz";// Choose Camera

	private Webcam webCam = null;
	private boolean stopCamera = false;
	private BufferedImage grabbedImage;
	private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();

	// ------------------------------------------------------------------------------//

//	@FXML
//	private void handleStartButtonClick(ActionEvent event) {
//		System.out.println("Button:" + event.getSource());
//		startWebCamCamera();
//	}

	@FXML
	private void handleTakePictureButtonClick(ActionEvent event) {
		System.out.println("Button:" + event.getSource());
		stopWebCamCamera();
	}

//	@FXML
//	private void handleCloseButtonClick(ActionEvent event) {
//		System.out.println("Button:" + event.getSource());
//		disposeWebCamCamera();
//	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		System.out.println("initialize WebCamController");

		int webCamCounter = 0;

		ObservableList<WebCamInfo> options = FXCollections.observableArrayList();

		for (Webcam webcam : Webcam.getWebcams()) {
			WebCamInfo webCamInfo = new WebCamInfo();
			webCamInfo.setWebCamIndex(webCamCounter);
			webCamInfo.setWebCamName(webcam.getName());
			options.add(webCamInfo);
			webCamCounter++;
		}

		initializeWebCam(0);

//		ComboBox<WebCamInfo> cameraOptions = new ComboBox<WebCamInfo>();
//		cameraOptions.setItems(options);
//		cameraOptions.setPromptText(cameraListPromptText);
	}

	protected void initializeWebCam(final int webCamIndex) {

		Task<Void> webCamTask = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				if (webCam != null) {
					disposeWebCamCamera();
				}

				webCam = Webcam.getWebcams().get(webCamIndex);

				webCam.setCustomViewSizes(new Dimension[] { WebcamResolution.FHDP.getSize() });
				webCam.setViewSize(WebcamResolution.FHDP.getSize());

				webCam.open();

				startWebCamStream();

				return null;
			}
		};

		Thread webCamThread = new Thread(webCamTask);
		webCamThread.setDaemon(true);
		webCamThread.start();

	}

	protected void startWebCamStream() {

		stopCamera = false;

		Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {

				final AtomicReference<WritableImage> ref = new AtomicReference<>();
				BufferedImage img = null;

				while (!stopCamera) {
					try {
						if ((img = webCam.getImage()) != null) {

							ref.set(SwingFXUtils.toFXImage(img, ref.get()));
							img.flush();

							Platform.runLater(new Runnable() {

								@Override
								public void run() {
									imageProperty.set(ref.get());
								}
							});
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();

				ImageIO.write(SwingFXUtils.fromFXImage(imageProperty.get(), null), "png", byteOutput);

				com.itextpdf.text.Image graph;
				graph = com.itextpdf.text.Image.getInstance(byteOutput.toByteArray());

				try (OutputStream outputStream = new FileOutputStream("C:\\Java\\FX\\screen.png")) {
					byteOutput.writeTo(outputStream);
				}

				System.out.println("save");
				return null;
			}
		};

		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
		imgWebCamCapturedImage.imageProperty().bind(imageProperty);

	}

	protected void disposeWebCamCamera() {
		stopCamera = true;
		webCam.close();
//		btnCamreaStart.setDisable(true);
//		btnCamreaStop.setDisable(true);
//		primaryStage.close();
	}

	protected void startWebCamCamera() {
		stopCamera = false;
		startWebCamStream();
//		btnCamreaStop.setDisable(false);
//		btnCamreaStart.setDisable(true);
	}

	protected void stopWebCamCamera() {
		stopCamera = true;

//		btnCamreaStart.setDisable(false);
//		btnCamreaStop.setDisable(true);
	}

}
