package com.sbm.module.sync.bi.api.bi.biz;

import com.sbm.module.sync.bi.api.bi.domain.Bi;

public interface IBiService {

	void findByBuildingCode(Bi vo);

	void refresh();

}
