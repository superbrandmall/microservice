package com.sbm.module.sync.bi.api.bi.biz;

import com.sbm.module.sync.bi.api.bi.domain.BiDetail;

import java.util.List;

public interface IBiService {

	List<BiDetail> findByMallCode(String mallCode);

	void refresh();

}
