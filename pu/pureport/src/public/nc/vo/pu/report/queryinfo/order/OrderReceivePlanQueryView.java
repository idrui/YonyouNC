package nc.vo.pu.report.queryinfo.order;

import java.util.HashMap;
import java.util.Map;

import nc.bs.pubapp.report.ReportPermissionUtils;
import nc.pub.smart.context.SmartContext;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.report.queryinfo.PUAbstractReportQueryView;
import nc.vo.pu.report.queryinfo.PUReportQueryPara;
import nc.vo.pu.report.queryinfo.TableJoinInfo;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class OrderReceivePlanQueryView extends PUAbstractReportQueryView{
  
  // 表名数组
  public static final String[] TABLES = new String[]{PUEntity.M21_H_TABLE, 
    PUEntity.M21_B_TABLE, PUEntity.M21_BB1_TABLE, PUEntity.M21_TRANTYPE_TABLE};
  
  /** 到货计划是否收货完成片段 */
  private StringBuilder finishrecvSql = new StringBuilder();
  
  public OrderReceivePlanQueryView(ConditionVO[] conds, SmartContext context) {
    super(conds, TABLES, context);
    // 初始化查询信息
    this.initQueryPara();
    // 处理from和where片段
    this.processFromAndWhere();
  }

  /**
   *  初始化查询信息
   */
  private void initQueryPara(){
    PUReportQueryPara queryPara = new PUReportQueryPara();
    
    // 添加class类
    queryPara.addClazz(PUEntity.M21_H_TABLE, OrderHeaderVO.class);
    queryPara.addClazz(PUEntity.M21_B_TABLE, OrderItemVO.class);
    queryPara.addClazz(PUEntity.M21_BB1_TABLE, OrderReceivePlanVO.class);
    queryPara.addClazz(PUEntity.M21_TRANTYPE_TABLE, PoTransTypeVO.class);
    
    // 添加选择字段
    String[] bodySelectFields = new String[]{OrderReceivePlanVO.PK_ORDER_BB1};
    queryPara.addSelectFields(PUEntity.M21_BB1_TABLE, bodySelectFields);
    
    // 添加排序字段
    String[] headOrderFields = new String[]{OrderHeaderVO.DBILLDATE, OrderHeaderVO.VBILLCODE};
    String[] bb1OrderFields = new String[]{OrderReceivePlanVO.VBILLCODE};
    queryPara.addOrderFields(PUEntity.M21_H_TABLE, headOrderFields);
    queryPara.addOrderFields(PUEntity.M21_BB1_TABLE, bb1OrderFields);
    
    // 添加表连接信息
    queryPara.addJoinInfos(new TableJoinInfo(PUEntity.M21_H_TABLE, 
        PUEntity.M21_B_TABLE, OrderHeaderVO.PK_ORDER, OrderItemVO.PK_ORDER));
    queryPara.addJoinInfos(new TableJoinInfo(PUEntity.M21_B_TABLE, 
        PUEntity.M21_BB1_TABLE, OrderItemVO.PK_ORDER_B, OrderReceivePlanVO.PK_ORDER_B));
    queryPara.addJoinInfos(new TableJoinInfo(PUEntity.M21_H_TABLE, 
        PUEntity.M21_TRANTYPE_TABLE, OrderHeaderVO.CTRANTYPEID, PoTransTypeVO.CTRANTYPEID));
    
    // 将参数赋值给父类
    this.setQueryPara(queryPara);
  }
  
  @Override
  protected void addFixWherePart() {
    String pk_group = AppContext.getInstance().getPkGroup();
    SqlBuilder where = new SqlBuilder();
    where.append(TableJoinInfo.AND + PUEntity.M21_H_TABLE + "." + OrderHeaderVO.PK_GROUP, pk_group);
    where.append(TableJoinInfo.AND + PUEntity.M21_H_TABLE + "." + OrderHeaderVO.BISLATEST, "Y");
    this.appendWherePart(where.toString());
    this.appendWherePart(this.finishrecvSql.toString());
  }
  
  /**
   * 到货计划是否收货完成
   * 
   * @param bfinishrecv
   */
  public void setFinishrecv(UFBoolean bfinishrecv){
    SqlBuilder where = new SqlBuilder();
    where.append(TableJoinInfo.AND);
    EnumActive fisactive = EnumActive.CLOSE;
    String logic = TableJoinInfo.OR; 
    UFBoolean bclose = UFBoolean.TRUE;
    String operator = " <= ";
    if(!bfinishrecv.booleanValue()){
      fisactive = EnumActive.ACTIVE;
      logic = TableJoinInfo.AND; 
      bclose = UFBoolean.FALSE;
      operator = " > ";
    }else{
      where.append("(");
    }
    where.append(PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.FISACTIVE, 
        fisactive.toIntValue());
    where.append(logic);
    where.append("((");
    // bfinishrecv为true，交易类型入库，无到货，完全入库，或者入库关闭
    // 为false, 无到货环节，未完全入库，入库打开
    where.append(PUEntity.M21_TRANTYPE_TABLE + "." + PoTransTypeVO.BSTORE, "Y");
    where.append(TableJoinInfo.AND + PUEntity.M21_TRANTYPE_TABLE + "." + PoTransTypeVO.BARRIVE, "N");
    where.append(TableJoinInfo.AND + " ( " + PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.NNUM);
    where.append(operator);
    where.append("isnull (" + PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.NACCUMSTORENUM + ",0)");
    where.append(logic);
    where.append(PUEntity.M21_B_TABLE + "." + OrderItemVO.BSTOCKCLOSE, bclose);
    where.append(")) or (");
    // bfinishrecv为true，交易类型有到货，完全到货，或者到货关闭
    // 为false, 或有到货环节，未完全到货，到货打开
    where.append(PUEntity.M21_TRANTYPE_TABLE + "." + PoTransTypeVO.BARRIVE, "Y");
    where.append(TableJoinInfo.AND + " ( " +PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.NNUM);
    where.append(" > isnull (" + PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.NACCUMARRVNUM + ",0)");
    where.append(logic);
    where.append(PUEntity.M21_B_TABLE + "." + OrderItemVO.BARRIVECLOSE, bclose);
    where.append(")))");
    
    if(bfinishrecv.booleanValue()){
      where.append(")");
    }
    
    this.finishrecvSql.append(where.toString());
  }
  
  @Override
  protected void appendRptPermission() {
    Map<Class<? extends ISuperVO>, String> beanMap =
        new HashMap<Class<? extends ISuperVO>, String>();
    beanMap.put(OrderHeaderVO.class, PUEntity.M21_H_TABLE);
    beanMap.put(OrderItemVO.class, PUEntity.M21_B_TABLE);
    String rptPermissionPart =
        new ReportPermissionUtils(this.getContext()).getPermissionSQL(beanMap);
    if (rptPermissionPart != null && rptPermissionPart.length() > 0) {
      this.appendFromPart(rptPermissionPart);
    }
  }
  
}
