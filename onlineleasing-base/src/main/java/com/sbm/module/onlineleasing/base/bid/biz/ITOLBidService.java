package com.sbm.module.onlineleasing.base.bid.biz;

import com.sbm.module.onlineleasing.base.bid.domain.TOLBid;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;

public interface ITOLBidService extends IOLDataService<TOLBid, Integer> {

	TOLBid newInstance();

	TOLBid findOneByBillNumber(String billNumber);

}
