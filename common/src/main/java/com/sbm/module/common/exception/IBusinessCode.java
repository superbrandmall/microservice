package com.sbm.module.common.exception;

public interface IBusinessCode {

	String getClazz();

	String getCode();

	String getMessage();

	String getCustomerMessage();

	Object getData();
}
