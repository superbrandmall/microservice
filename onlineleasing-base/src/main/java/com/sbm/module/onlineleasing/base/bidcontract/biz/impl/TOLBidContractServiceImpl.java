package com.sbm.module.onlineleasing.base.bidcontract.biz.impl;

import com.sbm.module.onlineleasing.base.bidcontract.biz.ITOLBidContractService;
import com.sbm.module.onlineleasing.base.bidcontract.domain.TOLBidContract;
import com.sbm.module.onlineleasing.base.bidcontract.repository.ITOLBidContractRepository;
import com.sbm.module.onlineleasing.data.biz.impl.OLDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TOLBidContractServiceImpl extends OLDataServiceImpl<TOLBidContract, Integer> implements ITOLBidContractService {

	@Autowired
	private ITOLBidContractRepository repository;


}
