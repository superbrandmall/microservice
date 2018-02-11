package com.sbm.module.partner.hd.base.biztype.biz.impl;

import com.sbm.module.common.data.biz.impl.DataServiceImpl;
import com.sbm.module.partner.hd.base.biztype.biz.IBiztypeService;
import com.sbm.module.partner.hd.base.biztype.domain.Biztype;
import org.springframework.stereotype.Service;

@Service
public class BiztypeServiceImpl extends DataServiceImpl<Biztype, String> implements IBiztypeService {

}
