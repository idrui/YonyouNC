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
 *              �빺��������֯���°�Ĺ���<br>
 *              ���淶��ת�������ε�����֯����ֱ�Ӷ������εİ汾��һ��Ҫ���¼���һ�����°汾<br>
 *              �˹���ɷ��ں�̨����ǰ��Ҳ������ǰ̨����<br>
 *              Ҫ���ͷ�ͱ����������֯�ֶΣ�������Ϊpk_org��pk_org_v<br>
 *              ֻ���������д���
 * @scene
 *        �����������뵥�����������빺���Ͳɹ��������桢�޸�
 * @param String headPkName ��ͷ��������
 * @since 6.3
 * @version 2014-10-21 ����9:43:13
 * @author yanxm5
 */
public class NewestOrgVersionFillRule<E extends AbstractBill> implements
    IRule<E> {
  private static final String PK_ORG = "pk_org";

  private static final String PK_ORG_V = "pk_org_v";

  private String headPkName;

  private IKeyValue[] kvHelpers;

  /**
   * @param headItemPkName[��ͷ�ͱ������������]
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
    // �������°棨����֯��
    this.setVID(orgLst);
  }

  private void setVID(List<String> orgLst) {

    Map<String, String> orgVMap =
        OrgUnitPubService.getNewVIDSByOrgIDS(orgLst.toArray(new String[orgLst
            .size()]));
    for (IKeyValue kv : this.kvHelpers) {
      String pk_org = (String) kv.getHeadValue(NewestOrgVersionFillRule.PK_ORG);
      String headPk = (String) kv.getHeadValue(this.headPkName);
      // ֻ���µĵ��ݲ�����
      if (StringUtils.isBlank(headPk)) {
        kv.setHeadValue(NewestOrgVersionFillRule.PK_ORG_V, orgVMap.get(pk_org));
      }
    }

  }
}
