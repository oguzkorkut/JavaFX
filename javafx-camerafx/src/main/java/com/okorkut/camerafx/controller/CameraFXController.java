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

import com.okorkut.camerafx.util.CameraFXConstants;
import com.okorkut.camerafx.webcam.Webcam;
import com.okorkut.camerafx.webcam.WebcamResolution;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
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
	private Webcam webCam = null;
	private boolean stopCamera = false;
	private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();

	// ------------------------------------------------------------------------------//

	@FXML
	private void handleTakePictureButtonClick(ActionEvent event) {
		System.out.println("Button:" + event.getSource());
		takePicture();
	}
	
	@FXML
	private void handleCloseButtonClick(ActionEvent event) {
		System.out.println("Button:" + event.getSource());
		disposeWebCamCamera();
	}
	
	@FXML
	private void handleRestartButtonClick(ActionEvent event) {
		System.out.println("Button:" + event.getSource());
		
		startWebCamStream();
//		startWebCamStream();
//		btnCamreaStop.setDisable(false);
//		btnCamreaStart.setDisable(true);
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		System.out.println("initialize WebCamController");

		if (Webcam.getWebcams().size() != 0) {
			initializeWebCam(0);
		}
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
		
//		Webcam.shutdown();
//		
//		int webListenerCount = webCam.getWebcamListenersCount();
//		
//		for (int i = 0; i < webListenerCount; i++) {
//			webCam.removeWebcamListener(webCam.getWebcamListeners()[i]);
//		}
//		
		CameraFXConstants.PRIMARY_STAGE.close();
	}

	private void takePicture() {
		stopCamera = true;
		
		try {
			ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();

			ImageIO.write(SwingFXUtils.fromFXImage(imageProperty.get(), null), "png", byteOutput);

			com.itextpdf.text.Image graph;
			graph = com.itextpdf.text.Image.getInstance(byteOutput.toByteArray());

			try (OutputStream outputStream = new FileOutputStream("C:\\Java\\JavaFX\\screen.png")) {
				byteOutput.writeTo(outputStream);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		System.out.println("save");
	}

}
