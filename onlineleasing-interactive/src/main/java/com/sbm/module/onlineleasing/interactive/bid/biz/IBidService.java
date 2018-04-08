package com.sbm.module.onlineleasing.interactive.bid.biz;

import com.sbm.module.onlineleasing.interactive.bid.domain.ApproveResult;
import com.sbm.module.onlineleasing.interactive.bid.domain.BidResult;
import com.sbm.module.onlineleasing.interactive.bid.domain.EffectResult;

public interface IBidService {

	/**
	 * 审批结果
	 *
	 * @param vo
	 */
	void approve(BidResult<ApproveResult> vo);

	/**
	 * 是否生效
	 *
	 * @param vo
	 */
	void effect(BidResult<EffectResult> vo);

}
