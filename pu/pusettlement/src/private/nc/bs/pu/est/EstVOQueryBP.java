/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-1 下午12:03:08
 */
package nc.bs.pu.est;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.util.AggVOUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估VO查询BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-7-1 下午12:03:08
 */
public class EstVOQueryBP<T extends EstVO> {
  private Class<T> estClazz;

  private Class<GoodsEstVO> headClazz;

  private Class<FeeEstVO> itemClazz;

  // 入库行字段名，考虑期初命名不一致
  private String stock_b_key = GoodsEstVO.PK_STOCKPS_B;

  /**
   * EstVOQueryBP 的构造子
   * 
   * @param estClazz
   * @param headClazz
   * @param itemClazz
   */
  @SuppressWarnings("unchecked")
  public EstVOQueryBP(Class<T> estClazz, Class<? extends GoodsEstVO> headClazz,
      Class<? extends FeeEstVO> itemClazz) {
    this.estClazz = estClazz;
    this.headClazz = (Class<GoodsEstVO>) headClazz;
    this.itemClazz = (Class<FeeEstVO>) itemClazz;
  }

  public T[] combineVO(GoodsEstVO[] heads, FeeEstVO[] fees) {
    if (ArrayUtils.isEmpty(heads)) {
      return null;
    }
    return AggVOUtil.createAggVO(heads, fees, this.estClazz,
        this.getStock_b_key());
  }

  public String getStock_b_key() {
    return this.stock_b_key;
  }

  public T[] query(String[] stockbids) {
    return this
        .combineVO(this.queryHead(stockbids), this.queryItems(stockbids));
  }

  public GoodsEstVO[] queryHead(String[] stockbids) {
    ViewQuery<GoodsEstVO> vquery = new ViewQuery<GoodsEstVO>(this.headClazz);
    return vquery.query(stockbids);
  }

  public FeeEstVO[] queryItems(String[] stockbids) {
    VOQuery<FeeEstVO> voquery = new VOQuery<FeeEstVO>(this.itemClazz);
    IDExQueryBuilder inbuilder =
        new IDExQueryBuilder(PUTempTable.tmp_po_est_02.name());
    String insql =
        " and " + inbuilder.buildSQL(this.getStock_b_key(), stockbids);
    return voquery.query(insql, null);
  }

  public void setStock_b_key(String stock_b_key) {
    this.stock_b_key = stock_b_key;
  }
}
