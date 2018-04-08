package com.sbm.module.onlineleasing.interactive.bid.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.interactive.bid.biz.IBidService;
import com.sbm.module.onlineleasing.interactive.bid.domain.ApproveResult;
import com.sbm.module.onlineleasing.interactive.bid.domain.BidResult;
import com.sbm.module.onlineleasing.interactive.bid.domain.EffectResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/bid")
public class BidController extends BaseController {

	@Autowired
	private IBidService service;

	@ApiOperation(value = "审批结果接口", notes = "合同保证金缴纳，收到纸质盖章合同后，合同生效，返回生效合同和非生效合同列表，给到OL")
	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	public JsonContainer approve(@RequestBody @Validated BidResult<ApproveResult> vo) {
		service.approve(vo);
		return setSuccessMessage();
	}

	@ApiOperation(value = "出价生效接口", notes = "合同保证金缴纳，收到纸质盖章合同后，合同生效，返回生效合同和非生效合同列表，给到OL")
	@RequestMapping(value = "/effect", method = RequestMethod.POST)
	public JsonContainer effect(@RequestBody @Validated BidResult<EffectResult> vo) {
		service.effect(vo);
		return setSuccessMessage();
	}

}
