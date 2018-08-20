package com.sbm.module.onlineleasing.admin.brand.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.admin.brand.biz.IBrandService;
import com.sbm.module.onlineleasing.domain.brand.Brand;
import com.sbm.module.onlineleasing.domain.brand.BrandMinInfo;
import com.sbm.module.onlineleasing.domain.brand.BrandQuery;
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
@RequestMapping("/api/brand")
public class BrandController extends BaseController {

	@Autowired
	private IBrandService service;

	@ApiOperation(value = "分页查询", notes = "分页查询")
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	public JsonContainer<Page<BrandMinInfo>> findAll(@RequestBody BrandQuery query, @PageableDefault Pageable pageable) {
		return setSuccessMessage(service.findAll(query, pageable));
	}

	@ApiOperation(value = "品牌信息", notes = "品牌信息")
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public JsonContainer<Brand> findOneByCode(@PathVariable @NotBlank String code) {
		return setSuccessMessage(service.findOneByCode(code));
	}

}
