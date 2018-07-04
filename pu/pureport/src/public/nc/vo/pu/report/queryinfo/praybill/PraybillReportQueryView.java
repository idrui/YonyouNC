package nc.vo.pu.report.queryinfo.praybill;

import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.report.queryinfo.PUAbstractReportQueryView;
import nc.vo.pu.report.queryinfo.PUReportQueryPara;
import nc.vo.pu.report.queryinfo.TableJoinInfo;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class PraybillReportQueryView extends PUAbstractReportQueryView{
  
  /** �����Ƿ�ί�� */
  private StringBuilder bsctypeSql = new StringBuilder();
  
  // ��������
  public static final String[] TABLES = new String[]{PUEntity.M20_H_TABLE, PUEntity.M20_B_TABLE};
  
  public PraybillReportQueryView(ConditionVO[] conds) {
    super(conds, TABLES);
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
    queryPara.addClazz(PUEntity.M20_H_TABLE, PraybillHeaderVO.class);
    queryPara.addClazz(PUEntity.M20_B_TABLE, PraybillItemVO.class);
    
    // ���ѡ���ֶ�
    String[] bodyFields = new String[]{PraybillItemVO.PK_PRAYBILL_B};
    queryPara.addSelectFields(PUEntity.M20_B_TABLE, bodyFields);
    
    // ��ӱ�������Ϣ
    queryPara.addJoinInfos(new TableJoinInfo(PUEntity.M20_H_TABLE, 
        PUEntity.M20_B_TABLE, PraybillHeaderVO.PK_PRAYBILL, PraybillItemVO.PK_PRAYBILL));
    
    // ��������ֵ������
    this.setQueryPara(queryPara);
  }
  
  /**
   * �����Ƿ�ί��
   * 
   * @param isBsc
   */
  public void setBsctype(UFBoolean isBsc){
    SqlBuilder where = new SqlBuilder();
    where.append(" and " + PUEntity.M20_H_TABLE + "." + PraybillHeaderVO.BSCTYPE, isBsc);
    this.bsctypeSql.append(where.toString());
  }
  
  @Override
  protected void addFixWherePart() {
    String pk_group = AppContext.getInstance().getPkGroup();
    SqlBuilder where = new SqlBuilder();
    where.append(" and " + PUEntity.M20_H_TABLE + "." + PraybillHeaderVO.PK_GROUP, pk_group);
    where.append(" and " + PUEntity.M20_H_TABLE + "." + PraybillHeaderVO.BISLATEST, UFBoolean.TRUE);
    this.appendWherePart(where.toString());
    this.appendWherePart(this.bsctypeSql.toString());
  }
}
