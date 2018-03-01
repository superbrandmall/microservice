package com.sbm.module.onlineleasing.file.media.biz.impl;


import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.onlineleasing.base.fileuploaddetail.domain.TOLFileUploadDetail;
import com.sbm.module.onlineleasing.file.exception.FileCode;
import com.sbm.module.onlineleasing.file.media.caller.BurlapServiceCaller;
import com.sbm.module.onlineleasing.file.media.image.ImageSize;
import com.sbm.module.onlineleasing.file.media.service.IFileProcessService;
import com.sbm.module.onlineleasing.file.media.service.MediaSFileInfo;
import com.sbm.module.onlineleasing.file.media.util.MediaSUtil;
import com.sbm.module.onlineleasing.file.upload.biz.IUploadMethodService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("hdUploadMethodServiceImpl")
public class HdUploadMethodServiceImpl implements IUploadMethodService {

	public void uploadMethod(TOLFileUploadDetail detail, MultipartFile file) {
		try {
			IFileProcessService service = BurlapServiceCaller.getFileProcessService();
			MediaSFileInfo info = service.uploadFile(file.getBytes(), file.getOriginalFilename());
			// 设置文件id
			detail.setFileId(info.getFileID());
			// 设置uri
			detail.setUri(MediaSUtil.getFileGetUrl(info.getFileID(), file.getOriginalFilename(), ImageSize.SIZE_DEFAULT));
		} catch (Exception e) {
			throw new BusinessException(FileCode.F2100, e);
		}
	}

	public void setFileInfo(TOLFileUploadDetail detail) {
		try {
			IFileProcessService service = BurlapServiceCaller.getFileProcessService();
			MediaSFileInfo info = service.getMediaSFileInfo(detail.getFileId());
			detail.setFileId(info.getFileID());
			detail.setOriginalFilename(info.getFileName());
			detail.setSize(info.getFileSize());
			detail.setUri(MediaSUtil.getFileGetUrl(info.getFileID(), detail.getOriginalFilename(), ImageSize.SIZE_DEFAULT));
		} catch (Exception e) {
			throw new BusinessException(FileCode.F2100, e);
		}
	}

}
