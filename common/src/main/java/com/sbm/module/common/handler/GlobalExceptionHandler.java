package com.sbm.module.common.handler;

import com.sbm.module.common.controller.BaseController;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.exception.BusinessCode;
import com.sbm.module.common.exception.BusinessException;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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
	public JsonContainer<BusinessException> handleBusinessException(BusinessException e) {
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
	public JsonContainer<BusinessException> handleException(Exception e) {
		return setErrorMessage(new BusinessException(BusinessCode.C9999, e, new Object[]{e.getMessage()}));
	}

	/**
	 * 处理空指针异常
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NullPointerException.class)
	@ResponseBody
	public JsonContainer<BusinessException> handleNullPointerException(NullPointerException e) {
		return setErrorMessage(new BusinessException(BusinessCode.C9998, e));
	}

	/**
	 * 处理参数校验异常，针对@RequestParam等
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public JsonContainer<BusinessException> handleConstraintViolationException(ConstraintViolationException e) {
		Map<String, String> data = new HashMap<>();
		for (ConstraintViolation constraintViolation : e.getConstraintViolations()) {
			PathImpl pathImpl = (PathImpl) constraintViolation.getPropertyPath();
			data.put(pathImpl.getLeafNode().asString(), constraintViolation.getMessage());
		}
		return setErrorMessage(new BusinessException(BusinessCode.C9997, e, data));
	}

	/**
	 * 处理参数校验异常，针对@RequestBody
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public JsonContainer<BusinessException> handleConstraintViolationException(MethodArgumentNotValidException e) {
		Map<String, String> data = new HashMap<>();
		for (FieldError error : e.getBindingResult().getFieldErrors()) {
			data.put(error.getField(), error.getDefaultMessage());
		}
		return setErrorMessage(new BusinessException(BusinessCode.C9997, e, data));
	}

	/**
	 * 处理参数校验异常，针对@RequestParam 缺少请求参数
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
	public JsonContainer<BusinessException> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
		return setErrorMessage(new BusinessException(BusinessCode.C9996, e, e.getMessage()));
	}

	/**
	 * HTTP消息异常
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	public JsonContainer<BusinessException> handleJsonParseException(HttpMessageNotReadableException e) {
		return setErrorMessage(new BusinessException(BusinessCode.C9989, e, new Object[]{e.getMessage()}));
	}
}
