package com.sbm.module.onlineleasing.customer.reservation.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.customer.reservation.biz.IReservationService;
import com.sbm.module.onlineleasing.domain.reservation.Reservation;
import com.sbm.module.onlineleasing.domain.reservation.ReservationMinInfo;
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

	@ApiOperation(value = "预约铺位明细", notes = "预约铺位明细")
	@RequestMapping(value = "/details/{userCode}", method = RequestMethod.GET)
	public JsonContainer<Page<Reservation>> details(@PathVariable @NotBlank String userCode, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.getDetails(userCode, pageable));
	}

	@ApiOperation(value = "铺位信息", notes = "铺位信息")
	@RequestMapping(value = "/{shopCode}", method = RequestMethod.GET)
	public JsonContainer<ReservationMinInfo> findOneByShopCodeAndUserCode(@PathVariable @NotBlank String shopCode, @RequestParam @NotBlank String userCode) {
		return setSuccessMessage(service.findOneByShopCodeAndUserCode(shopCode, userCode));
	}

	@ApiOperation(value = "预约", notes = "预约")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonContainer save(@RequestBody @Validated Reservation vo) {
		service.save(vo);
		return setSuccessMessage();
	}

}
