package nc.test.pubitf.pu.m20.mm.a2;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.framework.test.AbstractTestCase;
import nc.pubitf.pu.m20.mm.a2.IPushSave20ForA2;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.enumeration.EnumPraySource;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

public class PushSave20ForA2TestCase extends AbstractTestCase {

  public void test() throws BusinessException {
    IPushSave20ForA2 pushSave20ForA2 =
        NCLocator.getInstance().lookup(IPushSave20ForA2.class);

    // 请购单表头VO。
    PraybillHeaderVO headerVO = new PraybillHeaderVO();
    headerVO.setDbilldate(new UFDate());
    headerVO.setPk_org("000133100000000069DJ");
    headerVO.setPk_org_v("000133100000000069DJ");
    headerVO.setPk_group("00013310000000002CVS");
    headerVO.setFpraysource(EnumPraySource.ICPO.toInteger());
    headerVO.setCtrantypeid("00013310000000003KRO");
    headerVO.setVtrantypecode("20-01");

    // 第一条请购单表体VO
    PraybillItemVO itemVO1 = new PraybillItemVO();
    itemVO1.setPk_material("10043310000000000TTD");
    itemVO1.setPk_srcmaterial("10043310000000000TTD");
    itemVO1.setCastunitid("0001Z0100000000000XP");
    itemVO1.setCunitid("0001Z0100000000000XP");
    itemVO1.setPk_group("00013310000000002CVS");
    itemVO1.setNastnum(new UFDouble(12));
    itemVO1.setNnum(new UFDouble(23));
    itemVO1.setPk_org("000133100000000069DJ");
    itemVO1.setPk_org_v("000133100000000069DJ");

    // 请购单，单据VO
    PraybillVO praybillVO = new PraybillVO();
    praybillVO.setHVO(headerVO);
    praybillVO.setBVO(new PraybillItemVO[] {
      itemVO1
    });

    // 单据VO数组，输入参数完成。
    PraybillVO[] praybillVOs = new PraybillVO[] {
      praybillVO
    };

    // 推式保存
    pushSave20ForA2.pushSaveBills(praybillVOs);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    // 为登录环境设置集团信息。
    InvocationInfoProxy.getInstance().setGroupId("00013310000000002CVS");
  }
}
