package nc.vo.pu.m21.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.pf.PfBillItfDefUtil;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.scmpub.reference.uap.pf.TransTypeMapping;
import nc.pubitf.scmf.pu.ordertranstype.pu.IQueryOrdertranstypeForPu;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.rule.BusitypeFillRule;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

/**
 * ��ȡ�������ͣ����ȸ��ݼ���+�����֯+�ɹ���֯+����ƥ�����϶������ͣ�û��ƥ�䵽������²�ѯ�ӿڹ�ϵ���塣
 * 
 * @since 6.1
 * @version 2012-2-14 ����01:46:49
 * @author lixyp
 */
public class TrantypeValueRule {
  private OrderVO[] orderVOs = null;

  public TrantypeValueRule(OrderVO[] orderVOs) {
    this.orderVOs = orderVOs;
  }

  public void setTrantypeValue() {
    // �����϶�����������ȡ����ƥ��Ľ������͡�
    Map<String, String> trantypeMap = this.getTranstype();

    for (OrderVO orderVO : this.orderVOs) {
      String pk_org = orderVO.getHVO().getPk_org();

      // ����Ѿ����ڽ������ͣ���ֱ��ָ��ҵ�����̺��˳���
      // if (orderVO.getHVO().getVtrantypecode() != null) {
      // this.setBusitype(orderVO);
      // continue;
      // }

      String trantype = null;
      for (OrderItemVO orderItemVO : orderVO.getBVO()) {
        String pk_srcMaterial = orderItemVO.getPk_srcmaterial();
        // ������ȷ�Ϲ���Ӧ������������֯
        String stockorg = orderItemVO.getPk_reqstoorg();
        String pk = pk_org + stockorg + pk_srcMaterial;
        trantype = trantypeMap.get(pk);
        if (trantype != null) {
          break;
        }
      }

      if (trantype == null) {
        trantype = this.getDestTransType(orderVO);
      }
      if (trantype != null) {
        orderVO.getHVO().setVtrantypecode(trantype);
        orderVO.getHVO().setCtrantypeid(
            PfServiceScmUtil.getTrantypeidByCode(new String[] {
              trantype
            }).get(trantype));
        this.setBusitype(orderVO);
      }
    }
  }

  /**
   * �ӽӿڹ�ϵ�����в��ҽ������͡�
   * 
   * @param orderVO
   * @return ��������
   */
  private String getDestTransType(OrderVO orderVO) {
    OrderItemVO[] orderItemVOs = orderVO.getBVO();
    if (orderItemVOs.length > 0) {
      String billtype = orderItemVOs[0].getCsourcetypecode();
      String trantype = orderItemVOs[0].getVsourcetrantype();
      if (StringUtils.isEmpty(billtype) && StringUtils.isEmpty(trantype)) {
        return null;
      }

      TransTypeMapping mapping = new TransTypeMapping();
      mapping.setSrcBillType(billtype);
      mapping.setSrcTransType(trantype);
      mapping.setDestBillType(POBillType.Order.getCode());

      // ȡ�ö�������
      PfBillItfDefUtil.queryTransTypeMapping(orderVO.getHVO().getPk_group(),
          mapping);
      return mapping.getDestTransTypeCode();
    }

    return null;
  }

  /**
   * �����������������ݼ��š��ɹ���֯�������֯�����������϶������������в��Ҷ������͡�
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   *         keyΪ�����֯+�ɹ���֯+���ϡ�valueΪ��������
   * @since 6.1
   * @author lixy
   * @time 2010-12-23 ����10:13:19
   */
  private Map<String, String> getTranstype() {
    Map<String, String> retMap = new HashMap<String, String>();
    if (this.orderVOs.length == 0) {
      return retMap;
    }

    // ������ѯ�ӿڱ�Ҫ���ĸ�������
    String pk_group = this.orderVOs[0].getHVO().getPk_group();
    List<String> materialList = new ArrayList<String>();
    List<String> pk_orgs = new ArrayList<String>();
    List<String> stockOrgs = new ArrayList<String>();

    // �ռ������������ĸ�����
    for (OrderVO orderVO : this.orderVOs) {
      // �ɹ���֯
      String pk_org = orderVO.getHVO().getPk_org();
      for (OrderItemVO orderItemVO : orderVO.getBVO()) {
        // ����
        materialList.add(orderItemVO.getPk_srcmaterial());
        pk_orgs.add(pk_org);
        // ������ȷ����������֯
        stockOrgs.add(orderItemVO.getPk_reqstoorg());
      }
    }
    // ���ýӿڲ�ѯ��
    IQueryOrdertranstypeForPu service =
        NCLocator.getInstance().lookup(IQueryOrdertranstypeForPu.class);
    retMap =
        service.getTranstypeByOrg(pk_group,
            pk_orgs.toArray(new String[pk_orgs.size()]),
            materialList.toArray(new String[materialList.size()]),
            stockOrgs.toArray(new String[stockOrgs.size()]));

    return retMap;
  }

  /**
   * ��������
   * 
   * @param orderVO
   */
  private void setBusitype(OrderVO orderVO) {
    // ȷ��ҵ�����̣��Ա������̺���
    new BusitypeFillRule(new BillHelper<OrderVO>(orderVO),
        POBillType.Order.getCode()).process();
  }
}
