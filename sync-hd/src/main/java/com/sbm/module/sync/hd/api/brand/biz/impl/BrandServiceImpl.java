package com.sbm.module.sync.hd.api.brand.biz.impl;

import com.sbm.module.common.biz.impl.BusinessServiceImpl;
import com.sbm.module.onlineleasing.base.brand.biz.ITOLBrandService;
import com.sbm.module.onlineleasing.base.brand.constant.BrandConstant;
import com.sbm.module.onlineleasing.base.brand.domain.TOLBrand;
import com.sbm.module.onlineleasing.base.tempparam.biz.ITOLTempParamService;
import com.sbm.module.onlineleasing.base.tempparam.constant.TempParamConstant;
import com.sbm.module.onlineleasing.base.tempparam.domain.TOLTempParam;
import com.sbm.module.partner.hd.rest.base.domain.HdQueryFilter;
import com.sbm.module.partner.hd.rest.base.domain.HdResult;
import com.sbm.module.partner.hd.rest.base.domain.HdResultBody;
import com.sbm.module.partner.hd.rest.brand.client.IHdBrandClient;
import com.sbm.module.partner.hd.rest.brand.domain.HdBrand;
import com.sbm.module.sync.hd.api.brand.biz.IBrandService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
	private static final String BIZTYPE_MESSAGE = "biztype is missing, brandUuid: {}";
	private static final String BIZTYPE_LENGTH_MESSAGE = "biztype.length != {}, brandUuid: {}, biztype: {}";
	private static final String PROPERTY_MESSAGE = "property is missing, brandUuid: {}, param: {}, value: {}";

	private static final String BRAND_SYNC = "brand sync, page: {}, pageSize: {}, pageCount: {}, RecordCount: {}";

	@Override
	@Scheduled(cron = "0 0 0 * * ?")
	public void refresh() {
		HdQueryFilter filter = new HdQueryFilter();
		HdResult<HdResultBody<HdBrand>> result;
		List<TOLBrand> pos;
		do {
			result = hdBrandClient.query(filter);
			pos = findAll(result.getBody().getRecords());
			brandService.save(pos);
			filter.setPage(filter.getPage() + 1);
			log.info(BRAND_SYNC, result.getBody().getPage(), result.getBody().getPageSize(), result.getBody().getPageCount(), result.getBody().getRecordCount());
		} while (filter.getPage() < result.getBody().getPageCount());
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
			po.setAttribute(findKey(TempParamConstant.attribute, e.getProperties().getIntroductions(), e.getUuid()));
			// 品牌价位
			po.setBrandClass(findKey(TempParamConstant.brandClass, e.getProperties().getBrandGrade(), e.getUuid()));
			// 标准店面积
			po.setStandardArea(findKey(TempParamConstant.standardArea, e.getProperties().getAreaLow(), e.getUuid()));
			// 主要客户群
			po.setTarget(findKey(TempParamConstant.target, e.getProperties().getTarget(), e.getUuid()));
			// 开店区域
			po.setLocation(findKey(TempParamConstant.location, e.getProperties().getLocation(), e.getUuid()));
			// 当前已开店数
			po.setShopAmount(findKey(TempParamConstant.shopAmount, e.getProperties().getShopCount(), e.getUuid()));
			// 品牌发展历史
			po.setHistory(findKey(TempParamConstant.history, e.getProperties().getHistory(), e.getUuid()));
			// 口碑
			po.setReputation(findKey(TempParamConstant.reputation, e.getProperties().getReputation(), e.getUuid()));
			// 是否有旗下品牌已入驻
			po.setJoined(findKey(TempParamConstant.joined, e.getProperties().getJoined(), e.getUuid()));
			// 是否有意进驻正大其它门店
			po.setJoinOtherMall(findKey(TempParamConstant.joinOtherMall, e.getProperties().getJoin_other_mall(), e.getUuid()));
			// 月均销售额坪效
			po.setCompare(findKey(TempParamConstant.compare, e.getProperties().getCompare(), e.getUuid()));
			// 品牌信息来源
			po.setSource(findKey(TempParamConstant.source, e.getProperties().getSource(), e.getUuid()));
			// 客单价
			po.setAverageUnitPrice(e.getProperties().getPriceLow());
		}
		// 品牌状态 同步过来的品牌默认已准入
		po.setStatus(BrandConstant.ADMITTANCE);
		return po;
	}

	/**
	 * 获取key
	 *
	 * @param string
	 * @param value
	 * @param hdUuid
	 * @return
	 */
	private Integer findKey(String string, String value, String hdUuid) {
		Integer key = null;
		if (StringUtils.isEmpty(value)) {
			return key;
		}
		TOLTempParam tempParam = tempParamService.findOneByParamAndValue(string, value);
		if (null != tempParam) {
			key = tempParam.getKey();
		} else {
			log.warn(PROPERTY_MESSAGE, hdUuid, string, value);
		}
		return key;
	}

}
