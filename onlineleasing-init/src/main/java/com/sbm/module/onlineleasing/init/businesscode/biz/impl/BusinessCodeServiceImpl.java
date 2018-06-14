package com.sbm.module.onlineleasing.init.businesscode.biz.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.sbm.module.common.authorization.api.businesscode.client.IBusinessCodeClient;
import com.sbm.module.common.authorization.api.businesscode.domain.BusinessCode;
import com.sbm.module.common.authorization.api.businesscode.domain.BusinessCodeLang;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.init.businesscode.biz.IBusinessCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BusinessCodeServiceImpl extends CommonServiceImpl implements IBusinessCodeService {

	@Autowired
	private IBusinessCodeClient client;

	private static final String ERROR_MESSAGE = "业务代码初始化异常";
	private static final String WARN_MESSAGE = "";

	@Override
	public void init(String path) {
		// 根目录
		File file = new File(path);
		try {
			List<String> lines = Files.readLines(file, Charsets.UTF_8);
			process(lines);
		} catch (Exception ex) {
			log.error(ERROR_MESSAGE, ex);
		}
	}

	private void process(List<String> lines) {
		List<BusinessCode> vos = new ArrayList<>();
		String[] args;
		for (String line : lines) {
			args = line.split("\",\"");
			if (args.length == 6) {
				process(vos, args[0].replace("\"", ""), args[1], args[2], args[3], args[4], args[5].replace("\"", ""));
			}
		}
		List<BusinessCode> result = checkJsonContainer(client.register(vos));
		log(result);
	}

	private void process(List<BusinessCode> vos, String businessClazz, String businessCode, String message, String customerMessage, String lang, String customerMessageLang) {
		BusinessCode vo = vos.stream().filter(e -> businessClazz.equals(e.getBusinessClazz()) && businessCode.equals(e.getBusinessCode())).findFirst().orElse(null);
		if (null == vo) {
			vo = new BusinessCode(businessClazz, businessCode, message, customerMessage);
			vos.add(vo);
		}
		vo.getBusinessCodeLangs().add(new BusinessCodeLang(lang, customerMessageLang));
	}

	/**
	 * 记录日志
	 *
	 * @param result
	 */
	private void log(List<BusinessCode> result) {
		for (BusinessCode vo : result) {
			log.info(JSON.toJSONString(vo));
		}
	}

}
