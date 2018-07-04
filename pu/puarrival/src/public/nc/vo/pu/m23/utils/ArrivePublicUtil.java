package nc.vo.pu.m23.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pu.pub.constant.PUParaValue;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pu.pub.writeback.IWriteBackPubPara;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������Ĺ���������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-2-26 ����09:52:04
 */
public class ArrivePublicUtil {

  /**
   * ���鱣�������ϵ���Ч���ں�ʧЧ����
   * 
   * @param vos
   * @author mengjian
   */
  public static void checkQuality(ArriveVO[] vos) {
    if (vos == null) {
      return;
    }
    for (ArriveVO vo : vos) {
      if (vo == null || vo.getBVO() == null) {
        continue;
      }
      String pk_org = vo.getHVO().getPk_org();
      Set<String> cmaterialVIDs = new HashSet<String>();
      for (ArriveItemVO body : vo.getBVO()) {
        if (body.getStatus() == VOStatus.DELETED) {
          continue;
        }
        cmaterialVIDs.add(body.getPk_material());
      }
      Map<String, MaterialStockVO> materialVOs =
          ArrivePublicUtil.fetchMaterialInfo(pk_org, cmaterialVIDs);
      if (materialVOs == null) {
        continue;
      }
      StringBuilder errStr = new StringBuilder();
      ArrivePublicUtil.checkQualityMngInv(vo, materialVOs, errStr);
      if (errStr.toString().length() > 0) {
        ExceptionUtils.wrappBusinessException(errStr.toString());
      }
    }

  }

  /**
   * �����������������������ľۺ�VOת��Ϊ��ͼVO,һ��һת��
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-7-12 ����02:30:40
   */
  public static ArriveViewVO[] convertAggToViewVO(ArriveVO[] vos) {
    List<ArriveViewVO> viewList = new ArrayList<ArriveViewVO>();
    for (ArriveVO vo : vos) {
      int size = vo.getBVO().length;
      for (int i = 0; i < size; i++) {
        ArriveViewVO view = new ArriveViewVO();
        view.setVO(vo.getParent());
        view.setVO(vo.getBVO()[i]);
        viewList.add(view);
      }
    }
    return viewList.toArray(new ArriveViewVO[0]);
  }

  /**
   * ��������������������������ͼVOת��Ϊ�ۺ�VO,һ��һת��
   * <p>
   * <b>����˵��</b>
   * 
   * @param views
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-7-12 ����02:19:00
   */
  public static ArriveVO[] convertViewToAggVO(ArriveViewVO[] views) {
    ArriveVO[] vos = new ArriveVO[views.length];
    for (int i = 0, len = views.length; i < len; i++) {
      vos[i] = views[i].changeToBill();
    }
    return vos;
  }

  public static ArriveVO[] getBatchCodeArriveVO(ArriveVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    List<ArriveVO> list = new ArrayList<ArriveVO>();
    for (ArriveVO vo : vos) {
      String pk_org = vo.getHVO().getPk_org();
      if (PUParaValue.po44.arrival.equals(PUSysParamUtil.getPO44(pk_org))) {
        list.add(vo);
      }
    }
    if (0 == list.size()) {
      return null;
    }
    return list.toArray(new ArriveVO[list.size()]);
  }

  /**
   * ����������������õ��������������еĲ�ͬ�ɹ�������ID
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemArray
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-3-29 ����02:05:06
   */
  public static String[] getOrderBidArray(ArriveItemVO[] itemArray) {
    List<String> orderBidList = new ArrayList<String>();
    for (int i = 0, len = itemArray.length; i < len; i++) {
      if (StringUtils.isEmpty(itemArray[i].getPk_order_b())) {
        continue;
      }
      if (orderBidList.contains(itemArray[i].getPk_order_b())) {
        continue;
      }
      orderBidList.add(itemArray[i].getPk_order_b());
    }
    return orderBidList.toArray(new String[orderBidList.size()]);
  }

  /**
   * ����������������û�д�����е���ID����
   * <p>
   * <b>����˵��</b>
   * 
   * @param paraArray
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-3-29 ����02:04:30
   */
  public static String[] getWriteParaBidArray(IWriteBackPubPara[] paraArray) {
    String[] bidArray = new String[paraArray.length];
    for (int i = 0, len = paraArray.length; i < len; i++) {
      bidArray[i] = paraArray[i].getBID();
    }
    return bidArray;
  }

  /**
   * ���������������жϵ�����VO�Ƿ���Դ��ί�ⶩ���ġ�����ǣ�����true������false
   * <p>
   * <b>����˵��</b>
   * 
   * @param aggVO
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-20 ����02:10:08
   */
  public static boolean isArriveFromSC(ArriveVO aggVO) {

    if (ArrayUtils.isEmpty(aggVO.getBVO())) {
      return false;
    }

    if (POBillType.Order.getCode().equals(
        aggVO.getBVO()[0].getCsourcetypecode())) {
      return false;
    }

    return true;
  }

  /**
   * �жϵ�����VO�Ƿ���Դ��ί�ⶩ����
   * 
   * @param aggVOs ����������
   * @return ������һ����Դ��ί�ⶩ�����򷵻�true������false
   */
  public static boolean isArriveFromSC(ArriveVO[] aggVOs) {
    if (ArrayUtils.isEmpty(aggVOs)) {
      return false;
    }
    for (ArriveVO vo : aggVOs) {
      if (ArrivePublicUtil.isArriveFromSC(vo)) {
        return true;
      }
    }
    return false;
  }

  /**
   * ��������������������ѯ�������������������Ӧ�Ŀ����֯ҳǩ�����ԣ����ݼ�������⡢�Ƿ���죩
   * <p>
   * <b>����˵��</b>
   * 
   * @param bitems
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-10-15 ����09:09:56
   */
  public static Map<String, MaterialStockVO> queryMaterialStockInfo(
      ArriveItemVO[] bitems) {
    Map<String, MaterialStockVO> m = new HashMap<String, MaterialStockVO>();
    // ���ݱ���Ŀ����֯���ڱ������ݽ��з���
    Map<String, ArrayList<ArriveItemVO>> org_bitems =
        CirVOUtil.getFieldVOList(bitems, ArriveItemVO.PK_ORG);
    // ���ݼ��������, �Ƿ����
    String[] fields = new String[] {
      MaterialStockVO.STOCKBYCHECK, MaterialStockVO.CHKFREEFLAG
    };
    for (Entry<String, ArrayList<ArriveItemVO>> e : org_bitems.entrySet()) {
      // ��ѯ���ϵĿ��ҳǩ��Ϣ
      String stock = e.getKey();
      ArriveItemVO[] items = e.getValue().toArray(new ArriveItemVO[0]);
      String k = ArriveItemVO.PK_MATERIAL;
      String[] mrlpks =
          CirVOUtil.getDistinctFieldSet(items, k).toArray(new String[0]);
      Map<String, MaterialStockVO> map = null;
      map = MaterialPubService.queryMaterialStockInfo(mrlpks, stock, fields);
      if (map == null || map.size() == 0) {
        continue;
      }
      // ���ݲ�ѯ�������Ͽ����Ϣ������ṹ��<�����������������ϵĿ��ҳǩ��Ϣ>
      for (ArriveItemVO item : items) {
        String bid = item.getPk_arriveorder_b();
        MaterialStockVO mrlVO = map.get(item.getPk_material());
        if (mrlVO == null) {
          String msg =
              nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                  "4004040_0", "04004040-0167")/*
                                                * @res
                                                * "��ѯ���ϵĿ��ҳǩ����ʱ���Ҳ�����Ӧ��������Ϣ��"
                                                */;
          ExceptionUtils.wrappBusinessException(msg);
        }
        m.put(bid, mrlVO);
      }
    }
    return m;
  }

  private static void checkQualityMngInv(ArriveVO vo,
      Map<String, MaterialStockVO> materialVOs, StringBuilder errStr) {
    for (ArriveItemVO body : vo.getBVO()) {
      if (body.getStatus() == VOStatus.DELETED || body.getNnum() == null) {
        continue;
      }
      if (body.getPk_material() != null) {
        MaterialStockVO materialVO = materialVOs.get(body.getPk_material());
        if (materialVO != null && materialVO.getQualitymanflag() != null
            && materialVO.getQualitymanflag().equals(UFBoolean.TRUE)) {
          UFDate dproduceDate = body.getDproducedate();
          UFDate dvalidateDate = body.getDinvaliddate();
          if (dproduceDate == null || dvalidateDate == null) {
            errStr.append(
                NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                    "04004040-0207", null, new String[] {
                      body.getCrowno()
                    })/* ��{0}������Ϊ�����ڹ�������ϣ���Ч���ں�ʧЧ���ڲ���Ϊ��! */).append("\n");
          }
        }
      }
    }
  }

  private static Map<String, MaterialStockVO> fetchMaterialInfo(String pk_org,
      Set<String> cmaterialVIDs) {
    String[] fields =
        {
          MaterialStockVO.QUALITYMANFLAG, MaterialStockVO.QUALITYNUM,
          MaterialStockVO.QUALITYUNIT
        };
    Map<String, MaterialStockVO> map =
        MaterialPubService.queryMaterialStockInfo(
            cmaterialVIDs.toArray(new String[0]), pk_org, fields);
    return map;
  }
}
