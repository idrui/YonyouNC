/**
 * $文件说明$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-9 下午01:42:15
 */
package nc.bs.pu.costfactor.maintain.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.costfactor.entity.CostfactorItemVO;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              费用检查
 * @scene
 *        成本要素定义新增保存BP、成本要素定义修改保存BP
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午2:25:39
 * @author yanxm5
 */
public class CheckMaterialRule implements IRule<CostfactorVO> {

  @Override
  public void process(CostfactorVO[] bill) {
    for (CostfactorVO vo : bill) {
      CostfactorItemVO[] items = vo.getChildrenVO();
      if (ArrayUtils.isEmpty(items)) {
        continue;
      }
      String pk_org = vo.getParentVO().getPk_org();
      // 判断该费用已存在于本成本要素中
      this.checkThis(items);

      // 判断该费用已存在于其它成本要素中
      this.checkUsed(items, pk_org);
    }
  }

  private void check(String sql, String message) {
    IRowSet rowset = new DataAccessUtils().query(sql);

    if (rowset != null && rowset.size() > 0) {
      ExceptionUtils.wrappBusinessException(message);
    }
  }

  private void checkThis(CostfactorItemVO[] items) {
    // 判断该费用已存在于本成本要素中
    for (int i = 0; i < items.length; i++) {
      String sInvSrcID = items[i].getPk_srcmaterial().trim();
      for (int k = 0; k < items.length; k++) {
        String s = items[k].getPk_srcmaterial().trim();
        if (k != i && s.equals(sInvSrcID)) {
          String[] value = new String[] {
            String.valueOf(i + 1)
          };
          String message =
              NCLangResOnserver.getInstance().getStrByID("4004060_0",
                  "04004060-0205", null, value)/* 第{0}行费用已存在于本成本要素中，不能保存！ */;

          ExceptionUtils.wrappBusinessException(message);
        }
      }
    }
  }

  private void checkUsed(CostfactorItemVO[] items, String pk_org) {

    for (int i = 0; i < items.length; i++) {
      String sql = "select pk_srcmaterial from po_costfactor_b where ";
      String where =
          " pk_srcmaterial= '" + items[i].getPk_srcmaterial() + "' and dr = 0 ";
      if (null != pk_org) {
        where +=
            " and pk_costfactor in (select pk_costfactor from po_costfactor where pk_org = '"
                + pk_org + "')";
      }

      if (null != items[i].getPk_costfactor()) {
        where += " and Pk_costfactor != '" + items[i].getPk_costfactor() + "' ";
      }

      sql += where;
      int num = i + 1;
      String msg =
          NCLangResOnserver.getInstance().getStrByID("4004060_0",
              "04004060-0196", null, new String[] {
                String.valueOf(num)
              })/* 第{0}行费用已存在于其它成本要素中，不能保存！ */;
      this.check(sql, msg);
    }
  }
}
