package com.sbm.module.onlineleasing.init.merchant.biz.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.io.Files;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.constant.CommonConstant;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.common.redis.constant.RedisConstant;
import com.sbm.module.onlineleasing.base.merchant.biz.ITOLMerchantService;
import com.sbm.module.onlineleasing.base.merchant.domain.TOLMerchant;
import com.sbm.module.onlineleasing.file.upload.client.IUploadClient;
import com.sbm.module.onlineleasing.file.upload.constant.UploadConstant;
import com.sbm.module.onlineleasing.file.upload.domain.UploadResult;
import com.sbm.module.onlineleasing.init.merchant.biz.IMerchantService;
import com.sbm.module.onlineleasing.init.merchant.domain.MerchantCheck;
import com.sbm.module.partner.tianyancha.rest.api736.client.IApi736Client;
import com.sbm.module.partner.tianyancha.rest.api736.domain.Api736;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MerchantServiceImpl extends CommonServiceImpl implements IMerchantService {

	@Autowired
	private ITOLMerchantService merchantService;
	@Autowired
	private IApi736Client api736Client;
	@Autowired
	private IRedisService redisService;
	@Autowired
	private IUploadClient uploadClient;

	private static SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

	private static final String COMMA = ",";

	private static final String NEWLINE = "\n";

	public final static String TEXT_CSV = "text/csv";

	@Override
	public List<MerchantCheck> init() {
		List<MerchantCheck> merchantChecks;
		String valuer = (String) redisService.get(RedisConstant.getKey(MerchantCheck.class, RedisConstant.LIST));
		if (StringUtils.isNotBlank(valuer)) {
			merchantChecks = JSON.parseArray(valuer, MerchantCheck.class);
		} else {
			merchantChecks = new ArrayList<>();
			MerchantCheck merchantCheck;
			boolean b;
			List<TOLMerchant> merchants = merchantService.findAllByTianyanchaIdIsNull();
			for (TOLMerchant merchant : merchants) {
				merchantCheck = new MerchantCheck(merchant.getUscc(), merchant.getName());
				// 检查统一信用代码
				b = checkUscc(merchantCheck);
				if (b) {
					// 查询天眼查
					Api736 result = api736Client.getCompanyByCode(merchantCheck.getUscc()).getResult();
					if (null != result) {
						merchantCheck.setTianyanchaName(result.getName());
					}
					// 检查名称
					b = checkName(merchantCheck);
					// 更新天眼查id
					if (b) {
						merchant.setTianyanchaId(result.getId());
						merchantService.save(merchant);
					}
				}
				if (!b) {
					merchantChecks.add(merchantCheck);
				}
			}
			redisService.set2Redis(RedisConstant.getKey(MerchantCheck.class, RedisConstant.LIST), JSON.toJSONString(merchantChecks));
		}
		return merchantChecks;
	}

	/**
	 * 检查名称
	 *
	 * @param merchantCheck
	 * @return
	 */
	private boolean checkName(MerchantCheck merchantCheck) {
		boolean b = true;
		if (StringUtils.isBlank(merchantCheck.getTianyanchaName())) {
			merchantCheck.setReason("没有查询到商户，请检查社会统一信用代码");
			b = false;
		}
		if (b && !merchantCheck.getTianyanchaName().equals(merchantCheck.getDbName())) {
			merchantCheck.setReason("商户名称不匹配，请确认");
			b = false;
		}
		return b;
	}

	/**
	 * 检查统一信用代码
	 *
	 * @param merchantCheck
	 * @return
	 */
	private boolean checkUscc(MerchantCheck merchantCheck) {
		boolean b = true;
		if (StringUtils.isBlank(merchantCheck.getUscc())) {
			merchantCheck.setReason("社会统一信用代码为空");
			b = false;
		}
		if (b && 18 != merchantCheck.getUscc().length()) {
			merchantCheck.setReason("社会统一信用代码不是18位");
			b = false;
		}
		return b;
	}

	@Override
	@SneakyThrows
	public String initDownload() {
		List<MerchantCheck> merchantChecks = init();
		StringBuffer sb = new StringBuffer("社会统一信用代码,数据库名称,天眼查名称,原因\n");
		for (MerchantCheck merchantCheck : merchantChecks) {
			sb.append(merchantCheck.getUscc()).append(COMMA)
					.append(merchantCheck.getDbName()).append(COMMA)
					.append(merchantCheck.getTianyanchaName()).append(COMMA)
					.append(merchantCheck.getReason()).append(NEWLINE);
		}
		String date = YYYYMMDD.format(new Date());
		String filename = "商户校验" + date + ".csv";
		File tmpFile = new File("F:/home/microservice/tmp/" + filename);
		Files.write(sb.toString().getBytes("GB2312"), tmpFile);
		MultipartFile file = new MockMultipartFile("file", filename, TEXT_CSV, new FileInputStream(tmpFile));
		JsonContainer<List<UploadResult>> result = uploadClient.upload(file, CommonConstant.SYSTEM, UploadConstant.CONTAINER_NAME_DEFAULT,
				MessageFormat.format(UploadConstant.PREFIX_DEFAULT, date, UploadConstant.CSV, UploadConstant.MERCHANT_CHECK));
		checkJsonContainer(result);
		tmpFile.delete();
		return result.getData().get(0).getUri();
	}
}

