/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-6 上午11:53:54
 */
package nc.bs.pu.m20.maintain.rule.delete;

import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              请购单删除时 同时删除老版本
 * @scene
 *        请购单删除
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午9:18:54
 * @author yanxm5
 */
public class DelOldVersionRule implements ICompareRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos, PraybillVO[] orgVos) {
    if (ArrayUtils.isEmpty(orgVos)) {
      return;
    }
    // 删除老版本
    this.deleteOld(orgVos);

  }

  private void deleteOld(PraybillVO[] vos) {
    String[] pk_praybills = new String[vos.length];
    for (int i = 0, len = vos.length; i < len; i++) {
      pk_praybills[i] = vos[i].getHVO().getPk_praybill();
    }

    SqlBuilder sql = new SqlBuilder();
    sql.append(" select pk_praybill from po_praybill where dr = 0 and bislatest ='N' and ");
    // 临时表
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_20_04.name());
    sql.append(builder.buildSQL(PraybillHeaderVO.PK_SRCPRAYBILL, pk_praybills));

    String[] pks =
        new DataAccessUtils().query(sql.toString()).toOneDimensionStringArray();
    if (ArrayUtils.isEmpty(pks)) {
      return;
    }
    PraybillVO[] delVOs =
        new BillQuery<PraybillVO>(PraybillVO.class).query(pks);

    new BillDelete<PraybillVO>().delete(delVOs);
  }
}
