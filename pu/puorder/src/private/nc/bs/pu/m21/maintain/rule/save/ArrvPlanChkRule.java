/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-12-29 ����02:43:26
 */
package nc.bs.pu.m21.maintain.rule.save;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              �ɹ��������е����ƻ��Ķ��������н��м��
 * @scene
 *        �ɹ����������޸�
 * @param
 *        OrderVO[] orgVos ԭʼ����vo
 * @since 6.3
 * @version 2014-10-22 ����11:29:55
 * @author luojw
 */
public class ArrvPlanChkRule implements IRule<OrderVO> {

  private OrderVO[] orgVos = null;

  public ArrvPlanChkRule(OrderVO[] orgVos) {
    this.orgVos = orgVos;
  }

  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    StringBuilder errMsg = new StringBuilder();
    // Map<String, OrderItemVO> map = AggVOUtil.createItemMap(this.orgVos);
    BillIndex index = new BillIndex(this.orgVos);
    IVOMeta meta = this.orgVos[0].getMetaData().getVOMeta(OrderItemVO.class);
    for (OrderVO vo : vos) {
      if (VOStatus.NEW == vo.getHVO().getStatus()) {
        continue;
      }

      String vbillcode = vo.getHVO().getVbillcode();
      for (OrderItemVO itemVO : vo.getBVO()) {
        if (!ValueUtils.getUFBoolean(itemVO.getBreceiveplan()).booleanValue()) {
          continue;
        }

        if (VOStatus.DELETED == itemVO.getStatus()) {
          errMsg.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
              "04004030-0230", null, new String[] {
                vbillcode, itemVO.getCrowno()
              })/* ���ݺţ�{0}�кţ�{1}���ڵ����ƻ�����ɾ����\n */);
        }
        else {
          // ������
          UFDouble nnum = itemVO.getNnum();
          // �ۼƵ����ƻ�����
          UFDouble accArrvPlanNum = itemVO.getNaccumrpnum();

          // �������� ����С���ۼƵ����ƻ�����
          if (MathTool.compareTo(nnum, accArrvPlanNum) < 0) {
            errMsg.append(NCLangResOnserver.getInstance().getStrByID(
                "4004030_0", "04004030-0231", null, new String[] {
                  vbillcode, itemVO.getCrowno()
                })/* ���ݺţ�{0}�кţ�{1}����������С���ۼ����������ƻ�����!\n */);
          }

          // OrderItemVO orgItemVO = map.get(itemVO.getPk_order_b());
          OrderItemVO orgItemVO =
              (OrderItemVO) index.get(meta, itemVO.getPk_order_b());
          // ���ϲ��ܸı�
          if (null != orgItemVO
              && !orgItemVO.getPk_material().equals(itemVO.getPk_material())) {
            errMsg.append(NCLangResOnserver.getInstance().getStrByID(
                "4004030_0", "04004030-0232", null, new String[] {
                  vbillcode, itemVO.getCrowno()
                })/* ���ݺţ�{0}�кţ�{1}���ڵ����ƻ��Ķ����в��ܸı�����!\n */);
          }
          // ��λ���ܸı�
          if (null != orgItemVO
              && !orgItemVO.getCastunitid().equals(itemVO.getCastunitid())) {
            errMsg.append(NCLangResOnserver.getInstance().getStrByID(
                "4004030_0", "04004030-0233", null, new String[] {
                  vbillcode, itemVO.getCrowno()
                })/* ���ݺţ�{0}�кţ�{1}���ڵ����ƻ��Ķ����в��ܸı䵥λ!\n */);
          }

        }

      }
    }

    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }

}
