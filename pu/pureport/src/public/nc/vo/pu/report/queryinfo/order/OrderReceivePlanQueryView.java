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
  
  // ��������
  public static final String[] TABLES = new String[]{PUEntity.M21_H_TABLE, 
    PUEntity.M21_B_TABLE, PUEntity.M21_BB1_TABLE, PUEntity.M21_TRANTYPE_TABLE};
  
  /** �����ƻ��Ƿ��ջ����Ƭ�� */
  private StringBuilder finishrecvSql = new StringBuilder();
  
  public OrderReceivePlanQueryView(ConditionVO[] conds, SmartContext context) {
    super(conds, TABLES, context);
    // ��ʼ����ѯ��Ϣ
    this.initQueryPara();
    // ����from��whereƬ��
    this.processFromAndWhere();
  }

  /**
   *  ��ʼ����ѯ��Ϣ
   */
  private void initQueryPara(){
    PUReportQueryPara queryPara = new PUReportQueryPara();
    
    // ���class��
    queryPara.addClazz(PUEntity.M21_H_TABLE, OrderHeaderVO.class);
    queryPara.addClazz(PUEntity.M21_B_TABLE, OrderItemVO.class);
    queryPara.addClazz(PUEntity.M21_BB1_TABLE, OrderReceivePlanVO.class);
    queryPara.addClazz(PUEntity.M21_TRANTYPE_TABLE, PoTransTypeVO.class);
    
    // ���ѡ���ֶ�
    String[] bodySelectFields = new String[]{OrderReceivePlanVO.PK_ORDER_BB1};
    queryPara.addSelectFields(PUEntity.M21_BB1_TABLE, bodySelectFields);
    
    // ��������ֶ�
    String[] headOrderFields = new String[]{OrderHeaderVO.DBILLDATE, OrderHeaderVO.VBILLCODE};
    String[] bb1OrderFields = new String[]{OrderReceivePlanVO.VBILLCODE};
    queryPara.addOrderFields(PUEntity.M21_H_TABLE, headOrderFields);
    queryPara.addOrderFields(PUEntity.M21_BB1_TABLE, bb1OrderFields);
    
    // ��ӱ�������Ϣ
    queryPara.addJoinInfos(new TableJoinInfo(PUEntity.M21_H_TABLE, 
        PUEntity.M21_B_TABLE, OrderHeaderVO.PK_ORDER, OrderItemVO.PK_ORDER));
    queryPara.addJoinInfos(new TableJoinInfo(PUEntity.M21_B_TABLE, 
        PUEntity.M21_BB1_TABLE, OrderItemVO.PK_ORDER_B, OrderReceivePlanVO.PK_ORDER_B));
    queryPara.addJoinInfos(new TableJoinInfo(PUEntity.M21_H_TABLE, 
        PUEntity.M21_TRANTYPE_TABLE, OrderHeaderVO.CTRANTYPEID, PoTransTypeVO.CTRANTYPEID));
    
    // ��������ֵ������
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
   * �����ƻ��Ƿ��ջ����
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
    // bfinishrecvΪtrue������������⣬�޵�������ȫ��⣬�������ر�
    // Ϊfalse, �޵������ڣ�δ��ȫ��⣬����
    where.append(PUEntity.M21_TRANTYPE_TABLE + "." + PoTransTypeVO.BSTORE, "Y");
    where.append(TableJoinInfo.AND + PUEntity.M21_TRANTYPE_TABLE + "." + PoTransTypeVO.BARRIVE, "N");
    where.append(TableJoinInfo.AND + " ( " + PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.NNUM);
    where.append(operator);
    where.append("isnull (" + PUEntity.M21_BB1_TABLE + "." + OrderReceivePlanVO.NACCUMSTORENUM + ",0)");
    where.append(logic);
    where.append(PUEntity.M21_B_TABLE + "." + OrderItemVO.BSTOCKCLOSE, bclose);
    where.append(")) or (");
    // bfinishrecvΪtrue�����������е�������ȫ���������ߵ����ر�
    // Ϊfalse, ���е������ڣ�δ��ȫ������������
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
