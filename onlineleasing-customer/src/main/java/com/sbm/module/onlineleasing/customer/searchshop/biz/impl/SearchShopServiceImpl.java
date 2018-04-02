package com.sbm.module.onlineleasing.customer.searchshop.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.brand.biz.ITOLBrandService;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.searchshopdetail.biz.ITOLSearchShopDetailService;
import com.sbm.module.onlineleasing.base.searchshopdetail.domain.TOLSearchShopDetail;
import com.sbm.module.onlineleasing.base.shopimages.biz.ITOLShopImagesService;
import com.sbm.module.onlineleasing.base.shopimages.domain.TOLShopImages;
import com.sbm.module.onlineleasing.customer.searchshop.biz.ISearchShopService;
import com.sbm.module.onlineleasing.customer.searchshop.biz.IShopScoreService;
import com.sbm.module.onlineleasing.domain.searchshop.SearchShop;
import com.sbm.module.onlineleasing.domain.searchshop.SearchShopResult;
import com.sbm.module.onlineleasing.domain.searchshop.ShopScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SearchShopServiceImpl extends CommonServiceImpl implements ISearchShopService {

	@Autowired
	private ITOLBrandService brandService;
	@Autowired
	private ITOLShopImagesService shopImagesService;
	@Autowired
	private ITOLMallService mallService;
	@Autowired
	private ITOLSearchShopDetailService searchShopDetailService;

	@Autowired
	private IShopScoreService shopScoreService;

	@Override
	@Transactional
	public SearchShopResult getDetails(SearchShop searchShop) {
		// 计算商铺得分
		List<ShopScore> result = shopScoreService.calShopScore(searchShop);
		// 商铺第一张图片
		setShopFirstImage(result);
		// 保存查询信息
		String searchShopDetailCode = saveSearchShopDetail(searchShop);
		return new SearchShopResult(result, searchShopDetailCode);
	}

	/**
	 * 保存查询信息，返回编号
	 *
	 * @param searchShop
	 * @return
	 */
	private String saveSearchShopDetail(SearchShop searchShop) {
		TOLSearchShopDetail po = searchShopDetailService.newInstance();
		// 用户code
		po.setUserCode(searchShop.getUserCode());
		// 品牌code
		po.setBrandCode(searchShop.getBrandCode());
		// 最小面积
		po.setMinArea(searchShop.getMinArea());
		// 最大面积
		po.setMaxArea(searchShop.getMaxArea());
		// 开始日期
		po.setStart(searchShop.getStart());
		// 结束日期
		po.setEnd(searchShop.getEnd());
		// 商场codes
		po.setMallCodes(JSON.toJSONString(searchShop.getMallCodes()));
		// 保存
		searchShopDetailService.save(po);
		return po.getCode();
	}

	/**
	 * 商铺第一张图片
	 *
	 * @param shopScores
	 */
	private void setShopFirstImage(List<ShopScore> shopScores) {
		for (ShopScore shopScore : shopScores) {
			// 商铺第一张图片
			List<TOLShopImages> shopImages = shopImagesService.findAllByCode(shopScore.getShopCode());
			if (null != shopImages && !shopImages.isEmpty()) {
				shopScore.setFirstImage(shopImages.get(0).getImage());
			} else {
				// 如果商铺图片不存在，返回mall图片
				shopScore.setFirstImage(mallService.findOneByCode(shopScore.getMallCode()).getImg());
			}
		}
	}

}
