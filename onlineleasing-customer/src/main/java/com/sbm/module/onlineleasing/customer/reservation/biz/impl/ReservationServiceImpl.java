package com.sbm.module.onlineleasing.customer.reservation.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.base.reservation.biz.ITOLReservationService;
import com.sbm.module.onlineleasing.base.reservation.domain.TOLReservation;
import com.sbm.module.onlineleasing.customer.reservation.biz.IReservationService;
import com.sbm.module.onlineleasing.customer.shop.biz.IShopService;
import com.sbm.module.onlineleasing.customer.user.biz.IUserService;
import com.sbm.module.onlineleasing.domain.reservation.Reservation;
import com.sbm.module.onlineleasing.domain.reservation.ReservationMinInfo;
import com.sbm.module.onlineleasing.domain.shop.Shop;
import com.sbm.module.onlineleasing.domain.user.UserMerchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationServiceImpl extends CommonServiceImpl implements IReservationService {

	@Autowired
	private ITOLReservationService reservationService;

	@Autowired
	private IShopService shopService;
	@Autowired
	private IUserService userService;

	@Override
	public Page<Reservation> getDetails(String userCode, Pageable pageable) {
		return reservationService.findAllByUserCode(userCode, pageable).map(e -> {
			Reservation vo = new Reservation(e.getMallCode(), e.getMallName(), e.getFloorCode(), e.getFloorName(), e.getShopCode(), e.getUnit(), e.getArea(), e.getModality(),
					e.getUserCode(), e.getMobile(), e.getMerchantCode(), e.getMerchantName(),
					e.getDeadRent(), e.getFloatingRentalRate(), e.getPromotionBudget(), e.getMaintenanceDuringDecoration(), e.getMaintenanceAfterDecoration(), e.getGurantee(),
					e.getBrandCode(), e.getBrandName(), e.getReserveTime(), e.getRentalLength(), e.getStartDate(), e.getEndDate());
			vo.setFirstImage(shopService.getShopFirstImage(vo.getShopCode()));
			return vo;
		});
	}

	@Override
	public ReservationMinInfo findOneByShopCodeAndUserCode(String shopCode, String userCode) {
		return mapOneIfNotNull(shopService.findOneByCode(shopCode), e -> {
			ReservationMinInfo vo = convert(e, userService.getUserMerchant(userCode));
			vo.setFirstImage(shopService.getShopFirstImage(vo.getShopCode()));
			return vo;
		});
	}

	private ReservationMinInfo convert(Shop e, UserMerchant userMerchant) {
		return new ReservationMinInfo(e.getMallCode(), e.getMallName(), e.getFloorCode(), e.getFloorName(), e.getCode(), e.getUnit(), e.getArea(), e.getModality(),
				userMerchant.getUserCode(), null, userMerchant.getMerchantCode(), userMerchant.getMerchantName(),
				// 只传固定租金浮动扣率，其他的暂时页面算
				e.getDeadRent(), e.getFloatingRentalRate(), null, null, null, null);
	}

	@Override
	@Transactional
	public void save(Reservation vo) {
		TOLReservation po = reservationService.newInstance();

		po.setMallCode(vo.getMallCode());
		po.setMallName(vo.getMallName());
		po.setFloorCode(vo.getFloorCode());
		po.setFloorName(vo.getFloorName());
		po.setShopCode(vo.getShopCode());
		po.setUnit(vo.getUnit());
		po.setArea(vo.getArea());
		po.setModality(vo.getModality());

		po.setUserCode(vo.getUserCode());
		po.setMobile(vo.getMobile());
		po.setMerchantCode(vo.getMerchantCode());
		po.setMerchantName(vo.getMerchantName());

		po.setDeadRent(vo.getDeadRent());
		po.setFloatingRentalRate(vo.getFloatingRentalRate());
		po.setPromotionBudget(vo.getPromotionBudget());
		po.setMaintenanceDuringDecoration(vo.getMaintenanceDuringDecoration());
		po.setMaintenanceAfterDecoration(vo.getMaintenanceAfterDecoration());
		po.setGurantee(vo.getGurantee());

		po.setBrandCode(vo.getBrandCode());
		po.setBrandName(vo.getBrandName());
		po.setReserveTime(vo.getReserveTime());
		po.setRentalLength(vo.getRentalLength());
		po.setStartDate(vo.getStartDate());
		po.setEndDate(vo.getEndDate());

		reservationService.save(po);
	}
}
