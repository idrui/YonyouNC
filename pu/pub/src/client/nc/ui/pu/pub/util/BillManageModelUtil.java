/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-27 ����09:33:59
 */
package nc.ui.pu.pub.util;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�Ե��ݵĹ���ģ��ʵ�ù���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-27 ����09:33:59
 */
public class BillManageModelUtil {

  /**
   * ����������������ģ���в�ѯĳ��VO��������
   * <p>
   * <b>����˵��</b>
   * 
   * @param model
   * @param vo
   * @return index in model,if not exists, reutrn -1
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-4-27 ����09:36:25
   */
  public static int getIndex(BillManageModel model, Object vo) {
    try {
      String pk = ((AggregatedValueObject) vo).getParentVO().getPrimaryKey();
      @SuppressWarnings("rawtypes")
      List datas = model.getData();
      for (int i = 0; i < datas.size(); ++i) {
        Object data = datas.get(i);
        if (data instanceof AggregatedValueObject) {
          String datapk =
              ((AggregatedValueObject) data).getParentVO().getPrimaryKey();
          if (datapk.equals(pk)) {
            return i;
          }
        }
      }
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }
    return -1;
  }

  /**
   * ��ñ�����ʾ����key����
   * 
   * @param cardPanel
   * @return String[]
   * @author wangljc
   * @date 2011-7-9 11:29:52
   */
  public static String[] getSelectItems(BillCardPanel cardPanel) {
    BillItem[] showItems = cardPanel.getBodyShowItems();
    List<String> listItem = new ArrayList<String>();
    if (showItems == null || showItems.length == 0) {
      return null;
    }
    for (int i = 0; i < showItems.length; i++) {
      if (showItems[i].isShow()) {
        listItem.add(showItems[i].getKey());
      }
    }
    return listItem.toArray(new String[listItem.size()]);
  }
}
