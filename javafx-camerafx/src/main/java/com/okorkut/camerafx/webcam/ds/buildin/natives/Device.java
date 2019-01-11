package com.okorkut.camerafx.webcam.ds.buildin.natives;


import org.bridj.Pointer;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import org.bridj.ann.Runtime;
import org.bridj.cpp.CPPObject;
import org.bridj.cpp.CPPRuntime;


@Library("OpenIMAJGrabber")
@Runtime(CPPRuntime.class)
@SuppressWarnings("all")
public final class Device extends CPPObject {

	public Device() {
		super();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Device(Pointer pointer) {
		super(pointer);
	}

	// / C type : const char*
	@Field(0)
	protected Pointer<Byte> name() {
		return this.io.getPointerField(this, 0);
	}

	// / C type : const char*
	@Field(0)
	protected Device name(Pointer<Byte> name) {
		this.io.setPointerField(this, 0, name);
		return this;
	}

	// / C type : const char*
	@Field(1)
	protected Pointer<Byte> identifier() {
		return this.io.getPointerField(this, 1);
	}

	// / C type : const char*
	@Field(1)
	protected Device identifier(Pointer<Byte> identifier) {
		this.io.setPointerField(this, 1, identifier);
		return this;
	}

	protected native Pointer<Byte> getName();

	public String getNameStr() {
		return getName().getCString();
	}

	protected native Pointer<Byte> getIdentifier();

	public String getIdentifierStr() {
		return getIdentifier().getCString();
	}

	/*
	 * (non-Javadoc)
	 * @see org.bridj.NativeObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		return o.toString().equals(this.toString());
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	@Override
	public String toString() {
		return String.format("Device[%s]=\"%s\"", getIdentifierStr(), getNameStr());
	}
}

