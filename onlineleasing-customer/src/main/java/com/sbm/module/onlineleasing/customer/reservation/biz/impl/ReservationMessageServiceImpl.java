package com.sbm.module.onlineleasing.customer.reservation.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.message.api.mail.client.IMailClient;
import com.sbm.module.common.message.api.sms.client.ISMSClient;
import com.sbm.module.onlineleasing.customer.reservation.biz.IReservationMessageService;
import com.sbm.module.onlineleasing.customer.reservation.biz.IReservationService;
import com.sbm.module.onlineleasing.domain.reservation.Reservation;
import com.sbm.module.onlineleasing.domain.reservation.ReservationShopInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ReservationMessageServiceImpl extends CommonServiceImpl implements IReservationMessageService {

	@Autowired
	private IMailClient mailClient;
	@Autowired
	private ISMSClient smsClient;
	@Autowired
	private IReservationService reservationService;

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
	public void send(Reservation<String> vo) {
		if (null == vo.getShops() || vo.getShops().isEmpty()) {
			return;
		}
		// 发送短信给顾客
		sendSMS2Customer(vo);
		sendMail2Leasing(vo);
	}

	private void sendSMS2Customer(Reservation<String> vo) {
		// 发送邮件 TODO 后续再考虑总线
		Map<String, Object> model = new HashMap<>();
		model.put("userName", vo.getUserName());

		ReservationShopInfo shopInfo = reservationService.getReservationShopInfo(vo.getShops().get(0));
		model.put("mallName", shopInfo.getMallName());
		model.put("floorName", shopInfo.getFloorName());
		model.put("unit", shopInfo.getUnit());

		model.put("count", vo.getShops().size());
		checkJsonContainer(smsClient.sendByTemplate(new com.sbm.module.common.message.api.sms.domain.SendByTemplate(vo.getMobile(), null, new Date(), reservationCustomerSmsTemplateCode, model)));
	}

	private void sendMail2Leasing(Reservation<String> vo) {
		// 发送邮件 TODO 后续再考虑总线
		Map<String, Object> model = new HashMap<>();
		model.put("leasingName", reservationLeasingMailLeasingName);
		model.put("userName", vo.getUserName());

		model.put("count", vo.getShops().size());

		List<Map<String, String>> shops = new ArrayList<>();
		Map<String, String> shop;
		for (String shopCode : vo.getShops()) {
			shop = new HashMap<>();
			ReservationShopInfo shopInfo = reservationService.getReservationShopInfo(shopCode);
			shop.put("mallName", shopInfo.getMallName());
			shop.put("floorName", shopInfo.getFloorName());
			shop.put("unit", shopInfo.getUnit());
			shops.add(shop);
		}
		model.put("shops", shops);

		model.put("merchantName", vo.getMerchantName());
		model.put("mobile", vo.getMobile());
		model.put("brandName", vo.getBrandName());
		model.put("reserveTime", YYYYMMDD.format(vo.getReserveTime()));
		model.put("rentalLength", vo.getRentalLength());
		model.put("startDate", YYYYMMDD.format(vo.getStartDate()));
		checkJsonContainer(mailClient.sendByTemplate(new com.sbm.module.common.message.api.mail.domain.SendByTemplate(reservationLeasingMailLeasingMail, MAIL_SUBJECT, null, new Date(), reservationLeasingMailTemplateCode, model)));
	}
}
