package com.sbm.module.onlineleasing.base.serialcode.annotation;

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
