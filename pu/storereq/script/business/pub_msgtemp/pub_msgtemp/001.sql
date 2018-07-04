insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1001Z81000000000E61A',0,'simpchn','@&m billmaker.user_name@ 提交的物资需求申请单待审批！','GLOBLE00000000000000','1002Z81000000001156X','PU_MP_422X','物资需求申请单消息模板','物资需求申请单移动审批','<div class="divtext">
<span  class="labeltext">申请单号:</span >
<span  class="normaltext">@&m vbillcode@</span >
</div>
<div class="divtext">
<span  class="labeltext">库存组织:</span >
<span  class="normaltext">@&m pk_org.name@</span >
</div>
<div class="divtext">
<span  class="labeltext">申请类型:</span >
<span  class="normaltext">@&m ctrantypeid.billtypename@</span >
</div>
<div class="divtext">
<span  class="labeltext">总数量:</span >
<span  class="keytext">@&m ntotalastnum@</span >
</div>','2015-10-26 11:19:42','422X',1,0,'1',null,null,null,null,null,'1003Z810000000001I0Q')
go

