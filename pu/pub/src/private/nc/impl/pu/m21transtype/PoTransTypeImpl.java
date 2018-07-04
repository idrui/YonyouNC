package nc.impl.pu.m21transtype;

import java.util.ArrayList;
import java.util.HashSet;

import nc.bs.bd.cache.CacheProxy;
import nc.bs.pub.pf.ITranstypeBiz;
import nc.impl.pu.m21transtype.rule.CanDelChkRule;
import nc.impl.pu.m21transtype.rule.CheckTranTypeReference;
import nc.impl.pu.m21transtype.rule.FillGroupRule;
import nc.impl.pu.m21transtype.rule.NotNullChkRule;
import nc.impl.pu.m21transtype.rule.PoTransTypeFillDataRule;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.sm.funcreg.FuncRegisterVO;

/**
 * 采购订单交易类型扩展属性业务接口实现类
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>维护采购订单交易类型扩展属性
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-9 上午10:54:01
 */
public class PoTransTypeImpl implements ITranstypeBiz {

  /**
   * PoTransTypeImpl 的构造子
   */
  public PoTransTypeImpl() {
    super();
  }

  @Override
  public void deleteTransType(Object userObj) throws BusinessException {

    try {
      PoTransTypeVO[] orgVo = new PoTransTypeVO[] {
        (PoTransTypeVO) userObj
      };
      CompareAroundProcesser<PoTransTypeVO> processer =
          new CompareAroundProcesser<PoTransTypeVO>(null);
      this.addBeforeRuleForDel(processer);
      // 检查交易类型是否被使用，只有修改时才检查一下，删除时平台已经检查好
      BatchOperateVO batchVo = new BatchOperateVO();
      batchVo.setDelObjs(orgVo);
      processer.before(orgVo, null);
      this.maintain(batchVo);
      processer.after(null, orgVo);
      // 删除后通知前台缓存
      HashSet<String> pkSet =
          CirVOUtil.getDistinctFieldSet(orgVo, PoTransTypeVO.PK_POTRANTYPE);
      CacheProxy.fireDataDeletedBatch(PUEntity.M21_TRANTYPE_TABLE,
          pkSet.toArray(new String[pkSet.size()]));
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void execOnDelPublish(BilltypeVO transTypeVO,
      ArrayList<FuncRegisterVO> funcVOs) throws BusinessException {
    // 方法暂未使用
  }

  @Override
  public void execOnPublish(String nodecode, String newNodecode,
      boolean isExecFunc) throws BusinessException {
    // 方法暂未使用
  }

  @Override
  public void saveTransType(Object userObj) throws BusinessException {

    try {
      CompareAroundProcesser<PoTransTypeVO> processer =
          new CompareAroundProcesser<PoTransTypeVO>(null);

      // 添加规则
      this.addBeforeRule(processer);

      PoTransTypeVO[] orgVo = new PoTransTypeVO[] {
        (PoTransTypeVO) userObj
      };
      processer.before(orgVo, null);

      BatchOperateVO batchVo = new BatchOperateVO();
      batchVo.setAddObjs(orgVo);
      this.maintain(batchVo);
      // 新增后通知前台缓存
      CacheProxy.fireDataInserted(PUEntity.M21_TRANTYPE_TABLE);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

  @Override
  public void updateTransType(Object userObj) throws BusinessException {

    try {
      CompareAroundProcesser<PoTransTypeVO> processer =
          new CompareAroundProcesser<PoTransTypeVO>(null);

      // 添加规则
      this.addBeforeRuleForUpdate(processer);

      PoTransTypeVO[] vos = new PoTransTypeVO[] {
        (PoTransTypeVO) userObj
      };

      PoTransTypeVO[] oldVos =
          new VOQuery<PoTransTypeVO>(PoTransTypeVO.class).query(new String[] {
            vos[0].getPk_potrantype()
          });

      processer.before(vos, oldVos);

      BatchOperateVO batchVo = new BatchOperateVO();
      batchVo.setUpdObjs(vos);
      this.maintain(batchVo);
      // 更新后通知前台缓存
      CacheProxy.fireDataUpdated(PUEntity.M21_TRANTYPE_TABLE);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  private void addBeforeRule(CompareAroundProcesser<PoTransTypeVO> processer) {
    // 给集团赋值规则
    processer.addBeforeRule(new FillGroupRule());
    // 非空校验
    processer.addBeforeFinalRule(new NotNullChkRule());
    // 填补订单在途状态的前置状态
    processer.addBeforeRule(new PoTransTypeFillDataRule());
  }

  private void addBeforeRuleForDel(
      CompareAroundProcesser<PoTransTypeVO> processer) {
    // 这个规则只负责检查，系统预置的一些交易类型（21-coop）不允许删除
    processer.addBeforeFinalRule(new CanDelChkRule());
  }

  private void addBeforeRuleForUpdate(
      CompareAroundProcesser<PoTransTypeVO> processer) {
    this.addBeforeRule(processer);
    // 检查交易类型是否被使用，只有修改时才检查一下，删除时平台已经检查好
    processer.addBeforeFinalRule(new CheckTranTypeReference());
  }

  private void maintain(BatchOperateVO batchVo) throws BusinessException {
    try {
      new BatchSaveAction<ISuperVO>().batchSave(batchVo);
    }
    catch (Exception e) {
      // 日志异常
      Log.info(e);
      // 按规范包装异常
      ExceptionUtils.marsh(e);
    }
  }

}
