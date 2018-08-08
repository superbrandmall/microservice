package com.sbm.module.onlineleasing.interactive.website.modality.biz;

import com.sbm.module.onlineleasing.interactive.website.modality.domain.Modality;
import com.sbm.module.onlineleasing.interactive.website.modality.domain.ModalityQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IModalityService {

	Page<Modality> findAll(ModalityQuery query, Pageable pageable);

}
