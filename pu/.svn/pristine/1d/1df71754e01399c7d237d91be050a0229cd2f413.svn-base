package nc.vo.pu.pub.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m21.exception.AskNumException;
import nc.vo.pu.pub.constant.PUParaValue.ctrltype;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>容差计算
 * <li>只用于后台
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chendb
 * @time 2010-11-10 下午07:09:42
 */
public abstract class ToleranceCalcRule {

  /**
   * 方法功能描述：表体id字段名（例：订单：getBidFiled()="pk_order_b"）
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author chendb
   * @time 2010-11-12 上午09:41:23
   */
  public abstract String getBidFiled();

  /**
   * 方法功能描述：主数量字段名（例：订单：getNumField()="nnum"）
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author chendb
   * @time 2010-11-12 上午09:41:38
   */
  public abstract String getNumField();

  /**
   * 方法功能描述：表体对应的表名（例：订单：getTable()="PO_ORDER_B"）
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author chendb
   * @time 2010-11-12 上午09:41:45
   */
  public abstract String getTable();

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param wbNumFiled 回写的字段名（例：到货单回写订单：wbNumFiled="naccumarrvnum"）
   * @param srcBids
   *          回写的表体id值（例：订单：srcBids={0001451000000000IVSD,0001451000000000IVSD
   *          }）
   * @param tolerFiled 容差字段名（例：入库容差：tolerFiled="intolerance"）
   * @param tolerPara 容差参数（例：PO02：tolerPara="严格控制"）
   *          <p>
   * @since 6.0
   * @author chendb
   * @time 2010-11-15 上午11:11:20
   */
  public void toleranceCompare(String wbNumFiled, String[] srcBids,
      String tolerFiled, ctrltype tolerPara, UFBoolean isUserConfirm) {
    if (ArrayUtils.isEmpty(srcBids)) {
      return;
    }
    if (null != isUserConfirm && isUserConfirm.booleanValue()) {
      return;
    }
    String[] results = this.getCodeOverToler(wbNumFiled, srcBids, tolerFiled);

    if (results != null && results.length > 0) {
      // 严格控制
      if (tolerPara.equals(ctrltype.not_save)) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4004000_0", "04004000-0100", null, new String[] {
              results[0]
            })/* 物料{0}超出容差控制的行，请检查！ */);
      }
      // 提示
      else if (tolerPara.equals(ctrltype.ask)) {
        ExceptionUtils.wrappException(new AskNumException(NCLangResOnserver
            .getInstance().getStrByID("4004000_0", "04004000-0101", null,
                new String[] {
                  results[0]
                })/* 物料{0}超出容差控制的行，是否继续！ */));
      }
    }
  }

  /**
   * 方法功能描述：返回超容差控制的物料编码
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author chendb
   * @time 2010-11-10 下午08:11:52
   */
  private String[] getCodeOverToler(String wbNumFiled, String[] srcBids,
      String tolerFiled) {
    // 物料基本信息表
    String mtable = MaterialVO.getDefaultTableName();

    DataAccessUtils utils = new DataAccessUtils();
    IDExQueryBuilder idQueryBuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_pub_02.name());
    String bidsql = idQueryBuilder.buildSQL("t." + this.getBidFiled(), srcBids);

    String sql =
        "select m.code from " + this.getTable() + " t inner join " + mtable
            + " m on t.pk_material=m.pk_material where abs(t."
            + this.getNumField() + ")* cast((1+m." + tolerFiled
            + "/100.0)as decimal (16, 8))< abs(t." + wbNumFiled + ") and "
            + bidsql;

    return utils.query(sql).toOneDimensionStringArray();
  }

}
