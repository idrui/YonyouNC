package nc.bs.pu.m21.maintain.rule.save;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.bd.supplierext.ISupplierextPubQuery;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *              采购订单保存时校验，如果供应商或开票供应商被列入黑名单（供应商档案扩展信息页签的“黑名单”标志为“是”）则不能保存
 * @scene
 *        采购订单保存校验
 * @param 无
 * @since 6.3
 * @version 2014-12-23 上午10:20:12
 * @author yanxm5
 */
public class IsBlistBeforeRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    Set<String> pks = new HashSet<String>();
    for (OrderVO vo : vos) {
      String pk_supplier = vo.getHVO().getPk_supplier();
      String pk_invcsupplier = vo.getHVO().getPk_invcsupllier();
      pks.add(pk_supplier);
      pks.add(pk_invcsupplier);
    }
    ISupplierextPubQuery pubQuery =
        NCLocator.getInstance().lookup(ISupplierextPubQuery.class);
    Map<String, UFBoolean> map = null;
    try {
      if (pks.size() == 0) {
        return;
      }
       map = pubQuery.queryBisblackByPKs(pks.toArray(new String[pks.size()]));
      if (map == null) {
        return;
      }
      for (UFBoolean value : map.values()) {
        if (value.booleanValue()) {
          String msg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4004030_0", "04004030-0373")/*
                                                * @res
                                                * "表头供应商或开票供应商已在黑名单内，不允许保存！";
                                                */;
          ExceptionUtils.wrappBusinessException(msg);
        }
      }
      }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
