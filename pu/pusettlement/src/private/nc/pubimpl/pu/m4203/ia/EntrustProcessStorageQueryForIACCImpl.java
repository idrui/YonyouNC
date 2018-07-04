package nc.pubimpl.pu.m4203.ia;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.pu.m4203.ia.IEntrustProcessStorageQueryForIACC;
import nc.vo.pu.m4203.entity.SubcontinFIHeaderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.scmpub.parameter.ia.QueryParaVO;

/**
 * @ClassName:EntrustProcessStorageQueryForIACCImpl
 * @Description:ί�мӹ���Ϊ��������ṩ���˼��ӿڵ�ʵ����
 * @author liyjp
 * @date 2014-11-24 ����11:30:34
 */
public class EntrustProcessStorageQueryForIACCImpl implements
    IEntrustProcessStorageQueryForIACC {
  /**
   * @Title:queryUnsettledPFI
   * @Description:�������δ����ί�мӹ�����ѯ
   * @param: queryParaVO ��ѯ����VO
   *         ����pk_financeorg������֯��startData����ڼ俪ʼ���ڡ�endpData����ڼ��������
   * @return String[] �ɹ���ⵥ��ͷVO���������飨Pk_stockps��
   * @throws BusinessException ҵ���쳣
   */
  @Override
  public String[] queryUnsettledEPS(QueryParaVO queryParaVO)
      throws BusinessException {
    if (queryParaVO == null) {
      return null;
    }
    String pk_financeorg = queryParaVO.getPk_financeorg(); // ������֯
    UFDate startData = queryParaVO.getStartData(); // ����ڼ俪ʼ����
    UFDate endpData = queryParaVO.getEndData(); // ����ڼ��������

    /*
     * ƴ��sql���
     * select * from po_subcontinfi where dr=0 and pk_org='0001D11000000000GHDQ'
     * and pk_stockps in(
     * select pk_stockps from po_subcontinfi_b where (dbizdate between
     * '2014-11-06 00:00:00' and '2015-11-06 00:00:00') andr dr=0 and
     * dtocostapdate is null and bsettlefinish='N')
     */
    StringBuffer sb =
        new StringBuffer(" and pk_org='" + pk_financeorg
            + "' and pk_stockps in(");
    sb.append("select pk_stockps from po_subcontinfi_b where dr=0 and "
        + "dtocostapdate is" +
        " null and bsettlefinish='N'");
    if (startData != null) {
      sb.append(" and (dbizdate between '" + startData);
      if (endpData != null) {
        sb.append("' and '" + endpData + "')");
      }
      else {
        sb.append("' and '" + startData + "')");
      }
    }
    sb.append(")");
    /* ��ѯ���ݿ� ��ñ�ͷʵ�� */
    VOQuery<SubcontinFIHeaderVO> voquery =
        new VOQuery<SubcontinFIHeaderVO>(SubcontinFIHeaderVO.class);
    SubcontinFIHeaderVO[] subcontinFIHeaderVO =
        voquery.query(sb.toString(), null);

    /* ����ί�мӹ����������� */
    String[] ids = new String[subcontinFIHeaderVO.length];
    int count = 0;
    for (SubcontinFIHeaderVO vo : subcontinFIHeaderVO) {
      ids[count] = vo.getPk_stockps();
      count++;
    }

    return ids;
  }
}
