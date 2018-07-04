package nc.vo.pu.upgrade.v636.util;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.uap.pf.IPFMetaModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.JavaType;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * �����������뵥������������
 * 
 * @since 6.0
 * @version 2015-4-14 ����3:00:29
 * @author luojw
 */
public class StoreReqTrantypeUpgrate {

  public static final DataAccessUtils utils = new DataAccessUtils();

  /**
   * ����
   * 
   * @param parallel_storereq
   */
  public static void upgrate(String parallel_storereq) {
    IPFMetaModel model = NCLocator.getInstance().lookup(IPFMetaModel.class);
    Logger.debug("��ȡ��������δ�·��ļ��š�����������");
    List<String> groups = StoreReqTrantypeUpgrate.getGroups();
    Logger.debug("��ȡĬ�ϵĽ�������VO������������");
    BilltypeVO defvo = StoreReqTrantypeUpgrate.getDefaultVO(model);
    Logger.debug("����Ĭ�Ͻ�������VO�ͼ��ţ���ȡ�µ�VO������������");
    List<BilltypeVO> vos =
        StoreReqTrantypeUpgrate.getVOsByGroups(defvo, groups);
    Logger.debug("���ݼ����·��������͵�bd_billtype������������");
    StoreReqTrantypeUpgrate.insertBilltypeVO(vos, model);
    Logger.debug("��ȡ�޸�po_storereq������ݡ�����������");
    List<List<Object>> datas = StoreReqTrantypeUpgrate.getAllBilltype();
    Logger.debug("�޸�po_storereq������������");
    StoreReqTrantypeUpgrate.updateStoreReq(datas, parallel_storereq);
  }

  /**
   * ��ȡ���н�������
   * 
   * @param datas
   */
  private static List<List<Object>> getAllBilltype() {
    List<List<Object>> datas = new ArrayList<List<Object>>();
    String sql =
        "select pk_billtypeid, pk_group, pk_billtypecode from bd_billtype where nodecode = '40040000' and pk_billtypecode <> '422X' and  pk_group not in ('~','global00000000000000')";
    IRowSet rs = StoreReqTrantypeUpgrate.utils.query(sql);
    while (rs.next()) {
      List<Object> data = new ArrayList<Object>();
      datas.add(data);
      data.add(rs.getString(0));
      data.add(rs.getString(1));
      data.add(rs.getString(2));
    }
    return datas;
  }

  /**
   * ��ȡĬ�ϵĽ�������VO
   * 
   * @param model
   * @return
   */
  private static BilltypeVO getDefaultVO(IPFMetaModel model) {
    String sqlwhere =
        "nodecode = '40040000' and pk_group = 'global00000000000000'";
    BilltypeVO[] vo = null;
    try {
      vo = model.queryBillTypeByWhere(sqlwhere);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (vo == null || vo.length == 0) {
      return null;
    }
    return vo[0];
  }

  /**
   * ��ȡ��������δ�·��ļ���
   * 
   * @return
   */
  private static List<String> getGroups() {
    List<String> groups = new ArrayList<String>();
    String sql =
        "select pk_group from org_group where pk_group not in (select pk_group from bd_billtype where nodecode = '40040000' and pk_billtypecode = '422X-01')";
    IRowSet rs = StoreReqTrantypeUpgrate.utils.query(sql);
    while (rs.next()) {
      groups.add(rs.getString(0));
    }
    return groups;
  }

  /**
   * ����Ĭ�Ͻ�������VO�ͼ��ţ���ȡ�µ�VO
   * 
   * @param defvo
   * @param groups
   * @return
   */
  private static List<BilltypeVO> getVOsByGroups(BilltypeVO defvo,
      List<String> groups) {
    List<BilltypeVO> vos = new ArrayList<BilltypeVO>();
    for (String group : groups) {
      BilltypeVO vo = (BilltypeVO) defvo.clone();
      vo.setPk_billtypeid(null);
      vo.setPk_group(group);
      vo.setTs(new UFDateTime());
      vos.add(vo);
    }
    return vos;
  }

  /**
   * ���ݼ����·��������͵�bd_billtype��ͬʱ��ȡ�޸�po_storereq�������
   * 
   * @param vos
   * @param model
   * @return
   */
  private static List<List<Object>> insertBilltypeVO(List<BilltypeVO> vos,
      IPFMetaModel model) {
    List<List<Object>> datas = new ArrayList<List<Object>>();
    for (BilltypeVO vo : vos) {
      try {
        model.saveBilltypeVO(vo);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return datas;
  }

  /**
   * �޸�po_storereq��,���ݼ��źͽ�������code�����Ľ�������id
   * 
   * @param datas
   */
  private static void updateStoreReq(List<List<Object>> datas,
      String parallel_storereq) {
    String update =
        "update "
            + parallel_storereq
            + "po_storereq set ctrantypeid = ? where pk_group = ? and vtrantypecode = ?";
    JavaType[] types = new JavaType[] {
      JavaType.String, JavaType.String, JavaType.String
    };
    StoreReqTrantypeUpgrate.utils.update(update, types, datas);
  }
}
