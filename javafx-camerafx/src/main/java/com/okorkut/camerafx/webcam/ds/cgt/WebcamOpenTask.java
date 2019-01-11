package com.okorkut.camerafx.webcam.ds.cgt;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.okorkut.camerafx.webcam.WebcamDevice;
import com.okorkut.camerafx.webcam.WebcamDriver;
import com.okorkut.camerafx.webcam.WebcamTask;

public class WebcamOpenTask extends WebcamTask {

	private static final Logger LOG = LoggerFactory.getLogger(WebcamOpenTask.class);

	public WebcamOpenTask(WebcamDriver driver, WebcamDevice device) {
		super(driver, device);
	}

	public void open() throws InterruptedException {
		process();
	}

	@Override
	protected void handle() {

		WebcamDevice device = getDevice();

		if (device.isOpen()) {
			return;
		}

		if (device.getResolution() == null) {
			device.setResolution(device.getResolutions()[0]);
		}

		LOG.info("Opening webcam {}", device.getName());

		device.open();
	}
}
