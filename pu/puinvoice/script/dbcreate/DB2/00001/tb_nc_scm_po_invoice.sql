/* tablename: �ɹ���Ʊ���� */
create table po_invoice (pk_invoice char(20) not null 
/*�ɹ���Ʊ*/,
pk_group varchar(20) null 
/*��������*/,
pk_org varchar(20) null 
/*������֯(OID)*/,
pk_org_v varchar(20) null 
/*������֯(VID)*/,
vbillcode varchar(200) null 
/*��Ʊ��*/,
dbilldate char(19) null 
/*��Ʊ����*/,
darrivedate varchar(19) null 
/*Ʊ������*/,
vtrantypecode varchar(20) null 
/*��Ʊ����(��������)����*/,
pk_busitype varchar(20) null 
/*ҵ������*/,
bfee char(1) null default 'N' 
/*���÷�Ʊ*/,
pk_purchaseorg varchar(20) null 
/*�ɹ���֯(OID)*/,
pk_purchaseorg_v varchar(20) null 
/*�ɹ���֯(VID)*/,
pk_stockorg varchar(20) null 
/*�����֯(OID)*/,
pk_stockorg_v varchar(20) null 
/*�����֯(VID)*/,
pk_supplier varchar(20) null 
/*��Ӧ��*/,
pk_bankaccbas varchar(20) null 
/*�����˻�*/,
pk_freecust varchar(20) null 
/*ɢ��*/,
pk_bizpsn varchar(20) null 
/*ҵ��Ա*/,
pk_dept varchar(20) null 
/*�ɹ�����(OID)*/,
pk_dept_v varchar(20) null 
/*�ɹ�����(VID)*/,
pk_paytosupplier varchar(20) null 
/*���λ*/,
pk_payterm varchar(20) null 
/*����Э��*/,
vdef1 varchar(101) null 
/*�Զ�����1*/,
vdef2 varchar(101) null 
/*�Զ�����2*/,
vdef3 varchar(101) null 
/*�Զ�����3*/,
vdef4 varchar(101) null 
/*�Զ�����4*/,
vdef5 varchar(101) null 
/*�Զ�����5*/,
vdef6 varchar(101) null 
/*�Զ�����6*/,
vdef7 varchar(101) null 
/*�Զ�����7*/,
vdef8 varchar(101) null 
/*�Զ�����8*/,
vdef9 varchar(101) null 
/*�Զ�����9*/,
vdef10 varchar(101) null 
/*�Զ�����10*/,
vdef11 varchar(101) null 
/*�Զ�����11*/,
vdef12 varchar(101) null 
/*�Զ�����12*/,
vdef13 varchar(101) null 
/*�Զ�����13*/,
vdef14 varchar(101) null 
/*�Զ�����14*/,
vdef15 varchar(101) null 
/*�Զ�����15*/,
vdef16 varchar(101) null 
/*�Զ�����16*/,
vdef17 varchar(101) null 
/*�Զ�����17*/,
vdef18 varchar(101) null 
/*�Զ�����18*/,
vdef19 varchar(101) null 
/*�Զ�����19*/,
vdef20 varchar(101) null 
/*�Զ�����20*/,
corigcurrencyid varchar(20) null 
/*����*/,
nexchangerate decimal(28,8) null default 1.00 
/*����*/,
bapflag char(1) null default 'N' 
/*�Ѵ�Ӧ����־*/,
bfrozen char(1) null default 'N' 
/*����*/,
vfrozenreason varchar(181) null 
/*��󶳽�ԭ��*/,
ntotalastnum decimal(28,8) null 
/*��������*/,
ntotalorigmny decimal(28,8) null 
/*������˰�ϼ�(ԭ��)*/,
vmemo varchar(181) null 
/*��ע*/,
pk_frozenuser varchar(20) null 
/*������*/,
tfrozentime varchar(19) null 
/*��������*/,
fbillstatus integer null default 0 
/*����״̬*/,
billmaker varchar(20) null 
/*�Ƶ���*/,
dmakedate char(19) null 
/*�Ƶ�����*/,
creationtime char(19) null 
/*����ʱ��*/,
approver varchar(20) null 
/*������*/,
taudittime varchar(19) null 
/*��������*/,
modifier varchar(20) null 
/*����޸���*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
iprintcount integer null default 0 
/*��ӡ����*/,
pk_parentinvoice varchar(20) null 
/*���÷�Ʊ��Ӧ���﷢Ʊ*/,
finvoiceclass integer null default 0 
/*��Ʊ����*/,
ftaxtypeflagh integer null default 1 
/*������˰���*/,
ntaxrateh decimal(28,8) null 
/*����˰��*/,
ccurrencyid varchar(20) null 
/*���ұ���(��λ��)*/,
ngroupexchgrate decimal(28,8) null 
/*���ű�λ�һ���*/,
nglobalexchgrate decimal(28,8) null 
/*ȫ�ֱ�λ�һ���*/,
binitial char(1) null default 'N' 
/*�Ƿ��ڳ���Ʊ*/,
creator varchar(20) null 
/*������*/,
bvirtual char(1) null default 'N' 
/*���ⷢƱ��־*/,
ctrantypeid varchar(20) null 
/*��Ʊ����(��������)*/,
csendcountryid varchar(20) null 
/*��������/����*/,
crececountryid varchar(20) null 
/*�ջ�����/����*/,
ctaxcountryid varchar(20) null 
/*��˰����/����*/,
fbuysellflag integer null 
/*��������*/,
btriatradeflag char(1) null 
/*����ó��*/,
ctradewordid varchar(20) null 
/*ó������*/,
bopptaxflag char(1) null default 'N' 
/*������˰*/,
vvatcode varchar(40) null 
/*VATע����*/,
vvendorvatcode varchar(40) null 
/*��Ӧ��VATע����*/,
finvoicetype integer null 
/*��Ʊ����*/,
vadjustreason varchar(181) null 
/*����ԭ��*/,
pk_balatype varchar(20) null 
/*���㷽ʽ*/,
 constraint pk_po_invoice primary key (pk_invoice),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �ɹ���Ʊ�ӱ� */
create table po_invoice_b (pk_invoice_b char(20) not null 
/*�ɹ���Ʊ��ϸ*/,
pk_group varchar(20) null 
/*��������*/,
pk_org varchar(20) null 
/*������֯(OID)*/,
pk_org_v varchar(20) null 
/*������֯(VID)*/,
pk_apfinanceorg varchar(20) null 
/*Ӧ��������֯(OID)*/,
pk_apfinanceorg_v varchar(20) null 
/*Ӧ��������֯(VID)*/,
crowno varchar(20) null 
/*�к�*/,
pk_material varchar(20) null 
/*����(VID)*/,
pk_srcmaterial varchar(20) null 
/*����(OID)*/,
cunitid varchar(20) null 
/*����λ*/,
nnum decimal(28,8) null 
/*������*/,
norigprice decimal(28,8) null 
/*����˰����*/,
norigmny decimal(28,8) null 
/*��˰���*/,
norigtaxmny decimal(28,8) null 
/*��˰�ϼ�*/,
norigtaxprice decimal(28,8) null 
/*����˰����*/,
ftaxtypeflag integer null default 1 
/*��˰���*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
nprice decimal(28,8) null 
/*��������˰����*/,
nmny decimal(28,8) null 
/*������˰���*/,
ntax decimal(28,8) null 
/*����˰��*/,
ntaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
ntaxprice decimal(28,8) null 
/*�����Һ�˰����*/,
castunitid varchar(20) null 
/*��λ*/,
vchangerate varchar(60) null 
/*������*/,
nastnum decimal(28,8) null 
/*����*/,
pk_costsubj varchar(20) null 
/*��֧��Ŀ*/,
pk_stordoc varchar(20) null 
/*�ֿ�*/,
vbatchcode varchar(40) null 
/*���κ�*/,
pk_batchcode varchar(20) null 
/*���ε���*/,
pk_usedept varchar(20) null 
/*ʹ�ò���(OID)*/,
pk_usedept_v varchar(20) null 
/*ʹ�ò���(VID)*/,
naccumsettnum decimal(28,8) null 
/*�ۼƽ���������*/,
naccumsettmny decimal(28,8) null 
/*�ۼƱ��ҽ�����*/,
nreasonwastenum decimal(28,8) null 
/*�������������*/,
vbdef1 varchar(101) null 
/*�Զ�����1*/,
vbdef2 varchar(101) null 
/*�Զ�����2*/,
vbdef3 varchar(101) null 
/*�Զ�����3*/,
vbdef4 varchar(101) null 
/*�Զ�����4*/,
vbdef5 varchar(101) null 
/*�Զ�����5*/,
vbdef6 varchar(101) null 
/*�Զ�����6*/,
vbdef7 varchar(101) null 
/*�Զ�����7*/,
vbdef8 varchar(101) null 
/*�Զ�����8*/,
vbdef9 varchar(101) null 
/*�Զ�����9*/,
vbdef10 varchar(101) null 
/*�Զ�����10*/,
vbdef11 varchar(101) null 
/*�Զ�����11*/,
vbdef12 varchar(101) null 
/*�Զ�����12*/,
vbdef13 varchar(101) null 
/*�Զ�����13*/,
vbdef14 varchar(101) null 
/*�Զ�����14*/,
vbdef15 varchar(101) null 
/*�Զ�����15*/,
vbdef16 varchar(101) null 
/*�Զ�����16*/,
vbdef17 varchar(101) null 
/*�Զ�����17*/,
vbdef18 varchar(101) null 
/*�Զ�����18*/,
vbdef19 varchar(101) null 
/*�Զ�����19*/,
vbdef20 varchar(101) null 
/*�Զ�����20*/,
vfree1 varchar(101) null 
/*���ɸ�������1*/,
vfree2 varchar(101) null 
/*���ɸ�������2*/,
vfree3 varchar(101) null 
/*���ɸ�������3*/,
vfree4 varchar(101) null 
/*���ɸ�������4*/,
vfree5 varchar(101) null 
/*���ɸ�������5*/,
vfree6 varchar(101) null 
/*���ɸ�������6*/,
vfree7 varchar(101) null 
/*���ɸ�������7*/,
vfree8 varchar(101) null 
/*���ɸ�������8*/,
vfree9 varchar(101) null 
/*���ɸ�������9*/,
vfree10 varchar(101) null 
/*���ɸ�������10*/,
cprojectid varchar(20) null 
/*��Ŀ*/,
cproductorid varchar(20) null 
/*��������*/,
vmemob varchar(181) null 
/*��ע*/,
vctcode varchar(40) null 
/*��ͬ��*/,
cfirsttypecode varchar(20) null 
/*Դͷ��������*/,
vfirstcode varchar(40) null 
/*Դͷ���ݺ�*/,
cfirstid varchar(20) null 
/*Դͷ��������*/,
vfirstrowno varchar(20) null 
/*Դͷ�����к�*/,
vfirsttrantype varchar(20) null 
/*Դͷ��������*/,
cfirstbid varchar(20) null 
/*Դͷ����������*/,
csourcetypecode varchar(20) null 
/*��Դ��������*/,
vsourcecode varchar(40) null 
/*��Դ���ݺ�*/,
csourceid varchar(20) null 
/*��Դ��������*/,
csourcebid varchar(20) null 
/*��Դ����������*/,
vsourcerowno varchar(20) null 
/*��Դ�����к�*/,
vsourcetrantype varchar(20) null 
/*��Դ��������*/,
pk_order varchar(20) null 
/*��������*/,
pk_order_b varchar(20) null 
/*����������*/,
vordercode varchar(40) null 
/*������*/,
vordertrantype varchar(20) null 
/*������������*/,
pk_apliabcenter varchar(20) null 
/*���������������°汾*/,
pk_apliabcenter_v varchar(20) null 
/*������������*/,
ngroupmny decimal(28,8) null 
/*���ű�����˰���*/,
ngrouptaxmny decimal(28,8) null 
/*���ű��Ҽ�˰�ϼ�*/,
nglobalmny decimal(28,8) null 
/*ȫ�ֱ�����˰���*/,
nglobaltaxmny decimal(28,8) null 
/*ȫ�ֱ��Ҽ�˰�ϼ�*/,
nastorigprice decimal(28,8) null 
/*��˰����*/,
nastorigtaxprice decimal(28,8) null 
/*��˰����*/,
nastprice decimal(28,8) null 
/*������˰����*/,
nasttaxprice decimal(28,8) null 
/*���Һ�˰����*/,
frowtype integer null default 0 
/*������*/,
dbilldate char(19) null 
/*��Ʊ����*/,
pk_supplier varchar(20) null 
/*��Ӧ��*/,
cqualitylevelid varchar(20) null 
/*�����ȼ�*/,
dsourcedate char(19) null 
/*��Դ��������*/,
pk_invoice char(20) not null 
/*�ɹ���Ʊ����_����*/,
casscustid varchar(20) null 
/*�ͻ�*/,
ctaxcodeid varchar(20) null 
/*˰��*/,
ncaltaxmny decimal(28,8) null 
/*��˰���*/,
nnosubtaxrate decimal(28,8) null 
/*���ɵֿ�˰��*/,
nnosubtax decimal(28,8) null 
/*���ɵֿ�˰��*/,
ncalcostmny decimal(28,8) null 
/*�Ƴɱ����*/,
cprojecttaskid varchar(20) null 
/*��Ŀ����*/,
cadjustedinvid varchar(20) null 
/*��������Ʊ����*/,
cadjustedrowid varchar(20) null 
/*��������Ʊ������*/,
vadjedbillcode varchar(40) null 
/*��������Ʊ��*/,
ccontractid varchar(20) null 
/*��ͬ����*/,
ccontractrowid varchar(20) null 
/*��ͬ������*/,
nadjustorgprice decimal(28,8) null 
/*��������*/,
cffileid varchar(20) null 
/*������*/,
 constraint pk_po_invoice_b primary key (pk_invoice_b),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ����ƻ� */
create table po_invoice_settle (pk_invoice_settle char(20) not null 
/*����ƻ�*/,
pk_group varchar(20) null default '~' 
/*��������*/,
pk_apfinanceorg varchar(20) null default '~' 
/*������֯[OID]*/,
pk_apfinanceorg_v varchar(20) null default '~' 
/*������֯[VID]*/,
vpaydate varchar(20) null 
/*������*/,
npayrate decimal(28,8) null 
/*�������*/,
bpreflag char(1) null 
/*Ԥ����*/,
csettletype varchar(20) null default '~' 
/*���㷽ʽ*/,
cincomeperiod varchar(20) null default '~' 
/*��Ч����*/,
feffectdatebill integer null 
/*��Ч���ڶ�Ӧ����*/,
ieffectaddday integer null 
/*��Ч������������*/,
ipaymentday integer null 
/*��������*/,
icheckdata integer null 
/*�̶�������*/,
feffectmonth integer null 
/*��Ч��*/,
ieffectaddmonth integer null 
/*������*/,
csettlerowno varchar(20) null 
/*�к�*/,
vmemo varchar(181) null 
/*��ע*/,
pk_invoice char(20) not null 
/*�ɹ���Ʊ����_����*/,
npaymny decimal(28,8) null 
/*Ԥ�Ƹ�����*/,
dpaydate char(19) null 
/*Ԥ�Ƹ�������*/,
 constraint pk__invoice_settle primary key (pk_invoice_settle),
 ts char(19) null,
dr smallint null default 0
)
;

