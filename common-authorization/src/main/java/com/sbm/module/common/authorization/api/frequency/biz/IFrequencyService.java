package com.sbm.module.common.authorization.api.frequency.biz;

import com.sbm.module.common.authorization.api.frequency.domain.Frequency;

public interface IFrequencyService {

	/**
	 * 检查频率
	 *
	 * @param vo
	 */
	void checkFrequency(Frequency vo);

}
