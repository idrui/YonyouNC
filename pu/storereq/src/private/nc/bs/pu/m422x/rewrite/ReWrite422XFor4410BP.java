package nc.bs.pu.m422x.rewrite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.pu.m422x.rewrite.rule.ProcessReWriteClearRule;
import nc.bs.pu.m422x.rewrite.rule.ProcessReWriteParamRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.invp.result.CalBalanceNumFor442XParam;
import nc.itf.invp.result.ICalBalanceNumFor442X;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppViewVO;
import nc.vo.pu.m422x.entity.WriteBack422XForInv9ParamVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.res.billtype.TOBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 出库申请单回写BP
 * 
 * @since 6.0
 * @version 2010-12-16 下午02:15:11
 * @author wuxla
 */
public class ReWrite422XFor4410BP {

  /**
   * 取消库存平衡后回写物资需求申请单实现--清空下游信息
   * 
   * @param pk_reqline
   * @throws BusinessException
   */
  public void backWriteClearFor4410(WriteBack422XForInv9ParamVO[] vos) {
    Set<String> reqLines = new HashSet<String>();

    for (WriteBack422XForInv9ParamVO para : vos) {
      reqLines.add(para.getPk_storereq_b());
    }

    // 查询更新前的物资需求申请数据
    StoreReqAppViewVO[] bodys =
        new ViewQuery<StoreReqAppViewVO>(StoreReqAppViewVO.class)
            .query(reqLines.toArray(new String[reqLines.size()]));

    Map<String, StoreReqAppViewVO> viewMap = CirVOUtil.createKeyVOMap(bodys);
    for (WriteBack422XForInv9ParamVO vo : vos) {
      StoreReqAppViewVO view = viewMap.get(vo.getPk_storereq_b());
      // 并发校验
      if (!view.getAttributeValue(StoreReqAppItemVO.TS).toString().trim()
          .equals(vo.getStrBTS().trim())) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004010_0", "04004010-0027"));/*
                                                                       * @res
                                                                       * "上游单据已被其他人修改！"
                                                                       */
      }
    }

    if (ArrayUtils.isEmpty(bodys)) {
      return;
    }

    Set<String> spKs = new HashSet<String>();
    Set<String> fpKs = new HashSet<String>();
    for (StoreReqAppViewVO body : bodys) {

      if (body.getAttributeValue(StoreReqAppItemVO.CSOURCEBID2) != null) {
        spKs.add(body.getAttributeValue(StoreReqAppItemVO.CSOURCEBID2)
            .toString());
      }
      if (body.getAttributeValue(StoreReqAppItemVO.CFIRSTBID2) != null) {
        fpKs.add(body.getAttributeValue(StoreReqAppItemVO.CFIRSTBID2)
            .toString());
      }
    }

    if (spKs.size() == 0 && fpKs.size() == 0) {
      return;
    }

    SqlBuilder conditinon = new SqlBuilder();
    IDExQueryBuilder builder = new IDExQueryBuilder("tmp_422x_4410_");
    conditinon.append(" and (");
    if (spKs.size() > 0) {
      conditinon.append(builder.buildSQL(StoreReqAppItemVO.CSOURCEBID2,
          spKs.toArray(new String[spKs.size()])));
    }
    if (fpKs.size() > 0) {
      if (spKs.size() > 0) {
        conditinon.append(" or ");
      }
      conditinon.append(builder.buildSQL(StoreReqAppItemVO.CFIRSTBID2,
          fpKs.toArray(new String[fpKs.size()])));
    }
    conditinon.append(" )");

    Set<String> keys = new HashSet<String>();

    VOQuery<StoreReqAppItemVO> query =
        new VOQuery<StoreReqAppItemVO>(StoreReqAppItemVO.class);
    StoreReqAppItemVO[] itemVOs = query.query(conditinon.toString(), null);

    for (StoreReqAppItemVO item : itemVOs) {
      keys.add(item.getPk_storereq_b());
    }

    if (keys.size() == 0) {
      return;
    }
    // 查询更新前的物资需求申请数据
    StoreReqAppViewVO[] views =
        new ViewQuery<StoreReqAppViewVO>(StoreReqAppViewVO.class).query(keys
            .toArray(new String[keys.size()]));

    if (ArrayUtils.isEmpty(views)) {
      return;
    }

    AroundProcesser<StoreReqAppViewVO> processer =
        new AroundProcesser<StoreReqAppViewVO>(null);
    this.addClearRule(processer, vos);
    processer.before(views);
    String[] wbNames =
        new String[] {
          StoreReqAppItemVO.CSOURCEID2, StoreReqAppItemVO.CSOURCEBID2,
          StoreReqAppItemVO.CSOURCETYPECODE2, StoreReqAppItemVO.VSOURCECODE2,
          StoreReqAppItemVO.VSOURCEROWNO2, StoreReqAppItemVO.VSOURCETRANTYPE2,
          StoreReqAppItemVO.CFIRSTID2, StoreReqAppItemVO.CFIRSTBID2,
          StoreReqAppItemVO.CFIRSTTYPECODE2, StoreReqAppItemVO.VFIRSTCODE2,
          StoreReqAppItemVO.VFIRSTROWNO2, StoreReqAppItemVO.VFIRSTTRANTYPE2,
          StoreReqAppItemVO.BENDGATHER, StoreReqAppItemVO.CGATHERID,
          StoreReqAppItemVO.CGATHERPSNID, StoreReqAppItemVO.TGATHERTIME,
          StoreReqAppItemVO.NACCCUSTORNUM

        };
    ViewUpdate<StoreReqAppViewVO> bo = new ViewUpdate<StoreReqAppViewVO>();
    views = bo.update(views, StoreReqAppItemVO.class, wbNames);
    processer.after(views);
  }

  /**
   * 删除、修改下游单据数量回写上游物资需求申请单累计转请购数量、转净需求数量
   * 
   * @param returnNums Map<String, UFDouble> 下游单据行pk，主数量变化差 （变化后-变化前）
   * @param billtype 下游单据类型
   */
  public void backWriteFor4410(Map<String, UFDouble> returnNums, String billtype) {

    if (returnNums.size() == 0) {
      return;
    }
    String[] downbillpks =
        returnNums.keySet().toArray(new String[returnNums.keySet().size()]);

    Map<String, UFDateTime> upbillpks = new HashMap<String, UFDateTime>();

    SqlBuilder condition = this.getQuerySqlcondition(billtype, downbillpks);

    // 查询上游所有单据
    VOQuery<StoreReqAppItemVO> query =
        new VOQuery<StoreReqAppItemVO>(StoreReqAppItemVO.class);
    StoreReqAppItemVO[] itemVOs = query.query(condition.toString(), null);

    if (ArrayUtils.isEmpty(itemVOs)) {// 无库存计划上游
      return;
    }
    // 重算接口参数1：下游行Pk，上游para[]
    Map<String, List<CalBalanceNumFor442XParam>> procParaMap =
        this.getReCalacateData(billtype, upbillpks, itemVOs);

    Map<String, UFDouble> backMap = null;

    try {
      ICalBalanceNumFor442X service =
          NCLocator.getInstance().lookup(ICalBalanceNumFor442X.class);

      backMap = service.calBalanceNumFor442X(procParaMap, returnNums);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }

    if (upbillpks.size() == 0 || backMap == null) {
      return;
    }

    WriteBack422XForInv9ParamVO[] vos =
        this.getBackRewriteParam(billtype, upbillpks, backMap);

    StoreReqAppViewVO[] views =
        new ViewQuery<StoreReqAppViewVO>(StoreReqAppViewVO.class)
            .query(upbillpks.keySet().toArray(
                new String[upbillpks.keySet().size()]));

    if (ArrayUtils.isEmpty(views) || ArrayUtils.isEmpty(vos)) {
      return;
    }
    Map<String, StoreReqAppViewVO> viewMap = CirVOUtil.createKeyVOMap(views);
    // 累加值
    for (WriteBack422XForInv9ParamVO para : vos) {
      StoreReqAppViewVO view = viewMap.get(para.getPk_storereq_b());

      UFDouble oid_naccumbuyreqnum =
          view.getNaccumbuyreqnum() == null ? UFDouble.ZERO_DBL : view
              .getNaccumbuyreqnum();
      UFDouble oid_naccumminusnum =
          (UFDouble) view.getAttributeValue(StoreReqAppItemVO.NACCUMMINUSNUM);
      // 修改后的累计请购数量 = 原累计请购数量 + （原汇总平衡转请购数量-新汇总平衡转请购数量）
      UFDouble new_naccumbuyreqnum =
          oid_naccumbuyreqnum.sub(
              oid_naccumminusnum == null ? UFDouble.ZERO_DBL
                  : oid_naccumminusnum).add(
              para.getNaccumminusnum() == null ? UFDouble.ZERO_DBL : para
                  .getNaccumminusnum());
      para.setNaccpraynum(new_naccumbuyreqnum);

      para.setNaccumminusnum(para.getNaccumminusnum());

    }

    AroundProcesser<StoreReqAppViewVO> processer =
        new AroundProcesser<StoreReqAppViewVO>(null);
    this.addRule(processer, vos);
    processer.before(views);
    String[] wbNames =
        new String[] {
          StoreReqAppItemVO.NNETNUM, StoreReqAppItemVO.NACCUMBUYREQNUM,
          StoreReqAppItemVO.NACCUMMINUSNUM
        };
    ViewUpdate<StoreReqAppViewVO> bo = new ViewUpdate<StoreReqAppViewVO>();
    views = bo.update(views, StoreReqAppItemVO.class, wbNames);
    processer.after(views);
  }

  /**
   * 库存平衡后回写物资需求申请单实现
   * 
   * @param WriteBack422XVO[]
   * @throws BusinessException
   */
  public void backWriteFor4410(WriteBack422XForInv9ParamVO[] vos) {
    Set<String> keys = new HashSet<String>();

    for (WriteBack422XForInv9ParamVO para : vos) {
      keys.add(para.getPk_storereq_b());
    }

    // 查询更新前的物资需求申请数据
    StoreReqAppViewVO[] views =
        new ViewQuery<StoreReqAppViewVO>(StoreReqAppViewVO.class).query(keys
            .toArray(new String[keys.size()]));

    Map<String, StoreReqAppViewVO> viewMap = CirVOUtil.createKeyVOMap(views);

    // 累加值
    for (WriteBack422XForInv9ParamVO para : vos) {
      StoreReqAppViewVO view = viewMap.get(para.getPk_storereq_b());

      para.setNaccumminusnum(para.getNaccpraynum());
      para.setNaccpraynum(MathTool.add(para.getNaccpraynum(),
          view.getNaccumbuyreqnum()));
      UFDouble nnetnum =
          (UFDouble) view.getAttributeValue(StoreReqAppItemVO.NNETNUM);
      para.setNnetnum(MathTool.add(para.getNnetnum(), nnetnum));

    }

    if (ArrayUtils.isEmpty(views)) {
      return;
    }

    AroundProcesser<StoreReqAppViewVO> processer =
        new AroundProcesser<StoreReqAppViewVO>(null);
    this.addRule(processer, vos);
    processer.before(views);
    String[] wbNames =
        new String[] {
          StoreReqAppItemVO.CSOURCEID2, StoreReqAppItemVO.CSOURCEBID2,
          StoreReqAppItemVO.CSOURCETYPECODE2, StoreReqAppItemVO.VSOURCECODE2,
          StoreReqAppItemVO.VSOURCEROWNO2, StoreReqAppItemVO.VSOURCETRANTYPE2,
          StoreReqAppItemVO.CFIRSTID2, StoreReqAppItemVO.CFIRSTBID2,
          StoreReqAppItemVO.CFIRSTTYPECODE2, StoreReqAppItemVO.VFIRSTCODE2,
          StoreReqAppItemVO.VFIRSTROWNO2, StoreReqAppItemVO.VFIRSTTRANTYPE2,
          StoreReqAppItemVO.BENDGATHER, StoreReqAppItemVO.CGATHERID,
          StoreReqAppItemVO.CGATHERPSNID, StoreReqAppItemVO.TGATHERTIME,
          StoreReqAppItemVO.NNETNUM, StoreReqAppItemVO.NACCCUSTORNUM,
          StoreReqAppItemVO.NACCUMBUYREQNUM, StoreReqAppItemVO.NACCUMMINUSNUM
        };
    ViewUpdate<StoreReqAppViewVO> bo = new ViewUpdate<StoreReqAppViewVO>();
    views = bo.update(views, StoreReqAppItemVO.class, wbNames);
    processer.after(views);
  }

  private void addClearRule(AroundProcesser<StoreReqAppViewVO> processer,
      WriteBack422XForInv9ParamVO[] vos) {

    // 汇总平衡回写参数处理
    processer.addBeforeRule(new ProcessReWriteClearRule(vos));
  }

  private void addRule(AroundProcesser<StoreReqAppViewVO> processer,
      WriteBack422XForInv9ParamVO[] vos) {

    // 汇总平衡回写参数处理
    processer.addBeforeRule(new ProcessReWriteParamRule(vos));
  }

  /**
   * 组织回写参数
   * 
   * @param billtype 下游单据类型
   * @param backMap 回写变化量 map<上游行pk，变化量>
   * @return 回写参数
   */
  private WriteBack422XForInv9ParamVO[] getBackRewriteParam(String billtype,
      Map<String, UFDateTime> bts, Map<String, UFDouble> backMap) {
    List<WriteBack422XForInv9ParamVO> paraList =
        new ArrayList<WriteBack422XForInv9ParamVO>();

    if (backMap.size() > 0) {
      for (String strpk : backMap.keySet()) {
        WriteBack422XForInv9ParamVO para = new WriteBack422XForInv9ParamVO();
        para.setPk_storereq_b(strpk);
        para.setStrBTS(bts.get(strpk).toString().trim());
        if (billtype.equals(POBillType.PrayBill.getCode())) {
          para.setNaccumminusnum(backMap.get(strpk));
          // para.setNaccpraynum(backMap.get(strpk));
        }
        else if (billtype.equals(POBillType.MRBill.getCode())) {
          para.setNnetnum(backMap.get(strpk));
        }
        paraList.add(para);
      }
    }

    return paraList.toArray(new WriteBack422XForInv9ParamVO[paraList.size()]);
  }

  private SqlBuilder getQuerySqlcondition(String billtype, String[] downbillpks) {
    SqlBuilder condition = new SqlBuilder();
    IDExQueryBuilder builder = new IDExQueryBuilder("temp_422x_4410_");

    if (billtype == POBillType.PrayBill.getCode()) {
      condition.append(" and ");
      condition.append(StoreReqAppItemVO.CSOURCETYPECODE2,
          POBillType.PrayBill.getCode());
      condition.append(" and ");
      condition.append(builder.buildSQL(" csourcebid2 ", downbillpks));
    }
    else if (billtype == POBillType.MRBill.getCode()) {
      condition.append(" and ");
      condition.append(StoreReqAppItemVO.CSOURCETYPECODE2,
          POBillType.MRBill.getCode());
      condition.append(" and ");
      condition.append(builder.buildSQL(" csourcebid2 ", downbillpks));
    }
    else if (billtype == TOBillType.TransOrder.getCode()) {
      condition.append(" and ");
      condition.append(StoreReqAppItemVO.CFIRSTTYPECODE2,
          TOBillType.TransOrder.getCode());
      condition.append(" and ");
      condition.append(builder.buildSQL(" cfirstbid2 ", downbillpks));
    }
    return condition;
  }

  /**
   * 组织上游数量重算所需数据
   * 
   * @param billtype 下游单据类型
   * @param upbillpks 上游被回写pk[]
   * @param itemVOs 上游被回写VOs
   * @return 重算参数组
   */
  private Map<String, List<CalBalanceNumFor442XParam>> getReCalacateData(
      String billtype, Map<String, UFDateTime> upbillpks,
      StoreReqAppItemVO[] itemVOs) {
    Map<String, List<CalBalanceNumFor442XParam>> procParaMap =
        new HashMap<String, List<CalBalanceNumFor442XParam>>();

    for (StoreReqAppItemVO item : itemVOs) {
      upbillpks.put(item.getPk_storereq_b(), item.getTs());

      if (!procParaMap.containsKey(item.getCsourcebid2())) {
        procParaMap.put(item.getCsourcebid2(),
            new ArrayList<CalBalanceNumFor442XParam>());

      }

      CalBalanceNumFor442XParam param = new CalBalanceNumFor442XParam();
      param.setCsrcbid(item.getPk_storereq_b());
      if (billtype.equals(POBillType.PrayBill.getCode())) {
        // param.setNcalnum(item.getNaccumbuyreqnum());
        param.setNcalnum(item.getNaccumminusnum());
      }
      else if (billtype.equals(POBillType.MRBill.getCode())) {
        param.setNcalnum(item.getNnetnum());
      }
      param.setNnum(item.getNnum());
      param.setNoutnum(item.getNaccuoutnum());
      procParaMap.get(item.getCsourcebid2()).add(param);
    }
    return procParaMap;
  }

}
