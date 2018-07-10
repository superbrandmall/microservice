package com.sbm.module.onlineleasing.admin.shop.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.onlineleasing.admin.brand.biz.IBrandService;
import com.sbm.module.onlineleasing.admin.shop.biz.IShopService;
import com.sbm.module.onlineleasing.base.shop.biz.ITOLShopService;
import com.sbm.module.onlineleasing.base.shopcoords.biz.ITOLShopCoordsService;
import com.sbm.module.onlineleasing.base.shopcoords.domain.TOLShopCoords;
import com.sbm.module.onlineleasing.base.shopengineeringimages.biz.ITOLShopEngineeringImagesService;
import com.sbm.module.onlineleasing.base.shopengineeringspecifications.biz.ITOLShopEngineeringSpecificationsService;
import com.sbm.module.onlineleasing.base.shopimages.biz.ITOLShopImagesService;
import com.sbm.module.onlineleasing.base.shopimages.domain.TOLShopImages;
import com.sbm.module.onlineleasing.domain.searchshop.SearchShopMinInfo;
import com.sbm.module.onlineleasing.domain.shop.*;
import com.sbm.module.onlineleasing.exception.OnlineleasingCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShopServiceImpl extends CommonServiceImpl implements IShopService {

	@Autowired
	private ITOLShopService shopService;
	@Autowired
	private ITOLShopImagesService shopImagesService;
	@Autowired
	private ITOLShopEngineeringImagesService shopEngineeringImagesService;
	@Autowired
	private ITOLShopEngineeringSpecificationsService shopEngineeringSpecificationsService;
	@Autowired
	private ITOLShopCoordsService shopCoordsService;

	@Autowired
	private IBrandService brandService;

	private static final String BRAND_IS_NULL = "品牌为空";
	private static final String MODALITY_IS_NULL = "业态为空";
	private static final String MODALITY_LENGTH_IS_NOT_8 = "业态长度不是8位";
	private static final String CONTRACT_EXPIRE_DATE_IS_NULL = "合同到期日为空";
	private static final String IMAGES_LENGTH_IS_0 = "图片数量为0";
	private static final String COORDS_IS_NULL = "坐标为空";

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<Shop> findAll(ShopQuery query, Pageable pageable) {
		return shopService.findAll(query.findAll(), pageable).map(e -> ShopMaxInfo.convertAll(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public ShopMaxInfo findOneByShopCode(String shopCode) {
		return mapOneIfNotNull(shopService.findOneByCode(shopCode), e -> {
			ShopMaxInfo vo = ShopMaxInfo.convertAll(e);
			// 品牌名称
			vo.setBrandName(mapOneIfNotNull(brandService.findOneByCode(vo.getBrandCode()), s -> s.getName()));
			// 铺位图片
			vo.setImages(map(shopImagesService.findAllByCodeOrderByPosition(vo.getCode()), s -> new ShopImages(s.getImage(), s.getPosition())));
			// 工程图
			vo.setEngineeringImages(map(shopEngineeringImagesService.findAllByCode(vo.getCode()), s -> new ShopEngineeringImages(s.getAttachmentType(), s.getImage())));
			// 工程条件
			vo.setEngineeringSpecifications(map(shopEngineeringSpecificationsService.findAllByCode(vo.getCode()),
					s -> new ShopEngineeringSpecifications(s.getKeyword(), s.getName(), s.getTitle(), s.getNumber(), s.getSpec())));
			// 坐标
			vo.setCoords(mapOneIfNotNull(shopCoordsService.findOneByCode(e.getCode()), s -> s.getCoords()));
			return vo;
		});
	}

	@Override
	@Transactional
	public void save(ShopMaxInfo shopMaxInfo) {
		if (null != shopMaxInfo.getCode()) {
			// 保存图片
			shopImagesService.saveOrDelete(convert2ShopImages(shopMaxInfo));
			// 保存坐标
			TOLShopCoords po = checkIfNullNewInstance(shopCoordsService.findOneByCode(shopMaxInfo.getCode()), e -> new TOLShopCoords(shopMaxInfo.getCode()));
			po.setBuildingCode(shopMaxInfo.getBuildingCode());
			po.setCoords(shopMaxInfo.getCoords());
			po.setUnit(shopMaxInfo.getUnit());
			po.setShopName(shopMaxInfo.getShopName());
			shopCoordsService.save(po);
		}
	}

	private List<TOLShopImages> convert2ShopImages(ShopMaxInfo shopMaxInfo) {
		List<TOLShopImages> pos = shopImagesService.findAllByCode(shopMaxInfo.getCode());
		return mergeAndSetDeleteFlag(pos, shopMaxInfo.getImages(), (po, vo) -> convert2ShopImages(shopMaxInfo.getCode(), po, vo), TOLShopImages.class);
	}

	private TOLShopImages convert2ShopImages(String code, TOLShopImages po, ShopImages vo) {
		// 编号
		po.setCode(code);
		// 图片
		po.setImage(vo.getImage());
		// 位置
		po.setPosition(vo.getPosition());
		return po;
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Map<String, List<ShopCheck>> findAllBySearchShopAndCheck(SearchShopMinInfo searchShopMinInfo) {
		Map<String, List<ShopCheck>> map = new HashMap<>();
		shopService.findAllBySearchShop(searchShopMinInfo.getMallCodes(), new BigDecimal(searchShopMinInfo.getMinArea()), new BigDecimal(searchShopMinInfo.getMaxArea())).stream()
				.forEachOrdered(e -> {
					ShopCheck vo = new ShopCheck(e.getCode(), e.getState(), e.getUnit(), e.getMallCode(), e.getFloorCode(), e.getArea(), e.getModality(),
							e.getContractExpireDate(), e.getShopState(), e.getSubType(), e.getBrandCode(), e.getIsSync(),
							shopImagesService.findAllByCodeOrderByPosition(e.getCode()).size(), mapOneIfNotNull(shopCoordsService.findOneByCode(e.getCode()), s -> s.getCoords()));
					// 品牌为空
					if (StringUtils.isBlank(vo.getBrandCode())) vo.getCheckItems().add(BRAND_IS_NULL);
					// 业态为空
					if (StringUtils.isBlank(vo.getModality())) {
						vo.getCheckItems().add(MODALITY_IS_NULL);
					} else {
						// 业态长度不是8位
						if (8 != vo.getModality().length()) vo.getCheckItems().add(MODALITY_LENGTH_IS_NOT_8);
					}
					// 合同到期日为空
					if (null == vo.getContractExpireDate()) vo.getCheckItems().add(CONTRACT_EXPIRE_DATE_IS_NULL);
					// 图片数量为0
					if (0 == vo.getImagesSize()) vo.getCheckItems().add(IMAGES_LENGTH_IS_0);
					// 坐标为空
					if (StringUtils.isBlank(vo.getCoords())) vo.getCheckItems().add(COORDS_IS_NULL);
					if (!vo.getCheckItems().isEmpty()) {
						List<ShopCheck> vos = map.get(vo.getMallCode());
						if (null == vos) {
							vos = new ArrayList<>();
						}
						vos.add(vo);
						map.put(vo.getMallCode(), vos);
					}
				});
		return map;
	}

	@Override
	@Transactional
	public void lock(String code, String operate) {
		shopService.updateState(checkIfNullThrowException(shopService.findOneByCode(code), new BusinessException(OnlineleasingCode.S0003, new Object[]{code})), operate);
	}
}
