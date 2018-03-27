package com.sbm.module.onlineleasing.init.shop.image.biz.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.io.Files;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.constant.CommonConstant;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import com.sbm.module.onlineleasing.base.shop.biz.ITOLShopService;
import com.sbm.module.onlineleasing.base.shop.domain.TOLShop;
import com.sbm.module.onlineleasing.base.shopimages.biz.ITOLShopImagesService;
import com.sbm.module.onlineleasing.base.shopimages.domain.TOLShopImages;
import com.sbm.module.onlineleasing.data.constant.HdConstant;
import com.sbm.module.onlineleasing.file.upload.client.IUploadClient;
import com.sbm.module.onlineleasing.file.upload.constant.UploadConstant;
import com.sbm.module.onlineleasing.file.upload.domain.UploadResult;
import com.sbm.module.onlineleasing.init.shop.image.biz.IImageService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ImageServiceImpl extends CommonServiceImpl implements IImageService {

	@Autowired
	private ITOLMallService mallService;
	@Autowired
	private ITOLShopService shopService;
	@Autowired
	private ITOLShopImagesService shopImagesService;

	@Autowired
	private IUploadClient client;

	private static final String ERROR_MESSAGE = "铺位图片初始化异常";

	@Override
	public void init(String path) {
		// 根目录
		File root = new File(path);
		if (root.isDirectory()) {
			// 项目
			for (File mallDir : root.listFiles()) {
				if (mallDir.isDirectory()) {
					// 楼层
					for (File floorDir : mallDir.listFiles()) {
						if (floorDir.isDirectory()) {
							// 单元
							for (File unitDir : floorDir.listFiles()) {
								if (unitDir.isDirectory()) {
									try {
										List<TOLShopImages> shopImages = convert2ShopImages(getShopCode(mallDir.getName(), unitDir.getName()), getImages(unitDir));
										shopImagesService.saveOrDelete(shopImages);
									} catch (Exception ex) {
										log.error(ERROR_MESSAGE, ex);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 获取商铺编号
	 *
	 * @param mallHdCode
	 * @param shopHdCode
	 * @return
	 */
	private String getShopCode(String mallHdCode, String shopHdCode) {
		TOLMall mall = mallService.findOneByHdCodeAndHdState(mallHdCode, HdConstant.HD_STATE_USING);
		TOLShop shop = shopService.findOneByMallCodeAndHdCodeAndHdState(mall.getCode(), shopHdCode, HdConstant.HD_STATE_USING);
		return shop.getCode();
	}

	/**
	 * 获取图片列表
	 *
	 * @param unitDir
	 * @return
	 */
	private List<File> getImages(File unitDir) {
		List<File> vos = new ArrayList<>();
		// 图片
		for (File image : unitDir.listFiles()) {
			if ("jpg".equals(Files.getFileExtension(image.getName()))) {
				vos.add(image);
			}
		}
		return vos;
	}

	/**
	 * 商铺图片
	 *
	 * @param code
	 * @param vos
	 * @return
	 */
	private List<TOLShopImages> convert2ShopImages(String code, List<File> vos) throws Exception {
		List<TOLShopImages> pos = shopImagesService.findAllByCode(code);
		return mergeAndSetDeleteFlag(pos, vos, (po, vo) -> convert2ShopImages(code, po, vo), TOLShopImages.class);
	}

	@SneakyThrows
	private TOLShopImages convert2ShopImages(String code, TOLShopImages po, File vo) {
		// 编号
		po.setCode(code);
		// 图片
		MultipartFile file = new MockMultipartFile("file", vo.getName(), MediaType.IMAGE_JPEG_VALUE, new FileInputStream(vo));
		JsonContainer<List<UploadResult>> result = client.upload(file, CommonConstant.SYSTEM, UploadConstant.CONTAINER_NAME_DEFAULT,
				MessageFormat.format(UploadConstant.PREFIX_DEFAULT, code, UploadConstant.PIC, UploadConstant.SHOP_IMAGE));
		checkJsonContainer(result);
		System.out.println(JSON.toJSONString(result));
		po.setImage(result.getData().get(0).getUri());
		// 位置
		po.setPosition(Integer.valueOf(Files.getNameWithoutExtension(vo.getName())));
		return po;
	}

}
