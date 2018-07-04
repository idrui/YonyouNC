package nc.bs.pu.m21.alert;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.res.NCModule;
import nc.vo.sm.createcorp.CreateOrgInfo;
import nc.vo.sm.createcorp.CreatecorpVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 采购预制数据监听类(当企业初始化的时候在这里把询价计算预警条目的信息插入数据库)
 * 
 * @since 6.1
 * @version 2011-11-29 上午11:59:19
 * @author yangtian
 */
public class PriceCalculateAlertInit implements IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {
    BusinessEvent busi = (BusinessEvent) event;
    // 判断是否为集团初始化事件
    if (!busi.getSourceID().equals("createorg")) {
      return;
    }
    // 集团初始化事件的userobject为CreateOrgInfo，其中包含和建账相关的信息
    CreateOrgInfo info = (CreateOrgInfo) busi.getObject();
    // 集团级数据，无论是否增补，只要qc根节点被初始化就执行预制数据
    // if (info.isAppend()) {
    // dosomething();
    // }
    // 获得本次初始化的模块
    CreatecorpVO[] ccvos = info.getCreatecorpVOs();
    if (ArrayUtils.isEmpty(ccvos)) {
      return;
    }
    for (CreatecorpVO vo : ccvos) {
      if (NCModule.PO.getCode().equals(vo.getFunccode())) {

        PriceCalculateTaskReg priceCalculateTaskReg =
            new PriceCalculateTaskReg(vo.getPk_org());

        priceCalculateTaskReg.regMonthlyAlert();
        priceCalculateTaskReg.regDailyAlert();
      }
    }

  }

}
