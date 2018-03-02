package com.sbm.module.sync.hd.api.shop.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.biz.impl.SyncServiceImpl;
import com.sbm.module.common.constant.CommonConstant;
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
import com.sbm.module.onlineleasing.base.shopengineeringimages.biz.ITOLShopEngineeringImagesService;
import com.sbm.module.onlineleasing.base.shopengineeringimages.domain.TOLShopEngineeringImages;
import com.sbm.module.onlineleasing.base.shopengineeringspecifications.biz.ITOLShopEngineeringSpecificationsService;
import com.sbm.module.onlineleasing.base.shopengineeringspecifications.domain.TOLShopEngineeringSpecifications;
import com.sbm.module.onlineleasing.file.upload.client.IUploadProcessClient;
import com.sbm.module.onlineleasing.file.upload.constant.UploadConstant;
import com.sbm.module.onlineleasing.file.upload.domain.UploadDetail;
import com.sbm.module.partner.hd.rest.base.domain.HdMediaFile;
import com.sbm.module.partner.hd.rest.base.domain.HdQueryFilter;
import com.sbm.module.partner.hd.rest.base.domain.HdUCN;
import com.sbm.module.partner.hd.rest.shop.client.IHdShopClient;
import com.sbm.module.partner.hd.rest.shop.domain.HdConditionTemplate;
import com.sbm.module.partner.hd.rest.shop.domain.HdProjectCondition;
import com.sbm.module.partner.hd.rest.shop.domain.HdProjectContent;
import com.sbm.module.partner.hd.rest.shop.domain.HdShop;
import com.sbm.module.sync.hd.api.shop.biz.IShopService;
import com.sbm.module.sync.hd.api.shop.domain.SyncShop;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ShopServiceImpl extends SyncServiceImpl<SyncShop, HdShop, HdQueryFilter> implements IShopService {

	@Autowired
	private IHdShopClient hdShopClient;
	@Autowired
	private ITOLShopService shopService;
	@Autowired
	private ITOLShopEngineeringImagesService shopEngineeringImagesService;
	@Autowired
	private ITOLShopEngineeringSpecificationsService shopEngineeringSpecificationsService;


	@Autowired
	private ITOLMallService mallService;
	@Autowired
	private ITOLBuildingService buildingService;
	@Autowired
	private ITOLFloorService floorService;
	@Autowired
	private ITOLBrandService brandService;

	@Autowired
	private IUploadProcessClient uploadProcessClient;

	private static final String MALL_MESSAGE = "mall is missing, hduuid:{}";
	private static final String BUILDING_MESSAGE = "building is missing, hduuid:{}";
	private static final String FLOOR_MESSAGE = "floor is missing, hduuid:{}";
	private static final String BIZTYPE_MESSAGE = "biztype is missing, shopuuid:{}";
	private static final String BRAND_MESSAGE = "brand is missing, hduuid:{}";

	private static final String ERROR_MESSAGE = "铺位同步异常";

	@Override
	@Scheduled(cron = "${sync.cron.shop}")
	public void refresh() {
		HdQueryFilter filter = new HdQueryFilter();
		filter.getFilter().put("type", "shoppe");
		execute(filter);
	}

	@Override
	public SyncShop newInstance(HdShop e) {
		SyncShop sync = new SyncShop();
		try {
			// 添加铺位
			sync.setShop(convert2Shop(e));
			// 添加铺位工程图
			sync.setEngineeringImages(convert2ShopEngineeringImages(sync.getShop().getCode(), e));
			// 添加铺位工程条件
			sync.setEngineeringSpecifications(convert2ShopEngineeringSpecifications(sync.getShop().getCode(), e));
		} catch (Exception ex) {
			log.error(ERROR_MESSAGE, ex);
		}
		return sync;
	}

	/**
	 * 铺位
	 *
	 * @param e
	 * @return
	 */
	private TOLShop convert2Shop(HdShop e) {
		TOLShop po = shopService.findOneByHdUuid(e.getUuid());
		if (null == po) {
			po = shopService.newInstance();
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

	/**
	 * 铺位工程图
	 *
	 * @param code
	 * @param e
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private List<TOLShopEngineeringImages> convert2ShopEngineeringImages(String code, HdShop e) throws InstantiationException, IllegalAccessException {
		List<HdMediaFile> vos = e.getMediaFiles();
		List<TOLShopEngineeringImages> pos = shopEngineeringImagesService.findAllByCode(code);
		return mergeAndSetDeleteFlag(pos, vos, (po, vo) -> convert2ShopEngineeringImages(code, po, vo), TOLShopEngineeringImages.class);
	}

	private TOLShopEngineeringImages convert2ShopEngineeringImages(String code, TOLShopEngineeringImages po, HdMediaFile vo) {
		// 类型
		po.setAttachmentType(vo.getAttachmentType());
		// 生成upload明细
		String uri = uploadProcessClient.saveUploadDetail(new UploadDetail(
				CommonConstant.SYSTEM, vo.getId(), UploadConstant.CONTAINER_NAME_DEFAULT,
				code, UploadConstant.PIC, UploadConstant.ENGINEERING_IMAGE))
				.getData();
		// 地址
		po.setImage(uri);
		return po;
	}

	/**
	 * 铺位工程条件
	 *
	 * @param code
	 * @param e
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private List<TOLShopEngineeringSpecifications> convert2ShopEngineeringSpecifications(String code, HdShop e) throws InstantiationException, IllegalAccessException {
		List<TOLShopEngineeringSpecifications> vos = new ArrayList<>();
		for (HdConditionTemplate template : e.getTemplates()) {
			for (HdProjectCondition condition : template.getConditions()) {
				List<HdProjectContent> contents = JSON.parseArray(condition.getContent(), HdProjectContent.class);
				for (HdProjectContent content : contents) {
					vos.add(convert2ShopEngineeringSpecifications(content, condition));
				}
			}
		}
		List<TOLShopEngineeringSpecifications> pos = shopEngineeringSpecificationsService.findAllByCode(code);
		return mergeAndSetDeleteFlag(pos, vos, (po, vo) -> convert2ShopEngineeringSpecifications(code, po, vo), TOLShopEngineeringSpecifications.class);
	}

	private TOLShopEngineeringSpecifications convert2ShopEngineeringSpecifications(HdProjectContent content, HdProjectCondition condition) {
		TOLShopEngineeringSpecifications vo = new TOLShopEngineeringSpecifications();
		vo.setKeyword(condition.getKey());
		vo.setName(condition.getName());
		vo.setTitle(content.getTitle());
		vo.setNumber(content.getNumber());
		vo.setSpec(content.getSpec());
		return vo;
	}

	private TOLShopEngineeringSpecifications convert2ShopEngineeringSpecifications(String code, TOLShopEngineeringSpecifications po, TOLShopEngineeringSpecifications vo) {
		po.setCode(code);
		po.setKeyword(vo.getKeyword());
		po.setName(vo.getName());
		po.setTitle(vo.getTitle());
		po.setNumber(vo.getNumber());
		po.setSpec(vo.getSpec());
		return po;
	}

	@Override
	protected void save(List<SyncShop> pos) {
		for (SyncShop po : pos) {
			// 铺位
			shopService.save(po.getShop());
			// 铺位工程图
			if (!po.getEngineeringImages().isEmpty()) {
				po.getEngineeringImages().stream().forEach(e -> e.setCode(po.getShop().getCode()));
				shopEngineeringImagesService.saveOrDelete(po.getEngineeringImages());
			}
			// 铺位工程条件
			if (!po.getEngineeringSpecifications().isEmpty()) {
				shopEngineeringSpecificationsService.saveOrDelete(po.getEngineeringSpecifications());
			}
		}
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
