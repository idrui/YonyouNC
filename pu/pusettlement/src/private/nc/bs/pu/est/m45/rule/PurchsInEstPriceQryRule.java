/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-19 上午09:33:41
 */
package nc.bs.pu.est.m45.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.pu.est.rule.GoodsEstPriceQueryRule;
import nc.bs.pu.est.rule.pricequery.AbstractEstPriceQueryStrategy;
import nc.bs.pu.est.rule.pricequery.EstPriceQryStrategyFactory;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.it.est.importin.IImportInServiceForPu;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 入库单暂估的询价
 * @scene
 * 自动暂估,前台货物(费用同时)暂估查询暂估
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-23 上午9:31:50
 * @author zhangshqb
 */
public class PurchsInEstPriceQryRule extends GoodsEstPriceQueryRule implements
    IRule<PurchaseInEstVO> {

  @Override
  public void process(PurchaseInEstVO[] vos) {
    if (!SysInitGroupQuery.isITEnabled()) {
      super.process(vos);
      return;
    }
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    List<PurchaseInEstVO> listpu = new ArrayList<PurchaseInEstVO>();
    List<PurchaseInEstVO> listit = new ArrayList<PurchaseInEstVO>();
    for (PurchaseInEstVO vo : vos) {
      UFBoolean flag = vo.getParentVO().getBitinbill();
      if (UFBoolean.TRUE.equals(flag)) {
        listit.add(vo);
      }
      else {
        listpu.add(vo);
      }
    }
    if (listpu.size() > 0) {
      super.process(listpu.toArray(new PurchaseInEstVO[listpu.size()]));
    }
    if (listit.size() > 0) {
      try {
        NCLocator
            .getInstance()
            .lookup(IImportInServiceForPu.class)
            .importInEstPriceQuery(
                listit.toArray(new PurchaseInEstVO[listit.size()]));
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  @Override
  protected AbstractEstPriceQueryStrategy getPriceQryStrategy(PriceSource ps) {
    return EstPriceQryStrategyFactory.getPurchsInStrategy(ps);
  }

}
