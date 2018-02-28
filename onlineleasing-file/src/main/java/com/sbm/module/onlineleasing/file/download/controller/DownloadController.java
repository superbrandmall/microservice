package com.sbm.module.onlineleasing.file.download.controller;

import com.sbm.module.common.annotation.CreateApiDocs;
import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.file.download.biz.IDownloadService;
import com.sbm.module.onlineleasing.file.download.domain.Download;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Validated
@CreateApiDocs
@RestController
@RequestMapping("/api/download")
public class DownloadController extends BaseController {

	@Autowired
	private IDownloadService service;

	/**
	 * 下载预处理
	 *
	 * @param uri
	 * @return
	 */
	@RequestMapping(value = "/pre", method = RequestMethod.POST)
	@ResponseBody
	public JsonContainer<Download> pre(@NotBlank @RequestParam String uri) {
		return setSuccessMessage(service.preDownload(uri));
	}

	/**
	 * 下载
	 *
	 * @param key
	 * @param type
	 * @param response
	 */
	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public void file(@NotBlank @RequestParam String key, @RequestParam(required = false) String type, HttpServletResponse response) {
		service.getFile(key, type, response);
	}

}
