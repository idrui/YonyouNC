package nc.vo.pu.pub.constant;

/**
 * 采购查询对话框用到的过滤常量
 * 
 * @since 6.0
 * @version 2011-1-6 上午08:08:06
 * @author wuxla
 */

public class PUQueryConst {

  /**
   * 是否退货或者退库
   */
  public static final String BACK = "back";

  /** 制单人 */
  public static final String BILLMAKER = "billmaker";

  // 2012.4.19 lixyp 为修改发布的交易类型转单查询查询出了非此交易类型的数据而作的修改
  // 因任务关闭无法以该任务之名签入文件，特加此注释。
  /**
   * 查询方案流程常量，可以作为key放置当前单据（转单时指下游单据）的单据类型或交易类型（交易类型发布节点时）
   */
  public static final String BILLTYPE_QS_KEY = "billtype_qs_key";

  /**
   * 待审批
   */
  public static final String BISAPPROVING = "bisapproving";

  /**
   * 流程过滤常量
   */
  public static final String BUSITYPES = "busitypes";

  /**
   * 查询方案流程常量，可以作为key放置业务流程
   */
  public static final String BUSITYPES_QS_KEY = "busitypes_qs_key";

  /**
   * 单据日期
   */
  public static final String DBILLDATE = "dbilldate";

  /** 制单日期 */
  public static final String DMAKEDATE = "dmakedate";

  /**
   * 分隔符
   */
  public static final String DOT = ".";

  /**
   * 物料基本分类编码
   */
  public static final String MARBASCLASS_CODE =
      ".pk_material.pk_marbasclass.code";

  /**
   * 物料采购分类编码
   */
  public static final String MARPUCLASS_CODE =
      ".pk_material.materialstock.pk_marpuclass.code";

  /**
   * 物料编码
   */
  public static final String MATERIAL_CODE = ".pk_material.code";

  /**
   * 物料名称
   */
  public static final String MATERIAL_NAME = ".pk_material.name";

  /**
   * 合并打印时自定义变量物料编码
   */
  public static final String MATERIALNAME = "materialname";

  /** 业务流程 */
  public static final String PK_BUSITYPE = "pk_busitype";

  public static final String PK_ORG = "pk_org";

  public static final String PK_ORG_V = "pk_org_v";

  /**
   * 需求部门
   */
  public static final String PK_REQDEPT = ".pk_reqdept";

  /**
   * 需求库存组织
   */
  public static final String PK_REQSTOORG = ".pk_reqstoorg";

  /**
   * 物料编码
   */
  public static final String SRCMATERIAL_CODE = ".pk_srcmaterial.code";

  /**
   * 物料名称
   */
  public static final String SRCMATERIAL_NAME = ".pk_srcmaterial.name";

  /**
   * 供应商
   */
  public static final String SUPPLIER = "pk_supplier";

  /**
   * 供应商编码
   */
  public static final String SUPPLIER_CODE = "pk_supplier.code";

  /**
   * 供应商名称
   */
  public static final String SUPPLIER_NAME = "pk_supplier.name";

  /** 单据号 **/
  public static final String VBILLCODE = "vbillcode";

  /** 交易类型编码 */
  public static final String VTRANTYPECODE = "vtrantypecode";

  public static final String Y = "Y";

}
