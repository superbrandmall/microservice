package com.sbm.module.common.handler;

import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.exception.BusinessCode;
import com.sbm.module.common.exception.BusinessException;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends BaseController {

	/**
	 * 处理业务错误
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public JsonContainer handleBusinessException(BusinessException e) {
		return setErrorMessage(e);
	}

	/**
	 * 处理未捕获异常
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonContainer handleException(Exception e) {
		return setErrorMessage(new BusinessException(BusinessCode.C9999, e));
	}

	/**
	 * 处理空指针异常
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NullPointerException.class)
	@ResponseBody
	public JsonContainer handleNullPointerException(NullPointerException e) {
		return setErrorMessage(new BusinessException(BusinessCode.C9998, e));
	}

	/**
	 * 处理参数校验异常
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public JsonContainer handleConstraintViolationException(ConstraintViolationException e) {
		Map<String, String> data = new HashMap<>();
		for (ConstraintViolation constraintViolation : e.getConstraintViolations()) {
			PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();
			data.put(pathImpl.getLeafNode().asString(), constraintViolation.getMessage());
		}
		return setErrorMessage(new BusinessException(BusinessCode.C9997, e), data);
	}

}
