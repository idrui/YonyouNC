package nc.vo.pu.margin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.margin.BillMarginContext;
import nc.vo.pubapp.margin.MarginContextFactory;
import nc.vo.pubapp.margin.MarginEntry;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 默认实现尾差处理器
 * 
 * @since 6.0
 * @version 2012-4-21 下午7:41:08
 * @author zhaoyha
 */
public class DefaultPuMarginProcess implements IPuMarginProcess {

  private IPuMarginCondition condition;

  private CircularlyAccessibleValueObject[] curVos;

  private CircularlyAccessibleValueObject[] siblingVos;

  private CircularlyAccessibleValueObject[] srcVos;

  private IPuMarginVOProcess voProcess;

  /**
   * 构造一个默认的尾差处理器
   * 
   * @param condition 处理器比较判断条件提供者
   * @param voProcess vo加工处理器
   * @param curVos 当前要进行倒挤的VO
   * @param siblingVos 对应的兄弟VO，来源相同的VO
   * @param srcVos 来源VO
   */
  public DefaultPuMarginProcess(IPuMarginCondition condition,
      IPuMarginVOProcess voProcess, CircularlyAccessibleValueObject[] curVos,
      CircularlyAccessibleValueObject[] siblingVos,
      CircularlyAccessibleValueObject[] srcVos) {
    super();
    this.condition = condition;
    this.voProcess = voProcess;
    this.curVos = curVos;
    this.siblingVos = siblingVos;
    this.srcVos = srcVos;
    this.initCurVOUniqueKey();// 将当前单据中初始化进特殊key
  }

  public IPuMarginCondition getCondition() {
    return this.condition;
  }

  public CircularlyAccessibleValueObject[] getCurVos() {
    return this.curVos;
  }

  public CircularlyAccessibleValueObject[] getSiblingVos() {
    return this.siblingVos;
  }

  public CircularlyAccessibleValueObject[] getSrcVos() {
    return this.srcVos;
  }

  public IPuMarginVOProcess getVoProcess() {
    return this.voProcess;
  }

  @Override
  public void process() {
    Map<String, CircularlyAccessibleValueObject> uniqueKeySrcMap =
        this.getVoProcess().procSrcVO(this.getCurVos(), this.getSrcVos());
    Map<String, CircularlyAccessibleValueObject[]> uniqueKeySiblingMap =
        this.getVoProcess().procCurSiblingVO(this.getCurVos(),
            this.getSiblingVos());
    Map<String, String> marginFieldsMap =
        this.getCondition().curSrcMarginField();
    String[] srcMarginFields = new String[marginFieldsMap.size()];
    String[] curMarginFields = new String[marginFieldsMap.size()];
    int i = 0;
    for (Entry<String, String> entry : marginFieldsMap.entrySet()) {
      srcMarginFields[i] = entry.getValue();
      curMarginFields[i] = entry.getKey();
      i++;
    }
    Map<String, String> compareFieldsMap =
        this.getCondition().curSrcCondJudgeField();
    String[] srcCompareFields = new String[compareFieldsMap.size()];
    String[] curCompareFields = new String[compareFieldsMap.size()];
    i = 0;
    for (Entry<String, String> entry : compareFieldsMap.entrySet()) {
      srcCompareFields[i] = entry.getValue();
      curCompareFields[i] = entry.getKey();
      i++;
    }

    this.execProcess(srcMarginFields, curMarginFields, srcCompareFields,
        curCompareFields, uniqueKeySrcMap, uniqueKeySiblingMap);
  }

  private void execProcess(String[] srcMarginFields, String[] curMarginFields,
      String[] srcCompareFields, String[] curCompareFields,
      Map<String, CircularlyAccessibleValueObject> uniqueKeySrcMap,
      Map<String, CircularlyAccessibleValueObject[]> uniqueKeySiblingMap) {
    List<CircularlyAccessibleValueObject[]> splitByCurSrcSign =
        this.splitByCurSrcSign(uniqueKeySrcMap);
    // 上下游符号相同
    if (null != splitByCurSrcSign.get(0)) {
      this.execProcessBySplitCurItems(splitByCurSrcSign.get(0),
          srcMarginFields, curMarginFields, srcCompareFields, curCompareFields,
          uniqueKeySrcMap, uniqueKeySiblingMap, false);
    }
    // 上下游符号不同
    if (null != splitByCurSrcSign.get(1)) {
      this.execProcessBySplitCurItems(splitByCurSrcSign.get(1),
          srcMarginFields, curMarginFields, srcCompareFields, curCompareFields,
          uniqueKeySrcMap, uniqueKeySiblingMap, true);
    }
  }

  private void execProcessBySplitCurItems(
      CircularlyAccessibleValueObject[] curProcessVos,
      String[] srcMarginFields, String[] curMarginFields,
      String[] srcCompareFields, String[] curCompareFields,
      Map<String, CircularlyAccessibleValueObject> uniqueKeySrcMap,
      Map<String, CircularlyAccessibleValueObject[]> uniqueKeySiblingMap,
      boolean isDiffsign) {
    try {
      BillMarginContext context =
          MarginContextFactory.getInstance()
              .produceMarginContext(
                  curProcessVos,
                  null,// 来源单据类型
                  this.getCondition().srcNumFieldName(),// 来源单据主数量字段
                  srcMarginFields,// 要倒挤的字段对应的来源单据字段
                  null,// 当前单据类型
                  this.getCondition().curNumFieldName(),// 当前单据主数量字段
                  curMarginFields,// 当前单据要倒挤字段
                  null,// 来源单据bid在当前单据上的来源单据字段名称
                  uniqueKeySrcMap,// 来源单据map，以特殊key
                  uniqueKeySiblingMap,// 当前单据的兄弟单据map，以特殊key
                  new MuiltyWordMarginJudgement(srcCompareFields,
                      curCompareFields));
      MarginEntry me = MarginEntry.getInstance();
      me.setDownSymbolMinus(isDiffsign);
      me.process(context);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void initCurVOUniqueKey() {
    for (int i = 0; i < this.getCurVos().length; i++) {
      this.getCurVos()[i].setAttributeValue(MarginContextFactory.uniqueKey,
          Integer.valueOf(i));
    }
  }

  /**
   * 根据上下游数量的正负号，将CurVos分隔成两部分，一部分是正负号相同的，一部分是正负号不同的。
   * 
   * @param uniqueKeySrcMap
   * @return vo数组List，第一个元素是相同的vo数组，第二个元素是不同的vo数组
   */
  private List<CircularlyAccessibleValueObject[]> splitByCurSrcSign(
      Map<String, CircularlyAccessibleValueObject> uniqueKeySrcMap) {
    List<CircularlyAccessibleValueObject> sameSignList =
        new ArrayList<CircularlyAccessibleValueObject>();
    List<CircularlyAccessibleValueObject> diffSignList =
        new ArrayList<CircularlyAccessibleValueObject>();
    for (CircularlyAccessibleValueObject curItem : this.getCurVos()) {
      UFDouble curNum =
          (UFDouble) curItem.getAttributeValue(this.getCondition()
              .curNumFieldName());
      String uniueKey =
          curItem.getAttributeValue(MarginContextFactory.uniqueKey).toString();
      CircularlyAccessibleValueObject srcItem = uniqueKeySrcMap.get(uniueKey);
      UFDouble srcNum =
          (UFDouble) srcItem.getAttributeValue(this.getCondition()
              .srcNumFieldName());
      if (MathTool.isDiffSign(curNum, srcNum)) {
        diffSignList.add(curItem);
      }
      else {
        sameSignList.add(curItem);
      }
    }
    List<CircularlyAccessibleValueObject[]> splitLists =
        new ArrayList<CircularlyAccessibleValueObject[]>();

    if (sameSignList.size() > 0) {
      splitLists.add(new ListToArrayTool<CircularlyAccessibleValueObject>()
          .convertToArray(sameSignList));
    }
    else {
      splitLists.add(null);
    }
    if (diffSignList.size() > 0) {
      splitLists.add(new ListToArrayTool<CircularlyAccessibleValueObject>()
          .convertToArray(diffSignList));
    }
    else {
      splitLists.add(null);
    }
    return splitLists;

  }

}
