package com.sbm.module.onlineleasing.admin.base.coords.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.common.redis.constant.RedisConstant;
import com.sbm.module.onlineleasing.admin.base.coords.biz.ICoordsService;
import com.sbm.module.onlineleasing.admin.brand.biz.IBrandService;
import com.sbm.module.onlineleasing.base.building.biz.ITOLBuildingService;
import com.sbm.module.onlineleasing.base.floor.biz.ITOLFloorService;
import com.sbm.module.onlineleasing.base.floor.domain.TOLFloor;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import com.sbm.module.onlineleasing.base.shop.biz.ITOLShopService;
import com.sbm.module.onlineleasing.base.shopcoords.biz.ITOLShopCoordsService;
import com.sbm.module.onlineleasing.constant.HdConstant;
import com.sbm.module.onlineleasing.domain.base.coords.Coords;
import com.sbm.module.onlineleasing.domain.brand.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoordsServiceImpl extends CommonServiceImpl implements ICoordsService {

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
	@Autowired
	private ITOLShopCoordsService shopCoordsService;

	@Autowired
	private IBrandService brandService;

	@Override
	@Scheduled(cron = "${sync.cron.base.coords}")
	public void refresh() {
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
				List<Coords> coords = getCoords(floors.stream().map(e -> e.getCode()).collect(Collectors.toList()));
				// 存入缓存
				redisService.set2RedisTwoDays(RedisConstant.getKey(Coords.class, mall.getCode(), description), JSON.toJSONString(coords));
			}
		}
	}

	/**
	 * 坐标
	 *
	 * @param floorCodes
	 * @return
	 */
	private List<Coords> getCoords(List<String> floorCodes) {
		return map(shopService.findAllByFloorCodeInAndHdState(floorCodes, HdConstant.HD_STATE_USING), e -> {
					Brand brand = brandService.findOneByCode(e.getBrandCode());
					return new Coords(e.getCode(), e.getState(), e.getShopState(), e.getUnit(),
							mapOneIfNotNull(brand, s -> s.getName()), mapOneIfNotNull(brand, s -> s.getNameEng()),
							mapOneIfNotNull(shopCoordsService.findOneByCode(e.getCode()), s -> s.getCoords()));
				}

		);
	}
}
