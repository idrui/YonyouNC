/* tablename: ��������ϸ�� */
create table po_arriveorder_b (pk_arriveorder_b char(20) not null 
/*��������ϸ*/,
crowno varchar(20) null 
/*�к�*/,
pk_org varchar(20) null 
/*�����֯���°汾*/,
pk_org_v varchar(20) null 
/*�����֯*/,
pk_group varchar(20) null 
/*����*/,
pk_psfinanceorg varchar(20) null 
/*���������֯���°汾*/,
pk_psfinanceorg_v varchar(20) null 
/*���������֯*/,
pk_apfinanceorg varchar(20) null 
/*Ӧ����֯���°汾*/,
pk_apfinanceorg_v varchar(20) null 
/*Ӧ����֯*/,
pk_reqstoorg varchar(20) null 
/*��������֯���°汾*/,
pk_reqstoorg_v varchar(20) null 
/*��������֯*/,
pk_reqstore varchar(20) null 
/*����ֿ�*/,
pk_material varchar(20) null 
/*����VID*/,
pk_srcmaterial varchar(20) null 
/*����OID*/,
cunitid varchar(20) null 
/*����λ*/,
castunitid varchar(20) null 
/*��λ*/,
vchangerate varchar(60) null 
/*������*/,
nnum decimal(28,8) null 
/*����������*/,
nastnum decimal(28,8) null 
/*��������*/,
nwastnum decimal(28,8) null 
/*;��������*/,
nwastastnum decimal(28,8) null 
/*;������*/,
nplannum decimal(28,8) null 
/*Ӧ��������*/,
nplanastnum decimal(28,8) null 
/*Ӧ������*/,
bpresent char(1) null 
/*��Ʒ*/,
npresentnum decimal(28,8) null 
/*��Ʒ������*/,
npresentastnum decimal(28,8) null 
/*��Ʒ����*/,
corigcurrencyid varchar(20) null 
/*ԭ�ұ���*/,
norigtaxprice decimal(28,8) null 
/*��ԭ�Һ�˰����*/,
norigprice decimal(28,8) null 
/*��ԭ����˰����*/,
norigtaxmny decimal(28,8) null 
/*ԭ�Һ�˰���*/,
norigmny decimal(28,8) null 
/*ԭ����˰���*/,
ccurrencyid varchar(20) null 
/*���ұ���*/,
ntaxprice decimal(28,8) null 
/*�����Һ�˰����*/,
nprice decimal(28,8) null 
/*��������˰����*/,
ntaxmny decimal(28,8) null 
/*���Һ�˰���*/,
nmny decimal(28,8) null 
/*������˰���*/,
ntax decimal(28,8) null 
/*˰��*/,
nexchangerate decimal(28,8) null 
/*�۱�����*/,
dplanreceivedate varchar(19) null 
/*�ƻ���������*/,
dproducedate varchar(19) null 
/*��������*/,
ivalidday integer null 
/*����������*/,
dinvaliddate varchar(19) null 
/*ʧЧ����*/,
bbackreforder char(1) null 
/*�˻�����ԭ��������*/,
creporterid varchar(20) null 
/*������*/,
dreportdate varchar(19) null 
/*��������*/,
nelignum decimal(28,8) null 
/*�ϸ�������*/,
nnotelignum decimal(28,8) null 
/*���ϸ�������*/,
bletgostate char(1) null 
/*�Ƿ��������״̬*/,
pk_passbill varchar(20) null 
/*�������е�����*/,
vpassbillcode varchar(40) null 
/*�������е���*/,
pk_passbill_b varchar(20) null 
/*�������е���������*/,
cpassbollrowno varchar(20) null 
/*�������е��к�*/,
naccumletgonum decimal(28,8) null 
/*�ۼƽ�������������*/,
naccumletgoinnum decimal(28,8) null 
/*�ۼƽ��������������???*/,
naccumchecknum decimal(28,8) null 
/*�ۼƱ���������*/,
naccumstorenum decimal(28,8) null 
/*�ۼ����������*/,
naccumreplnum decimal(28,8) null 
/*�ۼƲ���������*/,
pk_receivestore varchar(20) null 
/*�ջ��ֿ�*/,
pk_rack varchar(20) null 
/*��λ*/,
pk_batchcode varchar(20) null 
/*���κ�����*/,
vmemob varchar(181) null 
/*��ע*/,
vbackreasonb varchar(20) null 
/*�˻�����*/,
cprojectid varchar(20) null 
/*��Ŀ*/,
cproductorid varchar(20) null 
/*��������*/,
bfaflag char(1) null 
/*�������ʲ�Ƭ*/,
bpresentsource char(1) null 
/*��Դ�������Ƿ���Ʒ*/,
pk_order varchar(20) null 
/*�ɹ�����*/,
pk_order_b varchar(20) null 
/*�ɹ�������ϸ*/,
pk_order_bb1 varchar(20) null 
/*���������ƻ�*/,
csourcetypecode varchar(20) null 
/*��Դ��������*/,
vsourcecode varchar(40) null 
/*��Դ���ݺ�*/,
csourceid varchar(20) null 
/*��Դ����*/,
csourcebid varchar(20) null 
/*��Դ������ϸ*/,
vsourcerowno varchar(20) null 
/*��Դ�����к�*/,
vsourcetrantype varchar(20) null 
/*��Դ��������*/,
cfirsttypecode varchar(20) null 
/*Դͷ��������*/,
vfirstcode varchar(40) null 
/*Դͷ���ݺ�*/,
cfirstid varchar(20) null 
/*Դͷ����*/,
cfirstbid varchar(20) null 
/*Դͷ������ϸ*/,
vfirstrowno varchar(20) null 
/*Դͷ�����к�*/,
vfirsttrantype varchar(20) null 
/*Դͷ��������*/,
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
vbatchcode varchar(40) null 
/*���κű���*/,
dbilldate char(19) null 
/*��������*/,
ntaxrate decimal(28,8) null 
/*˰��*/,
btransasset char(1) null default 'N' 
/*������ת�̵�*/,
pk_arriveorder char(20) not null 
/*����������_����*/,
cqualitylevelid varchar(20) null 
/*�����ȼ�*/,
casscustid varchar(20) null 
/*�ͻ�*/,
cprojecttaskid varchar(20) null 
/*��Ŀ����*/,
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
csourcearriveid char(20) null 
/*��Դ������*/,
csourcearrivebid char(20) null 
/*��Դ��������ϸ*/,
naccumbacknum decimal(28,8) null 
/*�ۼ��˻�������*/,
ftaxtypeflag integer null 
/*��˰���*/,
pk_apliabcenter varchar(20) null 
/*���������������°汾*/,
pk_apliabcenter_v varchar(20) null 
/*������������*/,
pk_arrliabcenter varchar(20) null 
/*�ջ������������°汾*/,
pk_arrliabcenter_v varchar(20) null 
/*�ջ���������*/,
fproductclass integer null 
/*��Ʒ����*/,
cffileid varchar(20) null 
/*������*/,
bc_vvendbatchcode varchar(50) null 
/*��Ӧ�����κ�*/,
 constraint pk_o_arriveorder_b primary key (pk_arriveorder_b),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ���������� */
create table po_arriveorder (pk_arriveorder char(20) not null 
/*������*/,
pk_group varchar(20) null 
/*����*/,
pk_org varchar(20) null 
/*�����֯���°汾*/,
pk_org_v varchar(20) null 
/*�����֯*/,
pk_purchaseorg varchar(20) null 
/*�ɹ���֯���°汾*/,
pk_purchaseorg_v varchar(20) null 
/*�ɹ���֯*/,
vbillcode varchar(40) null 
/*��������*/,
dbilldate char(19) null 
/*��������*/,
pk_freecust varchar(20) null 
/*ɢ��*/,
pk_supplier varchar(20) null 
/*��Ӧ��*/,
pk_busitype varchar(20) null 
/*ҵ������*/,
vtrantypecode varchar(20) null 
/*�������ͱ���*/,
pk_transporttype varchar(20) null 
/*���䷽ʽ*/,
pk_receivepsndoc varchar(20) null 
/*�ջ���*/,
pk_dept varchar(20) null 
/*�ɹ��������°汾*/,
pk_dept_v varchar(20) null 
/*�ɹ�����*/,
pk_pupsndoc varchar(20) null 
/*�ɹ�Ա*/,
fbillstatus integer null default 0 
/*����״̬*/,
vmemo varchar(181) null 
/*��ע*/,
bisback char(1) null 
/*�˻�*/,
iprintcount integer null 
/*��ӡ����*/,
vbackreason varchar(20) null 
/*�˻�����*/,
ntotalastnum decimal(28,8) null 
/*������*/,
ntotaltaxmny decimal(28,8) null 
/*���Ҽ�˰�ϼ�*/,
creationtime char(19) null 
/*����ʱ��*/,
billmaker varchar(20) null 
/*�Ƶ���*/,
dmakedate char(19) null 
/*�Ƶ�����*/,
taudittime varchar(19) null 
/*��������*/,
approver varchar(20) null 
/*������*/,
modifiedtime char(19) null 
/*����޸�ʱ��*/,
modifier varchar(20) null 
/*����޸���*/,
creator varchar(20) null 
/*������*/,
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
ctrantypeid varchar(20) null 
/*��������*/,
bpublish char(1) null 
/*����*/,
pk_pubpsn varchar(20) null 
/*������*/,
tpubtime varchar(19) null 
/*����ʱ��*/,
irespstatus integer null 
/*��Ӧ״̬*/,
pk_resppsn varchar(20) null 
/*��Ӧ��*/,
tresptime varchar(19) null 
/*��Ӧʱ��*/,
vsupbackreason varchar(181) null 
/*��Ӧ���˻�˵��*/,
 constraint pk_po_arriveorder primary key (pk_arriveorder),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �������ʼ���ϸ�� */
create table po_arriveorder_bb (pk_arriveorder_bb char(20) not null 
/*�������ʼ���ϸ*/,
pk_arriveorder char(20) null 
/*����������*/,
pk_group varchar(20) null 
/*��������*/,
bcanstore char(1) null 
/*�Ƿ�����*/,
nnum decimal(28,8) null 
/*������*/,
nastnum decimal(28,8) null 
/*����*/,
pk_inbatchcode varchar(20) null 
/*������κ�����*/,
vinbatchcode varchar(40) null 
/*������κű���*/,
naccumstorenum decimal(28,8) null 
/*�ۼ��������*/,
beligible char(1) null 
/*�Ƿ�ϸ�*/,
bchanged char(1) null 
/*�Ƿ����*/,
pk_chgmaterial varchar(20) null 
/*��������VID*/,
pk_chgsrcmaterial varchar(20) null 
/*��������OID*/,
cunitid varchar(20) null 
/*����λ*/,
castunitid varchar(20) null 
/*��λ*/,
vchangerate varchar(60) null 
/*������*/,
vchgfree1 varchar(100) null 
/*�������ɸ�������1*/,
vchgfree2 varchar(100) null 
/*�������ɸ�������2*/,
vchgfree3 varchar(100) null 
/*�������ɸ�������3*/,
vchgfree4 varchar(100) null 
/*�������ɸ�������4*/,
vchgfree5 varchar(100) null 
/*�������ɸ�������5*/,
vchgfree6 varchar(100) null 
/*�������ɸ�������6*/,
vchgfree7 varchar(100) null 
/*�������ɸ�������7*/,
vchgfree8 varchar(100) null 
/*�������ɸ�������8*/,
vchgfree9 varchar(100) null 
/*�������ɸ�������9*/,
vchgfree10 varchar(100) null 
/*�������ɸ�������10*/,
pk_qcreport char(20) null 
/*�ʼ챨������*/,
pk_arriveorder_b char(20) not null 
/*��������ϸ��_����*/,
 constraint pk__arriveorder_bb primary key (pk_arriveorder_bb),
 ts char(19) null,
dr smallint null default 0
)
;

