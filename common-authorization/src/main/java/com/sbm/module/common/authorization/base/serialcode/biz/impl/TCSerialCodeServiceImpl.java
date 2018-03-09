package com.sbm.module.common.authorization.base.serialcode.biz.impl;

import com.sbm.module.common.authorization.base.serialcode.biz.ITCSerialCodeService;
import com.sbm.module.common.authorization.base.serialcode.domain.TCSerialCode;
import com.sbm.module.common.authorization.base.serialcode.exception.SerialCodeErrorCode;
import com.sbm.module.common.authorization.base.serialcode.repository.ITCSerialCodeRepository;
import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TCSerialCodeServiceImpl extends DataServiceImpl<TCSerialCode, Integer> implements ITCSerialCodeService {

	@Autowired
	private ITCSerialCodeRepository repository;


	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
	private static DecimalFormat decimalFormat = new DecimalFormat("000000");

	@Override
	public TCSerialCode findBySerialGroup(String serialGroup) {
		return repository.findOneBySerialGroup(serialGroup);
	}

	@Override
	@Transactional
	public TCSerialCode next(String serialGroup) {
		// 取当前流水号
		TCSerialCode obj = repository.findOneBySerialGroup(serialGroup);
		// 查不到抛异常
		if (null == obj) {
			throw new BusinessException(SerialCodeErrorCode.SC0001, new Object[]{serialGroup});
		}
		Integer currentNum = obj.getSerialNum();
		Date serialDate = obj.getSerialDate();

		// 如果流水日期为空，设为当前日期, 流水号重置为1
		if (null == obj.getSerialDate()) {
			serialDate = new Date();
			currentNum = 1;
		} else {
			// 比较流水日期与当前日期，如果是同一天，继续流水，否则重置
			if (simpleDateFormat.format(obj.getSerialDate()).equals(simpleDateFormat.format(new Date()))) {
				// 流水号+1
				currentNum++;
			} else {
				serialDate = new Date();
				currentNum = 1;
			}
		}

		// 重设流水号
		obj.setSerialNum(currentNum);
		obj.setSerialDate(serialDate);
		// 流水号更新到DB
		repository.save(obj);
		// 产生表单编号
		obj.setNext(generalBizId(serialGroup, serialDate, currentNum));
		return obj;
	}

	private static String generalBizId(String prefix, Date date, int serialNum) {
		return prefix + simpleDateFormat.format(date) + decimalFormat.format(serialNum);
	}
}
