/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-28 ����09:28:36
 */
package nc.impl.pu.m21.action.rule.revise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.DownFlowCheck;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;
import nc.vo.pubapp.pattern.model.tool.BillIndex;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * @description
 *              �ɹ��������к������ݣ��������κš������š���Ŀ���ջ��ֿ⡢�ջ���ַ����Ʒ�����޸�
 * @scene
 *        �ɹ������޶�
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����10:34:41
 * @author luojw
 */
public class ItemChangeCheckRule implements ICompareRule<OrderVO> {

  private Map<String, String> checkFieldmap;

  private String[] items;

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.ICompareRule#process(E[], E[])
   */
  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    // �������κš������š���Ŀ���ջ��ֿ⡢�ջ���ַ����Ʒ���ջ������֯
    this.items =
        new String[] {
          OrderItemVO.VBATCHCODE, OrderItemVO.PK_REQDEPT,
          OrderItemVO.CPROJECTID, OrderItemVO.PK_RECVSTORDOC,
          OrderItemVO.PK_RECEIVEADDRESS, OrderItemVO.BLARGESS,
          OrderItemVO.PK_ARRVSTOORG
        };
    this.checkFieldmap = this.getCheckFieldMap(this.items);

    StringBuilder sb = new StringBuilder();
    // ��VO�ı������ɣ۱�������������ݵ�Maps
    // Map<String, OrderItemVO> originItemMap =
    // AggVOUtil.createItemMap(originVOs);
    BillIndex index = new BillIndex(originVOs);

    for (OrderVO orderVO : vos) {
      // this.changeCheck(orderVO, originItemMap, sb);
      this.changeCheck(orderVO, index, sb);
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

  /**
   * ������������������ֶ��Ƿ��޸�
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVO
   * @param originItemMap
   * @param sb
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-16 ����04:10:42
   */
  // private void changeCheck(OrderVO orderVO,
  // Map<String, OrderItemVO> originItemMap, StringBuilder sb) {
  // if (null == orderVO || ArrayUtils.isEmpty(orderVO.getBVO())) {
  // return;
  // }
  //
  // DownFlowCheck check = new DownFlowCheck();
  // for (OrderItemVO itemVO : orderVO.getBVO()) {
  // // �ж�״̬�ǲ���UPDATED
  // if (null == itemVO || itemVO.getStatus() != VOStatus.UPDATED) {
  // continue;
  // }
  //
  // // �õ��ı���
  // List<String> changes =
  // this.changeItems(itemVO, originItemMap.get(itemVO.getPk_order_b()));
  // if (changes.isEmpty()) {
  // continue;
  // }
  //
  // // �Ƿ��к�������
  // if (check.hasDownFlow(itemVO)) {
  // sb.append("����" + orderVO.getHVO().getVbillcode() + "��"
  // + itemVO.getCrowno() + "�д��ں������ݣ������޸������ֶΣ�");
  // for (int j = 0; j < changes.size(); ++j) {
  // if (j > 0) {
  // sb.append("��");
  // }
  // sb.append(this.checkFieldmap.get(changes.get(j)));
  // }
  // }
  // }
  // }
  private void changeCheck(OrderVO orderVO, BillIndex index, StringBuilder sb) {
    if (null == orderVO || ArrayUtils.isEmpty(orderVO.getBVO())) {
      return;
    }

    DownFlowCheck check = new DownFlowCheck();
    IVOMeta meta = orderVO.getMetaData().getVOMeta(OrderItemVO.class);
    for (OrderItemVO itemVO : orderVO.getBVO()) {
      // �ж�״̬�ǲ���UPDATED
      if (null == itemVO || itemVO.getStatus() != VOStatus.UPDATED) {
        continue;
      }

      // �õ��ı���
      List<String> changes =
          this.changeItems(itemVO,
              (OrderItemVO) index.get(meta, itemVO.getPk_order_b()));
      if (changes.isEmpty()) {
        continue;
      }

      // �Ƿ��к�������
      if (check.hasDownFlow(itemVO)) {
        sb.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
            "04004030-0286", null, new String[] {
              orderVO.getHVO().getVbillcode(), itemVO.getCrowno()
            })/* ����{0}��{1}�д��ں������ݣ������޸������ֶΣ� */);
        for (int j = 0; j < changes.size(); ++j) {
          if (j > 0) {
            sb.append(", ");
          }
          sb.append(this.checkFieldmap.get(changes.get(j)));
        }
      }
    }
  }

  /**
   * ���������������õ��ı����
   * <p>
   * <b>����˵��</b>
   * 
   * @param vo
   * @param originVO
   * @param items
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-28 ����10:00:12
   */
  private List<String> changeItems(OrderItemVO vo, OrderItemVO originVO) {
    List<String> changeItems = new ArrayList<String>();
    for (int i = 0; i < this.items.length; ++i) {
      Object value = vo.getAttributeValue(this.items[i]);
      Object originValue = originVO.getAttributeValue(this.items[i]);
      // value�޶���������ǰ̨����ֵΪ�� ����originValue���Ժ�̨����ֵΪnull����Ҫ�����ߴ����һ�±Ƚ�
      if (!ObjectUtils.equals(ObjectUtils.toString(value).trim(), ObjectUtils
          .toString(originValue).trim())) {
        changeItems.add(this.items[i]);
      }
    }

    return changeItems;
  }

  /**
   * �������������������Map[key:�ֶΣ�value:����]
   * <p>
   * <b>����˵��</b>
   * 
   * @param checkItems
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-16 ����04:05:09
   */
  private Map<String, String> getCheckFieldMap(String[] checkItems) {
    Map<String, String> map = new HashMap<String, String>();
    IVOMeta vometa = VOMetaFactory.getInstance().getVOMeta(PUEntity.M21_B);
    for (String item : checkItems) {
      map.put(item, vometa.getAttribute(item).toString());
    }
    return map;
  }

}
