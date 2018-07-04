/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-10 下午08:46:47
 */
package nc.bs.pu.m20.maintain.rule.pub;

import java.util.List;
import java.util.Map;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.itf.pu.reference.so.M30SOServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.so.m30arrange.Rewrite30ArrangePara;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售订单回写实现
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-10 下午08:46:47
 */
public class RewiteM30 implements IRewite {

  // @Override
  // public void addSRCClazz(BillRewriter tool, String billtype) {
  // tool.addSRCHeadClazz(billtype, SaleOrderHVO.class);
  // tool.addSRCItemClazz(billtype, SaleOrderBVO.class);
  // }

  // @Override
  // public void writeback(List<RewritePara> rwParas, String billtype,
  // UFBoolean bsc) {
  //
  // if (!SysInitGroupQuery.isSOEnabled()) {
  // return;
  // }
  //
  // if (rwParas == null || rwParas.isEmpty()) {
  // return;
  // }
  //
  // // 构造回写回写销售订单的参数
  // Rewrite30ArrangePara[] paraArray = this.buildParaArray(rwParas);
  //
  // try {
  // M30SOServices.writeback30For20(paraArray);
  // }
  // catch (BusinessException ex) {
  // ExceptionUtils.wrappException(ex);
  // }
  //
  // }

  @Override
  public void writeback(List<RewritePara> rwParas,
      Map<String, PraybillVO> bidVOMap) {

    if (!SysInitGroupQuery.isSOEnabled()) {
      return;
    }

    if (rwParas == null || rwParas.isEmpty()) {
      return;
    }

    // 构造回写回写销售订单的参数
    Rewrite30ArrangePara[] paraArray = this.buildParaArray(rwParas);

    try {
      M30SOServices.writeback30For20(paraArray);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
  }

  private Rewrite30ArrangePara[] buildParaArray(List<RewritePara> rwParas) {
    if (rwParas == null || rwParas.isEmpty()) {
      return null;
    }
    String userid = InvocationInfoProxy.getInstance().getUserId();
    Rewrite30ArrangePara[] paras = new Rewrite30ArrangePara[rwParas.size()];
    for (int i = 0, len = rwParas.size(); i < len; i++) {
      RewritePara para = rwParas.get(i);
      String csaleorderbid = para.getCsrcbid();
      UFDouble narrangeponum = para.getNnum();
      String carrangepersonid = userid;
      paras[i] =
          new Rewrite30ArrangePara(csaleorderbid, narrangeponum,
              carrangepersonid, POBillType.PrayBill.getCode());
    }
    return paras;
  }

}
