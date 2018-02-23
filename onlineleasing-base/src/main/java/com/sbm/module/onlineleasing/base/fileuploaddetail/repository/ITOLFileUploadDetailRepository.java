package com.sbm.module.onlineleasing.base.fileuploaddetail.repository;

import com.sbm.module.onlineleasing.base.fileuploaddetail.domain.TOLFileUploadDetail;
import com.sbm.module.onlineleasing.data.repository.IOLDataRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "fileuploaddetail")
public interface ITOLFileUploadDetailRepository extends IOLDataRepository<TOLFileUploadDetail, Integer> {

	TOLFileUploadDetail findOneByUri(String uri);

}
