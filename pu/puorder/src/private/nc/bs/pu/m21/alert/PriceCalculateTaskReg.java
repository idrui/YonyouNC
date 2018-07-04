package nc.bs.pu.m21.alert;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PaConstant;
import nc.itf.uap.pa.IPreAlertConfigQueryService;
import nc.itf.uap.pa.IPreAlertConfigService;
import nc.vo.pu.pub.enumeration.PuStrEnum;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.pa.AlertregistryVO;
import nc.vo.pub.pa.AlertsendconfigVO;
import nc.vo.pub.pa.AlerttypeVO;
import nc.vo.pub.pa.TimingSettingVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.util.TimeUtils;
import nc.vo.uap.scheduler.TimeConst;

public class PriceCalculateTaskReg {

  private String pk_group;

  public PriceCalculateTaskReg(String pk_group) {
    this.pk_group = pk_group;
  }

  /**
   * ע����Ч��������Ԥ��
   * 
   * @return
   */
  public String regDailyAlert() {
    // PreAlertConfigServiceImpl.insertAlertRegistries�������ж��ظ��Ĵ��룬����getPk_alerttype��getGroupId��getAlertname�����԰�
    // alertnameͳһ�˾Ϳ��Ա�֤һ��������ֻ��һ����������Ч�۸�����Ԥ��
    String alertname = "po_price_daily";
    String filename =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("prealerttype",
            "alert4004-024");/* @res "�ɹ�ѯ��-������Ч��������" */

    AlertregistryVO alertRegVO =
        this.createAlertregistryVO(alertname, filename,
            PuStrEnum.poprc_daily_altexectm.code(),
            Integer.valueOf(TimeConst.DAY),
            PuStrEnum.poprc_daily_alttypepk.code(), null);

    try {
      return NCLocator.getInstance().lookup(IPreAlertConfigService.class)
          .insertAlertRegistries(new AlertregistryVO[] {
            alertRegVO
          })[0];
    }
    catch (BusinessException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * ע��ѯ�ۼ۸���ȡԤ��
   * 
   * @return
   */
  public String regMonthlyAlert() {
    // PreAlertConfigServiceImpl.insertAlertRegistries�������ж��ظ��Ĵ��룬����getPk_alerttype��getGroupId��getAlertname�����԰�
    // alertnameͳһ�˾Ϳ��Ա�֤һ��������ֻ��һ�������ļ۸���ȡԤ��
    String alertname = "po_price_monthly";
    String filename =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("prealerttype",
            "alert4004-023");/* @res "�ɹ�ѯ��-�����۸���ȡ" */
    AlertregistryVO alertRegVO =
        this.createAlertregistryVO(alertname, filename,
            PuStrEnum.poprc_monthly_altexectm.code(),
            Integer.valueOf(TimeConst.MONTH),
            PuStrEnum.poprc_monthly_alttypepk.code(),
            PuStrEnum.poprc_monthly_execday.code());

    try {
      return NCLocator.getInstance().lookup(IPreAlertConfigService.class)
          .insertAlertRegistries(new AlertregistryVO[] {
            alertRegVO
          })[0];
    }
    catch (BusinessException e) {
      throw new RuntimeException(e);
    }
  }

  private AlertregistryVO createAlertregistryVO(String alertname,
      String filename, String excuteTime, Integer frequencytype,
      String alertType, String execDay) {
    AlertregistryVO alertRegVO = new AlertregistryVO();
    alertRegVO.setTimingSettingVO(this.createTimingSettingVO(excuteTime,
        frequencytype, execDay));
    alertRegVO.setAlertname(alertname);
    alertRegVO.setFilename(filename);
    alertRegVO.setEnabled(UFBoolean.TRUE);
    alertRegVO.setIstiming(UFBoolean.TRUE);
    // alertRegVO.setPk_org(null);
    alertRegVO.setGroupId(this.pk_group);
    alertRegVO.setRegistrytype(Integer.valueOf(PaConstant.TASKTYPE_PA));
    alertRegVO.setCreator(AppContext.getInstance().getPkUser());
    AlertsendconfigVO sendvo = new AlertsendconfigVO();
    sendvo.setSendmethod(Integer.valueOf(4));
    alertRegVO.setSendConfigVo(new AlertsendconfigVO[] {
      sendvo
    });

    try {
      AlerttypeVO alertTypeVo =
          NCLocator.getInstance().lookup(IPreAlertConfigQueryService.class)
              .queryAlertyTypeByPK(alertType);
      alertRegVO.setPk_alerttype(alertType);
      alertRegVO.setAlertTypeVo(alertTypeVo);
    }
    catch (BusinessException e) {
      throw new RuntimeException(e);
    }
    return alertRegVO;
  }

  private TimingSettingVO createTimingSettingVO(String executetime,
      Integer frequencytype, String execDay) {
    TimingSettingVO timeSettingVO = new TimingSettingVO();
    timeSettingVO.setIsruncycledaily(UFBoolean.TRUE);
    timeSettingVO.setExecuteday(execDay);
    timeSettingVO.setExecutetime(executetime);
    timeSettingVO.setEffibegindate(TimeUtils.getsrvBaseDate().asBegin()
        .toString());
    timeSettingVO.setEffibegintime(PuStrEnum.poprc_altt_effibegintime.code());
    // timeSettingVO.setEffienddate(null);
    // timeSettingVO.setEffiendtime(null);
    // û������
    // timeSettingVO.setFrequencytype(Integer.valueOf(TimeConst.DAY));
    timeSettingVO.setFrequencytype(frequencytype);
    timeSettingVO.setIntervaltime(Integer.valueOf(1));

    return timeSettingVO;
  }
}
