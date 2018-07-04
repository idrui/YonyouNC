package nc.vo.pu.m20.vochange;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m20.IPraybillMaintain;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pf.change.PfUtilBaseTools;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.margin.PrayBillMarginRule;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;

import org.apache.commons.lang.ArrayUtils;

/**
 * 销售订单，调拨订单到请购单vo对照处理类
 * 
 * @since 6.0
 * @version 2011-6-18 上午10:08:21
 * @author liuchx
 */
public class ChgAfterTO20 implements IChangeVOAdjust {

  @Override
  public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
      AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    AggregatedValueObject[] srcVOs = new AggregatedValueObject[] {
      srcVO
    };
    AggregatedValueObject[] destVOs = new AggregatedValueObject[] {
      destVO
    };
    return this.batchAdjustAfterChange(srcVOs, destVOs, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject adjustBeforeChange(AggregatedValueObject srcVO,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    // 调用批处理方法
    return this.batchAdjustBeforeChange(new AggregatedValueObject[] {
      srcVO
    }, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    // 数组强转
    if (ArrayUtils.isEmpty(destVOs)) {
      return null;
    }
    PraybillVO[] vos = (PraybillVO[]) destVOs;

    vos =
        NCLocator.getInstance().lookup(IPraybillMaintain.class)
            .setDefaultValue(vos);
    this.setBusiDate(vos);
    // 进行尾差倒挤。
    new PrayBillMarginRule(PfUtilBaseTools.getRealBilltype(adjustContext
        .getSrcBilltype()), srcVOs).process(vos);
    return vos;
  }

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    return srcVOs;
  }

  /**
   * 设置业务日期
   * 
   * @param retvos
   */
  protected void setBusiDate(PraybillVO[] retvos) {
    UFDate busiDate = AppContext.getInstance().getBusiDate();
    for (PraybillVO vo : retvos) {
      vo.getHVO().setDbilldate(busiDate);
    }
  }
}
