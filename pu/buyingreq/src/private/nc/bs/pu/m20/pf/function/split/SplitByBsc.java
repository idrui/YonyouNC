package nc.bs.pu.m20.pf.function.split;

import java.util.List;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;

/**
 * �����ϵͳ���빺���������������Խ��зֵ�
 * 
 * @since 6.0
 * @version 2011-6-21 ����08:17:34
 * @author liuchx
 */
public class SplitByBsc {
  /**
   * �����ϵͳ���빺���������������Խ��зֵ�
   * 
   * @param vo
   * @return
   * @throws BusinessException
   */
  public List<String> split(AggregatedValueObject vo) throws BusinessException {

    // PraybillVO bill = (PraybillVO)vo;
    // PraybillItemVO[] items = bill.getBVO();
    //
    //
    // MaterialStockVO[] stockInfos = this.getStockInfo(items, vo);
    // if (ArrayUtils.isEmpty(stockInfos)) {
    // continue;
    // }
    //
    // for (MaterialStockVO stockInfo : stockInfos) {
    // if (IMaterialEnumConst.MATERTYPE_OT == stockInfo.getMartype()) {
    // vo.getHVO().setBsctype(UFBoolean.TRUE);
    // }
    // }
    //
    return null;
  }
}
