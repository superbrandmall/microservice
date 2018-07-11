package com.sbm.module.onlineleasing.admin.mall.biz.impl;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.onlineleasing.admin.mall.biz.IMallService;
import com.sbm.module.onlineleasing.base.mall.biz.ITOLMallService;
import com.sbm.module.onlineleasing.base.mall.domain.TOLMall;
import com.sbm.module.onlineleasing.base.malltraffic.biz.ITOLMallTrafficService;
import com.sbm.module.onlineleasing.base.malltraffic.domain.TOLMallTraffic;
import com.sbm.module.onlineleasing.domain.base.info.mall.MallTraffic;
import com.sbm.module.onlineleasing.domain.mall.Mall;
import com.sbm.module.onlineleasing.domain.mall.MallMaxInfo;
import com.sbm.module.onlineleasing.domain.mall.MallQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MallServiceImpl extends CommonServiceImpl implements IMallService {

	@Autowired
	private ITOLMallService mallService;
	@Autowired
	private ITOLMallTrafficService mallTrafficService;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public Page<Mall> findAll(MallQuery query, Pageable pageable) {
		return mallService.findAll(query.findAll(), pageable).map(e -> MallMaxInfo.convert(e));
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
	public MallMaxInfo findOneByMallCode(String mallCode) {
		return mapOneIfNotNull(mallService.findOneByCode(mallCode), e -> {
			MallMaxInfo vo = MallMaxInfo.convert(e);
			// 交通
			vo.setTraffic(map(mallTrafficService.findAllByCode(vo.getMallCode()), s -> new MallTraffic(s.getType(), s.getTypeEng(), s.getText(), s.getTextEng(), s.getRemark())));
			return vo;
		});
	}

	@Override
	@Transactional
	public void save(MallMaxInfo mallMaxInfo) {
		if (null != mallMaxInfo.getMallCode()) {
			// 保存项目
			TOLMall mall = mallService.findOneByCode(mallMaxInfo.getMallCode());
			// 位置
			mall.setLocation(mallMaxInfo.getLocation());
			mall.setLocationEng(mallMaxInfo.getLocationEng());
			// 描述
			mall.setDescription(mallMaxInfo.getDescription());
			mall.setDescriptionEng(mallMaxInfo.getDescriptionEng());
			// 位置
			mall.setPosition(mallMaxInfo.getPosition());
			// 图片
			mall.setImg(mallMaxInfo.getImg());
			// 视频
			mall.setVideo(mallMaxInfo.getVideo());
			// 电话
			mall.setPhone(mallMaxInfo.getPhone());
			mallService.save(mall);

			// 保存交通
			mallTrafficService.saveOrDelete(convert2Traffic(mallMaxInfo));
		}
	}

	private List<TOLMallTraffic> convert2Traffic(MallMaxInfo mallMaxInfo) {
		List<TOLMallTraffic> pos = mallTrafficService.findAllByCode(mallMaxInfo.getMallCode());
		return mergeAndSetDeleteFlag(pos, mallMaxInfo.getTraffic(), (po, vo) -> convert2Traffic(mallMaxInfo.getMallCode(), po, vo), TOLMallTraffic.class);
	}

	private TOLMallTraffic convert2Traffic(String code, TOLMallTraffic po, MallTraffic vo) {
		// 编号
		po.setCode(code);
		// 文本
		po.setText(vo.getText());
		po.setTextEng(vo.getTextEng());
		// 类型
		po.setType(vo.getType());
		po.setTypeEng(vo.getTypeEng());
		// 备注
		po.setRemark(vo.getRemark());
		return po;
	}

}
