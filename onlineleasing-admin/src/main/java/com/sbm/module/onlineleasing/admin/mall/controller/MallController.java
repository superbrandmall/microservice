package com.sbm.module.onlineleasing.admin.mall.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.admin.mall.biz.IMallService;
import com.sbm.module.onlineleasing.domain.mall.Mall;
import com.sbm.module.onlineleasing.domain.mall.MallMaxInfo;
import com.sbm.module.onlineleasing.domain.mall.MallQuery;
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
@RequestMapping("/api/mall")
public class MallController extends BaseController {

	@Autowired
	private IMallService service;

	@ApiOperation(value = "分页查询", notes = "分页查询")
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	public JsonContainer<Page<Mall>> findAll(@RequestBody MallQuery query, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAll(query, pageable));
	}

	@ApiOperation(value = "项目信息", notes = "项目信息")
	@RequestMapping(value = "/{mallCode}", method = RequestMethod.GET)
	public JsonContainer<MallMaxInfo> findOneByMallCode(@PathVariable @NotBlank String mallCode) {
		return setSuccessMessage(service.findOneByMallCode(mallCode));
	}

	@ApiOperation(value = "保存项目信息", notes = "保存项目信息")
	@RequestMapping(value = "/save", method = RequestMethod.PUT)
	public JsonContainer save(@RequestBody @Validated MallMaxInfo mallMaxInfo) {
		service.save(mallMaxInfo);
		return setSuccessMessage();
	}

}
