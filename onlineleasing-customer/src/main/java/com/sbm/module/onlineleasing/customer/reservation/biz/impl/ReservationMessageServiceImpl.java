package com.sbm.module.onlineleasing.customer.reservation.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.message.api.mail.client.IMailClient;
import com.sbm.module.common.message.api.sms.client.ISMSClient;
import com.sbm.module.onlineleasing.customer.base.info.floor.biz.IFloorInfoService;
import com.sbm.module.onlineleasing.customer.base.info.mall.biz.IMallInfoService;
import com.sbm.module.onlineleasing.customer.reservation.biz.IReservationMessageService;
import com.sbm.module.onlineleasing.customer.reservation.biz.IReservationService;
import com.sbm.module.onlineleasing.domain.base.info.floor.FloorMinInfo;
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

	@Autowired
	private IMallInfoService mallInfoService;
	@Autowired
	private IFloorInfoService floorInfoService;

	private static SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

	/*********************************************/
	private static final String LEASING_MAIL_SUBJECT = "OnlineLeasing租赁预约信息邮件";
	@Value("${reservation.emp.leasing.name}")
	private String reservationEmpLeasingName;

	@Value("${reservation.emp.leasing.mail.templateCode}")
	private String reservationEmpLeasingMailTemplateCode;
	@Value("${reservation.emp.leasing.mail.email}")
	private String reservationEmpLeasingMailEMail;

	@Value("${reservation.emp.leasing.sms.templateCode}")
	private String reservationEmpLeasingSMSTemplateCode;
	@Value("${reservation.emp.leasing.sms.mobile}")
	private String reservationEmpLeasingSMSMobile;

	private static final String YEAR = "年";
	private static final String MONTH = "个月";

	/*********************************************/

	private static final String EVENT_MAIL_SUBJECT = "OnlineLeasing场地预约信息邮件";
	@Value("${reservation.emp.event.name}")
	private String reservationEmpEventName;

	@Value("${reservation.emp.event.mail.templateCode}")
	private String reservationEmpEventMailTemplateCode;
	@Value("${reservation.emp.event.mail.email}")
	private String reservationEmpEventMailEMail;

	@Value("${reservation.emp.event.sms.templateCode}")
	private String reservationEmpEventSMSTemplateCode;
	@Value("${reservation.emp.event.sms.mobile}")
	private String reservationEmpEventSMSMobile;

	private static final String DAY = "天";

	/*********************************************/

	@Value("${reservation.customer.sms.templateCode}")
	private String reservationCustomerSmsTemplateCode;
	@Value("${reservation.customer.sms_en.templateCode}")
	private String reservationCustomerSmsEnTemplateCode;

	@Override
	public void send(Reservation<String> vo, String lang) {
		// 现在不需要铺位也能预约
//		if (null == vo.getShops() || vo.getShops().isEmpty()) {
//			return;
//		}
		// 发送给顾客短信
		sendSMS2Customer(vo, lang);
		// 发送给员工消息
		send2Emp(vo);
	}

	private void sendSMS2Customer(Reservation<String> vo, String lang) {
		// TODO 后续再考虑总线
		Map<String, Object> model = new HashMap<>();
		model.put("userName", vo.getUserName());

		String mallName;
		String floorName;
		// 判断是否有铺位
		if (null != vo.getShops() && !vo.getShops().isEmpty()) {
			// 组织铺位信息
			ReservationShopInfo shopInfo = reservationService.getReservationShopInfo(vo.getShops().get(0));

			if ("en-us".equalsIgnoreCase(lang)) {
				mallName = mallInfoService.findOneByMallCode(shopInfo.getMallCode()).getMallNameEng();
				floorName = floorInfoService.findAll().stream().filter(e -> e.getFloorCode().equals(shopInfo.getFloorCode())).findFirst().orElse(new FloorMinInfo()).getDescriptionEng();
			} else {
				mallName = mallInfoService.findOneByMallCode(shopInfo.getMallCode()).getMallName();
				floorName = floorInfoService.findAll().stream().filter(e -> e.getFloorCode().equals(shopInfo.getFloorCode())).findFirst().orElse(new FloorMinInfo()).getDescription();
			}

			model.put("mallName", mallName);
			model.put("floorName", floorName);
			model.put("unit", shopInfo.getUnit());
			model.put("count", vo.getShops().size());
		}
		if ("en-us".equalsIgnoreCase(lang)) {
			checkJsonContainer(smsClient.sendByTemplate(new com.sbm.module.common.message.api.sms.domain.SendByTemplate(vo.getMobile(), null, new Date(), reservationCustomerSmsEnTemplateCode, model)));
		} else {
			checkJsonContainer(smsClient.sendByTemplate(new com.sbm.module.common.message.api.sms.domain.SendByTemplate(vo.getMobile(), null, new Date(), reservationCustomerSmsTemplateCode, model)));
		}
	}

	private void send2Emp(Reservation<String> vo) {
		// TODO 后续再考虑总线
		// 判断是否有铺位
		if (null != vo.getShops() && !vo.getShops().isEmpty()) {
			// 租赁（正柜）
			List<Map<String, String>> leasingShops = new ArrayList<>();
			// 租赁（kiosk）
			List<Map<String, String>> kioskShops = new ArrayList<>();
			// 场地
			List<Map<String, String>> eventShops = new ArrayList<>();
			// 组织铺位信息
			Map<String, String> shop;
			for (String shopCode : vo.getShops()) {
				shop = new HashMap<>();
				ReservationShopInfo shopInfo = reservationService.getReservationShopInfo(shopCode);
				shop.put("mallName", mallInfoService.findOneByMallCode(shopInfo.getMallCode()).getMallName());
				shop.put("floorName", floorInfoService.findAll().stream().filter(e -> e.getFloorCode().equals(shopInfo.getFloorCode())).findFirst().orElse(new FloorMinInfo()).getDescription());
				shop.put("unit", shopInfo.getUnit());
				if ("正柜".equals(shopInfo.getSubType())) {
					leasingShops.add(shop);
				} else if ("kiosk".equals(shopInfo.getSubType())){
					kioskShops.add(shop);
				} else if ("固定场地".equals(shopInfo.getSubType()) || "公共区域".equals(shopInfo.getSubType()) || "临时场地".equals(shopInfo.getSubType())) {
					eventShops.add(shop);
				} else {
					// 其他全给租赁
					leasingShops.add(shop);
				}
			}
			// 租赁（正柜）
			if (!leasingShops.isEmpty()) {
				sendMail2Emp(vo, reservationEmpLeasingName, leasingShops, reservationEmpLeasingMailEMail, LEASING_MAIL_SUBJECT, reservationEmpLeasingMailTemplateCode, YEAR);
				sendSMS2Emp(vo, reservationEmpLeasingName, leasingShops, reservationEmpLeasingSMSMobile, reservationEmpLeasingSMSTemplateCode);
			}
			// 租赁（kiosk）
			if (!kioskShops.isEmpty()) {
				sendMail2Emp(vo, reservationEmpLeasingName, kioskShops, reservationEmpLeasingMailEMail, LEASING_MAIL_SUBJECT, reservationEmpLeasingMailTemplateCode, MONTH);
				sendSMS2Emp(vo, reservationEmpLeasingName, kioskShops, reservationEmpLeasingSMSMobile, reservationEmpLeasingSMSTemplateCode);
			}
			// 场地
			if (!eventShops.isEmpty()) {
				sendMail2Emp(vo, reservationEmpEventName, eventShops, reservationEmpEventMailEMail, EVENT_MAIL_SUBJECT, reservationEmpEventMailTemplateCode, DAY);
				sendSMS2Emp(vo, reservationEmpEventName, eventShops, reservationEmpEventSMSMobile, reservationEmpEventSMSTemplateCode);
			}
		}
		// 没有铺位当租赁发
		else {
			sendMail2Emp(vo, reservationEmpLeasingName, null, reservationEmpLeasingMailEMail, LEASING_MAIL_SUBJECT, reservationEmpLeasingMailTemplateCode, YEAR);
			sendSMS2Emp(vo, reservationEmpLeasingName, null, reservationEmpLeasingSMSMobile, reservationEmpLeasingSMSTemplateCode);
		}
	}

	private void sendMail2Emp(Reservation<String> vo, String name, List<Map<String, String>> shops, String email, String subject, String templateCode, String units) {
		Map<String, Object> model = new HashMap<>();
		model.put("name", name);
		model.put("userName", vo.getUserName());
		if (null != shops && !shops.isEmpty()) {
			model.put("count", shops.size());
			model.put("shops", shops);
		}
		model.put("merchantName", vo.getMerchantName());
		model.put("mobile", vo.getMobile());
		model.put("brandName", vo.getBrandName());
		model.put("reserveTime", YYYYMMDD.format(vo.getReserveTime()));
		model.put("rentalLength", vo.getRentalLength());
		model.put("units", units);
		model.put("startDate", YYYYMMDD.format(vo.getStartDate()));
		checkJsonContainer(mailClient.sendByTemplate(new com.sbm.module.common.message.api.mail.domain.SendByTemplate(email, subject, null, new Date(), templateCode, model)));
	}

	private void sendSMS2Emp(Reservation<String> vo, String name, List<Map<String, String>> shops, String mobile, String templateCode) {
		Map<String, Object> model = new HashMap<>();
		model.put("name", name);
		model.put("userName", vo.getUserName());
		if (null != shops && !shops.isEmpty()) {
			model.put("mallName", shops.get(0).get("mallName"));
			model.put("floorName", shops.get(0).get("floorName"));
			model.put("unit", shops.get(0).get("unit"));
			model.put("count", vo.getShops().size());
		}
		model.put("mobile", vo.getMobile());
		checkJsonContainer(smsClient.sendByTemplate(new com.sbm.module.common.message.api.sms.domain.SendByTemplate(mobile, null, new Date(), templateCode, model)));
	}

}
