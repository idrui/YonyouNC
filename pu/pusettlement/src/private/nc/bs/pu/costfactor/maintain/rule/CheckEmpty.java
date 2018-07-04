/**
 * $�ļ�˵��$
 *
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-16 ����06:40:59
 */
package nc.bs.pu.costfactor.maintain.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.costfactor.entity.CostfactorHeaderVO;
import nc.vo.pu.costfactor.entity.CostfactorItemVO;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;

/**
 * @description
 *              �ɱ�Ҫ�غ�̨�ǿ�У��
 * @scene
 *        �ɱ�Ҫ�ض�����������BP
 * @param ��
 * @since 6.3
 * @version 2014-10-21 ����2:24:52
 * @author yanxm5
 */
public class CheckEmpty implements IRule<CostfactorVO> {

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(CostfactorVO[] vos) {
    this.checkNull(vos);
  }

  private StringBuilder checkHeadNull(CostfactorHeaderVO head) {
    StringBuilder sb = new StringBuilder();
    for (String key : this.getHeadNotNullKey()) {
      if (null != head.getAttributeValue(key)) {
        continue;
      }
      sb.append("[").append(head.getMetaData().getAttribute(key).toString())
          .append("]");
    }
    if (StringUtils.isBlank(head.getVfactorname())
        && StringUtils.isBlank(head.getVfactorname2())
        && StringUtils.isBlank(head.getVfactorname3())
        && StringUtils.isBlank(head.getVfactorname4())
        && StringUtils.isBlank(head.getVfactorname5())
        && StringUtils.isBlank(head.getVfactorname6())) {
      sb.append("[")
          .append(
              head.getMetaData().getAttribute(CostfactorHeaderVO.VFACTORNAME)
                  .toString()).append("]");
    }
    return sb;
  }

  private StringBuilder checkItemNull(CostfactorItemVO[] childrenVO) {
    String[] notNullKeys = this.getItemNotNullKey();
    if (ArrayUtils.isEmpty(notNullKeys)) {
      return null;
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < childrenVO.length; i++) {
      StringBuilder itemErr = new StringBuilder();
      for (String key : notNullKeys) {
        if (null != childrenVO[i].getAttributeValue(key)) {
          continue;
        }
        itemErr.append("[")
            .append(childrenVO[i].getMetaData().getAttribute(key).toString())
            .append("]");
      }
      if (itemErr.length() > 0) {
        int num = i + 1;
        sb.append(
            NCLangResOnserver.getInstance().getStrByID("4004060_0",
                "04004060-0204", null, new String[] {
                  String.valueOf(num)
                })/* ��{0}���������Բ��ܿգ� */).append(itemErr.toString())
            .append(SystemUtils.LINE_SEPARATOR);
      }
    }
    return sb;
  }

  private void checkNull(CostfactorVO[] newVos) {
    StringBuilder err = new StringBuilder();
    for (CostfactorVO cfVo : newVos) {
      CostfactorHeaderVO head = cfVo.getParentVO();
      StringBuilder sbHead = this.checkHeadNull(head);
      StringBuilder sbItem = this.checkItemNull(cfVo.getChildrenVO());
      if (null != sbHead && sbHead.length() > 0) {
        err.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4004060_0", "04004060-0049")/* @res "�ɱ�Ҫ����ʵ�������ֶβ���Ϊ�գ�" */);
        err.append(SystemUtils.LINE_SEPARATOR);
        err.append(sbHead);
      }
      if (null != sbItem && sbItem.length() > 0) {
        if (err.length() > 0) {
          err.append(SystemUtils.LINE_SEPARATOR);
        }
        err.append(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4004060_0", "04004060-0050")/* @res "�ɱ�Ҫ�ط���������Ϣ��" */);
        err.append(SystemUtils.LINE_SEPARATOR);
        err.append(sbItem);
      }
    }
    if (err.length() > 0) {
      ExceptionUtils.wrappBusinessException(err.toString());
    }
  }

  private String[] getHeadNotNullKey() {
    return new String[] {
      CostfactorHeaderVO.PK_ORG, CostfactorHeaderVO.PK_ORG_V,
      CostfactorHeaderVO.PK_GROUP, CostfactorHeaderVO.BENTERCOST,
      CostfactorHeaderVO.FAPPORTIONMODE, CostfactorHeaderVO.IFACTORORDER
    };
  }

  private String[] getItemNotNullKey() {
    // 2011-12-15 ������wangyf�������zhaoyhaȷ�ϣ����ﲻ�Ա�����У�飬
    // �������ʱ��Ϊ�óɱ�Ҫ������������
    // return new String[] {
    // CostfactorItemVO.PK_MATERIAL, CostfactorItemVO.PK_SRCMATERIAL
    // };
    return null;
  }

}
