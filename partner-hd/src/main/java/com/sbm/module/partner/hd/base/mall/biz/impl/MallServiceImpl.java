package com.sbm.module.partner.hd.base.mall.biz.impl;

import com.sbm.module.common.data.biz.impl.JpaServiceImpl;
import com.sbm.module.partner.hd.base.mall.biz.IMallService;
import com.sbm.module.partner.hd.base.mall.domain.Mall;
import org.springframework.stereotype.Service;

@Service
public class MallServiceImpl extends JpaServiceImpl<Mall, String> implements IMallService {

}
