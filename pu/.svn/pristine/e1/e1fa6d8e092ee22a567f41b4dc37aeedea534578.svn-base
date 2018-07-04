package nc.vo.pu.pfxx.plugins;

import java.util.HashSet;
import java.util.Set;

import nc.bs.pfxx.ISwapContext;
import nc.bs.pfxx.plugin.AbstractPfxxPlugin;
import nc.vo.pfxx.auxiliary.AggxsysregisterVO;
import nc.vo.pfxx.util.PfxxPluginUtils;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * 采购外部导入插件抽象处理类
 * 
 * @since 6.0
 * @version 2011-4-26 上午09:35:59
 * @author 田锋涛
 */
public abstract class AbstractPuPfxxPlugin extends AbstractPfxxPlugin {

  /** 单据状态 */
  public static final String FBILLSTATUS = "fbillstatus";

  /** ts */
  public static final String TS = "ts";

  private void checkPK(AggregatedValueObject resvo) throws BusinessException {
    String pkHid = resvo.getParentVO().getPrimaryKey();
    if (!StringUtils.isEmpty(pkHid)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0142", null, null));
    }
    CircularlyAccessibleValueObject[] pkBids = resvo.getChildrenVO();
    Set<String> set = new HashSet<String>();
    for (CircularlyAccessibleValueObject bvo : pkBids) {
      set.add(bvo.getPrimaryKey());
    }
    for (String pkBid : set) {
      if (!StringUtils.isEmpty(pkBid)) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes()
            .getStrByID("4004000_0", "04004000-0142", null, null));
      }
    }
  }

  /**
   * 检验vo是否可导入
   * 
   * @param vo
   */
  protected void checkCanInster(AggregatedValueObject vo) {
    if (!POEnumBillStatus.FREE.toInteger().equals(
        vo.getParentVO().getAttributeValue(this.getBillStatusKey()))) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0136")/*
                                                                   * @res
                                                                   * "非自由态的单据不允许导入！"
                                                                   */);
    }
  }

  /**
   * 检验vo是否可更新
   * 
   * @param vo
   */
  protected void checkCanUpdate(AggregatedValueObject vo) {
    if (!POEnumBillStatus.FREE.toInteger().equals(
        vo.getParentVO().getAttributeValue(this.getBillStatusKey()))) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0062")/*
                                                                   * @res
                                                                   * "非自由态的单据不允许更新！"
                                                                   */);
    }
  }

  /**
   * 根据聚合vo主键删除vo
   * 
   * @param pk
   */
  protected abstract void deleteVO(AggregatedValueObject vo);

  protected String getBillStatusKey() {
    return AbstractPuPfxxPlugin.FBILLSTATUS;
  }

  protected abstract String getChildrenPkFiled();

  protected abstract String getParentPkFiled();

  /**
   * 新增操作，各个单据自己处理
   * 
   * @param vo
   * @return
   */
  protected abstract AggregatedValueObject insert(AggregatedValueObject vo);

  /**
   * 将由XML转换过来的VO导入NC系统。业务插件实现此方法即可。<br>
   * 请注意，业务方法的校验一定要充分
   * 
   * @param vo
   *          转换后的vo数据，在NC系统中可能为ValueObject,SuperVO,AggregatedValueObject,
   *          IExAggVO等。
   * @param swapContext
   *          各种交换参数，组织，接受方，发送方，帐套等等
   * @param aggxsysvo
   *          辅助信息vo
   * @return
   * @throws BusinessException
   */
  @Override
  protected Object processBill(Object vo, ISwapContext swapContext,
      AggxsysregisterVO aggxsysvo) throws BusinessException {

    // 1.得到转换后的VO数据,取决于向导第一步注册的VO信息
    AggregatedValueObject resvo = (AggregatedValueObject) vo;

    // 2.查询此单据是否已经被导入过，有两个方法，具体使用哪一个请参考方法说明javadoc
    // 1) String vopk =
    // PfxxPluginUtils.queryBillPKBeforeSaveOrUpdate(swapContext.getBilltype(),swapContext.getDocID());
    // 2) String vopk =
    // PfxxPluginUtils.queryBillPKBeforeSaveOrUpdate(swapContext.getBilltype(),swapContext.getDocID(),swapContext.getOrgPk());
    String vopk =
        PfxxPluginUtils.queryBillPKBeforeSaveOrUpdate(
            swapContext.getBilltype(), swapContext.getDocID());

    // 3. 如果单据设置有辅助信息，aggxsysvo为用户配置的具体辅助信息

    // 4.如果此单据没有导入过，那么准备保存新单据，保存单据前请进行必要的数据检查，并给出明确的业务异常...
    AggregatedValueObject returnVO = null;
    if (StringUtils.isEmpty(vopk)) {
      this.checkPK(resvo);
      resvo.getParentVO().setStatus(VOStatus.NEW);
      returnVO = this.insert(resvo);
    }
    else {
      // 5.如果此单据已经导入过，请调用PfxxPluginUtils.checkBillCanBeUpdate(UfinterfaceVO
      // swapContext)检查单据是否允许更新
      // 如果不允许更新,此方法会抛出业务异常
      AggregatedValueObject queryVo = this.queryVOByPk(vopk);
      // 修改
      if (queryVo != null) {
        // 平台不校验，业务组自己校验
        this.checkCanUpdate(queryVo);
        resvo = this.processVOWhenUpdate(resvo, queryVo);
        returnVO = this.update(resvo, queryVo);
      }
      // 新增
      else {
        this.checkPK(resvo);
        resvo.getParentVO().setStatus(VOStatus.NEW);
        returnVO = this.insert(resvo);
      }
    }
    vopk = returnVO.getParentVO().getPrimaryKey();
    // 6.如果希望单据将来可以更新，请调用下列接口插入文档流水号与生成PK的对照关系
    // 第2步查询对照关系相对应，也有两个个方法，具体请看javadoc
    // 1)PfxxPluginUtils.addDocIDVsPKContrast(swapContext.getBilltype(),swapContext.getDocID(),pk);
    // 2)PfxxPluginUtils.addDocIDVsPKContrast(swapContext.getBilltype(),swapContext.getDocID(),swapContext.getOrgPk(),pk);
    PfxxPluginUtils.addDocIDVsPKContrast(swapContext.getBilltype(),
        swapContext.getDocID(), vopk);

    // 7.准备返回值,此函数的返回值，最终会以字符串的形式返回给外系统，
    // 对于普通单据可以返回NC系统生成的PK值，对于凭证可能返回凭证号，具体视单据而定
    // 对于查询插件要求返回org.w3c.dom.Node[]数组 或者org.w3c.dom.Node
    return vopk;

  }

  /**
   * 更新时的处理。因为更新时导入文件里不应该有表体主键，所以表体的操作是删除原来的，插入新增的。
   * 表头部分处理更新部分。
   * 
   * @param resvo
   * @param queryVo
   * @return
   */
  protected AggregatedValueObject processVOWhenUpdate(
      AggregatedValueObject resvo, AggregatedValueObject queryVo) {
    try {
      // 表头处理
      String parentPkValue = queryVo.getParentVO().getPrimaryKey();
      resvo.getParentVO().setPrimaryKey(parentPkValue);
      resvo.getParentVO().setAttributeValue(AbstractPuPfxxPlugin.TS,
          queryVo.getParentVO().getAttributeValue(AbstractPuPfxxPlugin.TS));
      // queryVo.setParentVO(resvo.getParentVO());
      resvo.getParentVO().setStatus(VOStatus.UPDATED);
      // 表体处理
      // 原来的vo-删除标志
      for (CircularlyAccessibleValueObject item : queryVo.getChildrenVO()) {
        item.setStatus(VOStatus.DELETED);
      }
      // 导入的vo-新增标志
      for (CircularlyAccessibleValueObject item : resvo.getChildrenVO()) {
        item.setStatus(VOStatus.NEW);
        // 表头主键
        item.setAttributeValue(this.getParentPkFiled(), parentPkValue);
      }
      resvo.setChildrenVO(ArrayUtil.combinArrays(queryVo.getChildrenVO(),
          resvo.getChildrenVO()));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return resvo;
  }

  protected abstract AggregatedValueObject queryVOByPk(String voPk);

  /**
   * 更新操作
   * 
   * @param vo
   * @return
   */
  protected abstract AggregatedValueObject update(
      AggregatedValueObject updateVO, AggregatedValueObject origVO);

}
