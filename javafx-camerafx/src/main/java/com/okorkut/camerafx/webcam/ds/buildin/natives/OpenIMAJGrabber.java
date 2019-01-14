package com.okorkut.camerafx.webcam.ds.buildin.natives;


import org.bridj.BridJ;
import org.bridj.Platform;
import org.bridj.Pointer;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.CPPObject;
import org.bridj.cpp.CPPRuntime;

@Library("OpenIMAJGrabber")
@Runtime(CPPRuntime.class)
@SuppressWarnings("all")
public class OpenIMAJGrabber extends CPPObject {

	static {
//		Platform.addEmbeddedLibraryResourceRoot("com/github/sarxos/webcam/ds/buildin/lib/");
		Platform.addEmbeddedLibraryResourceRoot("buildin/lib/");
		BridJ.register();
	}

	public OpenIMAJGrabber() {
		super();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public OpenIMAJGrabber(Pointer pointer) {
		super(pointer);
	}

	public native Pointer<DeviceList> getVideoDevices();

	public native Pointer<Byte> getImage();

	public native int nextFrame();

	public native void setTimeout(int timeout);

	public native boolean startSession(int width, int height, double reqFPS);

	public native boolean startSession(int width, int height, double reqFPS, Pointer<Device> devptr);

	public native void stopSession();

	public native int getWidth();

	public native int getHeight();

	// / C type : void*
	@Field(0)
	protected Pointer<?> data() {
		return this.io.getPointerField(this, 0);
	}

	// / C type : void*
	@Field(0)
	protected OpenIMAJGrabber data(Pointer<?> data) {
		this.io.setPointerField(this, 0, data);
		return this;
	}
}
