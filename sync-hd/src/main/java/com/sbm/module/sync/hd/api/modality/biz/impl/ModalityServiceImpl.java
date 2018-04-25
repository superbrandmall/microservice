package com.sbm.module.sync.hd.api.modality.biz.impl;

import com.sbm.module.common.biz.impl.SyncServiceImpl;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.domain.SyncResult;
import com.sbm.module.onlineleasing.base.modality.biz.ITOLModalityService;
import com.sbm.module.onlineleasing.base.modality.constant.ModalityConstant;
import com.sbm.module.onlineleasing.base.modality.domain.TOLModality;
import com.sbm.module.partner.hd.api.biztype.client.IHdBiztypeClient;
import com.sbm.module.partner.hd.api.biztype.domain.HdBiztype;
import com.sbm.module.sync.hd.api.modality.biz.IModalityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ModalityServiceImpl extends SyncServiceImpl<TOLModality, HdBiztype, Object> implements IModalityService {

	@Autowired
	private IHdBiztypeClient hdBiztypeClient;
	@Autowired
	private ITOLModalityService modalityService;

	private static final String ERROR_MESSAGE = "业态同步异常";

	@Override
	@Scheduled(cron = "${sync.cron.modality}")
	public void refresh() {
		try {
			execute(null, e -> newInstance(e));
		} catch (Exception ex) {
			log.error(ERROR_MESSAGE, ex);
		}
	}

	public TOLModality newInstance(HdBiztype e) {
		TOLModality po = modalityService.findOneByHdUuid(e.getHdUuid());
		if (null == po) {
			po = new TOLModality();
		}
		// 业态
		po.setName(e.getName());
		// 编号（海鼎编号）
		po.setCode(e.getCode());
		// 级别
		po.setLv(getLv(e.getCode()));
		// 海鼎uuid
		po.setHdUuid(e.getHdUuid());
		// 海鼎上级编号
		po.setHdLevelid(e.getLevelid());
		// 备注（暂时存放英文名称）
		po.setRemark(e.getRemark());
		return po;
	}

	private String getLv(String code) {
		String lv;
		switch (code.length()) {
			case 2:
				lv = ModalityConstant.LV_1;
				break;
			case 4:
				lv = ModalityConstant.LV_2;
				break;
			case 6:
				lv = ModalityConstant.LV_3;
				break;
			case 8:
				lv = ModalityConstant.LV_4;
				break;
			default:
				lv = StringUtils.EMPTY;
				break;
		}
		return lv;
	}

	@Override
	protected void save(List<TOLModality> pos) {
		modalityService.save(pos);
	}

	@Override
	protected SyncResult<HdBiztype> getResult(Object filter) {
		JsonContainer<List<HdBiztype>> result = hdBiztypeClient.findAll();
		return new SyncResult<>(result.getData());
	}

	@Override
	protected void doAfter(Object filter) {

	}

	@Override
	protected boolean whileCondition(Object filter, SyncResult<HdBiztype> result) {
		return false;
	}
}
