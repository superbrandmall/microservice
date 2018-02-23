package com.sbm.module.onlineleasing.base.fileuploaddetail.repository;

import com.sbm.module.common.data.repository.IDataRepository;
import com.sbm.module.onlineleasing.base.fileuploaddetail.domain.TOLFileUploadDetail;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "fileuploaddetail")
public interface ITOLFileUploadDetailRepository extends IDataRepository<TOLFileUploadDetail, Integer> {

	TOLFileUploadDetail findOneByUri(String uri);

}
