package com.sbm.module.common.provider;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringContextProvider implements ApplicationContextAware {

	/**
	 * 上下文对象实例
	 */
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 获取applicationContext
	 *
	 * @return
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 通过name获取Bean.
	 * @param name
	 * @return
	 */
	public Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}

	/**
	 * 通过class获取Bean.
	 *
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}

	/**
	 * 通过name, Clazz获取指定的Bean
	 * @param name
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public <T> T getBean(String name, Class<T> clazz) {
		return getApplicationContext().getBean(name, clazz);
	}

	/**
	 * 通过clazz获取指定的Bean集合
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public <T> Map<String, T> getBeans(Class<T> clazz) {
		return getApplicationContext().getBeansOfType(clazz);
	}

}
