package com.sbm.module.sync.hd.api.modality.biz.impl;

import com.sbm.module.common.biz.impl.BusinessServiceImpl;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.base.modality.biz.ITOLModalityService;
import com.sbm.module.onlineleasing.base.modality.domain.TOLModality;
import com.sbm.module.partner.hd.api.biztype.client.IHdBiztypeClient;
import com.sbm.module.partner.hd.api.biztype.domain.HdBiztype;
import com.sbm.module.sync.hd.api.modality.biz.IModalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModalityServiceImpl extends BusinessServiceImpl<TOLModality, HdBiztype> implements IModalityService {

	@Autowired
	private IHdBiztypeClient hdBiztypeClient;
	@Autowired
	private ITOLModalityService modalityService;

	@Override
	@Scheduled(cron = "0 0 0 * * ?")
	public void refresh() {
		JsonContainer<List<HdBiztype>> result = hdBiztypeClient.findAllVo();
		List<TOLModality> pos = findAll(result.getData());
		modalityService.save(pos);
	}

	@Override
	public TOLModality newInstance(HdBiztype e) {
		TOLModality po = modalityService.findOneByHdUuid(e.getHdUuid());
		if (null == po) {
			po = new TOLModality();
		}
		po.setName(e.getName());
		po.setCode(e.getCode());
		po.setLv(getLv(e.getCode()));
		po.setHdUuid(e.getHdUuid());
		po.setHdLevelid(e.getLevelid());
		po.setRemark(e.getRemark());
		return po;
	}

	private String getLv(String code) {
		String lv;
		switch (code.length()) {
			case 2:
				lv = "1";
				break;
			case 4:
				lv = "2";
				break;
			case 6:
				lv = "3";
				break;
			case 8:
				lv = "4";
				break;
			default:
				lv = "";
				break;
		}
		return lv;
	}
}
