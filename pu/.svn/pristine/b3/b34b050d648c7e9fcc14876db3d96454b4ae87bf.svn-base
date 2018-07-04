package nc.vo.pu.m422x.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.pubitf.scmf.sourcing.sour4mm.ISourceMMService;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.enumeration.ReqTypesEnum;
import nc.vo.pu.pub.util.IKeyValue;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 下次平衡库存组织
 * 若为自制的毛需求，默认值为主组织；
 * 若为自制的净需求：
 * 若“库存组织”的物料档案库存信息页签的“物料类型”为工厂补货，则匹配内部货源定义；如果分销补货，则匹配分销补货体系。
 * 如果匹配到，将匹配到的其他供货库存组织的值带入到该行的“下次平衡库存组织”
 * 如果未匹配到，则“下次平衡库存组织”取主组织
 * 否则，取主组织作为“下次平衡库存组织”
 * 
 * @since 6.0
 * @version 2014-5-7 下午08:33:18
 * @author wuxla
 */
public class NextbalanceorgSetter {
  private boolean bclear = true;

  private IKeyValue keyValue;

  public NextbalanceorgSetter(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  public boolean isBclear() {
    return this.bclear;
  }

  public void setBclear(boolean bclear) {
    this.bclear = bclear;
  }

  public void setNextbalanceorg(int[] rows) {
    Integer freqtype =
        (Integer) this.keyValue.getHeadValue(StoreReqAppHeaderVO.FREQTYPEFLAG);
    if (ReqTypesEnum.GROSS_REQUIREMENT.toInteger().equals(freqtype)) {
      this.setNextbalanceorgWhenGross(rows);
    }
    else {
      this.setNextbalanceorgWhenNet(rows);
    }
  }

  /**
   * 毛需求
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @param rows
   */
  private void setNextbalanceorgWhenGross(int[] rows) {
    String pk_org =
        (String) this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_ORG);
    String pk_org_v =
        (String) this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_ORG_V);
    for (int i = 0; i < rows.length; ++i) {
      String pk_srcmaterial =
          (String) this.keyValue.getBodyValue(rows[i],
              StoreReqAppItemVO.PK_SRCMATERIAL);

      if (StringUtils.isEmpty(pk_srcmaterial)) {
        continue;
      }
      if (this.isBclear()) {
        this.keyValue.setBodyValue(rows[i],
            StoreReqAppItemVO.PK_NEXTBALANCEORG, pk_org);
        this.keyValue.setBodyValue(rows[i],
            StoreReqAppItemVO.PK_NEXTBALANCEORG_V, pk_org_v);
      }
      else {
        if (this.keyValue.getBodyValue(rows[i],
            StoreReqAppItemVO.PK_NEXTBALANCEORG) == null) {
          this.keyValue.setBodyValue(rows[i],
              StoreReqAppItemVO.PK_NEXTBALANCEORG, pk_org);
          this.keyValue.setBodyValue(rows[i],
              StoreReqAppItemVO.PK_NEXTBALANCEORG_V, pk_org_v);
        }
      }
    }
  }

  /**
   * 净需求
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @param rows
   */
  private void setNextbalanceorgWhenNet(int[] rows) {
    String pk_org =
        (String) this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_ORG);
    String pk_org_v =
        (String) this.keyValue.getHeadValue(StoreReqAppHeaderVO.PK_ORG_V);
    List<Integer> rowList = new ArrayList<Integer>();
    List<String> matList = new ArrayList<String>();
    List<String> orgList = new ArrayList<String>();
    for (int i = 0; i < rows.length; ++i) {
      String pk_srcmaterial =
          (String) this.keyValue.getBodyValue(rows[i],
              StoreReqAppItemVO.PK_SRCMATERIAL);

      if (StringUtils.isEmpty(pk_srcmaterial)) {
        continue;
      }
      rowList.add(Integer.valueOf(rows[i]));
      matList.add(pk_srcmaterial);
      orgList.add(pk_org);
    }

    if (matList.size() == 0) {
      return;
    }

    String[] mats = matList.toArray(new String[matList.size()]);
    String[] orgs = orgList.toArray(new String[orgList.size()]);
    // 注意65修改成pu接口
    ISourceMMService service =
        NCLocator.getInstance().lookup(ISourceMMService.class);
    String[] nextorgs = service.queryStockOrgs(mats, orgs);
    Map<String, String> map = OrgUnitPubService.getNewVIDSByOrgIDS(nextorgs);
    int[] nextrows =
        ArrayUtils.toPrimitive(rowList.toArray(new Integer[rowList.size()]));
    int length = nextrows.length;
    for (int i = 0; i < length; ++i) {
      if (nextorgs[i] != null) {
        if (this.isBclear()) {
          this.keyValue.setBodyValue(nextrows[i],
              StoreReqAppItemVO.PK_NEXTBALANCEORG, nextorgs[i]);
          this.keyValue.setBodyValue(nextrows[i],
              StoreReqAppItemVO.PK_NEXTBALANCEORG_V, map.get(nextorgs[i]));
        }
        else {
          if (this.keyValue.getBodyValue(nextrows[i],
              StoreReqAppItemVO.PK_NEXTBALANCEORG) == null) {
            this.keyValue.setBodyValue(nextrows[i],
                StoreReqAppItemVO.PK_NEXTBALANCEORG, nextorgs[i]);
            this.keyValue.setBodyValue(nextrows[i],
                StoreReqAppItemVO.PK_NEXTBALANCEORG_V, map.get(nextorgs[i]));
          }
        }
      }
      else {
        if (this.isBclear()) {
          this.keyValue.setBodyValue(nextrows[i],
              StoreReqAppItemVO.PK_NEXTBALANCEORG, pk_org);
          this.keyValue.setBodyValue(nextrows[i],
              StoreReqAppItemVO.PK_NEXTBALANCEORG_V, pk_org_v);
        }
        else {
          if (this.keyValue.getBodyValue(nextrows[i],
              StoreReqAppItemVO.PK_NEXTBALANCEORG) == null) {
            this.keyValue.setBodyValue(nextrows[i],
                StoreReqAppItemVO.PK_NEXTBALANCEORG, pk_org);
            this.keyValue.setBodyValue(nextrows[i],
                StoreReqAppItemVO.PK_NEXTBALANCEORG_V, pk_org_v);
          }
        }
      }
    }
  }

}
