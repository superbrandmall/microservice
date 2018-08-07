package com.sbm.module.onlineleasing.interactive.website.mall.biz;

import com.sbm.module.onlineleasing.interactive.website.mall.domain.Mall;
import com.sbm.module.onlineleasing.interactive.website.mall.domain.MallQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMallService {

	Page<Mall> findAll(MallQuery query, Pageable pageable);

}
