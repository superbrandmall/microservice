package com.sbm.module.onlineleasing.customer.base.modality.biz;

import com.sbm.module.onlineleasing.domain.base.modality.Modality;
import com.sbm.module.onlineleasing.domain.base.modality.ModalityMaxInfo;

import java.util.List;

public interface IModalityService {

	List<Modality> findAll();

	ModalityMaxInfo findOneByCode(String code);
}
