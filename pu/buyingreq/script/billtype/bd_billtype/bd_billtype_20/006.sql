insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z810000000007ZT5','nc.vo.pub.AggregatedValueObject:01','nc.bs.pu.m20.pf.function.split.SplitPrayByPosFor21','0015',0,'生成采购订单时根据采购岗分单','FunM20_0015',null,null,'splitByPosition','20','20','ARRAYLIST','2014-12-01 22:32:09',1)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1001Z810000000009R3F','nc.vo.pub.AggregatedValueObject:01','nc.bs.pu.m20.pf.function.split.SpPrayByTrans','0007',null,'生成采购订单时根据订单类型分单','FunM20_0007',null,null,'splitByTrans','20','20','ARRAYLIST','2014-12-01 22:32:09',1)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z81000000000HPDG','nc.vo.pub.AggregatedValueObject:01','nc.bs.pu.m20.pf.function.MaterialClass','0003',null,'物料采购分类编码包含','FunM20_0003',null,null,'getMaterialClassCode','20','20','STRING','2014-12-01 22:32:09',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z81000000000HPDI','nc.vo.pub.AggregatedValueObject:01','nc.bs.pu.m20.pf.function.MaterialCode','0004',null,'物料编码包含','FunM20_0004',null,null,'getMaterialCode','20','20','STRING','2014-12-01 22:32:09',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z81000000000HPDJ','nc.vo.pub.AggregatedValueObject:01','nc.bs.pu.m20.pf.function.PurchaseOrg','0002',null,'物料所属采购组织检查','FunM20_0002',null,null,'purchaseOrgMatch','20','20','BOOLEAN','2014-12-01 22:32:09',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z81000000000HPDK','nc.vo.pub.AggregatedValueObject:01','nc.bs.pu.m20.pf.function.SupplierMaterial','0005',null,'供应商与物料检查','FunM20_0005',null,null,'checkSupplierMaterial','20','20','BOOLEAN','2014-12-01 22:32:09',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z81000000000HPDL','nc.vo.pub.AggregatedValueObject:01','nc.bs.pu.m20.pf.function.PermitSupplier','0001',null,'核准供应商检查','FunM20_0001',null,null,'isPermitSupplier','20','20','BOOLEAN','2014-12-01 22:32:09',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z81000000000HPDM','nc.vo.pub.AggregatedValueObject:01','nc.bs.pu.m20.pf.function.PrayOverBudget','0006',0,'请购超采购计划金额','FunM20_0006',null,null,'getOverBudget','20','20','DOUBLE','2014-12-01 22:32:09',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z81000000000HPDN','nc.vo.pub.AggregatedValueObject:01','nc.bs.pu.m20.pf.function.PrayOverBudget','0008',0,'请购超采购计划数量','FunM20_0008',null,null,'getOverBudget','20','20','DOUBLE','2014-12-01 22:32:09',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z81000000000HPDO','nc.vo.pub.AggregatedValueObject:01','nc.bs.pu.m20.pf.function.PrayAstNumAmount','0009',null,'请购单合计数量','FunM20_0009',null,null,'getPraybillAssistNumAmount','20','20','DOUBLE','2014-12-01 22:32:09',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z81000000000HPDP','nc.vo.pub.AggregatedValueObject:01','nc.bs.pu.m20.pf.function.PrayTaxMnyAmount','0010',null,'请购单合计金额','FunM20_0010',null,null,'getTaxMnyAmount','20','20','DOUBLE','2014-12-01 22:32:09',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z81000000000HPDQ','nc.vo.pub.AggregatedValueObject:01','nc.bs.pu.m20.pf.function.PrayNumAmount','0011',null,'请购单合计主数量','FunM20_0011',null,null,'getPrayNumAmount','20','20','DOUBLE','2014-12-01 22:32:09',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z81000000000HPDR','nc.vo.pub.AggregatedValueObject:01','nc.bs.pu.m20.pf.function.MaterialCodeDuplicated','0012',null,'请购单行物料重复检查','FunM20_0012',null,'N','isMaterialCodeDuplicated','20','20','BOOLEAN','2014-12-01 22:32:09',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z81000000000HPDS','nc.vo.pub.AggregatedValueObject:01','nc.bs.pu.m20.pf.function.PsnDept','0013',null,'请购人所属部门检查','FunM20_0013',null,null,'isPsnBelongDept','20','20','BOOLEAN','2014-12-01 22:32:09',0)
go

insert into pub_function(pk_function,arguments,classname,code,dr,functionnote,functionnote_resid,hintmessage,iscomp,methodname,pk_billtype,pk_billtypeid,returntype,ts,functype) values('1002Z81000000000HPDT','nc.vo.pub.AggregatedValueObject:01','nc.bs.pu.m20.pf.function.PlanPosition','0014',null,'计划岗设置检查','FunM20_0014',null,null,'isMatchPlanPosition','20','20','BOOLEAN','2014-12-01 22:32:09',0)
go

