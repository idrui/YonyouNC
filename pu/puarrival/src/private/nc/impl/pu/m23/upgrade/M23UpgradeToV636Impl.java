package nc.impl.pu.m23.upgrade;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.pu.m23.upgrade.IM23UpgradeToV636;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class M23UpgradeToV636Impl implements IM23UpgradeToV636 {

  @Override
  public void doAfterUpdateDataFrom63To636() throws BusinessException {
    this.synBodyInfo();
  }

  @Override
  public void doBeforeUpdateDataFrom63To636() throws BusinessException {
  }

  @Override
  public void doBeforeUpdateDBFrom63To636() throws BusinessException {
  }

  private void synBodyInfo() {
    SqlBuilder sb = new SqlBuilder();
    String bodytablename = "po_arriveorder_b";
    sb.append("update ");
    sb.append(bodytablename);
    sb.append(" set ");
    sb.append(" fproductclass=1");
    sb.append(" where ");
    sb.append(" dr = 0");
    new DataAccessUtils().update(sb.toString());
  }

}
