/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-26 ����09:23:59
 */
package nc.vo.pu.m20.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.pubitf.scmf.sourcing.sour4pu.ISourcePUService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmf.sourcing.entity.PoSupplierVO;
import nc.vo.scmf.sourcing.entity.SourceVO;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����ѰԴ�㷨����Ĭ�ϲɹ���֯�ͽ��鹩Ӧ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-5-26 ����09:23:59
 */
public class SetPurchaseorg {

  /**
   * ������������������Ĭ�ϲɹ���֯�ͽ��鹩Ӧ�̡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param keyValue
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 ����04:29:46
   */
  public void setPurchaseorg(IKeyValue keyValue) {
    int[] rows = this.getRows(keyValue);

    this.setPurchaseorg(keyValue, rows);
  }

  /**
   * ������������������Ĭ�ϲɹ���֯�ͽ��鹩Ӧ�̡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param keyValue
   * @param rows
   *          <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-18 ����04:29:50
   */
  public void setPurchaseorg(IKeyValue keyValue, int[] rows) {

    String pk_org = (String) keyValue.getHeadValue(PraybillHeaderVO.PK_ORG);
    if (StringUtil.isEmptyWithTrim(pk_org)) {
      return;
    }
    Map<String, PoSupplierVO> pk_defOrgs =
        this.getPuorgAndSupply(keyValue, rows, pk_org);
    if (null == pk_defOrgs) {
      return;
    }

    Map<String, String> pk_orgvs = this.getNewVIDSByOrgIDS(pk_defOrgs);

    // ѭ������
    for (int i = 0, len = rows.length; i < len; i++) {
      keyValue.setBodyValue(rows[i], PraybillItemVO.BCANPURCHASEORGEDIT,
          UFBoolean.TRUE);
      // ���òɹ���֯�ͽ��鹩Ӧ��Ĭ��ֵ
      // ���ڷֵ���Ҫ����vo�����н��ɹ���֯�Ը������Σ�oid,vid��ͬ�����������ｫ�пյ�����ȥ������ʹ�вɹ���֯��Ҳ���¼���һ�飬������ܵ���vid����ȷ��
      // if (null == keyValue.getBodyValue(rows[i],
      // PraybillItemVO.PK_PURCHASEORG)) {
      String material =
          (String) keyValue
              .getBodyValue(rows[i], PraybillItemVO.PK_SRCMATERIAL);
      if (null == material) {
        continue;
      }

      PoSupplierVO posupvo = pk_defOrgs.get(material);
      String pk_defOrg = posupvo.getPuOrg();
      keyValue.setBodyValue(rows[i], PraybillItemVO.PK_PURCHASEORG, pk_defOrg);
      keyValue.setBodyValue(rows[i], PraybillItemVO.PK_PURCHASEORG_V,
          pk_orgvs.get(pk_defOrg));

      if (StringUtil.isEmptyWithTrim(pk_defOrg)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0109")/*
                                                                     * @res
                                                                     * "δѯ���ɹ���֯��"
                                                                     */);
      }
      if (!StringUtil.isEmptyWithTrim(pk_defOrg) && !pk_org.equals(pk_defOrg)) {
        // ���òɹ���֯���ɱ༭
        keyValue.setBodyValue(rows[i], PraybillItemVO.BCANPURCHASEORGEDIT,
            UFBoolean.FALSE);
      }

      if (keyValue.getBodyValue(rows[i], PraybillItemVO.PK_SUGGESTSUPPLIER) == null) {
        // ���ý��鹩Ӧ��
        keyValue.setBodyValue(rows[i], PraybillItemVO.PK_SUGGESTSUPPLIER,
            posupvo.getSupplier());
      }
      // }
    }
  }

  private Map<String, String> getNewVIDSByOrgIDS(
      Map<String, PoSupplierVO> pk_defOrgs) {
    HashSet<String> pk_orgs = new HashSet<String>();
    for (Map.Entry<String, PoSupplierVO> entry : pk_defOrgs.entrySet()) {
      pk_orgs.add(entry.getValue().getPuOrg());
    }

    Map<String, String> ret =
        OrgUnitPubService.getNewVIDSByOrgIDS(pk_orgs.toArray(new String[pk_orgs
            .size()]));
    if (null == ret) {
      ret = new HashMap<String, String>();
    }

    return ret;
  }

  private Map<String, PoSupplierVO> getPuorgAndSupply(IKeyValue keyValue,
      int[] rows, String pk_org) {
    // ѭ��ȡ������PK
    HashSet<String> materials = new HashSet<String>();
    for (int i = 0, len = rows.length; i < len; i++) {
      String pk_material =
          (String) keyValue
              .getBodyValue(rows[i], PraybillItemVO.PK_SRCMATERIAL);
      if (null != pk_material) {
        materials.add(pk_material);
      }
    }

    Object obsctype = keyValue.getHeadValue(PraybillHeaderVO.BSCTYPE);
    UFBoolean bsctype = UFBoolean.FALSE;
    if (obsctype != null && obsctype instanceof UFBoolean) {
      bsctype = (UFBoolean) obsctype;
    }
    else if (obsctype != null && obsctype instanceof Boolean) {
      bsctype = UFBoolean.valueOf(((Boolean) obsctype).booleanValue());
    }

    Map<String, PoSupplierVO> pk_defOrgs =
        this.queryPuorgSupply(materials, pk_org, bsctype);
    return pk_defOrgs;
  }

  private int[] getRows(IKeyValue keyValue) {
    int[] rows = new int[keyValue.getItemCount()];
    for (int i = 0; i < keyValue.getItemCount(); i++) {
      rows[i] = i;
    }
    return rows;
  }

  /**
   * ���������������ɹ���֯ѰԴ.<br>
   * ȡֵ�������������ȴ���<br>
   * �����ϵ��������Ϣҳǩ�еĲɹ���֯��<br>
   * �ڸ��ݱ�ͷ��������֯+����ƥ��ɹ�ҵ��ί�й�ϵ�ҵ���Ӧ�Ĳɹ���֯��<br>
   * ����֯ί�й�ϵ�����ֹ�¼��
   * <p>
   * <b>����˵��</b>
   * 
   * @param card
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-2-3 ����11:19:53
   */
  private Map<String, PoSupplierVO> queryPuorgSupply(HashSet<String> materials,
      String pk_org, UFBoolean bsctype) {

    if (materials.size() == 0) {
      return null;
    }
    SourceVO[] svos = new SourceVO[materials.size()];
    String[] smaterials = materials.toArray(new String[materials.size()]);
    for (int i = 0, len = smaterials.length; i < len; i++) {
      svos[i] = new SourceVO(smaterials[i], pk_org, bsctype, null, null);
    }

    try {
      PoSupplierVO[] supvos =
          NCLocator.getInstance().lookup(ISourcePUService.class)
              .queryforPrayBill(svos);
      Map<String, PoSupplierVO> ret = new HashMap<String, PoSupplierVO>();

      for (int i = 0, len = smaterials.length; i < len; i++) {
        ret.put(smaterials[i], supvos[i]);
      }

      return ret;
    }
    catch (BusinessException e) {
      // ��־�쳣
      ExceptionUtils.wrappException(e);
    }

    return null;
  }
}
