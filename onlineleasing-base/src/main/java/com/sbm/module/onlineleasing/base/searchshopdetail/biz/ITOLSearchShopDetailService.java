package com.sbm.module.onlineleasing.base.searchshopdetail.biz;

import com.sbm.module.onlineleasing.base.searchshopdetail.domain.TOLSearchShopDetail;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITOLSearchShopDetailService extends IOLDataService<TOLSearchShopDetail, Integer> {

	TOLSearchShopDetail newInstance();

	Page<TOLSearchShopDetail> findAllByUserCodeOrderByUpdatedDesc(String userCode, Pageable pageable);

}
