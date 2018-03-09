package com.sbm.module.common.authorization.api.serialcode.annotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SerialCodeRemark {

	/**
	 * 备注
	 *
	 * @return
	 */
	String remark() default "";

}
