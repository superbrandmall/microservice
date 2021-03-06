package com.sbm.module.onlineleasing.file.exception;

import com.sbm.module.common.exception.IBusinessCode;

public enum FileCode implements IBusinessCode {

	F1101("F1101", "获取指定文件失败，指定文件不存在:{0}", "获取指定文件失败，指定文件不存在"),
	F1102("F1102", "指定key过期或不存在:{0}", "链接已过期，请刷新"),
	F1104("F1104", "文件原始名称编码转换失败。originalFilename:{0}", "文件上传出出现异常"),
	F2100("F2100", "初始化文件访问接口失败", "文件上传出出现异常");


	private String clazz;

	private String code;

	private String message;

	private String customerMessage;

	FileCode(String code, String message, String customerMessage) {
		this.clazz = this.getClass().getName();
		this.code = code;
		this.message = message;
		this.customerMessage = customerMessage;
	}

	@Override
	public String getClazz() {
		return clazz;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String getCustomerMessage() {
		return customerMessage;
	}

	@Override
	public Object getData() {
		return null;
	}
}
