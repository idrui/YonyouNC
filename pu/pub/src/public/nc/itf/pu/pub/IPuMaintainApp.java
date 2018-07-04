package nc.itf.pu.pub;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.scmpub.res.billtype.IBillType;

public interface IPuMaintainApp<E extends IBill> {
  /**
   * ���ݲ�ѯ
   * 
   * @param scheme UI����֯�Ĳ�ѯ����
   * @return ���յ��ݺŽ�������ĵ��ݷ�ҳ����������ʽ��ֻ�е�һҳ�ĵ�һ�ŵ���
   *         ���б������ݡ�û�в�ѯ������ʱ�����㳤�ȵ�����
   * @throws BusinessException
   */
  PageQueryVO queryPuApp(IQueryScheme scheme, Class<E> clazz, String bislatest,
      String status, String vbillcode, IBillType billtype)
      throws BusinessException;

  /**
   * ���ݲ�ѯ
   * 
   * @param ids ������������
   * @return ��������ʽ��ֻ�е�һ�ŵ��ݲ��б������ݡ�
   * @throws BusinessException
   */
  IBill[] queryPuApp(String[] ids, Class<E> clazz) throws BusinessException;
}
