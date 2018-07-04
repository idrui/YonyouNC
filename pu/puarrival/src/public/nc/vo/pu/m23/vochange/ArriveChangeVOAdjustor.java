package nc.vo.pu.m23.vochange;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.bd.userdef.UserDefCheckUtils;
import nc.vo.pf.change.ChangeVOAdjustContext;
import nc.vo.pf.change.IChangeVOAdjust;
import nc.vo.pf.change.PfUtilBaseTools;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.margin.ArriveMarginRule;
import nc.vo.pu.m23.rule.transfer.AfterTransferUtil;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * @since 6.0
 * @version 2011-6-23 上午10:29:16
 * @author liugxa
 */

public abstract class ArriveChangeVOAdjustor implements IChangeVOAdjust {
  @Override
  public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
      AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
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
    // 调用批处理方法
    return this.batchAdjustBeforeChange(new AggregatedValueObject[] {
      srcVO
    }, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject[] batchAdjustAfterChange(
      AggregatedValueObject[] srcVOs, AggregatedValueObject[] destVOs,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    ArriveVO[] resultVOs = (ArriveVO[]) ArrayUtil.convertArrayType(destVOs);
    if (resultVOs != null && resultVOs.length > 0) {
      UserDefCheckUtils.check(resultVOs, new Class[] {
        ArriveHeaderVO.class, ArriveItemVO.class
      });
      List<ArriveVO> list = new ArrayList<ArriveVO>();
      for (ArriveVO vo : resultVOs) {
				for (ArriveItemVO bvo : vo.getBVO()) {
					if(bvo.getNnum().compareTo(bvo.getNsourcenum())!=0){
						list.add(vo);
						break;
					}
				}
			}
      if(!list.isEmpty()){
      	new AfterTransferUtil(resultVOs).process();
      }else{
      	new AfterTransferUtil(resultVOs,false).process();
      }
    }

    // lixy 2012.4.26日晚 - 原本下面语句是被注释的，
    // 因为在new AfterTransferUtil(resultVOs).process()中判断了如果数量和来源数量相等则不进行联动计算
    // 但退货单参照正数采购订单的时候，因为一正一负，不相等，导致了联动计算，造成尾差。
    // 所以放开注释，进行尾差倒挤 。
    new ArriveMarginRule(PfUtilBaseTools.getRealBilltype(adjustContext
        .getSrcBilltype()), srcVOs).process(resultVOs);
    return resultVOs;
  }

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    // 交换前处理
    return srcVOs;
  }

}
