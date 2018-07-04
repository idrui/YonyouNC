package nc.vo.pu.report.view.m422x;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.scmmm.vo.scmpub.report.pub.ISCMReportContext;
import nc.scmmm.vo.scmpub.report.viewfactory.sql.PermissionTableInfo;
import nc.scmmm.vo.scmpub.report.viewfactory.sql.SCMPermissionBeanSqlView;
import nc.vo.ic.pub.sql.BeanPath;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;

public class StoreReqSummaryRptView extends SCMPermissionBeanSqlView {

  private final static String BODYALIAS = "po_storereq_b";

  private final static String PK_STOREREQ_B = "pk_storereq_b";

  private static final long serialVersionUID = -7881470505032131939L;

  // 需要在SQL中出现的表体字段，使用List进行收集，统一转换为数组，避免在各处new数组，同时也方便进行一些判断和操作
  private List<String> bodyFields = new ArrayList<String>();

  // 需要在SQL中出现的表头字段，使用List进行收集，统一转换为数组，避免在各处new数组，同时也方便进行一些判断和操作
  private List<String> headerFields = new ArrayList<String>();

  public StoreReqSummaryRptView(ISCMReportContext context) {
    super(new StoreReqAppHeaderVO(), context);
  }

  /**
   * 当汇总方式为根据物料汇总时，添加相应的字段和分组方式。
   */
  public void addFieldsByMaterial() {
    this.bodyFields.add(StoreReqAppItemVO.PK_MATERIAL);

    this.commonProcess();
  }

  /**
   * 当汇总方式为根据库存组织 + 物料汇总时，添加相应的字段和分组方式。
   */
  public void addFieldsByOrgMaterial() {
    this.headerFields.add(StoreReqAppHeaderVO.PK_ORG_V);
    this.bodyFields.add(StoreReqAppItemVO.PK_MATERIAL);

    this.commonProcess();
  }

  /**
   * 当汇总方式为根据库存组织 + 需求日期 + 物料汇总时，添加相应的字段和分组方式。
   */
  public void addFieldsByOrgReqDateMaterial() {
    this.headerFields.add(StoreReqAppHeaderVO.PK_ORG_V);
    this.bodyFields.add(StoreReqAppItemVO.DREQDATE);
    this.bodyFields.add(StoreReqAppItemVO.PK_MATERIAL);

    this.commonProcess();
  }

  /**
   * 当汇总方式为根据需求日期 + 物料汇总时，添加相应的字段和分组方式。
   */
  public void addFieldsByReqDateMaterial() {
    this.bodyFields.add(StoreReqAppItemVO.DREQDATE);
    this.bodyFields.add(StoreReqAppItemVO.PK_MATERIAL);

    this.commonProcess();
  }

  /**
   * 当汇总方式为根据需求仓库 + 物料汇总时，添加相应的字段和分组方式。
   */
  public void addFieldsByReqStorDocMaterial() {
    this.bodyFields.add(StoreReqAppItemVO.PK_REQSTORDOC);
    this.bodyFields.add(StoreReqAppItemVO.PK_MATERIAL);

    this.commonProcess();
  }

  /**
   * 添加所有汇总方式都显示的字段
   */
  private void commonProcess() {
    // 加入通用字段
    if (!this.bodyFields.contains(StoreReqAppItemVO.PK_MATERIAL)) {
      this.bodyFields.add(StoreReqAppItemVO.PK_MATERIAL);
    }
    this.bodyFields.add(StoreReqAppItemVO.CUNITID);

    // 将之前收集的字段转换为数组
    String[] headerFieldArray = this.headerFields.toArray(new String[] {});
    String[] bodyFieldArray = this.bodyFields.toArray(new String[] {});

    // 添加分组字段
    this.addSelectFields(headerFieldArray, headerFieldArray);
    this.addSelectFields(StoreReqSummaryRptView.PK_STOREREQ_B, bodyFieldArray,
        bodyFieldArray);

    // 添加合计字段
    this.addViewSumFields(StoreReqSummaryRptView.PK_STOREREQ_B, new String[] {
      StoreReqAppItemVO.NNUM, StoreReqAppItemVO.NASTNUM,
      StoreReqAppItemVO.NTAXMNY, StoreReqAppItemVO.NACCUOUTNUM
    });
  }

  /**
   * 不要求数据权限
   */
  @Override
  protected Set<PermissionTableInfo> initFixTableAlias() {
    return new HashSet<PermissionTableInfo>();
  }

  @Override
  protected void initFixWhere() {
    // 过滤掉被删除的数据
    this.addWhereByAttrPathExp(BeanPath.DOT + "dr = 0");
    this.addWhereByAttrPathExp(StoreReqSummaryRptView.BODYALIAS + BeanPath.DOT
        + "dr = 0");
  }
}
