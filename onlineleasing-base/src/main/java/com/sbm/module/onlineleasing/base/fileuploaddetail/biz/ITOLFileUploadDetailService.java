package com.sbm.module.onlineleasing.base.fileuploaddetail.biz;

import com.sbm.module.onlineleasing.base.fileuploaddetail.domain.TOLFileUploadDetail;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;

public interface ITOLFileUploadDetailService extends IOLDataService<TOLFileUploadDetail, Integer> {

	TOLFileUploadDetail findOneByUri(String uri);

	void saveOrUpdateDetail(TOLFileUploadDetail obj);
}
