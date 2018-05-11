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

	private static final String LEASING_MAIL_SUBJECT = "OnlineLeasing租赁预约信息邮件";
	@Value("${reservation.leasing.mail.templateCode}")
	private String reservationLeasingMailTemplateCode;
	@Value("${reservation.leasing.mail.name}")
	private String reservationLeasingMailName;
	@Value("${reservation.leasing.mail.email}")
	private String reservationLeasingMailEMail;

	private static final String EVENT_MAIL_SUBJECT = "OnlineLeasing场地预约信息邮件";
	@Value("${reservation.event.mail.templateCode}")
	private String reservationEventMailTemplateCode;
	@Value("${reservation.event.mail.name}")
	private String reservationEventMailName;
	@Value("${reservation.event.mail.email}")
	private String reservationEventMailEMail;



	@Value("${reservation.customer.sms.templateCode}")
	private String reservationCustomerSmsTemplateCode;

	@Override
	public void send(Reservation<String> vo) {
		if (null == vo.getShops() || vo.getShops().isEmpty()) {
			return;
		}
		// 发送短信给顾客
		sendSMS2Customer(vo);
		sendMail2Emp(vo);
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

	private void sendMail2Emp(Reservation<String> vo) {
		// 发送邮件 TODO 后续再考虑总线
		// 租赁
		List<Map<String, String>> leasingShops = new ArrayList<>();
		// 场地
		List<Map<String, String>> eventShops = new ArrayList<>();

		Map<String, String> shop;
		for (String shopCode : vo.getShops()) {
			shop = new HashMap<>();
			ReservationShopInfo shopInfo = reservationService.getReservationShopInfo(shopCode);
			shop.put("mallName", shopInfo.getMallName());
			shop.put("floorName", shopInfo.getFloorName());
			shop.put("unit", shopInfo.getUnit());

			if ("正柜".equals(shopInfo.getSubType())) {
				leasingShops.add(shop);
			} else if ("固定场地".equals(shopInfo.getSubType()) || "公共区域".equals(shopInfo.getSubType()) || "临时场地".equals(shopInfo.getSubType())) {
				eventShops.add(shop);
			}
		}

		// 租赁
		if (!leasingShops.isEmpty()) {
			sendMail2Emp(vo, reservationLeasingMailName, leasingShops, reservationLeasingMailEMail, LEASING_MAIL_SUBJECT, reservationLeasingMailTemplateCode);
		}
		// 场地
		if (!eventShops.isEmpty()) {
			sendMail2Emp(vo, reservationEventMailEMail, leasingShops, reservationEventMailName, EVENT_MAIL_SUBJECT, reservationEventMailTemplateCode);
		}
	}

	private void sendMail2Emp(Reservation<String> vo, String name, List<Map<String, String>> shops, String email, String subject, String templateCode ){
		Map<String, Object> model = new HashMap<>();
		model.put("name", name);
		model.put("userName", vo.getUserName());
		model.put("count", shops.size());
		model.put("shops", shops);
		model.put("merchantName", vo.getMerchantName());
		model.put("mobile", vo.getMobile());
		model.put("brandName", vo.getBrandName());
		model.put("reserveTime", YYYYMMDD.format(vo.getReserveTime()));
		model.put("rentalLength", vo.getRentalLength());
		model.put("startDate", YYYYMMDD.format(vo.getStartDate()));
		checkJsonContainer(mailClient.sendByTemplate(new com.sbm.module.common.message.api.mail.domain.SendByTemplate(email, subject, null, new Date(), templateCode, model)));
	}

}
