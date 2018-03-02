package com.sbm.module.common.biz.impl;

import com.google.common.collect.Lists;
import com.sbm.module.common.biz.IConvertService;
import com.sbm.module.common.domain.BaseEntity;
import com.sbm.module.common.util.CloneUtil;

import java.util.ArrayList;
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
		return result.stream().map(mapper).collect(Collectors.toList());
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
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	protected <M extends BaseEntity, N> List<M> mergeAndSetDeleteFlag(List<M> pos, List<N> vos, IConvertService<M, N> service, Class<M> clazz) throws IllegalAccessException, InstantiationException {
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
}
