package com.sbm.module.common.biz.impl;

import com.google.common.collect.Lists;
import com.sbm.module.common.biz.IConvertService;
import com.sbm.module.common.biz.IMapService;
import com.sbm.module.common.domain.BaseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @param <T> 目标类型
 * @param <K> 当前类型
 */
public class CommonServiceImpl<T, K> {

	/**
	 * iterator转list
	 *
	 * @param result
	 * @return
	 */
	protected List<K> newArrayList(Iterable<K> result) {
		return Lists.newArrayList(result);
	}

	/**
	 * K类型iterator转T类型list
	 *
	 * @param result
	 * @param service
	 * @return
	 */
	protected List<T> map(Iterable<K> result, IMapService<T, K> service) {
		return map(newArrayList(result), service);
	}

	/**
	 * K类型list转T类型list
	 *
	 * @param result
	 * @param service
	 * @return
	 */
	protected List<T> map(List<K> result, IMapService<T, K> service) {
		return result.stream().map(e -> service.newInstance(e)).collect(Collectors.toList());
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


	protected <M extends BaseEntity, N> List<M> mergeAndSetDeleteFlag(List<M> pos, List<N> vos, IConvertService<M, N> service, Class<M> clazz) throws IllegalAccessException, InstantiationException {
		Integer posSize = pos == null ? 0 : pos.size();
		Integer vosSize = vos == null ? 0 : vos.size();
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

}
