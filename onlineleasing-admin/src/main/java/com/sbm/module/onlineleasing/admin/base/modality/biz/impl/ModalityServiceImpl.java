package com.sbm.module.onlineleasing.admin.base.modality.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.common.redis.constant.RedisConstant;
import com.sbm.module.onlineleasing.admin.base.modality.biz.IModalityService;
import com.sbm.module.onlineleasing.base.modality.biz.ITOLModalityService;
import com.sbm.module.onlineleasing.domain.base.modality.Modality;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModalityServiceImpl extends CommonServiceImpl implements IModalityService {

	@Autowired
	private IRedisService redisService;

	@Autowired
	private ITOLModalityService modalityService;


	@Override
	@Scheduled(cron = "${sync.cron.base.modality}")
	public void refresh() {
		Modality tmp = new Modality(StringUtils.EMPTY, StringUtils.EMPTY, "0", StringUtils.EMPTY);
		iterate(tmp);
		redisService.set2RedisTwoDays(RedisConstant.getKey(Modality.class, RedisConstant.LIST), JSON.toJSONString(tmp.getChildren()));
	}

	private List<Modality> findAllByLvAndCodeStartingWith(String lv, String code) {
		return map(modalityService.findAllByLvAndCodeStartingWith(lv, code), e -> new Modality(e.getCode(), e.getName(), e.getLv(), e.getRemark()));
	}

	private void iterate(Modality tmp) {
		List<Modality> list = findAllByLvAndCodeStartingWith(String.valueOf(Integer.valueOf(tmp.getLv()) + 1), tmp.getCode());
		if (null != list && !list.isEmpty()) {
			for (Modality vo : list) {
				iterate(vo);
			}
			tmp.setChildren(list);
		}
	}
}
