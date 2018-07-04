/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-29 下午06:47:07
 */
package nc.vo.pu.m21.rule;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmf.coop.ICoopService;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>协同关系检查
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-29 下午06:47:07
 */
public class CoopRelationCheckRule implements IRule<OrderVO> {

  /**
   * 父类方法重写
   *
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    for (OrderVO vo : vos) {
      String pk_supplier = vo.getHVO().getPk_supplier();
      String pk_org = vo.getHVO().getPk_org();
      ICoopService service = NCLocator.getInstance().lookup(ICoopService.class);
      UFBoolean coopRelation = service.hasCoopRelationShip(pk_supplier, pk_org);
      if (UFBoolean.FALSE.equals(coopRelation)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0184")/*@res "选择销售组织和采购组织没有协同设置！"*/);
      }
    }
  }

}