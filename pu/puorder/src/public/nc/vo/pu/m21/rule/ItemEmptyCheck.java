/**
 * $�ļ�˵��$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-13 ����05:42:42
 */
package nc.vo.pu.m21.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������޸ı���ʱ������VO����ķǿ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-13 ����05:42:42
 */
public class ItemEmptyCheck {

  /**
   * <p>
   * <b>������Ҫ������¹��ܣ�</b>
   * <ul>
   * <li>
   * </ul>
   * <p>
   * <p>
   * 
   * @version 6.0
   * @since 6.0
   * @author wuxla
   * @time 2010-6-13 ����05:47:03
   */
  private static class ItemChkInfo {

    private String itemCode;

    private String itemName;

    public ItemChkInfo(String itemCode, String itemName) {
      this.itemCode = itemCode;
      this.itemName = itemName;
    }

    public String getItemCode() {
      return this.itemCode;
    }

    public String getItemName() {
      return this.itemName;
    }
  }

  public void checkEmpty(OrderVO[] orderVOs) {
    if (ArrayUtils.isEmpty(orderVOs)) {
      return;
    }

    ItemChkInfo[] headInfos = new ItemChkInfo[9];
    headInfos[0] =
        new ItemChkInfo(OrderHeaderVO.DBILLDATE, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0003550")/* @res "��������" */);
    headInfos[1] =
        new ItemChkInfo(OrderHeaderVO.PK_SUPPLIER, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0000275")/* @res "��Ӧ��" */);
    headInfos[2] =
        new ItemChkInfo(OrderHeaderVO.PK_DEPT_V, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0004099")/* @res "�ɹ�����" */);
    headInfos[3] =
        new ItemChkInfo(OrderHeaderVO.PK_ORG, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0185")/*
                                                                     * @res
                                                                     * "�ɹ���֯���°�"
                                                                     */);
    headInfos[4] =
        new ItemChkInfo(OrderHeaderVO.PK_ORG_V, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0004091")/* @res "�ɹ���֯" */);
    headInfos[5] =
        new ItemChkInfo(OrderHeaderVO.PK_GROUP, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC001-0000072")/* @res "����" */);
    headInfos[6] =
        new ItemChkInfo(OrderHeaderVO.PK_DEPT, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0004099")/* @res "�ɹ�����" */);
    headInfos[7] =
        new ItemChkInfo(OrderHeaderVO.CTRANTYPEID, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "2UC000-000880")/*
                                                                       * @res
                                                                       * "��������"
                                                                       */);
    headInfos[8] =
        new ItemChkInfo(OrderHeaderVO.VTRANTYPECODE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "2UC000-000880")/* @res "��������" */);

    ItemChkInfo[] itemInfos = new ItemChkInfo[28];
    itemInfos[0] =
        new ItemChkInfo(OrderItemVO.CROWNO, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0003389")/* @res "�к�" */);
    itemInfos[1] =
        new ItemChkInfo(OrderItemVO.PK_MATERIAL, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0002911")/* @res "���ϱ���" */);
    itemInfos[2] =
        new ItemChkInfo(OrderItemVO.PK_SRCMATERIAL, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0186")/*
                                                                     * @res
                                                                     * "���ϰ汾��Ϣ"
                                                                     */);
    itemInfos[3] =
        new ItemChkInfo(OrderItemVO.NASTNUM, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0002282")/* @res "����" */);
    itemInfos[4] =
        new ItemChkInfo(OrderItemVO.NQTORIGPRICE, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0002306")/* @res "��˰����" */);
    itemInfos[5] =
        new ItemChkInfo(OrderItemVO.NQTORIGTAXPRICE,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
                "UC000-0001160")/* @res "��˰����" */);
    itemInfos[6] =
        new ItemChkInfo(OrderItemVO.FTAXTYPEFLAG, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0001999")/* @res "��˰���" */);
    itemInfos[7] =
        new ItemChkInfo(OrderItemVO.PK_PSFINANCEORG_V,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
                "04004030-0187")/* @res "���������֯" */);
    itemInfos[8] =
        new ItemChkInfo(OrderItemVO.PK_APFINANCEORG_V,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
                "04004030-0188")/* @res "Ӧ����֯" */);
    itemInfos[9] =
        new ItemChkInfo(OrderItemVO.PK_PSFINANCEORG,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
                "04004030-0187")/* @res "���������֯" */);
    itemInfos[10] =
        new ItemChkInfo(OrderItemVO.PK_APFINANCEORG,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
                "04004030-0188")/* @res "Ӧ����֯" */);
    itemInfos[11] =
        new ItemChkInfo(OrderItemVO.NEXCHANGERATE, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0002092")/* @res "�۱�����" */);
    itemInfos[12] =
        new ItemChkInfo(OrderItemVO.NNUM, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0189")/*
                                                                     * @res
                                                                     * "������"
                                                                     */);
    itemInfos[13] =
        new ItemChkInfo(OrderItemVO.NORIGTAXPRICE, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0190")/*
                                                                     * @res
                                                                     * "����˰����"
                                                                     */);
    itemInfos[14] =
        new ItemChkInfo(OrderItemVO.NORIGPRICE, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0191")/*
                                                                     * @res
                                                                     * "����˰����"
                                                                     */);
    itemInfos[15] =
        new ItemChkInfo(OrderItemVO.CCURRENCYID, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0192")/*
                                                                     * @res
                                                                     * "���ұ���"
                                                                     */);
    itemInfos[16] =
        new ItemChkInfo(OrderItemVO.CASTUNITID, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0193")/* @res "��λ" */);
    itemInfos[17] =
        new ItemChkInfo(OrderItemVO.CUNITID, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0194")/*
                                                                     * @res
                                                                     * "����λ"
                                                                     */);
    itemInfos[18] =
        new ItemChkInfo(OrderItemVO.CQTUNITID, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0195")/*
                                                                     * @res
                                                                     * "���۵�λ"
                                                                     */);
    itemInfos[19] =
        new ItemChkInfo(OrderItemVO.VCHANGERATE, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0002161")/* @res "������" */);
    itemInfos[20] =
        new ItemChkInfo(OrderItemVO.VQTUNITRATE, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("common", "UC000-0002116")/* @res "���ۻ�����" */);
    itemInfos[21] =
        new ItemChkInfo(OrderItemVO.NQTUNITNUM, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_0", "04004030-0196")/*
                                                                     * @res
                                                                     * "��������"
                                                                     */);

    itemInfos[22] =
        new ItemChkInfo(OrderItemVO.CSENDCOUNTRYID, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_2", "2400403002-0183")/*
                                                                       * @res
                                                                       * "������/����"
                                                                       */);
    itemInfos[23] =
        new ItemChkInfo(OrderItemVO.CRECECOUNTRYID, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_2", "2400403002-0184")/*
                                                                       * @res
                                                                       * "�ջ�����/����"
                                                                       */);
    itemInfos[24] =
        new ItemChkInfo(OrderItemVO.CTAXCOUNTRYID, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_2", "2400403002-0185")/*
                                                                       * @res
                                                                       * "��˰��/����"
                                                                       */);
    itemInfos[25] =
        new ItemChkInfo(OrderItemVO.FBUYSELLFLAG, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_2", "2400403002-0186")/*
                                                                       * @res
                                                                       * "��������"
                                                                       */);
    itemInfos[26] =
        new ItemChkInfo(OrderItemVO.CTAXCODEID, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_2", "2400403002-0187")/*
                                                                       * @res
                                                                       * "˰��"
                                                                       */);
    itemInfos[27] =
        new ItemChkInfo(OrderItemVO.FTAXTYPEFLAG, nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004030_2", "2400403004-0007")/*
                                                                       * @res
                                                                       * "��˰���"
                                                                       */);

    Map<String, PoTransTypeVO> transtypeMap = this.getTranstypeMap(orderVOs);

    for (OrderVO orderVO : orderVOs) {
      this.checkEmpty(orderVO, headInfos, itemInfos, transtypeMap);
    }
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVO
   * @param itemInfos
   * @param sb <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-17 ����09:23:37
   */
  private void checkBodyItems(OrderVO orderVO, ItemChkInfo[] itemInfos,
      StringBuilder sb, Map<String, PoTransTypeVO> transtypeMap) {
    OrderItemVO[] itemVOs = orderVO.getBVO();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return;
    }

    String vtrantypecode = orderVO.getHVO().getVtrantypecode();
    PoTransTypeVO transtypeVO = transtypeMap.get(vtrantypecode);

    boolean breceiveplan = true;
    if (null == transtypeVO || !transtypeVO.getBreceiveplan().booleanValue()) {
      breceiveplan = false;
    }

    for (OrderItemVO itemVO : itemVOs) {
      StringBuilder itemBuilder = new StringBuilder();
      for (int i = 0; i < itemInfos.length; ++i) {
        if (itemVO.getAttributeValue(itemInfos[i].getItemCode()) != null) {
          continue;
        }

        itemBuilder.append(itemInfos[i].getItemName()).append(", ");
      }

      if (!breceiveplan) {
        String pk_arrvstoorg = itemVO.getPk_arrvstoorg_v();
        if (StringUtil.isEmptyWithTrim(pk_arrvstoorg)) {
          itemBuilder
              .append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
                  "04004030-0306", null, new String[] {})/* �ջ������֯�� */);
        }
      }

      if (itemBuilder.length() > 0) {
        itemBuilder.deleteCharAt(itemBuilder.length() - 1);
        sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
            "04004030-0303", null, new String[] {
              itemVO.getCrowno(), itemBuilder.toString()
            })/* ��{0}�У�{1}\n */);
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004030_0", "04004030-0304", null, new String[] {
            sb.toString()
          })/* �����ֶγ��ֿ�ֵ��\n{0} */);
    }
  }

  /**
   * ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param orderVO <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-13 ����05:48:46
   */
  private void checkEmpty(OrderVO orderVO, ItemChkInfo[] headInfos,
      ItemChkInfo[] itemInfos, Map<String, PoTransTypeVO> transtypeMap) {
    StringBuilder sb = new StringBuilder();
    this.checkHeadItems(orderVO, headInfos, sb);
    this.checkBodyItems(orderVO, itemInfos, sb, transtypeMap);
  }

  private void checkHeadItems(OrderVO orderVO, ItemChkInfo[] headInfos,
      StringBuilder sb) {
    StringBuilder headBuilder = new StringBuilder();
    OrderHeaderVO headerVO = orderVO.getHVO();
    for (int i = 0; i < headInfos.length; ++i) {
      Object value = headerVO.getAttributeValue(headInfos[i].getItemCode());
      if (value != null && value.toString().length() > 0) {
        continue;
      }

      headBuilder.append(headInfos[i].getItemName()).append(", ");
    }

    if (headBuilder.length() > 0) {
      headBuilder.deleteCharAt(headBuilder.length() - 1);
      sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
          "04004030-0305", null, new String[] {
            headBuilder.toString()
          })/* ��ͷ��{0}\n */);
    }
  }

  private Map<String, PoTransTypeVO> getTranstypeMap(OrderVO[] orderVOs) {
    Set<String> set = new HashSet<String>();
    for (OrderVO orderVO : orderVOs) {
      set.add(orderVO.getHVO().getVtrantypecode());
    }

    if (set.size() > 0) {
      IPoTransTypeQuery query =
          NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
      try {
        return query.queryAttrByTypes(set.toArray(new String[set.size()]),
            new String[] {
              PoTransTypeVO.BRECEIVEPLAN
            });
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);

      }
    }

    return new HashMap<String, PoTransTypeVO>();
  }
}
