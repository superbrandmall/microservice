package com.sbm.module.common.authorization.api.serialcode.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.authorization.api.serialcode.biz.ISerialCodeRegisterService;
import com.sbm.module.common.authorization.api.serialcode.biz.ISerialCodeService;
import com.sbm.module.common.authorization.api.serialcode.domain.SerialCode;
import com.sbm.module.common.authorization.exception.SerialCodeErrorCode;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.common.redis.constant.RedisConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SerialCodeServiceImpl implements ISerialCodeService, ISerialCodeRegisterService {

	@Autowired
	private IRedisService redisService;

	/**
	 * 更新
	 */
	private static final String UPDATE = "update";
	/**
	 * 新增
	 */
	private static final String SAVE = "save";

	/******************** 下一编号 ********************/
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
	private static DecimalFormat decimalFormat = new DecimalFormat("000000");

	@Override
	public String next(String serialGroup) {
		return nextBizId(serialGroup);
	}

	private String nextBizId(String serialGroup) {
		String date = simpleDateFormat.format(new Date());
		Integer serialNum = (Integer) redisService.get(RedisConstant.getKey(SerialCode.class, serialGroup, date));
		if (null == serialNum) {
			// 必须注册的serialGroup才允许生成序列号
			String valuer = (String) redisService.get(RedisConstant.getKey(SerialCode.class, serialGroup));
			if (StringUtils.isBlank(valuer)) {
				throw new BusinessException(SerialCodeErrorCode.SC0001, new Object[]{serialGroup});
			}
			serialNum = 1;
		} else {
			serialNum++;
		}
		// 存入redis
		redisService.set2RedisTwoDays(RedisConstant.getKey(SerialCode.class, serialGroup, date), serialNum);
		return generalBizId(serialGroup, date, serialNum);
	}

	private static String generalBizId(String prefix, String date, int serialNum) {
		return prefix + date + decimalFormat.format(serialNum);
	}

	/******************** 注册 ********************/

	@Override
	@Transactional
	public void register(List<SerialCode> vos) {
		vos.forEach(e -> {
			SerialCode vo;
			String valuer = (String) redisService.get(RedisConstant.getKey(SerialCode.class, e.getSerialGroup()));
			if (StringUtils.isNotBlank(valuer)) {
				e.setOperate(UPDATE);
			} else {
				e.setOperate(SAVE);
			}
			vo = e;
			// 注册到redis，不设过期时间
			redisService.set(RedisConstant.getKey(SerialCode.class, vo.getSerialGroup()), JSON.toJSONString(vo));
		});
	}
}
