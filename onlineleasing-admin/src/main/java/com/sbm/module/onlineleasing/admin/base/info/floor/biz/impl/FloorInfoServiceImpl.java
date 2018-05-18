package com.sbm.module.onlineleasing.admin.base.info.floor.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.common.redis.constant.RedisConstant;
import com.sbm.module.onlineleasing.admin.base.info.floor.biz.IFloorInfoService;
import com.sbm.module.onlineleasing.base.building.biz.ITOLBuildingService;
import com.sbm.module.onlineleasing.base.floor.biz.ITOLFloorService;
import com.sbm.module.onlineleasing.base.floor.domain.TOLFloor;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import com.sbm.module.onlineleasing.base.shop.biz.ITOLShopService;
import com.sbm.module.onlineleasing.base.shop.constant.ShopConstant;
import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.constant.HdConstant;
import com.sbm.module.onlineleasing.domain.base.info.ModalityProportion;
import com.sbm.module.onlineleasing.domain.base.info.ModalityProportionDetail;
import com.sbm.module.onlineleasing.domain.base.info.floor.FloorInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FloorInfoServiceImpl extends CommonServiceImpl implements IFloorInfoService {

	@Autowired
	private IRedisService redisService;

	@Autowired
	private ITOLMallService mallService;
	@Autowired
	private ITOLBuildingService buildingService;
	@Autowired
	private ITOLFloorService floorService;
	@Autowired
	private ITOLShopService shopService;

	private static String OTHER = "其他";

	@Override
	@Scheduled(cron = "${sync.cron.base.info.floor}")
	public void refresh() {
		FloorInfo floorInfo;
		// 项目
		List<TOLMall> malls = mallService.findAllByHdState(HdConstant.HD_STATE_USING);
		for (TOLMall mall : malls) {
			// 建筑物
			List<String> buildingCodes = buildingService.findAllBuildingCodeByMallCode(mall.getCode());
			if (buildingCodes.isEmpty()) {
				continue;
			}
			// 楼层
			List<String> descriptions = floorService.findAllDescriptionByBuildingCodeIn(buildingCodes);
			for (String description : descriptions) {
				List<TOLFloor> floors = floorService.findAllByBuildingCodeInAndDescriptionAndHdState(buildingCodes, description, HdConstant.HD_STATE_USING);
				floorInfo = new FloorInfo();
				// 建筑物编号
				floorInfo.setMallCode(mall.getCode());
				// 描述
				floorInfo.setDescription(description);
				// 建筑面积
				floorInfo.setGrossFloorArea(new BigDecimal(floors.stream().mapToDouble(e -> e.getGrossFloorArea().doubleValue()).sum()).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				// 租赁面积
				floorInfo.setLeasingArea(new BigDecimal(floors.stream().mapToDouble(e -> e.getLeasingArea().doubleValue()).sum()).setScale(2, BigDecimal.ROUND_HALF_EVEN));
				// 业态占比
				floorInfo.setProportion(getModalityProportion(floors.stream().map(e -> e.getCode()).collect(Collectors.toList())));
				// 存入缓存
				redisService.set2RedisTwoDays(RedisConstant.getKey(FloorInfo.class, mall.getCode(), description), JSON.toJSONString(floorInfo));
			}
		}
	}

	private ModalityProportion getModalityProportion(List<String> floorCodes) {
		ModalityProportion proportion = new ModalityProportion();
		// 查询所有满足条件的铺位
		List<TOLShop> shops = shopService.findAllByFloorCodeInAndShopStateAndHdState(floorCodes, ShopConstant.SHOP_STATE_0, HdConstant.HD_STATE_USING);

		// 去除所有不是四级业态的铺位，根据四级业态截取出三级业态进行分组，计数
		Map<String, Long> count = shops.stream().filter(e -> StringUtils.isNotBlank(e.getModality()) && 8 == e.getModality().length()).collect(Collectors.groupingBy(e -> e.getModality().substring(0, 6), Collectors.counting()));
		BigDecimal total = new BigDecimal(shops.stream().filter(e -> StringUtils.isNotBlank(e.getModality()) && 8 == e.getModality().length()).collect(Collectors.toList()).size());

		// 排序，取前三位，计算百分比
		count.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(3).forEachOrdered(e -> proportion.getDetails().
				add(new ModalityProportionDetail(e.getKey(), e.getValue(), new BigDecimal(e.getValue()).divide(total, 2, BigDecimal.ROUND_HALF_EVEN))));

		// 加入第四项其他
		Long other = count.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).skip(3).mapToLong(e -> e.getValue()).sum();
		if (0 != other) {
			proportion.getDetails().add(new ModalityProportionDetail(OTHER, other, new BigDecimal(other).divide(total, 2, BigDecimal.ROUND_HALF_EVEN)));
		}

		return proportion;
	}

}
