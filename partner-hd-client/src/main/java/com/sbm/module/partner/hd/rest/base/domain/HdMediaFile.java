package com.sbm.module.partner.hd.rest.base.domain;

import lombok.Data;

import java.util.Date;

@Data
public class HdMediaFile {

	private String id;

	private String attachmentType;

	private String name;

	private String caption;

	private String fileType;

	private Integer size;

	private String md5;

	private String uploader;

	private Date uploadTime;

}
