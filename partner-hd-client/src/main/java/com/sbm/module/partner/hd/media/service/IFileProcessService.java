package com.sbm.module.partner.hd.media.service;

import com.sbm.module.partner.hd.media.image.ImageSize;

import javax.servlet.ServletException;
import java.util.List;

public interface IFileProcessService {

	MediaSFileInfo uploadFile(byte[] paramArrayOfByte, String paramString) throws ServletException;

	MediaSFileInfo uploadImage(byte[] paramArrayOfByte, String paramString, List<ImageSize> paramList)
			throws ServletException;

	MediaSFileInfo getMediaSFileInfo(String paramString) throws ServletException;

	void remove(String paramString) throws ServletException;

	byte[] getFile(String paramString) throws ServletException;

	byte[] getImage(String paramString, ImageSize paramImageSize) throws ServletException;

}
