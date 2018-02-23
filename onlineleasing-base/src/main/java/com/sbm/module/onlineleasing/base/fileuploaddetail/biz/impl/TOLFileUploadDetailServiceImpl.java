package com.sbm.module.onlineleasing.base.fileuploaddetail.biz.impl;

import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.onlineleasing.base.fileuploaddetail.biz.ITOLFileUploadDetailService;
import com.sbm.module.onlineleasing.base.fileuploaddetail.domain.TOLFileUploadDetail;
import com.sbm.module.onlineleasing.base.fileuploaddetail.repository.ITOLFileUploadDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TOLFileUploadDetailServiceImpl extends DataServiceImpl<TOLFileUploadDetail, Integer> implements ITOLFileUploadDetailService {

	@Autowired
	private ITOLFileUploadDetailRepository repository;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public TOLFileUploadDetail findOneByUri(String uri) {
		return repository.findOneByUri(uri);
	}

	@Override
	public void saveOrUpdateDetail(TOLFileUploadDetail obj) {
		TOLFileUploadDetail po = findOneByUri(obj.getUri());
		if (null == po) {
			po = new TOLFileUploadDetail();
		}
		convert(po, obj);
		save(po);
	}

	/**
	 * convert:转换
	 *
	 * @param po
	 * @param obj
	 * @author junkai.zhang
	 */
	private void convert(TOLFileUploadDetail po, TOLFileUploadDetail obj) {
		po.setUserCode(obj.getUserCode());
		po.setUri(obj.getUri());
		po.setContainerName(obj.getContainerName());
		po.setPrefix(obj.getPrefix());
		po.setFileId(obj.getFileId());
		po.setSize(obj.getSize());
		po.setOriginalFilename(obj.getOriginalFilename());
		po.setSuffix(obj.getSuffix());
	}
}
