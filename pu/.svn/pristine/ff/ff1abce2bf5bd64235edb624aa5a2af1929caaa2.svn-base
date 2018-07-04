package nc.tc.nc.vo.pu.cgfa;
import org.testng.*;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.io.Serializable;
import jxl.read.biff.BiffException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.yonyou.uat.util.ExcelDataProvider;
import com.yonyou.uat.util.DBDataProvider;
import com.yonyou.uat.dbmanagement.DBManage;
import com.yonyou.uat.dbmanagement.QueryInfoVO;
import nc.vo.pu.cgfa.Qg20ViewVO;
import nc.bs.framework.common.NCLocator;
import com.yonyou.uat.framework.BaseTestCase;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
public class Qg20ViewVOTest extends BaseTestCase {
  Qg20ViewVO qg20ViewVO=null;
  DBManage dbManage=null;
  
  @BeforeClass 
  public void BeforeClass(){
    qg20ViewVO=NCLocator.getInstance().lookup(Qg20ViewVO.class);
  }
  
  @AfterClass 
  public void AfterClass(){
  }
  
  @BeforeMethod 
  public void BeforeMethod(){
    List<String> tableList=new ArrayList<String>();
    tableList.add("pub_wfexptlog");
    dbManage=new DBManage();
    dbManage.setTableList(tableList);
    dbManage.tableExport();
  }
  
  @AfterMethod 
  public void AfterMethod(){
    dbManage.tableRollBack();
  }
  
  @Test(description="",dependsOnMethods={},groups="",timeOut=100000,dataProvider="dp") 
  public void changeToBill(  Map<String,ArrayList<String>> dp){
    
    //Construct method parameters
    
    //Invoke tested method
    PraybillVO retObj=null;
    retObj=qg20ViewVO.changeToBill();
    
    //Verify result is ok
    
    //Verify Object1 == Object2
    Assert.assertNotNull(retObj);
    Assert.assertNotNull(retObj.getBVO());
    Assert.assertNotNull(retObj.getHVO());
    Assert.assertNotNull(retObj.getIsUserConfirm());
    Assert.assertEquals(retObj.getIsUserConfirm(),true);
    Assert.assertNotNull(retObj.getMetaData());
    Assert.assertNotNull(retObj.getMsg());
    Assert.assertEquals(retObj.getMsg(),"expectValue");
    
    //Verify Return or middle Object == expect Object(from object file)
    Object expectedObj=super.getExpectResultObject("caseName");
    if (expectedObj != null) {
      Assert.assertEquals(retObj,expectedObj);
    }
 else {
      super.saveResultObject((Serializable)retObj,"caseName");
    }
    
    //Verify DB result is ok
    QueryInfoVO queryInfoVerify=new QueryInfoVO();
    queryInfoVerify.setDatasource("datasourceName");
    queryInfoVerify.setTableName("tableName");
    queryInfoVerify.setCondition("where condition");
    List<Object> actualObjects=super.getDBObjectClass(Object.class,queryInfoVerify);
    Object actualObject=(Object)actualObjects.get(0);
    Assert.assertEquals("actualObject.getxxx()",dp.get("colName").get(0));
    
    //Verify whether have exception information in log 
    super.verifyLog("Error key word");
  }
  
  @Test(description="",dependsOnMethods={},groups="",timeOut=100000,dataProvider="dp") 
  public void getHead(  Map<String,ArrayList<String>> dp){
    
    //Construct method parameters
    
    //Invoke tested method
    PraybillHeaderVO retObj=new PraybillHeaderVO();
    retObj=qg20ViewVO.getHead();
    
    //Verify result is ok
    
    //Verify Object1 == Object2
    Assert.assertNotNull(retObj);
    Assert.assertNotNull(retObj.getApprover());
    Assert.assertEquals(retObj.getApprover(),"expectValue");
    Assert.assertNotNull(retObj.getBdirecttransit());
    Assert.assertNotNull(retObj.getBillmaker());
    Assert.assertEquals(retObj.getBillmaker(),"expectValue");
    Assert.assertNotNull(retObj.getBislatest());
    Assert.assertNotNull(retObj.getBordernumexec());
    Assert.assertNotNull(retObj.getBsctype());
    Assert.assertNotNull(retObj.getBusin_type());
    Assert.assertEquals(retObj.getBusin_type(),"expectValue");
    Assert.assertNotNull(retObj.getCcurrencyid());
    Assert.assertEquals(retObj.getCcurrencyid(),"expectValue");
    Assert.assertNotNull(retObj.getCreationtime());
    Assert.assertNotNull(retObj.getCreator());
    Assert.assertEquals(retObj.getCreator(),"expectValue");
    Assert.assertNotNull(retObj.getCreviseoperid());
    Assert.assertEquals(retObj.getCreviseoperid(),"expectValue");
    Assert.assertNotNull(retObj.getCtrantypeid());
    Assert.assertEquals(retObj.getCtrantypeid(),"expectValue");
    Assert.assertNotNull(retObj.getDbilldate());
    Assert.assertNotNull(retObj.getDmakedate());
    Assert.assertNotNull(retObj.getFbillstatus());
    Assert.assertEquals(retObj.getFbillstatus(),Integer.valueOf("0"));
    Assert.assertNotNull(retObj.getFpotype());
    Assert.assertEquals(retObj.getFpotype(),Integer.valueOf("0"));
    Assert.assertNotNull(retObj.getFpraysource());
    Assert.assertEquals(retObj.getFpraysource(),Integer.valueOf("0"));
    Assert.assertNotNull(retObj.getIprintcount());
    Assert.assertEquals(retObj.getIprintcount(),Integer.valueOf("0"));
    Assert.assertNotNull(retObj.getMetaData());
    Assert.assertNotNull(retObj.getModifiedtime());
    Assert.assertNotNull(retObj.getModifier());
    Assert.assertEquals(retObj.getModifier(),"expectValue");
    Assert.assertNotNull(retObj.getNtotalastnum());
    Assert.assertNotNull(retObj.getNtotaltaxmny());
    Assert.assertNotNull(retObj.getNversion());
    Assert.assertEquals(retObj.getNversion(),Integer.valueOf("0"));
    Assert.assertNotNull(retObj.getPk_group());
    Assert.assertEquals(retObj.getPk_group(),"expectValue");
    Assert.assertNotNull(retObj.getPk_org());
    Assert.assertEquals(retObj.getPk_org(),"expectValue");
    Assert.assertNotNull(retObj.getPk_org_v());
    Assert.assertEquals(retObj.getPk_org_v(),"expectValue");
    Assert.assertNotNull(retObj.getPk_plandept());
    Assert.assertEquals(retObj.getPk_plandept(),"expectValue");
    Assert.assertNotNull(retObj.getPk_plandept_v());
    Assert.assertEquals(retObj.getPk_plandept_v(),"expectValue");
    Assert.assertNotNull(retObj.getPk_planpsn());
    Assert.assertEquals(retObj.getPk_planpsn(),"expectValue");
    Assert.assertNotNull(retObj.getPk_praybill());
    Assert.assertEquals(retObj.getPk_praybill(),"expectValue");
    Assert.assertNotNull(retObj.getPk_srcpraybill());
    Assert.assertEquals(retObj.getPk_srcpraybill(),"expectValue");
    Assert.assertNotNull(retObj.getPro_type());
    Assert.assertEquals(retObj.getPro_type(),"expectValue");
    Assert.assertNotNull(retObj.getReq_type());
    Assert.assertEquals(retObj.getReq_type(),"expectValue");
    Assert.assertNotNull(retObj.getReq_unit());
    Assert.assertEquals(retObj.getReq_unit(),"expectValue");
    Assert.assertNotNull(retObj.getTaudittime());
    Assert.assertNotNull(retObj.getTrevisiontime());
    Assert.assertNotNull(retObj.getTs());
    Assert.assertNotNull(retObj.getVbillcode());
    Assert.assertEquals(retObj.getVbillcode(),"expectValue");
    Assert.assertNotNull(retObj.getVdef1());
    Assert.assertEquals(retObj.getVdef1(),"expectValue");
    Assert.assertNotNull(retObj.getVdef10());
    Assert.assertEquals(retObj.getVdef10(),"expectValue");
    Assert.assertNotNull(retObj.getVdef11());
    Assert.assertEquals(retObj.getVdef11(),"expectValue");
    Assert.assertNotNull(retObj.getVdef12());
    Assert.assertEquals(retObj.getVdef12(),"expectValue");
    Assert.assertNotNull(retObj.getVdef13());
    Assert.assertEquals(retObj.getVdef13(),"expectValue");
    Assert.assertNotNull(retObj.getVdef14());
    Assert.assertEquals(retObj.getVdef14(),"expectValue");
    Assert.assertNotNull(retObj.getVdef15());
    Assert.assertEquals(retObj.getVdef15(),"expectValue");
    Assert.assertNotNull(retObj.getVdef16());
    Assert.assertEquals(retObj.getVdef16(),"expectValue");
    Assert.assertNotNull(retObj.getVdef17());
    Assert.assertEquals(retObj.getVdef17(),"expectValue");
    Assert.assertNotNull(retObj.getVdef18());
    Assert.assertEquals(retObj.getVdef18(),"expectValue");
    Assert.assertNotNull(retObj.getVdef19());
    Assert.assertEquals(retObj.getVdef19(),"expectValue");
    Assert.assertNotNull(retObj.getVdef2());
    Assert.assertEquals(retObj.getVdef2(),"expectValue");
    Assert.assertNotNull(retObj.getVdef20());
    Assert.assertEquals(retObj.getVdef20(),"expectValue");
    Assert.assertNotNull(retObj.getVdef3());
    Assert.assertEquals(retObj.getVdef3(),"expectValue");
    Assert.assertNotNull(retObj.getVdef4());
    Assert.assertEquals(retObj.getVdef4(),"expectValue");
    Assert.assertNotNull(retObj.getVdef5());
    Assert.assertEquals(retObj.getVdef5(),"expectValue");
    Assert.assertNotNull(retObj.getVdef6());
    Assert.assertEquals(retObj.getVdef6(),"expectValue");
    Assert.assertNotNull(retObj.getVdef7());
    Assert.assertEquals(retObj.getVdef7(),"expectValue");
    Assert.assertNotNull(retObj.getVdef8());
    Assert.assertEquals(retObj.getVdef8(),"expectValue");
    Assert.assertNotNull(retObj.getVdef9());
    Assert.assertEquals(retObj.getVdef9(),"expectValue");
    Assert.assertNotNull(retObj.getVmemo());
    Assert.assertEquals(retObj.getVmemo(),"expectValue");
    Assert.assertNotNull(retObj.getVtrantypecode());
    Assert.assertEquals(retObj.getVtrantypecode(),"expectValue");
    
    //Verify Return or middle Object == expect Object(from object file)
    Object expectedObj=super.getExpectResultObject("caseName");
    if (expectedObj != null) {
      Assert.assertEquals(retObj,expectedObj);
    }
 else {
      super.saveResultObject((Serializable)retObj,"caseName");
    }
    
    //Verify DB result is ok
    QueryInfoVO queryInfoVerify=new QueryInfoVO();
    queryInfoVerify.setDatasource("datasourceName");
    queryInfoVerify.setTableName("tableName");
    queryInfoVerify.setCondition("where condition");
    List<Object> actualObjects=super.getDBObjectClass(Object.class,queryInfoVerify);
    Object actualObject=(Object)actualObjects.get(0);
    Assert.assertEquals("actualObject.getxxx()",dp.get("colName").get(0));
    
    //Verify whether have exception information in log 
    super.verifyLog("Error key word");
  }
  
  @Test(description="",dependsOnMethods={},groups="",timeOut=100000,dataProvider="dp") 
  public void getItem(  Map<String,ArrayList<String>> dp){
    
    //Construct method parameters
    
    //Invoke tested method
    PraybillItemVO retObj=new PraybillItemVO();
    retObj=qg20ViewVO.getItem();
    
    //Verify result is ok
    
    //Verify Object1 == Object2
    Assert.assertNotNull(retObj);
    Assert.assertNotNull(retObj.getBanfn());
    Assert.assertEquals(retObj.getBanfn(),"expectValue");
    Assert.assertNotNull(retObj.getBcanpurchaseorgedit());
    Assert.assertNotNull(retObj.getBfixedrate());
    Assert.assertNotNull(retObj.getBisarrange());
    Assert.assertNotNull(retObj.getBisgensaorder());
    Assert.assertNotNull(retObj.getBnfpo());
    Assert.assertEquals(retObj.getBnfpo(),"expectValue");
    Assert.assertNotNull(retObj.getBpublishtoec());
    Assert.assertNotNull(retObj.getBrowclose());
    Assert.assertNotNull(retObj.getCasscustid());
    Assert.assertEquals(retObj.getCasscustid(),"expectValue");
    Assert.assertNotNull(retObj.getCastunitid());
    Assert.assertEquals(retObj.getCastunitid(),"expectValue");
    Assert.assertNotNull(retObj.getCde_equipment());
    Assert.assertEquals(retObj.getCde_equipment(),"expectValue");
    Assert.assertNotNull(retObj.getCde_fact());
    Assert.assertEquals(retObj.getCde_fact(),"expectValue");
    Assert.assertNotNull(retObj.getCde_manufac());
    Assert.assertEquals(retObj.getCde_manufac(),"expectValue");
    Assert.assertNotNull(retObj.getCffileid());
    Assert.assertEquals(retObj.getCffileid(),"expectValue");
    Assert.assertNotNull(retObj.getCfirstbid());
    Assert.assertEquals(retObj.getCfirstbid(),"expectValue");
    Assert.assertNotNull(retObj.getCfirstid());
    Assert.assertEquals(retObj.getCfirstid(),"expectValue");
    Assert.assertNotNull(retObj.getCfirsttypecode());
    Assert.assertEquals(retObj.getCfirsttypecode(),"expectValue");
    Assert.assertNotNull(retObj.getChecker_fac());
    Assert.assertEquals(retObj.getChecker_fac(),"expectValue");
    Assert.assertNotNull(retObj.getCode_facty());
    Assert.assertEquals(retObj.getCode_facty(),"expectValue");
    Assert.assertNotNull(retObj.getCode_proj_cst());
    Assert.assertEquals(retObj.getCode_proj_cst(),"expectValue");
    Assert.assertNotNull(retObj.getCordertrantypecode());
    Assert.assertEquals(retObj.getCordertrantypecode(),"expectValue");
    Assert.assertNotNull(retObj.getCproductorid());
    Assert.assertEquals(retObj.getCproductorid(),"expectValue");
    Assert.assertNotNull(retObj.getCprojectid());
    Assert.assertEquals(retObj.getCprojectid(),"expectValue");
    Assert.assertNotNull(retObj.getCprojecttaskid());
    Assert.assertEquals(retObj.getCprojecttaskid(),"expectValue");
    Assert.assertNotNull(retObj.getCrowno());
    Assert.assertEquals(retObj.getCrowno(),"expectValue");
    Assert.assertNotNull(retObj.getCsourcebid());
    Assert.assertEquals(retObj.getCsourcebid(),"expectValue");
    Assert.assertNotNull(retObj.getCsourceid());
    Assert.assertEquals(retObj.getCsourceid(),"expectValue");
    Assert.assertNotNull(retObj.getCsourcetypecode());
    Assert.assertEquals(retObj.getCsourcetypecode(),"expectValue");
    Assert.assertNotNull(retObj.getCunitid());
    Assert.assertEquals(retObj.getCunitid(),"expectValue");
    Assert.assertNotNull(retObj.getDbilldate());
    Assert.assertNotNull(retObj.getDreqdate());
    Assert.assertNotNull(retObj.getDsuggestdate());
    Assert.assertNotNull(retObj.getGroup_matl());
    Assert.assertEquals(retObj.getGroup_matl(),"expectValue");
    Assert.assertNotNull(retObj.getMaterial_quality());
    Assert.assertEquals(retObj.getMaterial_quality(),"expectValue");
    Assert.assertNotNull(retObj.getMetaData());
    Assert.assertNotNull(retObj.getNaccumulatenum());
    Assert.assertNotNull(retObj.getName_fact());
    Assert.assertEquals(retObj.getName_fact(),"expectValue");
    Assert.assertNotNull(retObj.getName_machine());
    Assert.assertEquals(retObj.getName_machine(),"expectValue");
    Assert.assertNotNull(retObj.getName_manufac());
    Assert.assertEquals(retObj.getName_manufac(),"expectValue");
    Assert.assertNotNull(retObj.getName_proj_cst());
    Assert.assertEquals(retObj.getName_proj_cst(),"expectValue");
    Assert.assertNotNull(retObj.getNastnum());
    Assert.assertNotNull(retObj.getNgenct());
    Assert.assertEquals(retObj.getNgenct(),Integer.valueOf("0"));
    Assert.assertNotNull(retObj.getNnum());
    Assert.assertNotNull(retObj.getNpriceauditbill());
    Assert.assertEquals(retObj.getNpriceauditbill(),Integer.valueOf("0"));
    Assert.assertNotNull(retObj.getNquotebill());
    Assert.assertEquals(retObj.getNquotebill(),Integer.valueOf("0"));
    Assert.assertNotNull(retObj.getNtaxmny());
    Assert.assertNotNull(retObj.getNtaxprice());
    Assert.assertNotNull(retObj.getPk_batchcode());
    Assert.assertEquals(retObj.getPk_batchcode(),"expectValue");
    Assert.assertNotNull(retObj.getPk_employee());
    Assert.assertEquals(retObj.getPk_employee(),"expectValue");
    Assert.assertNotNull(retObj.getPk_group());
    Assert.assertEquals(retObj.getPk_group(),"expectValue");
    Assert.assertNotNull(retObj.getPk_material());
    Assert.assertEquals(retObj.getPk_material(),"expectValue");
    Assert.assertNotNull(retObj.getPk_org());
    Assert.assertEquals(retObj.getPk_org(),"expectValue");
    Assert.assertNotNull(retObj.getPk_org_v());
    Assert.assertEquals(retObj.getPk_org_v(),"expectValue");
    Assert.assertNotNull(retObj.getPk_praybill());
    Assert.assertEquals(retObj.getPk_praybill(),"expectValue");
    Assert.assertNotNull(retObj.getPk_praybill_b());
    Assert.assertEquals(retObj.getPk_praybill_b(),"expectValue");
    Assert.assertNotNull(retObj.getPk_product());
    Assert.assertEquals(retObj.getPk_product(),"expectValue");
    Assert.assertNotNull(retObj.getPk_product_v());
    Assert.assertEquals(retObj.getPk_product_v(),"expectValue");
    Assert.assertNotNull(retObj.getPk_purchaseorg());
    Assert.assertEquals(retObj.getPk_purchaseorg(),"expectValue");
    Assert.assertNotNull(retObj.getPk_purchaseorg_v());
    Assert.assertEquals(retObj.getPk_purchaseorg_v(),"expectValue");
    Assert.assertNotNull(retObj.getPk_reqdept());
    Assert.assertEquals(retObj.getPk_reqdept(),"expectValue");
    Assert.assertNotNull(retObj.getPk_reqdept_v());
    Assert.assertEquals(retObj.getPk_reqdept_v(),"expectValue");
    Assert.assertNotNull(retObj.getPk_reqstoorg());
    Assert.assertEquals(retObj.getPk_reqstoorg(),"expectValue");
    Assert.assertNotNull(retObj.getPk_reqstoorg_v());
    Assert.assertEquals(retObj.getPk_reqstoorg_v(),"expectValue");
    Assert.assertNotNull(retObj.getPk_reqstor());
    Assert.assertEquals(retObj.getPk_reqstor(),"expectValue");
    Assert.assertNotNull(retObj.getPk_srcmaterial());
    Assert.assertEquals(retObj.getPk_srcmaterial(),"expectValue");
    Assert.assertNotNull(retObj.getPk_suggestsupplier());
    Assert.assertEquals(retObj.getPk_suggestsupplier(),"expectValue");
    Assert.assertNotNull(retObj.getPric_req());
    Assert.assertNotNull(retObj.getPric_req_rat());
    Assert.assertNotNull(retObj.getRating_life());
    Assert.assertEquals(retObj.getRating_life(),Integer.valueOf("0"));
    Assert.assertNotNull(retObj.getRemark_plan());
    Assert.assertEquals(retObj.getRemark_plan(),"expectValue");
    Assert.assertNotNull(retObj.getReq_document());
    Assert.assertEquals(retObj.getReq_document(),"expectValue");
    Assert.assertNotNull(retObj.getReq_group());
    Assert.assertEquals(retObj.getReq_group(),"expectValue");
    Assert.assertNotNull(retObj.getReq_group_b());
    Assert.assertEquals(retObj.getReq_group_b(),"expectValue");
    Assert.assertNotNull(retObj.getSeq_fac_no());
    Assert.assertEquals(retObj.getSeq_fac_no(),"expectValue");
    Assert.assertNotNull(retObj.getSeq_fac_plan());
    Assert.assertEquals(retObj.getSeq_fac_plan(),"expectValue");
    Assert.assertNotNull(retObj.getSourcebts());
    Assert.assertNotNull(retObj.getSourcets());
    Assert.assertNotNull(retObj.getSts_req());
    Assert.assertEquals(retObj.getSts_req(),"expectValue");
    Assert.assertNotNull(retObj.getTmstp_dispatch());
    Assert.assertNotNull(retObj.getTot_pric_req());
    Assert.assertNotNull(retObj.getTot_pric_req_rat());
    Assert.assertNotNull(retObj.getTs());
    Assert.assertNotNull(retObj.getUnit_pric());
    Assert.assertEquals(retObj.getUnit_pric(),Integer.valueOf("0"));
    Assert.assertNotNull(retObj.getVbatchcode());
    Assert.assertEquals(retObj.getVbatchcode(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef1());
    Assert.assertEquals(retObj.getVbdef1(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef10());
    Assert.assertEquals(retObj.getVbdef10(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef11());
    Assert.assertEquals(retObj.getVbdef11(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef12());
    Assert.assertEquals(retObj.getVbdef12(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef13());
    Assert.assertEquals(retObj.getVbdef13(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef14());
    Assert.assertEquals(retObj.getVbdef14(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef15());
    Assert.assertEquals(retObj.getVbdef15(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef16());
    Assert.assertEquals(retObj.getVbdef16(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef17());
    Assert.assertEquals(retObj.getVbdef17(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef18());
    Assert.assertEquals(retObj.getVbdef18(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef19());
    Assert.assertEquals(retObj.getVbdef19(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef2());
    Assert.assertEquals(retObj.getVbdef2(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef20());
    Assert.assertEquals(retObj.getVbdef20(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef3());
    Assert.assertEquals(retObj.getVbdef3(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef4());
    Assert.assertEquals(retObj.getVbdef4(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef5());
    Assert.assertEquals(retObj.getVbdef5(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef6());
    Assert.assertEquals(retObj.getVbdef6(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef7());
    Assert.assertEquals(retObj.getVbdef7(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef8());
    Assert.assertEquals(retObj.getVbdef8(),"expectValue");
    Assert.assertNotNull(retObj.getVbdef9());
    Assert.assertEquals(retObj.getVbdef9(),"expectValue");
    Assert.assertNotNull(retObj.getVbmemo());
    Assert.assertEquals(retObj.getVbmemo(),"expectValue");
    Assert.assertNotNull(retObj.getVchangerate());
    Assert.assertEquals(retObj.getVchangerate(),"expectValue");
    Assert.assertNotNull(retObj.getVfirstcode());
    Assert.assertEquals(retObj.getVfirstcode(),"expectValue");
    Assert.assertNotNull(retObj.getVfirstrowno());
    Assert.assertEquals(retObj.getVfirstrowno(),"expectValue");
    Assert.assertNotNull(retObj.getVfirsttrantype());
    Assert.assertEquals(retObj.getVfirsttrantype(),"expectValue");
    Assert.assertNotNull(retObj.getVfree1());
    Assert.assertEquals(retObj.getVfree1(),"expectValue");
    Assert.assertNotNull(retObj.getVfree10());
    Assert.assertEquals(retObj.getVfree10(),"expectValue");
    Assert.assertNotNull(retObj.getVfree2());
    Assert.assertEquals(retObj.getVfree2(),"expectValue");
    Assert.assertNotNull(retObj.getVfree3());
    Assert.assertEquals(retObj.getVfree3(),"expectValue");
    Assert.assertNotNull(retObj.getVfree4());
    Assert.assertEquals(retObj.getVfree4(),"expectValue");
    Assert.assertNotNull(retObj.getVfree5());
    Assert.assertEquals(retObj.getVfree5(),"expectValue");
    Assert.assertNotNull(retObj.getVfree6());
    Assert.assertEquals(retObj.getVfree6(),"expectValue");
    Assert.assertNotNull(retObj.getVfree7());
    Assert.assertEquals(retObj.getVfree7(),"expectValue");
    Assert.assertNotNull(retObj.getVfree8());
    Assert.assertEquals(retObj.getVfree8(),"expectValue");
    Assert.assertNotNull(retObj.getVfree9());
    Assert.assertEquals(retObj.getVfree9(),"expectValue");
    Assert.assertNotNull(retObj.getVsourcecode());
    Assert.assertEquals(retObj.getVsourcecode(),"expectValue");
    Assert.assertNotNull(retObj.getVsourcerowno());
    Assert.assertEquals(retObj.getVsourcerowno(),"expectValue");
    Assert.assertNotNull(retObj.getVsrctrantypecode());
    Assert.assertEquals(retObj.getVsrctrantypecode(),"expectValue");
    
    //Verify Return or middle Object == expect Object(from object file)
    Object expectedObj=super.getExpectResultObject("caseName");
    if (expectedObj != null) {
      Assert.assertEquals(retObj,expectedObj);
    }
 else {
      super.saveResultObject((Serializable)retObj,"caseName");
    }
    
    //Verify DB result is ok
    QueryInfoVO queryInfoVerify=new QueryInfoVO();
    queryInfoVerify.setDatasource("datasourceName");
    queryInfoVerify.setTableName("tableName");
    queryInfoVerify.setCondition("where condition");
    List<Object> actualObjects=super.getDBObjectClass(Object.class,queryInfoVerify);
    Object actualObject=(Object)actualObjects.get(0);
    Assert.assertEquals("actualObject.getxxx()",dp.get("colName").get(0));
    
    //Verify whether have exception information in log 
    super.verifyLog("Error key word");
  }
  
  @Test(description="",dependsOnMethods={},groups="",timeOut=100000,dataProvider="dp") 
  public void getMetaData(  Map<String,ArrayList<String>> dp){
    
    //Construct method parameters
    
    //Invoke tested method
    IDataViewMeta retObj=null;
    retObj=qg20ViewVO.getMetaData();
    
    //Verify result is ok
    
    //Verify Object1 == Object2
    Assert.assertNotNull(retObj);
    Assert.assertNotNull(retObj.getAttributeNames());
    Assert.assertNotNull(retObj.getMainVOMeta());
    Assert.assertNotNull(retObj.getStatisticInfo());
    Assert.assertNotNull(retObj.getVOMetas());
    
    //Verify DB result is ok
    QueryInfoVO queryInfoVerify=new QueryInfoVO();
    queryInfoVerify.setDatasource("datasourceName");
    queryInfoVerify.setTableName("tableName");
    queryInfoVerify.setCondition("where condition");
    List<Object> actualObjects=super.getDBObjectClass(Object.class,queryInfoVerify);
    Object actualObject=(Object)actualObjects.get(0);
    Assert.assertEquals("actualObject.getxxx()",dp.get("colName").get(0));
    
    //Verify whether have exception information in log 
    super.verifyLog("Error key word");
  }
  
  @Test(description="",dependsOnMethods={},groups="",timeOut=100000,dataProvider="dp") 
  public void setHead(  Map<String,ArrayList<String>> dp){
    
    //Invoke tested method
    qg20ViewVO.setHead(head);
    
    //Verify result is ok
    
    //Verify Object1 == Object2
    Assert.assertEquals("actual","expected");
    Assert.assertNotNull("actual");
    
    //Verify DB result is ok
    QueryInfoVO queryInfoVerify=new QueryInfoVO();
    queryInfoVerify.setDatasource("datasourceName");
    queryInfoVerify.setTableName("tableName");
    queryInfoVerify.setCondition("where condition");
    List<Object> actualObjects=super.getDBObjectClass(Object.class,queryInfoVerify);
    Object actualObject=(Object)actualObjects.get(0);
    Assert.assertEquals("actualObject.getxxx()",dp.get("colName").get(0));
    
    //Verify whether have exception information in log 
    super.verifyLog("Error key word");
  }
  
  @Test(description="",dependsOnMethods={},groups="",timeOut=100000,dataProvider="dp") 
  public void setItem(  Map<String,ArrayList<String>> dp){
    
    //Invoke tested method
    qg20ViewVO.setItem(item);
    
    //Verify result is ok
    
    //Verify Object1 == Object2
    Assert.assertEquals("actual","expected");
    Assert.assertNotNull("actual");
    
    //Verify DB result is ok
    QueryInfoVO queryInfoVerify=new QueryInfoVO();
    queryInfoVerify.setDatasource("datasourceName");
    queryInfoVerify.setTableName("tableName");
    queryInfoVerify.setCondition("where condition");
    List<Object> actualObjects=super.getDBObjectClass(Object.class,queryInfoVerify);
    Object actualObject=(Object)actualObjects.get(0);
    Assert.assertEquals("actualObject.getxxx()",dp.get("colName").get(0));
    
    //Verify whether have exception information in log 
    super.verifyLog("Error key word");
  }
}
