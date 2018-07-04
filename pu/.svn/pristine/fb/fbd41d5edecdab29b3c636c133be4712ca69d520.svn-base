package nc.vo.pu.report.queryinfo.journal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import nc.vo.pu.report.enumeration.GroupEnum;
import nc.vo.pu.report.enumeration.OrderType;

/**
 * private String SELECT_Feild = "  sum(ordernnum) ordernnum, "
 * + "  sum(ordernmny) ordernmny, " + "  sum(orderntax) orderntax,"
 * + "  sum(arrivennum) arrivennum,"
 * + "  sum(arrivebacknnum) arrivebacknnum,"
 * + "  sum(arrivenmny) arrivenmny," + "  sum(purnnum) purnnum,"
 * + "  sum(purbacknnum) purbacknnum," + "  sum(purnmny) purnmny,"
 * + "  sum(invoicennum) invoicennum," + "  sum(invoicetax) invoicetax,"
 * + "  sum(invoicenmny) invoicenmny," + "  sum(settlennum) settlennum,"
 * + "  sum(settlenmny) settlenmny";
 * 
 * @since 6.0
 * @version 2011-9-16 下午03:07:51
 * @author liuchx
 */
public class JournalHiddenKeyUtils {
  public static String DBILLDATE = "dbilldate";

  public static String MONTH = "monthvalue";

  public static String PK_DEPT_V = "pk_dept_v.name";

  public static String PK_MARBASCLASS = "pk_marbasclass.name";

  public static String PK_MATERIAL = "pk_material";

  public static String PK_ORG_V = "pk_org_v.name";

  public static String PK_PSNDOC = "pk_psndoc.name";

  public static String PK_SUPPLIER = "pk_supplier.name";

  /**
   * 取隐藏字段
   * 
   * @param para
   * @return
   */
  public static String[] getHiddenKeys(JournalInfoPara para) {

    Integer grouptype = Integer.valueOf(para.getGroupcondtion());

    String[] billtypes = para.getBilltypes();

    boolean bSupplier = para.isbShowBySupplier();

    boolean bMar = para.isbShowByMar();

    Set<String> hideSet = new HashSet<String>();
    // 汇总口径隐藏字段
    String[] groupHiddenKeys =
        JournalHiddenKeyUtils.getGroupHiddenKeys(grouptype);
    hideSet.addAll(Arrays.asList(groupHiddenKeys));

    // 是否展开隐藏字段
    if (!bSupplier && !grouptype.equals(GroupEnum.SUPPLIER.value())) {
      hideSet.add(JournalHiddenKeyUtils.PK_SUPPLIER);
    }
    if (!bMar && !grouptype.equals(GroupEnum.MAR.value())) {
      hideSet.add(JournalHiddenKeyUtils.PK_MATERIAL + ".code");
      hideSet.add(JournalHiddenKeyUtils.PK_MATERIAL + ".name");
      hideSet.add(JournalHiddenKeyUtils.PK_MATERIAL + ".materialspec");
      hideSet.add(JournalHiddenKeyUtils.PK_MATERIAL + ".materialtype");
    }

    // 统计内容隐藏字段
    JournalHiddenKeyUtils.getOrderTypeHiddenKeys(billtypes, hideSet);

    return hideSet.toArray(new String[hideSet.size()]);
  }

  private static String[] getGroupHiddenKeys(Integer grouptype) {
    // modify by wangljc at 2011-10-11 15:53:17
    String[] groupHiddenKeys = null;
    if (grouptype.equals(GroupEnum.MAR.value())) {
      groupHiddenKeys =
          new String[] {
            JournalHiddenKeyUtils.PK_MARBASCLASS,
            JournalHiddenKeyUtils.PK_ORG_V, JournalHiddenKeyUtils.PK_DEPT_V,
            JournalHiddenKeyUtils.PK_PSNDOC, JournalHiddenKeyUtils.MONTH,
            JournalHiddenKeyUtils.DBILLDATE,
          };
    }
    if (GroupEnum.MARCLASS.value().equals(grouptype)) {

      // 物料展开
      groupHiddenKeys =
          new String[] {
            JournalHiddenKeyUtils.PK_ORG_V, JournalHiddenKeyUtils.PK_DEPT_V,
            JournalHiddenKeyUtils.PK_PSNDOC, JournalHiddenKeyUtils.MONTH,
            JournalHiddenKeyUtils.DBILLDATE,
          };

    }
    if (GroupEnum.SUPPLIER.value().equals(grouptype)) {
      groupHiddenKeys =
          new String[] {
            JournalHiddenKeyUtils.PK_ORG_V, JournalHiddenKeyUtils.PK_DEPT_V,
            JournalHiddenKeyUtils.PK_PSNDOC, JournalHiddenKeyUtils.MONTH,
            JournalHiddenKeyUtils.DBILLDATE,
            JournalHiddenKeyUtils.PK_MARBASCLASS
          };
    }
    if (GroupEnum.PUR_ORG.value().equals(grouptype)) {
      groupHiddenKeys =
          new String[] {
            JournalHiddenKeyUtils.PK_DEPT_V, JournalHiddenKeyUtils.PK_PSNDOC,
            JournalHiddenKeyUtils.MONTH, JournalHiddenKeyUtils.DBILLDATE,
            JournalHiddenKeyUtils.PK_MARBASCLASS
          };
    }
    if (GroupEnum.DEPT.value().equals(grouptype)) {
      groupHiddenKeys =
          new String[] {
            JournalHiddenKeyUtils.PK_ORG_V, JournalHiddenKeyUtils.PK_PSNDOC,
            JournalHiddenKeyUtils.MONTH, JournalHiddenKeyUtils.DBILLDATE,
            JournalHiddenKeyUtils.PK_MARBASCLASS
          };
    }
    if (GroupEnum.PSNDOC.value().equals(grouptype)) {
      groupHiddenKeys =
          new String[] {
            JournalHiddenKeyUtils.PK_ORG_V, JournalHiddenKeyUtils.PK_DEPT_V,
            JournalHiddenKeyUtils.MONTH, JournalHiddenKeyUtils.DBILLDATE,
            JournalHiddenKeyUtils.PK_MARBASCLASS
          };
    }
    if (GroupEnum.DATE.value().equals(grouptype)) {
      groupHiddenKeys =
          new String[] {
            JournalHiddenKeyUtils.PK_ORG_V, JournalHiddenKeyUtils.PK_DEPT_V,
            JournalHiddenKeyUtils.PK_PSNDOC, JournalHiddenKeyUtils.MONTH,
            JournalHiddenKeyUtils.PK_MARBASCLASS
          };
    }
    if (GroupEnum.MONTH.value().equals(grouptype)) {
      groupHiddenKeys =
          new String[] {
            JournalHiddenKeyUtils.PK_ORG_V, JournalHiddenKeyUtils.PK_DEPT_V,
            JournalHiddenKeyUtils.PK_PSNDOC, JournalHiddenKeyUtils.DBILLDATE,
            JournalHiddenKeyUtils.PK_MARBASCLASS
          };
    }

    return groupHiddenKeys;
  }

  private static void getOrderTypeHiddenKeys(String[] billtypes,
      Set<String> hideSet) {
    // 所有统计内容
    Set<Integer> allTypes = new HashSet<Integer>();
    // 选中统计内容
    Set<Integer> selectTypes = new HashSet<Integer>();
    // 隐藏统计内容
    Set<Integer> hiddenTypes = new HashSet<Integer>();

    allTypes.add(OrderType.PUORDER.toInteger());
    allTypes.add(OrderType.PUARRIVE.toInteger());
    allTypes.add(OrderType.PURIN.toInteger());
    allTypes.add(OrderType.PUINVOICE.toInteger());
    allTypes.add(OrderType.PUSETTLE.toInteger());

    for (String billtype : billtypes) {
      String billtypeString = billtype.substring(1, 2);
      Integer billInt = Integer.valueOf(billtypeString);
      selectTypes.add(billInt);
    }

    for (Integer billtype : allTypes) {
      if (!selectTypes.contains(billtype)) {
        hiddenTypes.add(billtype);
      }
    }
    // 统计内容隐藏字段
    for (Integer billInt : hiddenTypes) {

      if (billInt.equals(OrderType.PUORDER.value())) {
        hideSet.add("ordernnum");
        hideSet.add("ordernmny");
        hideSet.add("orderntax");
        hideSet.add("orderprice");
        hideSet.add("ordertaxmny");
        hideSet.add("orderrate");
        hideSet.add("nacccancelinvmny");
        hideSet.add("nacccancelinvmnyrate");
      }
      if (billInt.equals(OrderType.PUARRIVE.value())) {
        hideSet.add("arrivennum");
        hideSet.add("arrivebacknnum");
        hideSet.add("arrivenmny");
      }
      if (billInt.equals(OrderType.PURIN.value())) {
        hideSet.add("purbacknnum");
        hideSet.add("purnnum");
        hideSet.add("purnmny");
      }
      if (billInt.equals(OrderType.PUINVOICE.value())) {
        hideSet.add("invoicennum");
        hideSet.add("invoicetax");
        hideSet.add("invoiceprice");
        hideSet.add("invoicenmny");
        hideSet.add("invoicetaxmny");
        hideSet.add("invoicerate");
      }
      if (billInt.equals(OrderType.PUSETTLE.value())) {
        hideSet.add("settlennum");
        hideSet.add("settleprice");
        hideSet.add("settlenmny");
      }
    }
  }
}
