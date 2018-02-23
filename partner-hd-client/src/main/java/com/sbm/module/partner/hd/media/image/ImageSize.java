package com.sbm.module.partner.hd.media.image;

import java.io.Serializable;

public class ImageSize implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String SIZE_URL_SEPARATOR = ",";
	public static final String SIZE_FILENAME_SEPARATOR = "x";
	public static final ImageSize SIZE_DEFAULT = new ImageSize(0, 0);

	public static final ImageSize SIZE_8_8 = new ImageSize(8, 8);

	public static final ImageSize SIZE_16_16 = new ImageSize(16, 16);

	public static final ImageSize SIZE_32_32 = new ImageSize(32, 32);

	public static final ImageSize SIZE_48_48 = new ImageSize(48, 48);

	public static final ImageSize SIZE_64_64 = new ImageSize(64, 64);
	private int width;
	private int height;

	public ImageSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public ImageSize() {
		this(SIZE_DEFAULT);
	}

	public ImageSize(ImageSize imageSize) {
		this(imageSize.getWidth(), imageSize.getHeight());
	}

	public ImageSize(String paramString) {
		if ((paramString == null) || ("".equals(paramString.trim()))) {
			setSize(SIZE_DEFAULT);
			return;
		}

		String[] sl = paramString.split(",");
		if (sl.length != 2) {
			setSize(SIZE_DEFAULT);
			return;
		}
		try {
			this.width = Integer.valueOf(sl[0]).intValue();
			this.height = Integer.valueOf(sl[1]).intValue();
		} catch (Exception e) {
			setSize(SIZE_DEFAULT);
		}
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void setSize(ImageSize imageSize) {
		this.width = imageSize.getWidth();
		this.height = imageSize.getHeight();
	}

	public ImageSize getSize() {
		return new ImageSize(this.width, this.height);
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getParamString() {
		return this.width + "," + this.height;
	}

	public String getFileNameString() {
		return this.width + "x" + this.height;
	}

	public boolean equals(Object obj) {
		if ((obj instanceof ImageSize)) {
			ImageSize d = (ImageSize) obj;
			return (this.width == d.width) && (this.height == d.height);
		}
		return false;
	}

	public int hashCode() {
		int sum = this.width + this.height;
		return sum * (sum + 1) / 2 + this.width;
	}

	public String toString() {
		return getClass().getName() + "[width=" + this.width + ",height=" + this.height + "]";
	}

	public static void main(String[] arg) {
		System.out.println(SIZE_16_16.getParamString());
		System.out.println(SIZE_32_32.getParamString());
		System.out.println(SIZE_32_32.toString());
	}

}
