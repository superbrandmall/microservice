package com.sbm.module.common.biz.impl;

import com.google.common.collect.Lists;
import com.sbm.module.common.biz.IConvertService;
import com.sbm.module.common.domain.BaseEntity;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.exception.BusinessCode;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.common.util.CloneUtil;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommonServiceImpl {

	/**
	 * iterator转list
	 *
	 * @param result
	 * @return
	 */
	protected <K> List<K> newArrayList(Iterable<K> result) {
		return Lists.newArrayList(result);
	}

	/**
	 * S类型list转R类型list
	 *
	 * @param result
	 * @param mapper
	 * @param <R>
	 * @param <S>
	 * @return
	 */
	protected <R, S> List<R> map(List<S> result, Function<? super S, ? extends R> mapper) {
		if (null == result) return null;
		return result.stream().map(mapper).collect(Collectors.toList());
	}

	/**
	 * 如果S不为空，则转换成R，否则返回null
	 *
	 * @param s
	 * @param mapper
	 * @param <R>
	 * @param <S>
	 * @return
	 */
	protected <R, S> R mapOneIfNotNull(S s, Function<? super S, ? extends R> mapper) {
		R r = null;
		if (null != s) {
			r = mapper.apply(s);
		}
		return r;
	}

	/**
	 * 如果R不为空，则抛出异常，如果为空，则由S转换
	 *
	 * @param r
	 * @param s
	 * @param mapper
	 * @param e
	 * @param <R>
	 * @param <S>
	 * @return
	 */
	protected <R, S> R mapOneIfNotNullThrowException(R r, S s, Function<? super S, ? extends R> mapper, BusinessException e) {
		if (null != r) {
			throw e;
		}
		return mapper.apply(s);
	}

	/**
	 * 如果为空，抛异常
	 *
	 * @param r
	 * @param e
	 * @param <R>
	 * @return
	 */
	protected <R> R checkIfNullThrowException(R r, BusinessException e) {
		if (null == r) {
			throw e;
		}
		return r;
	}

	/**
	 * 如果集合不为空，抛异常
	 *
	 * @param r
	 * @param e
	 * @param <R>
	 * @return
	 */
	protected <R extends Collection> R checkIfNotEmptyThrowException(R r, BusinessException e) {
		if (null != r && !r.isEmpty()) {
			throw e;
		}
		return r;
	}


	/**
	 * 如果为空，创建新对象
	 *
	 * @param r
	 * @param mapper
	 * @param <R>
	 * @return
	 */
	protected <R> R checkIfNullNewInstance(R r, Function<? super R, ? extends R> mapper) {
		if (null == r) {
			r = mapper.apply(r);
		}
		return r;
	}

	/**
	 * M集合与N集合合并，返回M结果集，以M集合为依据，进行增删改
	 *
	 * @param pos
	 * @param vos
	 * @param service
	 * @param clazz
	 * @param <M>
	 * @param <N>
	 * @return
	 */
	@SneakyThrows
	protected <M extends BaseEntity, N> List<M> mergeAndSetDeleteFlag(List<M> pos, List<N> vos, IConvertService<M, N> service, Class<M> clazz) {
		if (null == pos) pos = new ArrayList<>();
		if (null == vos) vos = new ArrayList<>();
		Integer posSize = pos.size();
		Integer vosSize = vos.size();
		List<M> result = new ArrayList<>();
		M m;
		// 实体少于查询结果
		if (posSize <= vosSize) {
			for (int i = 0; i < vosSize; i++) {
				// 少的修改
				if (i < posSize) {
					result.add(service.convert(pos.get(i), vos.get(i)));
				}
				// 多的新增
				else {
					result.add(service.convert(clazz.newInstance(), vos.get(i)));
				}
			}
		}
		// 实体多于查询结果
		else {
			for (int i = 0; i < posSize; i++) {
				// 少的修改
				if (i < vosSize) {
					result.add(service.convert(pos.get(i), vos.get(i)));
				} else {
					m = pos.get(i);
					m.setDeleteFlag(true);
					result.add(m);
				}
			}
		}
		return result;
	}

	/**
	 * json序列化克隆
	 *
	 * @param t
	 * @param clazz
	 * @return
	 */
	protected Object jsonClone(Object t, Class clazz) {
		return CloneUtil.jsonClone(t, clazz);
	}

	/**
	 * 生成随机的uuid
	 *
	 * @return
	 */
	protected String getUUID() {
		return UUID.randomUUID().toString();
	}


	public static <T> T checkJsonContainer(JsonContainer<T> result) {
		if (!BusinessCode.C0.getCode().equals(result.getCode())) {
			throw new BusinessException(result);
		}
		return result.getData();
	}

}
