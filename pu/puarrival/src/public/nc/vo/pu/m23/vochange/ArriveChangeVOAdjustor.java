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
 * @version 2011-6-23 ����10:29:16
 * @author liugxa
 */

public abstract class ArriveChangeVOAdjustor implements IChangeVOAdjust {
  @Override
  public AggregatedValueObject adjustAfterChange(AggregatedValueObject srcVO,
      AggregatedValueObject destVO, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    // ������������
    return this.batchAdjustAfterChange(new AggregatedValueObject[] {
      srcVO
    }, new AggregatedValueObject[] {
      destVO
    }, adjustContext)[0];
  }

  @Override
  public AggregatedValueObject adjustBeforeChange(AggregatedValueObject srcVO,
      ChangeVOAdjustContext adjustContext) throws BusinessException {
    // ������������
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

    // lixy 2012.4.26���� - ԭ����������Ǳ�ע�͵ģ�
    // ��Ϊ��new AfterTransferUtil(resultVOs).process()���ж��������������Դ��������򲻽�����������
    // ���˻������������ɹ�������ʱ����Ϊһ��һ��������ȣ��������������㣬���β�
    // ���Էſ�ע�ͣ�����β��� ��
    new ArriveMarginRule(PfUtilBaseTools.getRealBilltype(adjustContext
        .getSrcBilltype()), srcVOs).process(resultVOs);
    return resultVOs;
  }

  @Override
  public AggregatedValueObject[] batchAdjustBeforeChange(
      AggregatedValueObject[] srcVOs, ChangeVOAdjustContext adjustContext)
      throws BusinessException {
    // ����ǰ����
    return srcVOs;
  }

}
