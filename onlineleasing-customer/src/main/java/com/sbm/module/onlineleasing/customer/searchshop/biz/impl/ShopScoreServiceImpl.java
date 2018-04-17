package com.sbm.module.onlineleasing.customer.searchshop.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.util.DifferentDays;
import com.sbm.module.onlineleasing.customer.brand.biz.IBrandService;
import com.sbm.module.onlineleasing.customer.searchshop.biz.IShopScoreService;
import com.sbm.module.onlineleasing.customer.shop.biz.IShopService;
import com.sbm.module.onlineleasing.domain.brand.Brand;
import com.sbm.module.onlineleasing.domain.searchshop.SearchShop;
import com.sbm.module.onlineleasing.domain.searchshop.ShopScore;
import com.sbm.module.onlineleasing.domain.shop.Shop;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ShopScoreServiceImpl extends CommonServiceImpl implements IShopScoreService {

	@Autowired
	private IBrandService brandService;
	@Autowired
	private IShopService shopService;

	@Override
	public List<ShopScore> calShopScore(SearchShop searchShop) {
		List<ShopScore> shopScores = new ArrayList<>();
		// 查出对应品牌
		Brand brand = brandService.findOneByCode(searchShop.getBrandCode());
		// 查询出所有店铺
		List<Shop> shops = shopService.findAllBySearchShop(searchShop.getMallCodes());
		// 计算每个商铺的得分
		shops.forEach(e -> shopScores.add(calScore(searchShop, brand, e)));
		// 商铺得分排序
		Collections.sort(shopScores, (a, b) -> b.getScore().compareTo(a.getScore()));
		// 计算排序得分
		calcSortScore(shopScores);
		// 取前N条数据
		return shopScores.size() > searchShop.getMax() ? shopScores.subList(0, searchShop.getMax()) : shopScores;

	}

	/****************************************************************************************************/

	/**
	 * 计算排序得分
	 *
	 * @param shopScores
	 */
	private void calcSortScore(List<ShopScore> shopScores) {
		for (int i = 0; i < shopScores.size(); i++) {
			// (100 * (size - i))/size 取整
			BigDecimal score = (new BigDecimal(100).multiply(new BigDecimal(shopScores.size()).subtract(new BigDecimal(i))))
					.divide(new BigDecimal(shopScores.size()), 2, BigDecimal.ROUND_HALF_DOWN)
					.setScale(0, BigDecimal.ROUND_HALF_UP);
			shopScores.get(i).setScore(score);
		}
	}

	/****************************************************************************************************/

	/**
	 * 计算商铺得分
	 *
	 * @param searchShop
	 * @param brand
	 * @param shop
	 * @return
	 */
	private ShopScore calScore(SearchShop searchShop, Brand brand, Shop shop) {
		// 设置基本信息
		ShopScore shopScore = new ShopScore(shop.getCode(), shop.getUnit(), shop.getMallCode(), shop.getMallName(), shop.getFloorCode(), shop.getFloorName(), shop.getArea(), shop.getModality());
		// 计算得分
		BigDecimal score = new BigDecimal(0);
		// 计算业态得分
		score = score.add(calModality(brand, shop));
		// 计算面积得分
		score = score.add(calArea(searchShop, shop));
		// 计算日期得分
		score = score.add(calDate(searchShop, shop));
		// 取整
		score = score.setScale(0, BigDecimal.ROUND_HALF_UP);
		shopScore.setScore(score);
		return shopScore;
	}

	/**
	 * calModality:计算业态得分
	 *
	 * @param brand
	 * @param shop
	 * @return
	 * @author junkai.zhang
	 */
	private BigDecimal calModality(Brand brand, Shop shop) {
		BigDecimal score = new BigDecimal(0);
		// 取三级业态作比较
		String brandModality = brand.getModality_2();
		String shopModality = StringUtils.isNotBlank(shop.getModality()) && 8 == shop.getModality().length() ? shop.getModality().substring(0, 6) : StringUtils.EMPTY;
		// 商铺业态不为空
		if (StringUtils.isNotEmpty(shopModality)) {
			// 三级业态，相等100分
			if (brandModality.equals(shopModality)) {
				score = score.add(new BigDecimal(100));
			}
			// 二级业态，相等50分
			else if ((brandModality.substring(0, 4)).equals(shopModality.substring(0, 4))) {
				score = score.add(new BigDecimal(50));
			}
			// 一级业态，相等10分
			else if ((brandModality.substring(0, 2)).equals(shopModality.substring(0, 2))) {
				score = score.add(new BigDecimal(10));
			}
			// 都不相等，0分
			else {
				score = score.add(new BigDecimal(0));
			}
		}
		// 商铺业态为空则，100分
		else {
			score = score.add(new BigDecimal(100));
		}
		// 占比40%
		score = score.multiply(new BigDecimal(0.4));
		return score;
	}

	/**
	 * 计算面积得分
	 *
	 * @param searchShop
	 * @param shop
	 * @return
	 */
	private BigDecimal calArea(SearchShop searchShop, Shop shop) {
		BigDecimal score = new BigDecimal(0);
		// -1 最小面积小于商店店铺
		Integer min = new BigDecimal(searchShop.getMinArea()).compareTo(shop.getArea());
		// 1 最大面积大于商店店铺
		Integer max = new BigDecimal(searchShop.getMaxArea()).compareTo(shop.getArea());
		// 范围内，100分
		if (-1 == min && 1 == max) {
			score = score.add(new BigDecimal(100));
		}
		// 范围外，0分
		else {
			score = score.add(new BigDecimal(0));
		}
		// 占比10%
		score = score.multiply(new BigDecimal(0.1));
		return score;
	}

	/**
	 * 计算日期得分
	 *
	 * @param searchShop
	 * @param shop
	 * @return
	 */
	private BigDecimal calDate(SearchShop searchShop, Shop shop) {
		BigDecimal score = new BigDecimal(0);
		// 最早可入住日期
		Date earliestDate = getEarliestDate(shop.getContractExpireDate());
		// 店铺状态是空铺
		if (1 == shop.getShopState()) {
			// 满分
			score = score.add(new BigDecimal(100));
		}
		// 店铺状态是待租
		else {
			// 最早可入住时间或者开始时间为空（异常数据）
			if (null == earliestDate || null == searchShop.getStart()) {
				// 0分
				score = score.add(new BigDecimal(0));
			} else {
				// 相差天数
				Integer diffDays = getDiffDays(earliestDate, searchShop.getStart());
				// 相差0天，100分
				if (0 == diffDays) {
					score = score.add(new BigDecimal(100));
				}
				// 相差N天，1/N * 100 分 = 100/N 分
				else {
					BigDecimal tmp = new BigDecimal(100)
							.divide(new BigDecimal(diffDays), 2, BigDecimal.ROUND_HALF_DOWN);
					score = score.add(tmp);
				}
			}
		}
		// 占比50%
		score = score.multiply(new BigDecimal(0.5));
		return score;
	}

	/**
	 * 最早可入住日期
	 *
	 * @param date
	 * @return
	 */
	private Date getEarliestDate(Date date) {
		if (null != date) {
			// 合同结束日期+1天
			date = DifferentDays.addNdays(date, 1);
		}
		return date;
	}

	/**
	 * 相差天数
	 *
	 * @param d1
	 * @param d2
	 * @return
	 */
	private Integer getDiffDays(Date d1, Date d2) {
		Integer diffDays;
		if (d1.before(d2)) {
			diffDays = DifferentDays.differentDays(d1, d2);
		} else {
			diffDays = DifferentDays.differentDays(d2, d1);
		}
		return diffDays;
	}

}
