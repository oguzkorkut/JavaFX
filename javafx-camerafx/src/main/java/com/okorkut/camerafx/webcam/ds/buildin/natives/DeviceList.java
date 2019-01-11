package com.okorkut.camerafx.webcam.ds.buildin.natives;


import java.util.ArrayList;
import java.util.List;

import org.bridj.Pointer;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import org.bridj.cpp.CPPObject;


@Library("OpenIMAJGrabber")
@SuppressWarnings("all")
public class DeviceList extends CPPObject {

	public DeviceList() {
		super();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DeviceList(Pointer pointer) {
		super(pointer);
	}

	@Field(0)
	protected int nDevices() {
		return this.io.getIntField(this, 0);
	}

	@Field(0)
	protected DeviceList nDevices(int nDevices) {
		this.io.setIntField(this, 0, nDevices);
		return this;
	}

	// / C type : Device**
	@Field(1)
	protected Pointer<Pointer<Device>> devices() {
		return this.io.getPointerField(this, 1);
	}

	// / C type : Device**
	@Field(1)
	protected DeviceList devices(Pointer<Pointer<Device>> devices) {
		this.io.setPointerField(this, 1, devices);
		return this;
	}

	public native int getNumDevices();

	public native Pointer<Device> getDevice(int i);

	public List<Device> asArrayList() {
		List<Device> devices = new ArrayList<Device>();

		for (int i = 0; i < getNumDevices(); i++) {
			devices.add(getDevice(i).get());
		}

		return devices;
	}
}
