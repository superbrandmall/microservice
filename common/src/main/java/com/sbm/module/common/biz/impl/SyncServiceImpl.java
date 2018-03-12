package com.sbm.module.common.biz.impl;

import com.sbm.module.common.domain.SyncResult;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.function.Function;

@Slf4j
public abstract class SyncServiceImpl<T, K, F> extends BusinessServiceImpl {

	private static final String SYNC_INFO = "{} sync, page: {}, pageSize: {}, pageCount: {}, RecordCount: {}";

	/**
	 * 执行
	 *
	 * @param filter
	 */
	protected void execute(F filter, Function<? super K, ? extends T> mapper) {
		SyncResult<K> result;
		List<T> pos;
		do {
			result = getResult(filter);
			pos = map(result.getRecords(), mapper);
			save(pos);
			doAfter(filter);
			log.info(SYNC_INFO, this.getClass().getSimpleName(), result.getPage(), result.getPageSize(), result.getPageCount(), result.getRecordCount());
		} while (whileCondition(filter, result));
	}

	/**
	 * 保存
	 *
	 * @param pos
	 */
	protected abstract void save(List<T> pos);

	/**
	 * 获取返回结果
	 *
	 * @param filter
	 * @return
	 */
	protected abstract SyncResult<K> getResult(F filter);

	/**
	 * 后续操作
	 *
	 * @param filter
	 */
	protected abstract void doAfter(F filter);

	/**
	 * 判断条件
	 *
	 * @param filter
	 * @param result
	 * @return
	 */
	protected abstract boolean whileCondition(F filter, SyncResult<K> result);

}
