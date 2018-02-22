package com.sbm.module.sync.hd.api.shop.biz.impl;

import com.sbm.module.common.biz.impl.SyncServiceImpl;
import com.sbm.module.common.domain.SyncResult;
import com.sbm.module.common.util.DifferentDays;
import com.sbm.module.onlineleasing.base.brand.biz.ITOLBrandService;
import com.sbm.module.onlineleasing.base.brand.domain.TOLBrand;
import com.sbm.module.onlineleasing.base.building.biz.ITOLBuildingService;
import com.sbm.module.onlineleasing.base.building.domain.TOLBuilding;
import com.sbm.module.onlineleasing.base.floor.biz.ITOLFloorService;
import com.sbm.module.onlineleasing.base.floor.domain.TOLFloor;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import com.sbm.module.onlineleasing.base.shop.biz.ITOLShopService;
import com.sbm.module.onlineleasing.base.shop.constant.ShopConstant;
import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.partner.hd.rest.base.domain.HdQueryFilter;
import com.sbm.module.partner.hd.rest.base.domain.HdUCN;
import com.sbm.module.partner.hd.rest.shop.client.IHdShopClient;
import com.sbm.module.partner.hd.rest.shop.domain.HdShop;
import com.sbm.module.sync.hd.api.shop.biz.IShopService;
import com.sbm.module.sync.hd.api.shop.domain.SyncShop;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShopServiceImpl extends SyncServiceImpl<SyncShop, HdShop, HdQueryFilter> implements IShopService {

	@Autowired
	private IHdShopClient hdShopClient;
	@Autowired
	private ITOLShopService shopService;

	@Autowired
	private ITOLMallService mallService;
	@Autowired
	private ITOLBuildingService buildingService;
	@Autowired
	private ITOLFloorService floorService;
	@Autowired
	private ITOLBrandService brandService;

	private static final String MALL_MESSAGE = "mall is missing, hduuid:{0}";
	private static final String BUILDING_MESSAGE = "building is missing, hduuid:{0}";
	private static final String FLOOR_MESSAGE = "floor is missing, hduuid:{0}";
	private static final String BIZTYPE_MESSAGE = "biztype is missing, shopuuid:{0}";
	private static final String BRAND_MESSAGE = "brand is missing, hduuid:{0}";

	@Override
	//@Scheduled(cron = "${sync.cron.shop}")
	public void refresh() {
		HdQueryFilter filter = new HdQueryFilter();
		filter.getFilter().put("type", "shoppe");
		execute(filter);
	}

	@Override
	public SyncShop newInstance(HdShop e) {
		SyncShop sync = new SyncShop();
		// 添加shop
		sync.setShop(convert2Shop(e));
		// 添加工程图
		// 添加工程条件
		// TODO
		return sync;
	}

	private TOLShop convert2Shop(HdShop e) {
		TOLShop po = shopService.findOneByHdUuid(e.getUuid());
		if (null == po) {
			po = new TOLShop();
		}
		// 海鼎uuid
		po.setHdUuid(e.getUuid());
		// 海鼎编码
		po.setHdCode(e.getCode());
		// 海鼎名称
		po.setShopName(e.getName());
		// 海鼎状态
		po.setHdState(e.getState());

		// 商场编号
		if (null != e.getStore() && StringUtils.isNotBlank(e.getStore().getUuid())) {
			TOLMall mall = mallService.findOneByHdUuid(e.getStore().getUuid());
			if (null != mall) {
				po.setMallCode(mall.getCode());
				po.setMallName(mall.getMallName());
			} else {
				log.warn(MALL_MESSAGE, e.getStore().getUuid());
			}
		}
		// 楼宇编号
		if (null != e.getBuilding() && StringUtils.isNotBlank(e.getBuilding().getUuid())) {
			TOLBuilding building = buildingService.findOneByHdUuid(e.getBuilding().getUuid());
			if (null != building) {
				po.setBuildingCode(building.getCode());
				po.setBuildingName(building.getBuildingName());
			} else {
				log.warn(BUILDING_MESSAGE, e.getBuilding().getUuid());
			}
		}
		// 楼层编号
		if (null != e.getFloor() && StringUtils.isNotBlank(e.getFloor().getUuid())) {
			TOLFloor floor = floorService.findOneByHdUuid(e.getFloor().getUuid());
			if (null != floor) {
				po.setFloorCode(floor.getCode());
				// 铺位中的楼层名称使用楼层描述
				// po.setFloorName(floor.getFloorName());
				po.setFloorName(floor.getDescription());
			} else {
				log.warn(FLOOR_MESSAGE, e.getFloor().getUuid());
			}
		}
		// 业态
		if (null != e.getModality()) {
			po.setModality(e.getModality().getCode());
		} else {
			log.warn(BIZTYPE_MESSAGE, e.getUuid());
		}
		// 单元号
		po.setUnit(e.getCode());
		// 租赁面积
		po.setArea(e.getRentArea());

		// 商铺状态
		if (null == e.getContract_expire_date()) {
			po.setShopState(ShopConstant.SHOP_STATE_1);
		} else {
			Integer days = DifferentDays.differentDays(new Date(), e.getContract_expire_date());
			// 空铺
			if (days < 0) {
				po.setShopState(ShopConstant.SHOP_STATE_1);
			}
			// 待租
			else if (0 <= days && days < 180) {
				po.setShopState(ShopConstant.SHOP_STATE_2);
			}
			// 在租
			else {
				po.setShopState(ShopConstant.SHOP_STATE_0);
			}
		}

		// 最后一份合同到期日
		po.setContractExpireDate(e.getContract_expire_date());
		// 最低可出价租金
		po.setDeadRent(e.getDead_rent());
		// 最低可出价扣率
		po.setFloatingRentalRate(e.getFloating_rental_rate());
		// 最后一份合同的品牌
		if (null != e.getCurrentBrand() && !e.getCurrentBrand().isEmpty()) {
			HdUCN cb = e.getCurrentBrand().get(0);
			if (StringUtils.isNotBlank(cb.getUuid())) {
				TOLBrand brand = brandService.findOneByHdUuid(cb.getUuid());
				if (null != brand) {
					po.setBrandCode(brand.getCode());
				} else {
					log.warn(BRAND_MESSAGE, cb.getUuid());
				}
			}
		}
		// 铺位类型
		po.setSubType(e.getSubType());
		return po;
	}


	@Override
	protected void save(List<SyncShop> pos) {
		shopService.save(map(pos, e -> e.getShop()));
	}

	@Override
	protected SyncResult<HdShop> getResult(HdQueryFilter filter) {
		return hdShopClient.query(filter).getBody().toSyncResult();
	}

	@Override
	protected void doAfter(HdQueryFilter filter) {
		filter.addOne();
	}

	@Override
	protected boolean whileCondition(HdQueryFilter filter, SyncResult<HdShop> result) {
		return filter.getPage() < result.getPageCount();
	}

}
