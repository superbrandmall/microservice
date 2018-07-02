package com.sbm.module.onlineleasing.init.shop.vr.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import com.sbm.module.onlineleasing.base.shop.biz.ITOLShopService;
import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.init.shop.vr.biz.IVRService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VRServiceImpl extends CommonServiceImpl implements IVRService {

	@Autowired
	private ITOLMallService mallService;
	@Autowired
	private ITOLShopService shopService;

	private static final String ERROR_MESSAGE = "铺位VR初始化异常";
	private static final String MALL_HD_CODE_IS_BLANK = "项目海鼎编号为空。code:{0}";

	@Value("${init.shop.vr.path}")
	private String path;

	@Override
	public void init() {
		List<TOLMall> malls = mallService.findAll();
		Map<String, String> mallCodeMap = malls.stream().collect(Collectors.toMap(TOLMall::getCode, TOLMall::getHdCode));

		String mallHdCode;
		// 更新全部
		List<TOLShop> shops = shopService.findAll();
		// List<TOLShop> shops = shopService.findAllByHdState(HdConstant.HD_STATE_USING);
		for (TOLShop shop : shops) {
			try {
				mallHdCode = mallCodeMap.get(shop.getMallCode());
				if (StringUtils.isBlank(mallHdCode)) {
					throw new RuntimeException(MessageFormat.format(MALL_HD_CODE_IS_BLANK, shop.getCode()));
				}
				shop.setVr(MessageFormat.format(path, mallHdCode, shop.getHdCode()));
				shopService.save(shop);
			} catch (Exception ex) {
				log.error(ERROR_MESSAGE, ex);
			}
		}
	}
}
