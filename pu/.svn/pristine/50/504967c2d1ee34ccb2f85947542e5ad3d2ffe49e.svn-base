package nc.vo.pu.m422x.exception;

import nc.vo.pu.exception.AskYesNoException;
import nc.vo.scmpub.res.BusinessCheck;

/**
 * 维修计划整单关闭/行关闭时，需要关闭或删除下游的物资需求申请单/物资需求申请单行。
 * 如果存在自由态或审批不通过的物资需求申请单，给用户提示”关闭维修计划将会同时删除其生成的物资需求申请单[XXXxxxxxx],[XXXxxxxx]的申请行
 * ，是否继续？“提示框，
 * 由于资产调用的采购的接口，我们这边无法弹出提示框，需要资产来做，所以返回IResumeException类型的异常，资产那边捕获之后处理弹出框。
 * 
 * @since 6.5
 * @version 2014-2-17 下午03:49:17
 * @author fanly3
 */
public class StoreqDeleteException extends AskYesNoException {
  private static final long serialVersionUID = -9109296309594366444L;

  public StoreqDeleteException(String msg) {
    super(msg);
  }

  @Override
  public String getBusiExceptionType() {
    return BusinessCheck.StoreqDeleteCheck.getCheckCode();
  }

}
