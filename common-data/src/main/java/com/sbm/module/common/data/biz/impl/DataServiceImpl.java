package com.sbm.module.common.data.biz.impl;

import com.sbm.module.common.biz.IMapService;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.data.biz.IDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * @param <T>  目标类型
 * @param <K>  当前类型
 * @param <ID> 主键
 */
public abstract class DataServiceImpl<T, K, ID extends Serializable> extends CommonServiceImpl<T, K> implements IDataService<T, K, ID>, IMapService<T, K> {

	@Autowired
	private JpaServiceImpl<K, ID> jpaService;

	/****************************************************************************************************************/
	// po
	@Override
	public Page<K> findAllPoByPageable(Pageable pageable) {
		return jpaService.findAll(pageable);
	}

	/****************************************************************************************************************/
	// vo

	/**
	 * 创建目标实例
	 *
	 * @param e
	 * @return
	 */
	public abstract T newInstance(K e);

	@Override
	public List<T> findAllVo() {
		return map(jpaService.findAll(), this);
	}


}
