package com.sbm.module.onlineleasing.base.shopimages.biz;

import com.sbm.module.onlineleasing.base.shopimages.domain.TOLShopImages;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;

import java.util.List;

public interface ITOLShopImagesService extends IOLDataService<TOLShopImages, Integer> {

	List<TOLShopImages> findAllByCodeOrderByPosition(String code);

}
