/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-5-27 ����04:28:17
 */
package nc.impl.pu.m25trantype;

import java.util.ArrayList;

import nc.bs.pub.pf.ITranstypeBiz;
import nc.impl.pu.m25trantype.rule.CheckTranTypeRefForInv;
import nc.impl.pu.m25trantype.rule.FillGroupRule;
import nc.impl.pu.m25trantype.rule.NotNullChkRule;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pub.smart.BatchSaveAction;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m25trantype.entity.InvcTranTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;
import nc.vo.sm.funcreg.FuncRegisterVO;

/**
 * �ɹ���Ʊ����������չ����ҵ��ӿ�ʵ����
 * 
 * @version v60
 * @since v60
 * @author zhaoyha
 * @time 2009-5-27 ����04:28:17
 */
public class InvcTranTypeImpl implements ITranstypeBiz {

  /**
   * ʵ�ֽӿڷ���
   * 
   * @see nc.bs.pub.pf.ITranstypeBiz#deleteTransType(java.lang.Object)
   */
  @Override
  public void deleteTransType(Object userObj) throws BusinessException {

    try {
      AroundProcesser<InvcTranTypeVO> processer =
          new AroundProcesser<InvcTranTypeVO>(null);

      InvcTranTypeVO[] orgVo = new InvcTranTypeVO[] {
        (InvcTranTypeVO) userObj
      };

      processer.before(orgVo);

      BatchOperateVO batchVo = new BatchOperateVO();
      batchVo.setDelObjs(orgVo);
      this.maintain(batchVo);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * ʵ�ֽӿڷ���
   * 
   * @see nc.bs.pub.pf.ITranstypeBiz#execOnDelPublish(nc.vo.pub.billtype.BilltypeVO,
   *      java.util.ArrayList)
   */
  @Override
  public void execOnDelPublish(BilltypeVO transTypeVO,
      ArrayList<FuncRegisterVO> funcVOs) throws BusinessException {
    //

  }

  /**
   * ʵ�ֽӿڷ���
   * 
   * @see nc.bs.pub.pf.ITranstypeBiz#execOnPublish(java.lang.String,
   *      java.lang.String, boolean)
   */
  @Override
  public void execOnPublish(String nodecode, String newNodecode,
      boolean isExecFunc) throws BusinessException {
    //

  }

  /**
   * ʵ�ֽӿڷ���
   * 
   * @see nc.bs.pub.pf.ITranstypeBiz#saveTransType(java.lang.Object)
   */
  @Override
  public void saveTransType(Object userObj) throws BusinessException {

    try {
      CompareAroundProcesser<InvcTranTypeVO> processer =
          new CompareAroundProcesser<InvcTranTypeVO>(null);

      // ��ӹ���
      this.addBeforeRule(processer);

      InvcTranTypeVO[] vos = new InvcTranTypeVO[] {
        (InvcTranTypeVO) userObj
      };

      processer.before(vos, null);

      BatchOperateVO batchVo = new BatchOperateVO();
      batchVo.setAddObjs(vos);
      this.maintain(batchVo);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  /**
   * ʵ�ֽӿڷ���
   * 
   * @see nc.bs.pub.pf.ITranstypeBiz#updateTransType(java.lang.Object)
   */
  @Override
  public void updateTransType(Object userObj) throws BusinessException {
    try {
      CompareAroundProcesser<InvcTranTypeVO> processer =
          new CompareAroundProcesser<InvcTranTypeVO>(null);

      // ��ӹ���
      this.addUpdateBeforeRule(processer);

      InvcTranTypeVO[] vos = new InvcTranTypeVO[] {
        (InvcTranTypeVO) userObj
      };

      InvcTranTypeVO[] oldVos =
          new VOQuery<InvcTranTypeVO>(InvcTranTypeVO.class).query(new String[] {
            vos[0].getPk_invctrantype()
          });

      processer.before(vos, oldVos);

      BatchOperateVO batchVo = new BatchOperateVO();
      batchVo.setUpdObjs(vos);
      this.maintain(batchVo);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  private void addBeforeRule(CompareAroundProcesser<InvcTranTypeVO> processer) {
    // �����Ÿ�ֵ����
    processer.addBeforeRule(new FillGroupRule());
    // �ǿ�У��
    processer.addBeforeFinalRule(new NotNullChkRule());
  }

  private void addUpdateBeforeRule(
      CompareAroundProcesser<InvcTranTypeVO> processer) {
    this.addBeforeRule(processer);
    // �����Ƿ�����
    processer.addBeforeRule(new CheckTranTypeRefForInv());
  }

  private void maintain(BatchOperateVO batchVo) throws BusinessException {
    try {
      new BatchSaveAction<ISuperVO>().batchSave(batchVo);
    }
    catch (Exception e) {
      // ��־�쳣
      Log.info(e);
      // ���淶��װ�쳣
      ExceptionUtils.marsh(e);
    }
  }
}
