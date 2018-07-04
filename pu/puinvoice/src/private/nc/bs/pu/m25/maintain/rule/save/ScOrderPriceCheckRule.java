/**
 * 
 */
package nc.bs.pu.m25.maintain.rule.save;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.sc.m61.pu.ISCOrderFor25Ref;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoiceUserAnswerType;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.sc.m61.entity.wrtbck.SCOrdPriceChkVOFor25;
import nc.vo.scmpub.res.billtype.SCBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 * 发票参照委外订单生成时检查是否超订单单价容差控制
 * @scene
 * 保存的BP
 * @param
 * env 采购发票操作时前台到后台的环境信息，一般随平台动作的userObj向外传 
 *
 * @since 6.3
 * @version 2014-10-22 下午3:36:43
 * @author zhangshqb
 */
public class ScOrderPriceCheckRule implements ICompareRule<InvoiceVO> {

  private InvoiceUIToBSEnv env;

  /**
   * OrderPriceCheckRule 的构造子
   * 
   * @param env
   */
  public ScOrderPriceCheckRule(InvoiceUIToBSEnv env) {
    this.setEnv(env);
  }

  /**
   * @return env
   */
  public InvoiceUIToBSEnv getEnv() {
    return this.env;
  }

  @Override
  public void process(InvoiceVO[] vos, InvoiceVO[] originVOs) {
    if (!SysInitGroupQuery.isSCEnabled()) {
      return;
    }
    Map<String, InvoiceItemVO> orgMap = new HashMap<String, InvoiceItemVO>();
    if (!ArrayUtils.isEmpty(originVOs)) {
      orgMap = AggVOUtil.createItemMap(originVOs);
    }
    List<SCOrdPriceChkVOFor25> wbVoList = new ArrayList<SCOrdPriceChkVOFor25>();
    for (InvoiceVO vo : vos) {
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        String firstordertype = item.getCfirsttypecode();
        String srcordertype = item.getCsourcetypecode();
        if (!SCBillType.Order.getCode().equals(firstordertype)
            && !SCBillType.Order.getCode().equals(srcordertype)) {
          continue;
        }
        InvoiceItemVO oldItem = orgMap.get(item.getPk_order_b());
        UFDouble oldPrice = null != oldItem ? oldItem.getNprice() : null;
        UFDouble newPrice = item.getNprice();
        if (!StringUtils.isEmpty(item.getPk_order_b())
            && MathTool.compareTo(newPrice, oldPrice) > 0) {
          wbVoList.add(this.createScPriceCheckVO(item));
        }
      }
    }
    if (0 == wbVoList.size()) {
      return;
    }
    SCOrdPriceChkVOFor25[] wbVos =
        wbVoList.toArray(new SCOrdPriceChkVOFor25[wbVoList.size()]);
    try {
      // 调用订单的服务
      NCLocator.getInstance().lookup(ISCOrderFor25Ref.class)
          .invoicePriceChk(wbVos);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * @param env
   *          要设置的 env
   */
  public void setEnv(InvoiceUIToBSEnv env) {
    this.env = env;
  }

  private SCOrdPriceChkVOFor25 createScPriceCheckVO(InvoiceItemVO item) {

    SCOrdPriceChkVOFor25 checkVO =
        new SCOrdPriceChkVOFor25(item.getPk_order(), item.getPk_order_b(),
            item.getNprice());
    boolean confirm =
        InvoiceUserAnswerType.YES == this.getEnv().getOverPOPrice();
    checkVO.setUserConfrm(UFBoolean.valueOf(confirm));
    return checkVO;

  }

}
