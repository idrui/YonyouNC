package nc.pubimpl.pu.m20.invp.inv9;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.pu.m20.invp.inv9.IBuyingreqQueryForInv9;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * �빺����Ϊ���ƻ��Ĺ������ṩ�Ĳ�ѯ����ʵ����
 * 
 * @since 6.0
 * @version 2010-12-14 ����06:21:00
 * @author duy
 */
public class BuyingreqQueryForInv9Impl implements IBuyingreqQueryForInv9 {

  /**
   * ��ѯ�빺���Ƿ�������
   * 
   * @param pk_req
   * @return Map<String, UFBoolean> Key:��ͷPK ;Value:�Ƿ���������trueΪ������
   * @throws BusinessException
   */
  @Override
  public Map<String, UFBoolean> getIsApprovedOfBuyingreq(String[] pk_praybill)
      throws BusinessException {
    try {
      VOQuery<PraybillHeaderVO> query =
          new VOQuery<PraybillHeaderVO>(PraybillHeaderVO.class, new String[] {
            PraybillHeaderVO.PK_PRAYBILL, PraybillHeaderVO.FBILLSTATUS
          });
      PraybillHeaderVO[] vos = query.query(pk_praybill);

      Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
      for (PraybillHeaderVO vo : vos) {
        if (POEnumBillStatus.FREE.toInt() != vo.getFbillstatus().intValue()) {
          map.put(vo.getPk_praybill(), UFBoolean.TRUE);
        }
        else {
          map.put(vo.getPk_praybill(), UFBoolean.FALSE);
        }
      }
      return map;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
