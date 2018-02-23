package com.sbm.module.partner.hd.media.service;

import java.io.Serializable;

public class MediaSFileInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String fileID;
	private Long fileSize;
	private String fileName;

	public String getFileID() {
		return this.fileID;
	}

	public void setFileID(String fileID) {
		this.fileID = fileID;
	}

	public Long getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("fileID: " + this.fileID + ", ");
		sb.append("fileName: " + this.fileName + ", ");
		sb.append("fileSize: " + this.fileSize);
		return sb.toString();
	}

}
