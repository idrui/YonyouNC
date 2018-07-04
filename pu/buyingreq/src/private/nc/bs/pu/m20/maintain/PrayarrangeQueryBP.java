/**
 * 
 */
package nc.bs.pu.m20.maintain;

import nc.impl.pubapp.pattern.data.view.SchemeViewQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PrayarrangeViewVO;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20trantype.entity.BuyrTranTypeVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据查询方案返回PrayarrangeViewVO数组
 * </ul>
 * <p>
 * <p>
 * 
 * @version
 * @since
 * @author luojw
 * @time 2014-6-17 下午4:04:55
 */
public class PrayarrangeQueryBP {

  public PrayarrangeViewVO[] queryPrayarrangeViewVO(IQueryScheme queryScheme) {
    // 构造方案查询处理器，方便添加条件
    QuerySchemeProcessor processor = new QuerySchemeProcessor(queryScheme);
    // 获取表头表体表名
    String headerTable = processor.getMainTableAlias();
    String itemTable =
        processor.getTableAliasOfAttribute("pk_praybill_b.pk_org");
    // 将筛选条件加入
    processor
        .appendWhere(this.makeHeaderCondition(" and " + headerTable + "."));
    processor.appendWhere(this.makeItemCondition(" and " + itemTable + "."));
    // 构造查询视图VO查询处理类
    SchemeViewQuery<PrayarrangeViewVO> query =
        new SchemeViewQuery<PrayarrangeViewVO>(PrayarrangeViewVO.class);
    return query.query(queryScheme, null);
  }

  /**
   * 添加表头的筛选条件
   * 
   * @param table
   * @return
   *         下午7:16:32
   */
  private String makeHeaderCondition(String table) {
    String sql =
        "select " + BuyrTranTypeVO.CTRANTYPEID + " from " + PUEntity.M20_TRANTYPE_TABLE + " where "
            + BuyrTranTypeVO.BNEEDARRANGE + " = 'Y'";
    StringBuilder builder = new StringBuilder();
    builder
        .append(table + PraybillHeaderVO.BISLATEST + " = 'Y' ")
        // 交易类型为需要请购安排
        .append(table + PraybillHeaderVO.CTRANTYPEID + " in (" + sql + ")")
        // 审批通过
        .append(
            table + PraybillHeaderVO.FBILLSTATUS + " = '"
                + POEnumBillStatus.APPROVE.getEnumValue().getValue() + "'");
    return builder.toString();
  }

  /**
   * 添加表体的筛选条件
   * 
   * @param table
   * @return
   *         下午7:16:38
   */
  private String makeItemCondition(String table) {
    StringBuilder builder = new StringBuilder();
    // 行关闭
    builder.append(table + PraybillItemVO.BROWCLOSE + " = 'N'")
    // 发布到电子商务
        .append(table + PraybillItemVO.BPUBLISHTOEC + " = 'N'")
        // 是否已经生成合同
        .append(table + PraybillItemVO.NGENCT + " = 0")
        // 是否生成订单
        .append(table + PraybillItemVO.NACCUMULATENUM + " = 0")
        // 是否生成询报价单
        .append(table + PraybillItemVO.NQUOTEBILL + " = 0")
        // 是否生成价格审批单
        .append(table + PraybillItemVO.NPRICEAUDITBILL + " = 0");
    return builder.toString();
  }
}
