package com.okorkut.camerafx.webcam.ds.cgt;

import com.okorkut.camerafx.webcam.WebcamDevice;
import com.okorkut.camerafx.webcam.WebcamDriver;
import com.okorkut.camerafx.webcam.WebcamTask;

/**
 * Dispose webcam device.
 * 
 * @author Bartosz Firyn (sarxos)
 */
public class WebcamDisposeTask extends WebcamTask {

	public WebcamDisposeTask(WebcamDriver driver, WebcamDevice device) {
		super(driver, device);
	}

	public void dispose() throws InterruptedException {
		process();
	}

	@Override
	protected void handle() {
		getDevice().dispose();
	}
}
