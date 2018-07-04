package nc.impl.pu.m23.maintain.action;

import nc.bs.pu.m23.maintain.ArriveDeleteBP;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.vo.pu.m23.entity.ArriveVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��������ɾ����Ӧ��Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-13 ����11:14:26
 */
public class ArriveDeleteAction {

  /**
   * ����������������������ɾ����Action
   * <p>
   * <b>����˵��</b>
   * 
   * @param voArray
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-13 ����11:14:57
   */
  public void deleteArrive(ArriveVO[] voArray) {
    // �������� +���TS
    new BillTransferTool<ArriveVO>(voArray);
    // ����BP
    new ArriveDeleteBP(null).deleteArrive(voArray);
  }
}
