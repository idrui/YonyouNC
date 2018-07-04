/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-28 上午08:24:41
 */
package nc.itf.pu.m21.reference.so;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmf.coop.ICoopVOChange;
import nc.itf.so.m30.ref.scmpub.SaleOrderCoopDataEntity;
import nc.pubitf.pu.m21.so.m30.OrderCoopDataEntity;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmf.coop.entity.AbstractCoopDataEntity;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>协同VO交换工具类：进行一些默认值的处理
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-28 上午08:24:41
 */
public class CoopVOChangeRule implements IRule<OrderVO>{
  
  private SaleOrderVO[] srcSaleVOs;

  public CoopVOChangeRule(AggregatedValueObject[] srcSaleVOs) {
    this.srcSaleVOs = ArrayUtil.convertArrayType(srcSaleVOs);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void process(OrderVO[] destVOs) {
    // 构建协同数据
    try {
      AbstractCoopDataEntity<SaleOrderVO>[] srcCoopDatas =
          AbstractCoopDataEntity.createCoopDatas(SaleOrderCoopDataEntity.class,
              this.srcSaleVOs);
      AbstractCoopDataEntity<OrderVO>[] destCoopDatas =
          AbstractCoopDataEntity.createCoopDatas(OrderCoopDataEntity.class,
              destVOs);
      this.coopVOChange(srcCoopDatas, destCoopDatas);

      this.check(destVOs);

      this.fillInformation(this.srcSaleVOs, destVOs);

    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void check(OrderVO[] vos) throws BusinessException {
    for (OrderVO vo : vos) {
      String pk_supplier = vo.getHVO().getPk_supplier();
      if (StringUtil.isEmptyWithTrim(pk_supplier)) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0179")/*@res "根据销售订单没有在协同基础设置找到协同组织对应的供应商"*/);
      }

      String vtrantypecode = vo.getHVO().getVtrantypecode();
      if (StringUtil.isEmptyWithTrim(vtrantypecode)) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0180")/*@res "根据销售订单没有在协同基础设置找到协同组织对应的交易类型"*/);
      }
      // 付款协议表头有值，表体没值。在采购订单报错时会报错
      String payterm = vo.getHVO().getPk_payterm();
      if (!StringUtil.isEmptyWithTrim(payterm)) {
        throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0","04004030-0385")/*@res "请检查协同设置！在采购订单的取值字段上不应有付款协议！"*/);
      }
    }
  }

  /**
   * 方法功能描述：根据协同设置设值
   * <p>
   * <b>参数说明</b>
   *
   * @param srcCoopDatas
   * @param destCoopDatas
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-28 上午08:33:32
   */
  private OrderVO[] coopVOChange(
      AbstractCoopDataEntity<SaleOrderVO>[] srcCoopDatas,
      AbstractCoopDataEntity<OrderVO>[] destCoopDatas) throws BusinessException {
    ICoopVOChange service = NCLocator.getInstance().lookup(ICoopVOChange.class);
    OrderVO[] vos =
        (OrderVO[]) service.coopVOChange(srcCoopDatas, destCoopDatas);
    return vos;
  }

  /**
   * 方法功能描述：设值
   * <p>
   * <b>参数说明</b>
   *
   * @param srcVOs
   * @param destVOs
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-28 上午08:57:27
   */
  private void fillInformation(SaleOrderVO[] srcVOs, OrderVO[] destVOs) {
    for (int i = 0; i < srcVOs.length; ++i) {
      String vcoopordercode = srcVOs[i].getParentVO().getVbillcode();
      OrderHeaderVO headerVO = destVOs[i].getHVO();
      headerVO.setBsocooptome(UFBoolean.TRUE);
      headerVO.setVcoopordercode(vcoopordercode);
    }
  }
}