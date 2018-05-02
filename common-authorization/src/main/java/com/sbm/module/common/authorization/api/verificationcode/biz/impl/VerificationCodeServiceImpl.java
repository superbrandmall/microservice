package com.sbm.module.common.authorization.api.verificationcode.biz.impl;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.authorization.api.verificationcode.biz.IVerificationCodeService;
import com.sbm.module.common.authorization.api.verificationcode.domain.VerificationCode;
import com.sbm.module.common.authorization.api.verificationcode.domain.VerificationCodeCheck;
import com.sbm.module.common.authorization.api.verificationcode.domain.VerificationCodeSetting;
import com.sbm.module.common.authorization.api.verificationcode.exception.VerificationCodeErrorCode;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.common.redis.constant.RedisConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class VerificationCodeServiceImpl extends CommonServiceImpl implements IVerificationCodeService {

	@Autowired
	private IRedisService redisService;

	@Value("${verificationCode.default.sources}")
	private String sources;
	@Value("${verificationCode.default.size}")
	private String size;

	@Override
	public VerificationCode generate(VerificationCodeSetting setting) {
		String code = generateVerificationCode(setting.getSize(), setting.getSources());
		String key = RedisConstant.getKey(VerificationCodeSetting.class, setting.getKeyword(), getUUID());
		redisService.set2Redis(key, JSON.toJSONString(code));
		return new VerificationCode(key, code);
	}

	private String generateVerificationCode(Integer size, String sources) {
		// 使用默认长度
		if (null == size || 0 == size) {
			size = Integer.valueOf(this.size);
		}
		// 使用默认源
		if (StringUtils.isEmpty(sources)) {
			sources = this.sources;
		}
		// 随机数
		Random rand = new Random(System.currentTimeMillis());
		// 生成验证码
		StringBuilder verificationCode = new StringBuilder(size);
		for (int i = 0; i < size; i++) {
			verificationCode.append(sources.charAt(rand.nextInt(sources.length() - 1)));
		}
		return verificationCode.toString();
	}

	@Override
	public void check(VerificationCodeCheck check) {
		if (!check.getKey().contains(check.getKeyword())) {
			throw new BusinessException(VerificationCodeErrorCode.VC0003, new Object[]{check.getKeyword()});
		}
		String valuer = (String) redisService.get(check.getKey());
		if (StringUtils.isNotBlank(valuer)) {
			String _code = JSON.parseObject(valuer, String.class);
			if (check.getCode().equalsIgnoreCase(_code)) {
				// 删除key
				redisService.delete(check.getKey());
			} else {
				throw new BusinessException(VerificationCodeErrorCode.VC0002, new Object[]{check.getCode()});
			}
		} else {
			throw new BusinessException(VerificationCodeErrorCode.VC0001, new Object[]{check.getKey()});
		}

	}
}
