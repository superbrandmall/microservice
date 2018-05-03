package com.sbm.module.onlineleasing.customer.reservation.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.reservation.biz.IReservationService;
import com.sbm.module.onlineleasing.domain.reservation.Reservation;
import com.sbm.module.onlineleasing.domain.reservation.ReservationShopInfo;
import com.sbm.module.onlineleasing.domain.reservation.ReservationUserInfo;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/reservation")
public class ReservationController extends BaseController {

	@Autowired
	private IReservationService service;

	@ApiOperation(value = "预约用户信息", notes = "预约用户信息")
	@RequestMapping(value = "/user/{userCode}", method = RequestMethod.GET)
	public JsonContainer<ReservationUserInfo> getReservationUserInfo(@PathVariable @NotBlank String userCode) {
		return setSuccessMessage(service.getReservationUserInfo(userCode));
	}

	@ApiOperation(value = "预约铺位信息", notes = "预约铺位信息")
	@RequestMapping(value = "/shop/{shopCode}", method = RequestMethod.GET)
	public JsonContainer<ReservationShopInfo> getReservationShopInfo(@PathVariable @NotBlank String shopCode) {
		return setSuccessMessage(service.getReservationShopInfo(shopCode));
	}

	@ApiOperation(value = "预约", notes = "预约")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonContainer save(@RequestBody @Validated Reservation<String> vo) {
		service.save(vo);
		return setSuccessMessage();
	}

	@ApiOperation(value = "预约铺位明细", notes = "预约铺位明细")
	@RequestMapping(value = "/details/{userCode}", method = RequestMethod.GET)
	public JsonContainer<Page<Reservation<ReservationShopInfo>>> details(@PathVariable @NotBlank String userCode, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.getDetails(userCode, pageable));
	}

}
