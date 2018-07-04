/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-25 ����08:15:27
 */
package nc.vo.pu.m21.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.pf.PfBillItfDefUtil;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.scmpub.reference.uap.pf.TransTypeMapping;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.pubitf.scmf.pu.ordertranstype.pu.IQueryOrdertranstypeForPu;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.pub.rule.BusitypeFillRule;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-25 ����08:15:27
 */
public class TrantypeValue {

  private String csrcbilltype;

  private IKeyValue keyValue;

  private String vsrctrantype;

  public TrantypeValue(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public String getCsrcbilltype() {
    return this.csrcbilltype;
  }

  public String getVsrctrantype() {
    return this.vsrctrantype;
  }

  public void setCsrcbilltype(String csrcbilltype) {
    this.csrcbilltype = csrcbilltype;
  }

  /**
   * ��������������ֱ�˽�������
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-8-10 ����03:49:45
   */
  public void setDirectTrantypeValue() {
    // String vorgtypecode =
    // (String) this.keyValue.getHeadValue(OrderHeaderVO.VTRANTYPECODE);
    // if (vorgtypecode != null && !this.isTrantypeDirect(vorgtypecode)) {
    // this.keyValue.setHeadValue(OrderHeaderVO.CTRANTYPEID, null);
    // this.keyValue.setHeadValue(OrderHeaderVO.VTRANTYPECODE, null);
    // }
    // if (this.keyValue.getHeadValue(OrderHeaderVO.VTRANTYPECODE) != null) {
    // this.setBusitype();
    // return;
    // }
    //
    Map<String, String> trantypeMap = this.getTranstype();
    Map<String, PoTransTypeVO> directMap = this.getDirectMap(trantypeMap);

    String pk_org = (String) this.keyValue.getHeadValue(OrderHeaderVO.PK_ORG);
    if (StringUtil.isEmptyWithTrim(pk_org)) {
      return;
    }
    String trantype = null;
    String vtrantypecode = null;
    for (int i = 0; i < this.keyValue.getItemCount(); ++i) {
      String pk_srcmaterial =
          (String) this.keyValue.getBodyValue(i, OrderItemVO.PK_SRCMATERIAL);
      if (StringUtil.isEmptyWithTrim(pk_srcmaterial)) {
        continue;
      }
      String stockorg =
          (String) this.keyValue.getBodyValue(i, OrderItemVO.PK_REQSTOORG);
      String pk = pk_org.toString() + stockorg + pk_srcmaterial.toString();
      trantype = trantypeMap.get(pk);
      if (null == trantype) {
        continue;
      }
      PoTransTypeVO potranstypevo = directMap.get(trantype);
      if (potranstypevo != null && potranstypevo.getBdirect().booleanValue()) {
        vtrantypecode = potranstypevo.getVtrantypecode();
        trantype = potranstypevo.getCtrantypeid();
        break;
      }
      trantype = null;
    }

    if (null == vtrantypecode) {
      vtrantypecode = this.getDestTransType();
      if (vtrantypecode != null) {
        try {
          IPoTransTypeQuery service =
              NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
          Map<String, PoTransTypeVO> potrantypeMap =
              service.queryAttrByTypes(new String[] {
                vtrantypecode
              }, new String[] {
                PoTransTypeVO.VTRANTYPECODE, PoTransTypeVO.CTRANTYPEID,
                PoTransTypeVO.BDIRECT
              });
          PoTransTypeVO potrantyepvo = potrantypeMap.get(vtrantypecode);
          if (potrantyepvo != null
              && UFBoolean.TRUE.equals(potrantyepvo.getBdirect())) {
            vtrantypecode = potrantyepvo.getVtrantypecode();
            trantype = potrantyepvo.getCtrantypeid();
          }
          else {
            trantype = null;
            vtrantypecode = null;
          }
        }
        catch (BusinessException e) {
          ExceptionUtils.wrappException(e);
        }
      }
    }
    if (vtrantypecode != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.CTRANTYPEID, trantype);
      this.keyValue.setHeadValue(OrderHeaderVO.VTRANTYPECODE, vtrantypecode);
      this.setBusitype();
    }
  }

  /**
   * �����������������ݱ�������ϻ��߶�Ӧ�����Ϸ���+�ɹ���֯ƥ�����϶��������������ҵ���Ӧ�Ĳɹ������������ͣ�
   * ����ƥ��ʱ���ϼ����Ϸ��ఴ����ϸ���ȵĹ�����С� ���ƥ�����϶�����������δƥ�䵽��Ӧֵ����ƥ�������ε��ݽӿڹ�ϵ�����ȡĬ��ֵ��
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.1
   * @author lixyp
   * @time 2011-12-23 ����09:44:24
   */
  public void setTrantypeValue() {
    // ����Ѿ����ڽ������ͣ���ֱ��ָ��ҵ�����̺��˳���
    // if (this.keyValue.getHeadValue(OrderHeaderVO.VTRANTYPECODE) != null) {
    // this.setBusitype();
    // return;
    // }

    Map<String, String> trantypeMap = this.getTranstype();
    String pk_org = (String) this.keyValue.getHeadValue(OrderHeaderVO.PK_ORG);
    if (StringUtil.isEmptyWithTrim(pk_org)) {
      return;
    }

    String trantype = null;
    for (int i = 0; i < this.keyValue.getItemCount(); ++i) {
      String pk_srcmaterial =
          (String) this.keyValue.getBodyValue(i, OrderItemVO.PK_SRCMATERIAL);
      if (StringUtil.isEmptyWithTrim(pk_srcmaterial)) {
        continue;
      }
      String stockorg =
          (String) this.keyValue.getBodyValue(i, OrderItemVO.PK_REQSTOORG);
      String pk = pk_org + stockorg + pk_srcmaterial;
      trantype = trantypeMap.get(pk);
      if (trantype != null) {
        break;
      }
    }

    if (null == trantype) {
      trantype = this.getDestTransType();
    }
    if (trantype != null) {
      this.keyValue.setHeadValue(OrderHeaderVO.VTRANTYPECODE, trantype);
      this.keyValue.setHeadValue(OrderHeaderVO.CTRANTYPEID, PfServiceScmUtil
          .getTrantypeidByCode(new String[] {
            trantype
          }).get(trantype));
      this.setBusitype();
    }
  }

  public void setVsrctrantype(String vsrctrantype) {
    this.vsrctrantype = vsrctrantype;
  }

  /**
   * ��������������ƥ�������ε��ݽӿڹ�ϵ������Ҷ������͡�
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   *         ����Ŀ�Ľ������ͱ���
   * @since 6.1
   * @author lixyp
   * @time 2011-12-23 ����01:26:03
   */
  private String getDestTransType() {
    String billtype = this.getSrcBilltype();
    String trantype = this.getSrcTrantype();
    if (StringUtil.isEmptyWithTrim(billtype)
        && StringUtil.isEmptyWithTrim(trantype)) {
      return null;
    }

    TransTypeMapping mapping = new TransTypeMapping();
    mapping.setSrcBillType(billtype);
    mapping.setSrcTransType(trantype);
    mapping.setDestBillType(POBillType.Order.getCode());

    // ȡ�ö�������
    PfBillItfDefUtil.queryTransTypeMapping(
        (String) this.keyValue.getHeadValue(OrderItemVO.PK_GROUP), mapping);

    return mapping.getDestTransTypeCode();
  }

  private Map<String, PoTransTypeVO> getDirectMap(
      Map<String, String> trantypeMap) {
    if (0 == trantypeMap.size()) {
      return new HashMap<String, PoTransTypeVO>();
    }

    Set<String> transtypecodes = new HashSet<String>();
    for (Map.Entry<String, String> entry : trantypeMap.entrySet()) {
      transtypecodes.add(entry.getValue());
    }

    if (0 == transtypecodes.size()) {
      return new HashMap<String, PoTransTypeVO>();
    }

    IPoTransTypeQuery service =
        NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
    try {
      return service.queryAttrByTypes(
          transtypecodes.toArray(new String[transtypecodes.size()]),
          new String[] {
            PoTransTypeVO.CTRANTYPEID, PoTransTypeVO.VTRANTYPECODE,
            PoTransTypeVO.BDIRECT
          });
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    return new HashMap<String, PoTransTypeVO>();
  }

  /**
   * ����������������Դ��������
   * <p>
   * <b>����˵��</b>
   * 
   * @return <p>
   * @since 6.1
   * @author lixyp
   * @time 2011-12-23 ����02:22:52
   */
  private String getSrcBilltype() {
    if (this.getCsrcbilltype() != null) {
      return this.getCsrcbilltype();
    }
    for (int i = 0; i < this.keyValue.getItemCount(); ++i) {
      String srcBilltype =
          (String) this.keyValue.getBodyValue(i, OrderItemVO.CSOURCETYPECODE);
      if (srcBilltype != null) {
        return srcBilltype;
      }
    }

    return null;
  }

  private String getSrcTrantype() {
    if (this.getVsrctrantype() != null) {
      return this.getVsrctrantype();
    }
    for (int i = 0; i < this.keyValue.getItemCount(); ++i) {
      String srcTrantype =
          (String) this.keyValue.getBodyValue(i, OrderItemVO.VSOURCETRANTYPE);
      if (srcTrantype != null) {
        return srcTrantype;
      }
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
    // �ռ��������ϼ��ϡ�
    List<String> materialList = new ArrayList<String>();
    List<String> orgList = new ArrayList<String>();
    List<String> stockList = new ArrayList<String>();
    String pk_org = (String) this.keyValue.getHeadValue(OrderHeaderVO.PK_ORG);
    for (int i = 0; i < this.keyValue.getItemCount(); ++i) {
      String pk_srcmaterial =
          (String) this.keyValue.getBodyValue(i, OrderItemVO.PK_SRCMATERIAL);
      if (!StringUtil.isEmptyWithTrim(pk_srcmaterial)) {
        materialList.add(pk_srcmaterial);
        orgList.add(pk_org);
        stockList.add((String) this.keyValue.getBodyValue(i,
            OrderItemVO.PK_REQSTOORG));
      }
    }

    String pk_group =
        (String) this.keyValue.getHeadValue(OrderHeaderVO.PK_GROUP);
    if (StringUtil.isEmptyWithTrim(pk_group)
        || StringUtil.isEmptyWithTrim(pk_org) || materialList.isEmpty()) {
      return new HashMap<String, String>();
    }

    String[] pk_orgs = orgList.toArray(new String[orgList.size()]);
    String[] stockorgs = stockList.toArray(new String[stockList.size()]);

    IQueryOrdertranstypeForPu service =
        NCLocator.getInstance().lookup(IQueryOrdertranstypeForPu.class);
    return service.getTranstypeByOrg(pk_group, pk_orgs,
        materialList.toArray(new String[materialList.size()]), stockorgs);
  }

  private void setBusitype() {
    // ȷ��ҵ�����̣��Ա������̺���
    new BusitypeFillRule(this.keyValue, POBillType.Order.getCode()).process();
  }
}
