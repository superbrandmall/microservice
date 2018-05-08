package com.sbm.module.onlineleasing.init.shop.coords.biz.impl;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import com.sbm.module.onlineleasing.base.shop.biz.ITOLShopService;
import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.base.shopcoords.biz.ITOLShopCoordsService;
import com.sbm.module.onlineleasing.base.shopcoords.domain.TOLShopCoords;
import com.sbm.module.onlineleasing.constant.HdConstant;
import com.sbm.module.onlineleasing.init.shop.coords.biz.ICoordsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@Slf4j
public class CoordsServiceImpl extends CommonServiceImpl implements ICoordsService {

	@Autowired
	private ITOLShopService shopService;
	@Autowired
	private ITOLMallService mallService;
	@Autowired
	private ITOLShopCoordsService shopCoordsService;

	private static final String ERROR_MESSAGE = "铺位坐标初始化异常";
	private static final String WARN_MESSAGE = "没有查到铺位。mallHdCode:{}, shopHdCode: {}";

	@Override
	public void init(String path) {
		// 根目录
		File file = new File(path);
		try {
			List<String> lines = Files.readLines(file, Charsets.UTF_8);
			process(lines);
		} catch (Exception ex) {
			log.error(ERROR_MESSAGE, ex);
		}
	}

	private void process(List<String> lines) {
		String[] args;
		for (String line : lines) {
			args = line.split("\",\"");
			if (args.length == 3) {
				process(args[0].replace("\"", ""), args[1], args[2].replace("\"", ""));
			}
		}
	}

	private void process(String mallHdCode, String shopHdCode, String coords) {
		TOLMall mall = mallService.findOneByHdCodeAndHdState(mallHdCode, HdConstant.HD_STATE_USING);
		TOLShop shop = shopService.findOneByMallCodeAndHdCodeAndHdState(mall.getCode(), shopHdCode, HdConstant.HD_STATE_USING);
		if (null != shop) {
			TOLShopCoords po = shopCoordsService.findOneByCode(shop.getCode());
			if (null == po) {
				po = new TOLShopCoords();
				po.setCode(shop.getCode());
			}
			po.setBuildingCode(shop.getBuildingCode());
			po.setCoords(coords);
			po.setUnit(shop.getUnit());
			po.setShopName(shop.getShopName());
			shopCoordsService.save(po);
		} else {
			log.warn(WARN_MESSAGE, mallHdCode, shopHdCode);
		}
	}
}
