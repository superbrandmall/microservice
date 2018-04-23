package com.sbm.module.onlineleasing.base.myfavourite.biz;

import com.sbm.module.common.data.biz.IDataService;
import com.sbm.module.onlineleasing.base.myfavourite.domain.TOLMyFavourite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITOLMyFavouriteService extends IDataService<TOLMyFavourite, Integer> {

	Page<TOLMyFavourite> findAllByUserCode(String code, Pageable pageable);

	TOLMyFavourite findOneByUserCodeAndShopCode(String userCode, String shopCode);

}
