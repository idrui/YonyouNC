package nc.vo.pu.pub.rule;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pu.pub.constant.PUQueryConst;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pubapp.AppContext;
import nc.vo.util.remotecallcombination.RemoteCallCombinatorEx;
import nc.vo.util.remotecallcombination.Token;

import org.apache.commons.lang.StringUtils;

/**
 * 查询业务流程的规则，支持远程调用
 * 
 * @since 6.0
 * @version 2011-8-2 下午02:45:27
 * @author zhaoyha
 */
public class BusitypeFillRule implements IPURemoteCallCombinator {

  private IKeyValue billhelper;

  private String billtype;

  private Token token;

  /**
   * @param billhelper
   * @param billtype
   */
  public BusitypeFillRule(IKeyValue billhelper, String billtype) {
    super();
    this.billhelper = billhelper;
    this.billtype = billtype;
  }

  @Override
  public void prepare() {
    RemoteCallCombinatorEx rcc = RemoteCallCombinatorEx.getInstance();
    if (this.token != null) {
      rcc.update(this.token);
    }
    String[] para = this.getQueryPara();
    this.token =
        PfServiceScmUtil.register_getBusitype(this.billtype, para[0], para[1],
            para[2]);
  }

  @Override
  public void process() {
    String[] para = this.getQueryPara();
    String busitype =
        PfServiceScmUtil.getBusitype(this.billtype, para[0], para[1], para[2],
            this.token);
    this.billhelper.setHeadValue(PUQueryConst.PK_BUSITYPE, busitype);
  }

  private String[] getQueryPara() {
    String transtype =
        (String) this.billhelper.getHeadValue(PUQueryConst.VTRANTYPECODE);
    String pk_org = (String) this.billhelper.getHeadValue(PUQueryConst.PK_ORG);
    String userid =
        (String) this.billhelper.getHeadValue(PUQueryConst.BILLMAKER);
    userid =
        StringUtils.isNotBlank(userid) ? userid : AppContext.getInstance()
            .getPkUser();
    return new String[] {
      transtype, pk_org, userid
    };
  }

}
