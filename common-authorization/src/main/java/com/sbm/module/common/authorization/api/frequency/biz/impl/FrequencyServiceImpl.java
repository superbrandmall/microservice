package com.sbm.module.common.authorization.api.frequency.biz.impl;

import com.sbm.module.common.authorization.api.frequency.biz.IFrequencyService;
import com.sbm.module.common.authorization.api.frequency.domain.Frequency;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.redis.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FrequencyServiceImpl extends CommonServiceImpl implements IFrequencyService {

	@Override
	public void checkFrequency(Frequency vo) {
		log.info(getKey(vo));
	}

	private String getKey(Frequency vo) {
		return RedisConstant.getKey(Frequency.class, vo.getIp(), vo.getLogin() == null ? StringUtils.EMPTY : vo.getLogin(), vo.getPath());
	}


}
