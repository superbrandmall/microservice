package com.sbm.module.onlineleasing.customer.reservation.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.message.api.mail.client.IMailClient;
import com.sbm.module.common.message.api.sms.client.ISMSClient;
import com.sbm.module.onlineleasing.customer.reservation.biz.IReservationMessageService;
import com.sbm.module.onlineleasing.domain.reservation.ReservationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ReservationMessageServiceImpl extends CommonServiceImpl implements IReservationMessageService {

	@Autowired
	private IMailClient mailClient;
	@Autowired
	private ISMSClient smsClient;

	private static SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

	private static final String MAIL_SUBJECT = "OnlineLeasing租赁预约信息邮件";
	@Value("${reservation.leasing.mail.templateCode}")
	private String reservationLeasingMailTemplateCode;
	@Value("${reservation.leasing.mail.leasingName}")
	private String reservationLeasingMailLeasingName;
	@Value("${reservation.leasing.mail.leasingMail}")
	private String reservationLeasingMailLeasingMail;


	@Value("${reservation.customer.sms.templateCode}")
	private String reservationCustomerSmsTemplateCode;

	@Override
	public void send(ReservationResult vo) {
		// 发送短信给顾客
		sendSMS2Customer(vo);
		sendMail2Leasing(vo);
	}

	private void sendSMS2Customer(ReservationResult vo) {
		// 发送邮件 TODO 后续再考虑总线
		Map<String, Object> model = new HashMap<>();
		model.put("userName", vo.getUserName());
		model.put("mallName", vo.getMallName());
		model.put("floorName", vo.getFloorName());
		model.put("unit", vo.getUnit());
		checkJsonContainer(smsClient.sendByTemplate(new com.sbm.module.common.message.api.sms.domain.SendByTemplate(vo.getMobile(), null, new Date(), reservationCustomerSmsTemplateCode, model)));
	}

	private void sendMail2Leasing(ReservationResult vo) {
		// 发送邮件 TODO 后续再考虑总线
		Map<String, Object> model = new HashMap<>();
		model.put("leasingName", reservationLeasingMailLeasingName);
		model.put("userName", vo.getUserName());
		model.put("mallName", vo.getMallName());
		model.put("floorName", vo.getFloorName());
		model.put("unit", vo.getUnit());
		model.put("merchantName", vo.getMerchantName());
		model.put("mobile", vo.getMobile());
		model.put("brandName", vo.getBrandName());
		model.put("reserveTime", YYYYMMDD.format(vo.getReserveTime()));
		model.put("rentalLength", vo.getRentalLength());
		model.put("startDate", YYYYMMDD.format(vo.getStartDate()));
		checkJsonContainer(mailClient.sendByTemplate(new com.sbm.module.common.message.api.mail.domain.SendByTemplate(reservationLeasingMailLeasingMail, MAIL_SUBJECT, null, new Date(), reservationLeasingMailTemplateCode, model)));
	}
}
