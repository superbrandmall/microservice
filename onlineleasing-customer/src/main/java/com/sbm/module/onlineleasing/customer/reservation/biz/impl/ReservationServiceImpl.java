package com.sbm.module.onlineleasing.customer.reservation.biz.impl;

import com.sbm.module.common.authorization.api.user.constant.UserConstant;
import com.sbm.module.common.authorization.exception.VerificationCodeErrorCode;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.common.redis.biz.IRedisService;
import com.sbm.module.common.redis.constant.RedisConstant;
import com.sbm.module.onlineleasing.base.reservation.biz.ITOLReservationService;
import com.sbm.module.onlineleasing.base.reservation.domain.TOLReservation;
import com.sbm.module.onlineleasing.base.reservationdetail.biz.ITOLReservationShopService;
import com.sbm.module.onlineleasing.base.reservationdetail.domain.TOLReservationShop;
import com.sbm.module.onlineleasing.customer.reservation.biz.IReservationMessageService;
import com.sbm.module.onlineleasing.customer.reservation.biz.IReservationService;
import com.sbm.module.onlineleasing.customer.shop.biz.IShopService;
import com.sbm.module.onlineleasing.customer.user.biz.IUserSimpleService;
import com.sbm.module.onlineleasing.customer.user.v2.biz.IUserInfoService;
import com.sbm.module.onlineleasing.customer.verify.biz.IVerifyService;
import com.sbm.module.onlineleasing.domain.reservation.Reservation;
import com.sbm.module.onlineleasing.domain.reservation.ReservationShopInfo;
import com.sbm.module.onlineleasing.domain.reservation.ReservationUserInfo;
import com.sbm.module.onlineleasing.exception.OnlineleasingCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl extends CommonServiceImpl implements IReservationService {

	@Autowired
	private ITOLReservationService reservationService;
	@Autowired
	private ITOLReservationShopService reservationShopService;

	@Autowired
	private IShopService shopService;
	@Autowired
	private IUserSimpleService userSimpleService;
	@Autowired
	private IUserInfoService userInfoService;

	@Autowired
	private IReservationMessageService reservationMessageService;

	@Autowired
	private IVerifyService verifyService;

	@Autowired
	private IRedisService redisService;

	private static SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 每天预约5次
	 */
	private static Integer LIMIT = 5;

	/******************** 信息 ********************/

	@Override
	public ReservationUserInfo getReservationUserInfo(String userCode) {
		// 使用新版
//		return mapOneIfNotNull(userSimpleService.getUserSimple(userCode), e -> {
//			ReservationUserInfo userInfo = new ReservationUserInfo();
//			userInfo.setUserCode(e.getCode());
//			userInfo.setUserName(e.getSettings().getName());
//			userInfo.setMobile(e.getMobile());
//			userInfo.setEmail(e.getEmail());
//			userInfo.setMerchantName(e.getMerchantName());
//			userInfo.setBrandName(e.getBrandName());
//			userInfo.setBrandModality(e.getModality());
//
//			userInfo.setMobileVerified(e.getMobileVerified());
//			userInfo.setEmailVerified(e.getEmailVerified());
//			return userInfo;
//		});
		return mapOneIfNotNull(userInfoService.getUserMerchant(userCode), e -> {
			ReservationUserInfo userInfo = new ReservationUserInfo();
			userInfo.setUserCode(e.getCode());
			userInfo.setUserName(e.getSettings().getName());
			userInfo.setMobile(e.getMobile());
			userInfo.setEmail(e.getEmail());
			userInfo.setMerchantName(e.getMerchantName());
			userInfo.setBrandCode(e.getBrandCode());
			userInfo.setBrandName(e.getBrandName());
			userInfo.setBrandModality(e.getBrandModality());

			userInfo.setMobileVerified(e.getMobileVerified());
			userInfo.setEmailVerified(e.getEmailVerified());
			return userInfo;
		});
	}

	@Override
	public ReservationShopInfo getReservationShopInfo(String shopCode) {
		return mapOneIfNotNull(shopService.findOneByCode(shopCode), e -> {
			ReservationShopInfo shopInfo = new ReservationShopInfo(e.getCode(), e.getState(), e.getUnit(), e.getMallCode(), e.getFloorCode(), e.getArea(), e.getModality(), e.getContractExpireDate(), e.getShopState(), e.getSubType());
			shopInfo.setFirstImage(shopService.getShopFirstImage(shopCode));
			return shopInfo;
		});
	}

	/******************** 预约 ********************/

	@Override
	@Transactional
	public void save(Reservation<String> vo, String lang) {
		String dt = YYYYMMDD.format(new Date());
		// 获取预约次数
		Integer count = get(getKey(vo.getUserCode(), dt));
		// 没有超过限制
		if (count < LIMIT) {
			// 手机验证
			if (UserConstant.VERIFIED_MOBILE.equalsIgnoreCase(vo.getVerificationCodeCheck().getVerifyType())) {
				verifyService.check(vo.getVerificationCodeCheck(), vo.getMobile());
			}
			// 邮箱验证
			else if (UserConstant.VERIFIED_EMAIL.equalsIgnoreCase(vo.getVerificationCodeCheck().getVerifyType())) {
				verifyService.check(vo.getVerificationCodeCheck(), vo.getEmail());
			}
			// 除此之外报错
			else {
				throw new BusinessException(VerificationCodeErrorCode.VC0004);
			}
			// 现在不需要铺位也能预约
			//if (null == vo.getShops() || vo.getShops().isEmpty()) {
			//throw new BusinessException(OnlineleasingCode.RE0001);
			//}
			// 保存预约
			TOLReservation po = reservationService.newInstance();
			po.setUserCode(vo.getUserCode());
			po.setUserName(vo.getUserName());
			po.setMobile(vo.getMobile());
			po.setEmail(vo.getEmail());
			po.setMerchantCode(vo.getMerchantCode());
			po.setMerchantName(vo.getMerchantName());
			po.setBrandCode(vo.getBrandCode());
			po.setBrandName(vo.getBrandName());
			po.setBrandModality(vo.getBrandModality());
			po.setReserveTime(vo.getReserveTime());
			po.setRentalLength(vo.getRentalLength());
			po.setStartDate(vo.getStartDate());
			po.setEndDate(vo.getEndDate());

			reservationService.save(po);
			// 判断是否有铺位
			if (null != vo.getShops() && !vo.getShops().isEmpty()) {
				// 保存预约铺位
				List<TOLReservationShop> reservationShops = new ArrayList<>();
				for (String shopCode : vo.getShops()) {
					reservationShops.add(new TOLReservationShop(po.getCode(), shopCode));
				}
				reservationShopService.save(reservationShops);
			}
			reservationMessageService.send(vo, lang);
			// 更新预约次数
			set(getKey(vo.getUserCode(), dt), count + 1);
		}
		// 超过限制
		else {
			throw new BusinessException(OnlineleasingCode.RE0002, new Object[]{vo.getUserCode(), dt, LIMIT});
		}
	}

	private String getKey(String userCode, String dt) {
		return RedisConstant.getKey(Reservation.class, userCode, dt);
	}

	private Integer get(String key) {
		Integer count = (Integer) redisService.get(key);
		if (null == count) {
			count = 0;
		}
		return count;
	}

	private void set(String key, Integer count) {
		// 存入redis
		redisService.set2RedisOneDay(key, count);
	}

	/******************** 预约明细 ********************/

	@Override
	public Page<Reservation<ReservationShopInfo>> getDetails(String userCode, Pageable pageable) {
		return reservationService.findAllByUserCode(userCode, pageable).map(e -> {
			Reservation<ReservationShopInfo> vo = new Reservation<>(e.getUserCode(), e.getUserName(), e.getMobile(), e.getEmail(),
					e.getMerchantCode(), e.getMerchantName(), e.getBrandCode(), e.getBrandName(), e.getBrandModality(),
					e.getReserveTime(), e.getRentalLength(), e.getStartDate(), e.getEndDate());
			vo.setShops(map(reservationShopService.findAllByReservationCode(e.getCode()), s -> getReservationShopInfo(s.getShopCode())));
			// 添加记录创建时间，便于前台展示
			vo.setCreated(e.getCreated());
			return vo;
		});
	}
}
