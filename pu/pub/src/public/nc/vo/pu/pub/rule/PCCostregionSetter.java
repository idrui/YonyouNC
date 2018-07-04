package nc.vo.pu.pub.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import nc.vo.bd.stordoc.StordocVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;

import nc.pubitf.org.cache.IOrgUnitPubService_C;

import nc.bs.framework.common.NCLocator;

/**
 * ������������
 * 
 * @since 6.5
 * @version 2014-4-21 ����04:21:02
 * @author mengjian
 */
public class PCCostregionSetter {

  private IKeyValue keyValue;

  public PCCostregionSetter(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * ���ݿ����֯��������֯������������ ��������֯���������ģ����������Ĵӿ����֯Я��
   * ��������֯�����������ģ�������֯���������ģ����������ĴӲ�����֯Я��
   * 
   * @param event
   */
  /*public void setApliabcenterByOrg() {
    boolean stockFlag = false;
    boolean fiFlag = false;
    String pk_stockorg =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_STOCKORG);
    String pk_org =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG);
    if (StringUtils.isNotEmpty(pk_stockorg)) {
      stockFlag =
          OrgUnitPubService.isTypeOf(pk_stockorg,
              nc.itf.org.IOrgConst.LIACENTERTYPE);
    }
    if (StringUtils.isNotEmpty(pk_org)) {
      fiFlag =
          OrgUnitPubService
              .isTypeOf(pk_org, nc.itf.org.IOrgConst.LIACENTERTYPE);
    }
    int rowCount = this.keyValue.getItemCount();
    for (int i = 0; i < rowCount; i++) {
      Object pk_material =
          this.keyValue.getBodyValue(i, InitialEstItemVO.PK_MATERIAL);
      Object csourcebid =
          this.keyValue.getBodyValue(i, InitialEstItemVO.CSOURCEBID);
      // ����û����Դ�������в�����
      if (pk_material != null && csourcebid == null) {
        if (stockFlag) {
          this.keyValue.setBodyValue(i, InitialEstItemVO.PK_APLIABCENTER,
              pk_stockorg);
          continue;
        }
        if (fiFlag) {
          this.keyValue.setBodyValue(i, InitialEstItemVO.PK_APLIABCENTER,
              pk_org);
        }
      }
    }

  }*/

  /**
   * ���ݲֿ�������������
   * 
   * @param event
   */
  /*public void setApliabcenterByStordoc() {
    String pk_stordoc =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_STOCKORG);
    String[] fields = new String[] {
      StordocVO.PROFITCENTRE
    };
    StordocVO[] stcrdocs = StordocPubService.queryStordocByPks(new String[] {
      pk_stordoc
    }, fields);
    if (stcrdocs == null) {
      return;
    }
    String pk_apliabcenter =
        (String) stcrdocs[0].getAttributeValue(StordocVO.PROFITCENTRE);
    if (StringUtils.isBlank(pk_apliabcenter)) {
      return;
    }
    int rowCount = this.keyValue.getItemCount();
    for (int i = 0; i < rowCount; i++) {
      Object pk_material =
          this.keyValue.getBodyValue(i, InitialEstItemVO.PK_MATERIAL);
      Object csourcebid =
          this.keyValue.getBodyValue(i, InitialEstItemVO.CSOURCEBID);
      // ����û����Դ�������в�����
      if (pk_material != null && csourcebid == null) {
        this.keyValue.setBodyValue(i, InitialEstItemVO.PK_APLIABCENTER,
            pk_apliabcenter);
      }
    }
  }*/

  // zj �����ջ��ֿ������ջ���������
  public boolean setArrviCenterByOrg(HashMap<String, UFBoolean> hashfi,
      HashMap<String, UFBoolean> hashstock, int row)

  {

    // pk_psfinanceorg ���������֯
    String pk_org =
        (String) this.keyValue.getBodyValue(row, OrderItemVO.PK_PSFINANCEORG);

    String pk_stockorg =
        (String) this.keyValue.getBodyValue(row, OrderItemVO.PK_ARRVSTOORG);

    UFBoolean stockFlag =
        hashstock.containsKey(pk_stockorg) ? (UFBoolean) hashstock
            .get(pk_stockorg) : UFBoolean.FALSE;
    // �ɹ����� pk_apliabcenter ������������ �� �°汾pk_arrliabcenter �ջ���������

    if (stockFlag.booleanValue()) {
      this.keyValue
          .setBodyValue(row, OrderItemVO.PK_ARRLIABCENTER, pk_stockorg);
      return true;
    }

    UFBoolean fiFlag =
        hashfi.containsKey(pk_org) ? (UFBoolean) hashfi.get(pk_org)
            : UFBoolean.FALSE;
    if (fiFlag.booleanValue()) {
      this.keyValue.setBodyValue(row, OrderItemVO.PK_ARRLIABCENTER, pk_org);
      return true;
    }

    return false;

  }

  public void setArrviCentersByOrg(int[] rows) {
    // ׼����ѯ����
    HashMap<String, UFBoolean> hashstock = this.prePareHashStock(rows);
    HashMap<String, UFBoolean> hashfi = this.prePareHashOrg(rows);

    // ��ʼ��ֵ
    for (int i : rows) {

      if (this.setArrviCenterByOrg(hashfi, hashstock, i)) {
        continue;
      }
      // ��û����ӽ����������Ĵ��� pk_apliabcenter ���������������°汾
      // pk_arrliabcenter �ջ���������
      String pk_apliabcenter =
          (String) this.keyValue.getBodyValue(i, OrderItemVO.PK_APLIABCENTER);

      this.keyValue.setBodyValue(i, "pk_arrliabcenter", pk_apliabcenter);
    }

    // �������°汾
    this.setArrviCenterVbyArriv(rows);

  }

  /**
   * ������������ �Ӳֿ��Ӧ����������Я���� ��û�У���ӿ����֯Я�����������֯���������ģ��� ��û�У���Ӳ�����֯Я������������֯���������ģ���
   * ����û�У���Ϊ�ա�
   */
  /*public void setPK_apliabcenter() {
    this.setApliabcenterByOrg();
    this.setApliabcenterByStordoc();
  }*/

  // zj 20141026
  /**
   * �����ջ��������� ����
   */
  public void setPK_Arriabcenter(int[] rows) {

    HashMap<String, String> hashstordoc = this.preParerecvstordoc(rows);
    HashMap<String, UFBoolean> hashstock = this.prePareHashStock(rows);
    HashMap<String, UFBoolean> hashfi = this.prePareHashOrg(rows);

    for (int i : rows) {
      // luojw ����ջ�����������ֵ���򲻽��и�ֵ
      if(this.keyValue.getBodyValue(i, OrderItemVO.PK_ARRLIABCENTER) != null){
        continue;
      }
      // ���ջ��ֿ��Ӧ���������Ĵ�
      if (this.setarrivbystore(hashstordoc, i)) {
        continue;
      }
      else
      // �ֿ�δ����������ջ������֯����û����Ӳ�����֯��
      if (this.setArrviCenterByOrg(hashfi, hashstock, i)) {
        continue;
      }
      // ��û����ӽ����������Ĵ��� pk_apliabcenter ���������������°汾
      // pk_arrliabcenter �ջ���������
      String pk_apliabcenter =
          (String) this.keyValue.getBodyValue(i, OrderItemVO.PK_APLIABCENTER);

      this.keyValue.setBodyValue(i, OrderItemVO.PK_ARRLIABCENTER,
          pk_apliabcenter);
      // ��û����Ϊ��

    }
    // �������°汾
    this.setArrviCenterVbyArriv(rows);
  }

  private HashMap<String, UFBoolean> prePareHashOrg(int[] rows) {
    HashMap<String, UFBoolean> hashfi = new HashMap<String, UFBoolean>();

    for (int i : rows) {
      // �༭���ջ������֯�󣬲�����֯�Ѿ���ֵ
      String pk_org =
          (String) this.keyValue.getBodyValue(i, OrderItemVO.PK_PSFINANCEORG);

      if (StringUtils.isNotEmpty(pk_org) && !hashfi.containsKey(pk_org)) {
        boolean fiFlag =
            OrgUnitPubService.isTypeOf(pk_org,
                nc.itf.org.IOrgConst.LIACENTERTYPE);
        if (fiFlag) {
          hashfi.put(pk_org, UFBoolean.TRUE);
        }
        else {
          hashfi.put(pk_org, UFBoolean.FALSE);
        }

      }
    }
    return hashfi;

  }

  private HashMap<String, UFBoolean> prePareHashStock(int[] rows) {
    HashMap<String, UFBoolean> hashstock = new HashMap<String, UFBoolean>();
    for (int i : rows) {

      // �ɹ����� pk_arrvstoorg �ջ������֯
      String pk_stockorg =
          (String) this.keyValue.getBodyValue(i, OrderItemVO.PK_ARRVSTOORG);

      if (StringUtils.isNotEmpty(pk_stockorg)
          && !hashstock.containsKey(pk_stockorg)) {
        boolean stockFlag =
            OrgUnitPubService.isTypeOf(pk_stockorg,
                nc.itf.org.IOrgConst.LIACENTERTYPE);

        if (stockFlag) {

          hashstock.put(pk_stockorg, UFBoolean.TRUE);
        }
        else {
          hashstock.put(pk_stockorg, UFBoolean.FALSE);
        }

      }

    }
    return hashstock;

  }

  private HashMap<String, String> preParerecvstordoc(int[] rows) {
    HashMap<String, String> hashstrdocvo = new HashMap<String, String>();

    //
    ArrayList<String> strlist = new ArrayList<String>();
    for (int i : rows) {
      String PK_RECVSTORDOC =
          (String) this.keyValue.getBodyValue(i, OrderItemVO.PK_RECVSTORDOC);
      if (StringUtils.isBlank(PK_RECVSTORDOC)) {
        continue;
      }

      strlist.add(PK_RECVSTORDOC);
    }
    if (strlist.size() < 1) {
      return hashstrdocvo;
    }
    String[] pks = new String[strlist.size()];
    strlist.toArray(pks);
    //

    String[] fields = new String[] {
      StordocVO.PROFITCENTRE
    };

    StordocVO[] stcrdocs = StordocPubService.queryStordocByPks(pks, fields);
    if (stcrdocs == null) {

    }
    else {
      for (int i = 0; i < stcrdocs.length; i++) {

        hashstrdocvo.put(
            (String) stcrdocs[i].getAttributeValue(StordocVO.PK_STORDOC),
            (String) stcrdocs[i].getAttributeValue(StordocVO.PROFITCENTRE));

      }

    }
    return hashstrdocvo;
  }

  private boolean setarrivbystore(HashMap<String, String> hashstordoc, int row)

  {

    String pk_stordoc =
        (String) this.keyValue.getBodyValue(row, OrderItemVO.PK_RECVSTORDOC);

    String pk_arrliabcenter =
        hashstordoc.containsKey(pk_stordoc) ? (String) hashstordoc
            .get(pk_stordoc) : "";
    if (StringUtils.isBlank(pk_arrliabcenter)) {
      return false;
    }

    this.keyValue.setBodyValue(row, OrderItemVO.PK_ARRLIABCENTER,
        pk_arrliabcenter);
    return true;
    // Object pk_material =
    // this.keyValue.getBodyValue(row, OrderItemVO.PK_MATERIAL);
    // Object csourcebid = this.keyValue.getBodyValue(row,
    // OrderItemVO.CSOURCEBID);
    // // ����û����Դ�������в�����
    // if (pk_material != null && csourcebid == null) {
    // // pk_arrliabcenter �ջ���������
    // this.keyValue.setBodyValue(row, OrderItemVO.PK_ARRLIABCENTER,
    // pk_arrliabcenter);
    // return true;
    // }

    // return false;

  }

  private void setArrviCenterVbyArriv(int[] rows) {
    // ����PK_ORG �������°汾������ֵ
    try {
      ArrayList<String> strlist = new ArrayList<String>();
      for (int i : rows) {
        String PK_ARRLIABCENTER =
            (String) this.keyValue
                .getBodyValue(i, OrderItemVO.PK_ARRLIABCENTER);
        if (StringUtils.isBlank(PK_ARRLIABCENTER)) {
          continue;
        }

        strlist.add(PK_ARRLIABCENTER);
      }
      Map<String, String> mapvid = new HashMap<String, String>();
      if (strlist.size() > 0) {

        String[] strs = new String[strlist.size()];
        strlist.toArray(strs);

        mapvid =
            NCLocator.getInstance().lookup(IOrgUnitPubService_C.class)
                .getNewVIDSByOrgIDS(strs);

      }
      for (int i : rows) {
        String PK_ARRLIABCENTER =
            (String) this.keyValue
                .getBodyValue(i, OrderItemVO.PK_ARRLIABCENTER);
        if (StringUtils.isBlank(PK_ARRLIABCENTER)) {

          this.keyValue.setBodyValue(i, OrderItemVO.PK_ARRLIABCENTER_V, null);
          continue;
        }

        String newid = mapvid.get(PK_ARRLIABCENTER);
        this.keyValue.setBodyValue(i, OrderItemVO.PK_ARRLIABCENTER_V, newid);

      }

    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);

    }

  }

}
