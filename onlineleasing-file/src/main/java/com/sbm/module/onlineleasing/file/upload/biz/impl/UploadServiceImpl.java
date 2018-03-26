package com.sbm.module.onlineleasing.file.upload.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.constant.CommonConstant;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.onlineleasing.base.fileuploaddetail.biz.ITOLFileUploadDetailService;
import com.sbm.module.onlineleasing.base.fileuploaddetail.domain.TOLFileUploadDetail;
import com.sbm.module.onlineleasing.file.exception.FileCode;
import com.sbm.module.onlineleasing.file.upload.biz.IUploadMethodService;
import com.sbm.module.onlineleasing.file.upload.biz.IUploadService;
import com.sbm.module.onlineleasing.file.upload.constant.UploadConstant;
import com.sbm.module.onlineleasing.file.upload.domain.Upload;
import com.sbm.module.onlineleasing.file.upload.domain.UploadDetail;
import com.sbm.module.onlineleasing.file.upload.domain.UploadResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadServiceImpl extends CommonServiceImpl implements IUploadService {

	public static final String DOT = ".";

	@Autowired
	private ITOLFileUploadDetailService fileUploadDetailService;
	@Autowired
	@Qualifier("hdUploadMethodServiceImpl")
	private IUploadMethodService uploadMethodService;

	private String getSuffix(String filename) {
		String suffix = filename.substring(filename.lastIndexOf(DOT));
		if (StringUtils.isNotBlank(suffix)) {
			suffix = suffix.substring(1, suffix.length());
		}
		return suffix;
	}

	/*******************************************************************************************************/

	@Transactional
	public List<UploadResult> upload(Upload vo) {
		List<UploadResult> results = new ArrayList<>();
		TOLFileUploadDetail detail;
		for (MultipartFile file : vo.getFiles()) {
			detail = new TOLFileUploadDetail(vo.getVo().getUserCode(), vo.getVo().getContainerName(), vo.getVo().getPrefix());
			// 转换
			convert2FileUploadDetail(detail, file);
			// 上传
			uploadMethodService.uploadMethod(detail, file);
			fileUploadDetailService.saveOrUpdateDetail(detail);
			results.add(new UploadResult(detail.getUserCode(), detail.getUri(), detail.getContainerName(), detail.getPrefix(), detail.getFileId(), detail.getSize(), detail.getOriginalFilename(), detail.getSuffix()));
		}
		return results;
	}

	/*******************************************************************************************************/

	/**
	 * 转换成FileUploadDetail
	 *
	 * @param detail
	 * @param file
	 */
	private void convert2FileUploadDetail(TOLFileUploadDetail detail, MultipartFile file) {
		// 设置文件大小
		detail.setSize(file.getSize());
		// 设置文件原始名称
		try {
			detail.setOriginalFilename(new String(file.getOriginalFilename().getBytes(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new BusinessException(FileCode.F1104, e, new Object[]{file.getOriginalFilename()});
		}
		// 设置文件后缀
		detail.setSuffix(getSuffix(file.getOriginalFilename()));
	}

	/*******************************************************************************************************/

	@Transactional
	public String saveUploadDetail(UploadDetail vo) {
		// 生成upload明细
		TOLFileUploadDetail detail = new TOLFileUploadDetail();
		detail.setUserCode(vo.getUserCode());
		detail.setFileId(vo.getFileId());
		detail.setContainerName(vo.getContainerName());
		detail.setPrefix(getPrefix(vo));
		saveUploadDetail(detail);
		return detail.getUri();
	}

	@Transactional
	public void saveUploadDetail(TOLFileUploadDetail detail) {
		uploadMethodService.setFileInfo(detail);
		// 没用用户默认为空
		if (StringUtils.isEmpty(detail.getUserCode())) {
			detail.setUserCode(CommonConstant.DEFAULT);
		}
		// 设置文件后缀
		detail.setSuffix(getSuffix(detail.getOriginalFilename()));
		fileUploadDetailService.saveOrUpdateDetail(detail);
	}

	/*******************************************************************************************************/

	private String getPrefix(UploadDetail vo) {
		return MessageFormat.format(UploadConstant.PREFIX_DEFAULT, vo.getCode(), vo.getType(), vo.getUse());
	}
}
