package nc.vo.pu.pub.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

import org.apache.commons.lang.StringUtils;

/**
 * @description
 *              请购单处理组织最新版的规则<br>
 *              按规范，转单后下游的主组织不能直接对照上游的版本，一定要重新计算一个最新版本<br>
 *              此规则可放在后台保存前，也可用于前台处理<br>
 *              要求表头和表体均有主组织字段，并命名为pk_org和pk_org_v<br>
 *              只对新增进行处理
 * @scene
 *        物资需求申请单、到货单、请购单和采购订单保存、修改
 * @param String headPkName 表头主键名称
 * @since 6.3
 * @version 2014-10-21 上午9:43:13
 * @author yanxm5
 */
public class NewestOrgVersionFillRule<E extends AbstractBill> implements
    IRule<E> {
  private static final String PK_ORG = "pk_org";

  private static final String PK_ORG_V = "pk_org_v";

  private String headPkName;

  private IKeyValue[] kvHelpers;

  /**
   * @param headItemPkName[表头和表体的主键名称]
   */
  public NewestOrgVersionFillRule(String headPkName) {
    this.headPkName = headPkName;
  }

  @Override
  public void process(E[] vos) {
    this.initHelper(vos);
    this.procHelper();
  }

  public void process(IKeyValue[] helper) {
    this.kvHelpers = helper;
    this.procHelper();
  }

  private boolean hasNewItem(IKeyValue kv) {
    String headPk = (String) kv.getHeadValue(this.headPkName);
    if (StringUtils.isBlank(headPk)) {
      return true;
    }
    return false;
  }

  private void initHelper(E[] vos) {
    this.kvHelpers = new BillHelper[vos.length];
    for (int i = 0; i < vos.length; i++) {
      this.kvHelpers[i] = new BillHelper<E>(vos[i]);
    }
  }

  private void procHelper() {
    List<String> orgLst = new ArrayList<String>();
    for (IKeyValue kv : this.kvHelpers) {
      String pk_org = (String) kv.getHeadValue(NewestOrgVersionFillRule.PK_ORG);
      if (StringUtils.isNotBlank(pk_org) && this.hasNewItem(kv)) {
        orgLst.add(pk_org);
      }
    }
    if (orgLst.size() == 0) {
      return;
    }
    // 设置最新版（主组织）
    this.setVID(orgLst);
  }

  private void setVID(List<String> orgLst) {

    Map<String, String> orgVMap =
        OrgUnitPubService.getNewVIDSByOrgIDS(orgLst.toArray(new String[orgLst
            .size()]));
    for (IKeyValue kv : this.kvHelpers) {
      String pk_org = (String) kv.getHeadValue(NewestOrgVersionFillRule.PK_ORG);
      String headPk = (String) kv.getHeadValue(this.headPkName);
      // 只有新的单据才重算
      if (StringUtils.isBlank(headPk)) {
        kv.setHeadValue(NewestOrgVersionFillRule.PK_ORG_V, orgVMap.get(pk_org));
      }
    }

  }
}
