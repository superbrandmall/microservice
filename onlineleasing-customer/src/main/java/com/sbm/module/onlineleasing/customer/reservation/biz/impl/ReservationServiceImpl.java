package com.sbm.module.onlineleasing.customer.reservation.biz.impl;

import com.sbm.module.common.authorization.api.user.domain.User;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.onlineleasing.base.reservation.biz.ITOLReservationService;
import com.sbm.module.onlineleasing.base.reservation.domain.TOLReservation;
import com.sbm.module.onlineleasing.customer.brand.biz.IBrandService;
import com.sbm.module.onlineleasing.customer.reservation.biz.IReservationMessageService;
import com.sbm.module.onlineleasing.customer.reservation.biz.IReservationService;
import com.sbm.module.onlineleasing.customer.shop.biz.IShopService;
import com.sbm.module.onlineleasing.customer.user.biz.IUserService;
import com.sbm.module.onlineleasing.domain.reservation.Reservation;
import com.sbm.module.onlineleasing.domain.reservation.ReservationResult;
import com.sbm.module.onlineleasing.domain.shop.Shop;
import com.sbm.module.onlineleasing.domain.user.UserMerchant;
import com.sbm.module.onlineleasing.exception.OnlineleasingCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl extends CommonServiceImpl implements IReservationService {

	@Autowired
	private ITOLReservationService reservationService;

	@Autowired
	private IShopService shopService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IBrandService brandService;

	@Autowired
	private IReservationMessageService reservationMessageService;

	@Override
	public Page<ReservationResult> getDetails(String userCode, Pageable pageable) {
		return reservationService.findAllByUserCode(userCode, pageable).map(e -> {
			ReservationResult vo = new ReservationResult(e.getMallCode(), e.getMallName(), e.getFloorCode(), e.getFloorName(), e.getShopCode(), e.getUnit(), e.getArea(), e.getModality(),
					e.getUserCode(), e.getUserName(), e.getMobile(), e.getMerchantCode(), e.getMerchantName(),
					e.getDeadRent(), e.getFloatingRentalRate(), e.getPromotionBudget(), e.getMaintenanceDuringDecoration(), e.getMaintenanceAfterDecoration(), e.getGurantee(),
					e.getBrandCode(), e.getBrandName(), e.getReserveTime(), e.getRentalLength(), e.getStartDate(), e.getEndDate());
			vo.setFirstImage(shopService.getShopFirstImage(vo.getShopCode()));
			return vo;
		});
	}

	@Override
	public Reservation findOneByShopCodeAndUserCode(String shopCode, String userCode) {
		return mapOneIfNotNull(shopService.findOneByCode(shopCode), e -> {
			Reservation vo = convert(e,
					checkIfNullThrowException(userService.getUserMerchant(userCode), new BusinessException(OnlineleasingCode.U0003, new Object[]{userCode})),
					checkIfNullThrowException(userService.findUserByUserCode(userCode), new BusinessException(OnlineleasingCode.U0003, new Object[]{userCode})));
			vo.setFirstImage(shopService.getShopFirstImage(vo.getShopCode()));
			return vo;
		});
	}

	private Reservation convert(Shop e, UserMerchant userMerchant, User user) {
		return new Reservation(e.getMallCode(), e.getMallName(), e.getFloorCode(), e.getFloorName(), e.getCode(), e.getUnit(), e.getArea(), e.getModality(),
				userMerchant.getUserCode(), user.getSettings().getName(), user.getMobile(), userMerchant.getMerchantCode(), userMerchant.getMerchantName(),
				// 只传固定租金浮动扣率，其他的暂时页面算
				e.getDeadRent(), e.getFloatingRentalRate(), null, null, null, null,
				e.getContractExpireDate(), e.getShopState());
	}

	@Override
	public void save(ReservationResult vo) {
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
		po.setUserName(vo.getUserName());
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

		reservationMessageService.send(vo);
	}
}
