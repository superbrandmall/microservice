package com.sbm.module.onlineleasing.admin.base.info.mall.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.common.redis.constant.RedisConstant;
import com.sbm.module.onlineleasing.admin.base.info.mall.biz.IMallInfoService;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import com.sbm.module.onlineleasing.base.malltraffic.biz.ITOLMallTrafficService;
import com.sbm.module.onlineleasing.base.shop.biz.ITOLShopService;
import com.sbm.module.onlineleasing.base.shop.constant.ShopConstant;
import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.constant.HdConstant;
import com.sbm.module.onlineleasing.domain.base.info.ModalityProportion;
import com.sbm.module.onlineleasing.domain.base.info.ModalityProportionDetail;
import com.sbm.module.onlineleasing.domain.base.info.mall.MallInfo;
import com.sbm.module.onlineleasing.domain.base.info.mall.MallMinInfo;
import com.sbm.module.onlineleasing.domain.base.info.mall.MallTraffic;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MallInfoServiceImpl extends CommonServiceImpl implements IMallInfoService {

	@Autowired
	private IRedisService redisService;

	@Autowired
	private ITOLMallService mallService;
	@Autowired
	private ITOLMallTrafficService mallTrafficService;
	@Autowired
	private ITOLShopService shopService;


	@Override
	@Scheduled(cron = "${sync.cron.base.info.mall}")
	public void refresh() {
		// 单个mall信息
		single();
		// mall列表
		list();
	}

	/**
	 * 单个mall信息
	 */
	private void single() {
		MallInfo mallInfo;
		List<TOLMall> malls = mallService.findAllByHdState(HdConstant.HD_STATE_USING);
		for (TOLMall mall : malls) {
			mallInfo = new MallInfo();
			// 项目
			mallInfo.setMallCode(mall.getCode());
			mallInfo.setMallName(mall.getMallName());
			mallInfo.setLocation(mall.getLocation());
			mallInfo.setDescription(mall.getDescription());
			mallInfo.setGrossFloorArea(mall.getGrossFloorArea());
			mallInfo.setLeasingArea(mall.getLeasingArea());
			mallInfo.setPosition(mall.getPosition());
			mallInfo.setImg(mall.getImg());
			mallInfo.setMallNameEng(mall.getMallNameEng());
			mallInfo.setLocationEng(mall.getLocationEng());
			mallInfo.setVideo(mall.getVideo());
			// 交通
			mallInfo.setTraffic(map(mallTrafficService.findAllByCode(mall.getCode()), e -> new MallTraffic(e.getType(), e.getText(), e.getRemark())));
			// 业态占比
			mallInfo.setProportion(getModalityProportion(mall.getCode()));
			// 存入缓存
			redisService.set2RedisTwoDays(RedisConstant.getKey(MallInfo.class, mall.getCode()), JSON.toJSONString(mallInfo));
		}
	}

	private ModalityProportion getModalityProportion(String mallCode) {
		ModalityProportion proportion = new ModalityProportion();
		// 查询所有满足条件的铺位
		List<TOLShop> shops = shopService.findAllByMallCodeAndShopStateAndHdState(mallCode, ShopConstant.SHOP_STATE_0, HdConstant.HD_STATE_USING);
		// 去除所有不是四级业态的铺位，根据四级业态截取出三级业态进行分组，计数
		Map<String, Long> count = shops.stream().filter(e -> StringUtils.isNotBlank(e.getModality()) && 8 == e.getModality().length()).collect(Collectors.groupingBy(e -> e.getModality().substring(0, 6), Collectors.counting()));
		// 排序，取前十位，计算百分比
		count.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).limit(10)
				.forEachOrdered(e -> proportion.getDetails().
						add(new ModalityProportionDetail(e.getKey(), e.getValue(), new BigDecimal(e.getValue()).divide(new BigDecimal(shops.size()), 2, BigDecimal.ROUND_HALF_EVEN))));
		return proportion;
	}

	/**
	 * mall列表
	 */
	private void list() {
		// 存入缓存
		redisService.set2RedisTwoDays(RedisConstant.getKey(MallInfo.class, RedisConstant.LIST),
				JSON.toJSONString(map(mallService.findAllByHdStateOrderByPosition(HdConstant.HD_STATE_USING), e -> new MallMinInfo(e.getCode(), e.getMallName(), e.getLocation(), e.getPosition(), e.getImg()))));
	}

}
