package nc.vo.pu.m20.vochange;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m20.IPraybillMaintain;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.util.VORowNoUtils;

import org.apache.commons.lang.ArrayUtils;

public class ChangeVOAdjust4410To20 implements IChangeVOAdjust {

  @Override
  public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
      AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    if (null == destVO) {
      return null;
    }
    // 调用批处理方法
    return this.batchAdjustAfterChange(new AggregatedValueObject[] {
      srcVO
    }, new AggregatedValueObject[] {
      destVO
    }, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject adjustBeforeChange(AggregatedValueObject srcVO,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    if (null == srcVO) {
      return null;
    }
    // 调用批处理方法
    return this.batchAdjustBeforeChange(new AggregatedValueObject[] {
      srcVO
    }, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    PraybillVO[] vos = ArrayUtil.convertArrayType(destVOs);
    vos = this.fillInformation(vos);
    return vos;
  }

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    return srcVOs;
  }

  private PraybillVO[] fillInformation(PraybillVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    
    //补充行号
    VORowNoUtils.setVOsRowNoByRule(vos, PraybillItemVO.CROWNO);
    
    try {
      return NCLocator.getInstance().lookup(IPraybillMaintain.class)
          .setDefaultValue(vos);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

}
