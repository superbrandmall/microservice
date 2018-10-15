package com.sbm.module.common.message.api.sms.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.message.api.sms.biz.ISMSService;
import com.sbm.module.common.message.api.sms.domain.SMS;
import com.sbm.module.common.message.api.sms.domain.SendByTemplate;
import com.sbm.module.common.message.api.template.biz.ITemplateService;
import com.sbm.module.common.message.api.template.domain.Template;
import com.sbm.module.common.message.base.smssenddetail.biz.ITCSMSSendDetailService;
import com.sbm.module.common.message.base.smssenddetail.constant.SMSConstant;
import com.sbm.module.common.message.base.smssenddetail.domain.TCSMSSendDetail;
import com.sbm.module.common.util.CodecUtil;
import com.sbm.module.partner.hl95.rest.sms.client.IHl95Client;
import com.sbm.module.partner.hl95.rest.sms.constant.SMSCode;
import com.sbm.module.partner.hl95.rest.sms.domain.SMSResult;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Date;

@Service
@Slf4j
public class SMSServiceImpl extends CommonServiceImpl implements ISMSService {

	@Autowired
	private ITemplateService templateService;

	@Autowired
	private ITCSMSSendDetailService smsSendDetailService;

	@Autowired
//	private Hl95RestTemplateClient client;
	private IHl95Client client;

	@Value("${sms.hl95.username}")
	private String username;
	@Value("${sms.hl95.password}")
	private String password;
	@Value("${sms.hl95.epid}")
	private String epid;

	private static final String ERROR_MESSAGE = "短信发送失败";
	private static final String ERR_IP = "ERR IP";
	private static final String PREFIX = "C";
	private static final String MISSING_MESSAGE = "hl95 sendSMS returnCode is unrecognized ";

	@Async
	@Override
	public void send(SMS vo) {
		TCSMSSendDetail po = convert(vo);
		try {
			// 发送
			SMSResult result = prepareAndSend(vo);
			po.setText(vo.getMessage());
			po.setResultCode(result.getResultCode());
			po.setResultMessage(result.getResultMessage());
			po.setType(SMSConstant.SUCCESS);
		} catch (Exception ex) {
			log.error(ERROR_MESSAGE, ex);
			po.setType(SMSConstant.FAILURE);
		}
		smsSendDetailService.save(po);
	}

	@Async
	@Override
	public void sendByTemplate(SendByTemplate vo) {
		TCSMSSendDetail po = convert(vo);
		po.setTemplateCode(vo.getCode());
		try {
			// 模板结果
			vo.setMessage(templateService.processTemplateIntoString(new Template(vo.getCode(), vo.getModel())));
			// 发送
			SMSResult result = prepareAndSend(vo);
			po.setText(vo.getMessage());
			po.setResultCode(result.getResultCode());
			po.setResultMessage(result.getResultMessage());
			po.setType(SMSConstant.SUCCESS);
		} catch (Exception ex) {
			log.error(ERROR_MESSAGE, ex);
			po.setType(SMSConstant.FAILURE);
		}
		smsSendDetailService.save(po);
	}

	private TCSMSSendDetail convert(SMS vo) {
		TCSMSSendDetail po = smsSendDetailService.newInstance();
		po.setSentTo(vo.getTo());
		if (null == vo.getDate()) vo.setDate(new Date());
		po.setSentDate(vo.getDate());
		return po;
	}

	/**
	 * 准备并发送短信
	 *
	 * @param vo
	 */
	@SneakyThrows
	protected SMSResult prepareAndSend(SMS vo) {
		// 暂时使用RestTemplate实现，后续优化为Feign
//		return analysis(client.send(username, CodecUtil.md5Hex(password), epid, vo.getTo(), new String(vo.getMessage().getBytes("utf-8"), "gb2312"), StringUtils.EMPTY, StringUtils.EMPTY));
		return analysis(client.send(username, CodecUtil.md5Hex(password), epid, vo.getTo(), URLEncoder.encode(vo.getMessage(), "gb2312"), StringUtils.EMPTY, StringUtils.EMPTY));
	}

	/**
	 * 解析返回结果
	 *
	 * @param resultCode
	 * @return
	 */
	private SMSResult analysis(String resultCode) {
		SMSResult result = new SMSResult();
		result.setResultCode(resultCode);
		if (resultCode.contains(ERR_IP)) {
			result.setResultMessage(SMSCode.CERRIP.getReturnMessage());
		} else {
			try {
				SMSCode code = SMSCode.valueOf(PREFIX + resultCode);
				result.setResultMessage(code.getReturnMessage());
			} catch (Exception e) {
				log.warn(MISSING_MESSAGE);
			}
		}
		return result;
	}
}
