package nc.vo.pu.report.queryinfo.order;

import java.util.HashMap;
import java.util.Map;

import nc.bs.pubapp.report.ReportPermissionUtils;
import nc.pub.smart.context.SmartContext;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.report.queryinfo.PUAbstractReportQueryView;
import nc.vo.pu.report.queryinfo.PUReportQueryPara;
import nc.vo.pu.report.queryinfo.TableJoinInfo;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class OrderPayExecRptQueryView extends PUAbstractReportQueryView{
  
  // 表名数组
  public static final String[] TABLES = new String[]{PUEntity.M21_H_TABLE, PUEntity.M21_B_TABLE};
  
  public OrderPayExecRptQueryView(ConditionVO[] conds, SmartContext context) {
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
    
    // 添加选择字段
    String[] headSelectFields = new String[]{OrderHeaderVO.PK_ORDER,
        OrderHeaderVO.VBILLCODE, OrderHeaderVO.VCOOPORDERCODE, OrderHeaderVO.PK_SUPPLIER};
    queryPara.addSelectFields(PUEntity.M21_H_TABLE, headSelectFields);
    
    // 添加特殊字段
    queryPara.addSpecialNameField("SUM(po_order_b.ntaxmny)", "nordermny");
    queryPara.addSpecialNameField("SUM(ISNULL(po_order_b.naccuminvoicemny,0))", "ninvoicemny");
    queryPara.addSpecialNameField("SUM(ISNULL(po_order_b.naccuminvoicemny,0) -ISNULL(po_order_b.nacccancelinvmny,0))", "ninvoicebalance");
    
    // 添加分组字段
    String[] headGroupFields = new String[]{OrderHeaderVO.PK_ORDER,
        OrderHeaderVO.VBILLCODE, OrderHeaderVO.VCOOPORDERCODE, OrderHeaderVO.PK_SUPPLIER};
    queryPara.addGroupFields(PUEntity.M21_H_TABLE, headGroupFields);
    
    // 添加排序字段
    String[] headOrderFields = new String[]{OrderHeaderVO.VBILLCODE};
    queryPara.addOrderFields(PUEntity.M21_H_TABLE, headOrderFields);

    // 添加表连接信息
    queryPara.addJoinInfos(new TableJoinInfo(PUEntity.M21_H_TABLE, 
        PUEntity.M21_B_TABLE, OrderHeaderVO.PK_ORDER, OrderItemVO.PK_ORDER));
    
    // 将参数赋值给父类
    this.setQueryPara(queryPara);
  }
  
  @Override
  protected void addFixWherePart() {
    String pk_group = AppContext.getInstance().getPkGroup();
    SqlBuilder where = new SqlBuilder();
    where.append(TableJoinInfo.AND + PUEntity.M21_H_TABLE + "." + OrderHeaderVO.PK_GROUP, pk_group);
    where.append(TableJoinInfo.AND + PUEntity.M21_H_TABLE + "." + OrderHeaderVO.FORDERSTATUS, new int[]{3, 5});
    where.append(TableJoinInfo.AND + PUEntity.M21_H_TABLE + "." + OrderHeaderVO.BISLATEST, "Y");
    where.append(TableJoinInfo.AND + PUEntity.M21_B_TABLE + "." + OrderItemVO.BLARGESS, "N");
    this.appendWherePart(where.toString());
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
