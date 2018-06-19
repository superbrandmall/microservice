package com.sbm.module.onlineleasing.serialcode;

import com.sbm.module.common.authorization.api.serialcode.constant.ISerialCode;

public enum OnlineleasingSerialCode implements ISerialCode {

	/******************** 权限 ********************/
	OLBRAND("OLBRAND", "OL品牌"),
	OLMERCHANT("OLMERCHANT", "OL商户"),
	OLBUILDING("OLBUILDING", "OL建筑物"),
	OLMALL("OLMALL", "OL商场"),
	OLFLOOR("OLFLOOR", "OL楼层"),
	OLSHOP("OLSHOP", "OL店铺"),
	OLSEARCHSHOP("OLSEARCHSHOP", "OL店铺查询"),
	OLBID("OLBID", "OL出价"),
	OLRESERVATION("OLRESERVATION", "OL预约");


	private String serialGroup;

	private String remark;

	OnlineleasingSerialCode(String serialGroup, String remark) {
		this.serialGroup = serialGroup;
		this.remark = remark;
	}

	@Override
	public String getSerialGroup() {
		return serialGroup;
	}

	@Override
	public String getRemark() {
		return remark;
	}
}
