package com.sbm.module.sync.hd.api.brand.biz.impl;

import com.sbm.module.common.biz.impl.BusinessServiceImpl;
import com.sbm.module.onlineleasing.base.brand.biz.ITOLBrandService;
import com.sbm.module.onlineleasing.base.brand.constant.BrandConstant;
import com.sbm.module.onlineleasing.base.brand.domain.TOLBrand;
import com.sbm.module.onlineleasing.base.tempparam.biz.ITOLTempParamService;
import com.sbm.module.onlineleasing.base.tempparam.constant.TempParamConstant;
import com.sbm.module.partner.hd.rest.base.domain.HdQueryFilter;
import com.sbm.module.partner.hd.rest.base.domain.HdResult;
import com.sbm.module.partner.hd.rest.base.domain.HdResultBody;
import com.sbm.module.partner.hd.rest.brand.client.IHdBrandClient;
import com.sbm.module.partner.hd.rest.brand.domain.HdBrand;
import com.sbm.module.sync.hd.api.brand.biz.IBrandService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BrandServiceImpl extends BusinessServiceImpl<TOLBrand, HdBrand> implements IBrandService {

	@Autowired
	private IHdBrandClient hdBrandClient;
	@Autowired
	private ITOLBrandService brandService;
	@Autowired
	private ITOLTempParamService tempParamService;

	private static final Integer LENGTH = 8;
	private static final String BIZTYPE_MESSAGE = "biztype is missing, brandUuid:{0}";
	private static final String BIZTYPE_LENGTH_MESSAGE = "biztype.length != {0}, brandUuid:{1}, biztype:{2}";


	@Override
	public void refresh() {
		HdQueryFilter filter = new HdQueryFilter();
		// 第一次查询
		HdResult<HdResultBody<HdBrand>> result = hdBrandClient.query(filter);
		// 遍历查询
		for (int i = 1; i < result.getBody().getPageCount(); i++) {
			List<TOLBrand> pos = findAll(result.getBody().getRecords());
			brandService.save(pos);
		}
	}

	@Override
	public TOLBrand newInstance(HdBrand e) {
		TOLBrand po = brandService.findOneByHdUuid(e.getUuid());
		if (null == po) {
			po = new TOLBrand();
		}
		// 海鼎uuid
		po.setHdUuid(e.getUuid());
		// 海鼎编码
		po.setHdCode(e.getCode());
		// 名称
		po.setName(e.getName());
		// 英文名称
		po.setNameEng(e.getForeignName());
		// 海鼎状态
		po.setHdState(e.getState());
		// 业态3
		if (null != e.getBizType()) {
			if (StringUtils.isNotBlank(e.getBizType().getCode()) && e.getBizType().getCode().length() == LENGTH) {
				po.setModality_1(e.getBizType().getCode().substring(0, 4));
				po.setModality_2(e.getBizType().getCode().substring(0, 6));
				po.setModality_3(e.getBizType().getCode());
			} else {
				log.warn(BIZTYPE_LENGTH_MESSAGE, LENGTH, e.getUuid(), e.getBizType().getCode());
			}
		} else {
			log.warn(BIZTYPE_MESSAGE, e.getUuid());
		}
		// 城市
		po.setCity(e.getLocal());

		if (null != e.getProperties()) {
			// 品牌属性
			po.setAttribute(tempParamService.findOneByParamAndValue(TempParamConstant.attribute, e.getProperties().getIntroductions()).getKey());
			// 品牌价位
			po.setBrandClass(tempParamService.findOneByParamAndValue(TempParamConstant.brandClass, e.getProperties().getBrandGrade()).getKey());
			// 标准店面积
			po.setStandardArea(tempParamService.findOneByParamAndValue(TempParamConstant.standardArea, e.getProperties().getAreaLow()).getKey());
			// 主要客户群
			po.setTarget(tempParamService.findOneByParamAndValue(TempParamConstant.target, e.getProperties().getTarget()).getKey());
			// 开店区域
			po.setLocation(tempParamService.findOneByParamAndValue(TempParamConstant.location, e.getProperties().getLocation()).getKey());
			// 当前已开店数
			po.setShopAmount(tempParamService.findOneByParamAndValue(TempParamConstant.shopAmount, e.getProperties().getShopCount()).getKey());
			// 品牌发展历史
			po.setHistory(tempParamService.findOneByParamAndValue(TempParamConstant.history, e.getProperties().getHistory()).getKey());
			// 口碑
			po.setReputation(tempParamService.findOneByParamAndValue(TempParamConstant.reputation, e.getProperties().getReputation()).getKey());
			// 是否有旗下品牌已入驻
			po.setJoined(tempParamService.findOneByParamAndValue(TempParamConstant.joined, e.getProperties().getJoined()).getKey());
			// 是否有意进驻正大其它门店
			po.setJoinOtherMall(tempParamService.findOneByParamAndValue(TempParamConstant.joinOtherMall, e.getProperties().getJoin_other_mall()).getKey());
			// 月均销售额坪效
			po.setCompare(tempParamService.findOneByParamAndValue(TempParamConstant.compare, e.getProperties().getCompare()).getKey());
			// 品牌信息来源
			po.setSource(tempParamService.findOneByParamAndValue(TempParamConstant.source, e.getProperties().getSource()).getKey());
			// 客单价
			po.setAverageUnitPrice(e.getProperties().getPriceLow());
		}
		// 品牌状态 同步过来的品牌默认已准入
		po.setStatus(BrandConstant.ADMITTANCE);
		return po;
	}
}
