package com.okorkut.camerafx.webcam.ds.cgt;


import java.nio.ByteBuffer;

import com.okorkut.camerafx.webcam.WebcamDevice;
import com.okorkut.camerafx.webcam.WebcamDevice.BufferAccess;
import com.okorkut.camerafx.webcam.WebcamDriver;
import com.okorkut.camerafx.webcam.WebcamTask;


public class WebcamReadBufferTask extends WebcamTask {

	private volatile ByteBuffer target = null;

	public WebcamReadBufferTask(WebcamDriver driver, WebcamDevice device, ByteBuffer target) {
		super(driver, device);
		this.target = target;
	}

	public ByteBuffer readBuffer() {
		try {
			process();
		} catch (InterruptedException e) {
			return null;
		}
		return target;
	}

	@Override
	protected void handle() {

		WebcamDevice device = getDevice();
		if (!device.isOpen()) {
			return;
		}

		if (!(device instanceof BufferAccess)) {
			return;
		}

		((BufferAccess) device).getImageBytes(target);
	}
}
