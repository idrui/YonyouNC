package nc.pubimpl.pu.m4202.ia;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.pu.m4202.ia.IConsumeGatherQueryForIAClosingCheck;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.scmpub.parameter.ia.QueryParaVO;

/**
 * @ClassName:ConsumeGatherQueryForIAClosingCheck
 * @Description:���Ļ��ܵ��Ĵ��������˽ӿڵ�ʵ����
 * @author liyjp
 * @date 2014-11-24 ����4:03:35
 */
public class ConsumeGatherQueryForIAClosingCheckImpl implements
    IConsumeGatherQueryForIAClosingCheck {
  /**
   * @Title:queryUnsettledConsumeGather
   * @Description:�������δ�������Ļ��ܵ���ѯ�ӿ�
   * @param:@param queryParaVO ��ѯ����VO
   *               ����pk_financeorg������֯��startData����ڼ俪ʼ���ڡ�endpData����ڼ��������
   * @return String[] ���Ļ��ܲ����ͷVO������[]��Pk_stockps_b��
   * @throws BusinessException ҵ���쳣
   */
  @Override
  public String[] queryUnsettledConsumeGather(QueryParaVO queryParaVO)
      throws BusinessException {

    if (queryParaVO == null) {
      return null;
    }
    String pk_financeorg = queryParaVO.getPk_financeorg(); // ������֯
    UFDate startData = queryParaVO.getStartData(); // ����ڼ俪ʼ����
    UFDate endpData = queryParaVO.getEndData(); // ����ڼ��������
    /*
     * �齨sql
     * select * from po_vmifi where dr=0 and (dbilldate between 'A' and 'B') and
     * pk_org='' and (dtocostapdate is null) and bsettlefinish='N';
     */
    StringBuffer sb =
        new StringBuffer(" and pk_org='" + pk_financeorg
            + "' and (dtocostapdate is null) and bsettlefinish='N'");
    if (startData != null) {
      sb.append("and (dbilldate between '" + startData);
      if (endpData != null) {
        sb.append("' and '" + endpData + "')");
      }
      else {
        sb.append("' and '" + startData + "')");
      }
    }
    /* �����ݿ⽻�� */
    VOQuery<VmiFIHeaderVO> voquery =
        new VOQuery<VmiFIHeaderVO>(VmiFIHeaderVO.class);
    VmiFIHeaderVO[] vmiFIHeaderVOs = voquery.query(sb.toString(), null);

    /* �������Ļ��ܵ��������� */
    String[] ids = new String[vmiFIHeaderVOs.length];
    int count = 0;
    for (VmiFIHeaderVO vo : vmiFIHeaderVOs) {
      ids[count] = vo.getPk_stockps_b();
      count++;
    }
    return ids;
  }
}
