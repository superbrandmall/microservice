package com.sbm.module.onlineleasing.base.fileuploaddetail.biz;

import com.sbm.module.common.data.biz.IDataService;
import com.sbm.module.onlineleasing.base.fileuploaddetail.domain.TOLFileUploadDetail;

public interface ITOLFileUploadDetailService extends IDataService<TOLFileUploadDetail, Integer> {

	TOLFileUploadDetail findOneByUri(String uri);

	void saveOrUpdateDetail(TOLFileUploadDetail obj);
}
