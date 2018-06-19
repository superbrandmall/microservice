package com.sbm.module.common.authorization.init;

import com.sbm.module.common.authorization.api.serialcode.annotation.SerialCodeRemark;
import com.sbm.module.common.authorization.api.serialcode.constant.SerialCodeConstant;
import com.sbm.module.common.authorization.base.serialcode.biz.ITCSerialCodeService;
import com.sbm.module.common.authorization.base.serialcode.domain.TCSerialCode;
import com.sbm.module.common.init.InitAfterLoad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.MessageFormat;

@Component
@Slf4j
@Order(1)
public class SerialCodeInit implements InitAfterLoad {

	private static final String INSERT_MESSAGE = "insert serialGroup: {0}";
	private static final String WARN_MESSAGE = "field: {0} is not String";

	@Autowired
	private ITCSerialCodeService service;

	public void init() {
		// TODO 修改为各个项目配置
		Class<SerialCodeConstant> clazz = SerialCodeConstant.class;
		Field[] fields = clazz.getFields();
		for (Field field : fields) {
			try {
				String serialGroup = (String) field.get(new String());
				String remark = field.getAnnotation(SerialCodeRemark.class).remark();
				TCSerialCode serialCode = service.findBySerialGroup(serialGroup);
				if (null == serialCode) {
					serialCode = new TCSerialCode();
					process(serialCode, serialGroup, remark);
					service.save(serialCode);
					log.info(MessageFormat.format(INSERT_MESSAGE, field.getName()));
				}
			} catch (Exception e) {
				log.warn(MessageFormat.format(WARN_MESSAGE, field.getName()));
			}
		}
	}

	/**
	 * 处理
	 *
	 * @param serialCode
	 * @param serialGroup
	 * @param remark
	 */
	private void process(TCSerialCode serialCode, String serialGroup, String remark) {
		// 序列
		serialCode.setSerialGroup(serialGroup);
		// 备注
		serialCode.setRemark(remark);
	}

}
