package nc.pubimpl.pu.m422x.api.action;

import nc.bs.pu.m422x.maintain.StoreReqAppSaveBP;
import nc.bs.pu.m422x.plugin.StoreReqAppPluginPoint;
import nc.impl.pu.m422x.action.rule.ParaValidateRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.m422x.rule.api.StoreReqFillRuleBuilder;
import nc.vo.pu.m422x.rule.api.StoreReqNullItemRule;
import nc.vo.pub.BusinessException;

/**
 * 
 * @description
 *            物资需求申请单保存
 * @scene
 *      物资需求申请单保存
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-26 下午8:05:39
 * @author luojw
 */
public class StoreReqAppAPIInsertAction {

  public StoreReqAppVO[] insert(StoreReqAppVO[] bills) throws BusinessException {
    AroundProcesser<StoreReqAppVO> processer =
        new AroundProcesser<StoreReqAppVO>(StoreReqAppPluginPoint.UI_INSERT);
    this.addRule(processer);
    processer.before(bills);
    
    StoreReqFillRuleBuilder rule = new StoreReqFillRuleBuilder();
    StoreReqAppVO[] vos = rule.fillValue(bills);
    vos = new StoreReqAppSaveBP().save(vos, null, null);
    processer.after(bills);

    return vos;
  }

  private void addRule(AroundProcesser<StoreReqAppVO> processer) {
    processer.addBeforeRule(new ParaValidateRule());
    processer.addBeforeRule(new StoreReqNullItemRule());
  }

}
