/* tablename: ���㵥��ϸ */
create table po_settlebill_b (pk_settlebill_b char(20) not null 
/*������ϸ*/,
crowno varchar2(20) null 
/*�к�*/,
pk_group varchar2(20) null 
/*��������*/,
pk_org varchar2(20) null 
/*������֯*/,
pk_org_v varchar2(20) null 
/*������֯�汾*/,
frowtype integer null 
/*������*/,
vinvoicetrantype varchar2(20) null 
/*��Ʊ��������*/,
pk_invoice varchar2(20) null 
/*��Ʊ*/,
pk_invoice_b varchar2(20) null 
/*��Ʊ��*/,
vinvoicecode varchar2(40) null 
/*��Ʊ��*/,
vstockbilltype varchar2(20) null 
/*��浥������*/,
vstocktrantype varchar2(20) null 
/*��ⵥ��������*/,
pk_stock varchar2(20) null 
/*��浥��*/,
pk_stock_b varchar2(20) null 
/*��浥����*/,
vstockcode varchar2(40) null 
/*��ⵥ��/���Ļ���*/,
pk_stockorder varchar2(20) null 
/*��浥�ݵĶ���ͷ*/,
pk_stockorder_b varchar2(20) null 
/*��浥�ݵĶ�����*/,
pk_invoiceorder varchar2(20) null 
/*��Ʊ�Ķ���ͷ*/,
pk_invoiceorder_b varchar2(20) null 
/*��Ʊ�Ķ�����*/,
pk_dtransaleid varchar2(20) null 
/*��Ӧ��ֱ�����۶���*/,
pk_dtransalebid varchar2(20) null 
/*��Ӧ��ֱ�����۶�����*/,
pk_rushstock varchar2(20) null 
/*�Գ�Ŀ�浥��*/,
pk_rushstock_b varchar2(20) null 
/*�Գ�Ŀ�浥����*/,
pk_rushinvoice varchar2(20) null 
/*�Գ�ķ�Ʊ*/,
pk_rushinvoice_b varchar2(20) null 
/*�Գ�ķ�Ʊ��*/,
pk_srcmaterial varchar2(20) null 
/*����*/,
pk_material varchar2(20) null 
/*���ϰ汾*/,
cunitid varchar2(20) null 
/*����λ*/,
nsettlenum number(28,8) null 
/*��������*/,
nprice number(28,8) null 
/*����*/,
nmoney number(28,8) null 
/*���*/,
ngoodsprice number(28,8) null 
/*������㵥��*/,
ngoodsmoney number(28,8) null 
/*���������*/,
bwashest char(1) null 
/*�Ƿ�����ݹ���־*/,
nclashestmoney number(28,8) null 
/*�����ݹ����*/,
noppoconfmmoney number(28,8) null 
/*��Ӧ��ȷ�ϳɱ�*/,
noppoconfmapmny number(28,8) null 
/*��Ӧ��ȷ��Ӧ��ԭ�Ҽ�˰�ϼ�*/,
nreasonalwastnum number(28,8) null 
/*�����������*/,
nreasonalwastprice number(28,8) null 
/*������ĵ���*/,
nreasonalwastmny number(28,8) null 
/*������Ľ��*/,
pk_arrstockorg varchar2(20) null 
/*�ջ������֯*/,
pk_arrstockorg_v varchar2(20) null 
/*�ջ������֯�汾*/,
pk_costregion varchar2(20) null 
/*�ɱ���*/,
ncostfactor1 number(28,8) null 
/*���ҳɱ�Ҫ��1*/,
ncostfactor2 number(28,8) null 
/*���ҳɱ�Ҫ��2*/,
ncostfactor3 number(28,8) null 
/*���ҳɱ�Ҫ��3*/,
ncostfactor4 number(28,8) null 
/*���ҳɱ�Ҫ��4*/,
ncostfactor5 number(28,8) null 
/*���ҳɱ�Ҫ��5*/,
ncostfactor6 number(28,8) null 
/*���ҳɱ�Ҫ��6*/,
ncostfactor7 number(28,8) null 
/*���ҳɱ�Ҫ��7*/,
ncostfactor8 number(28,8) null 
/*���ҳɱ�Ҫ��8*/,
ndiscount number(28,8) null 
/*�ۿ�*/,
pk_supplier varchar2(20) null 
/*��Ӧ��*/,
pk_dept varchar2(20) null 
/*�ɹ�����ԭʼ��Ϣ*/,
pk_dept_v varchar2(20) null 
/*�ɹ�����*/,
pk_psndoc varchar2(20) null 
/*ҵ��Ա*/,
pk_purchasein varchar2(20) null 
/*�ɹ����ͷID*/,
pk_purchasein_b varchar2(20) null 
/*�ɹ������ID*/,
pk_subcontract varchar2(20) null 
/*ί�����ͷID*/,
pk_subcontract_b varchar2(20) null 
/*ί�������ID*/,
pk_voiconsume varchar2(20) null 
/*���Ļ��ܱ�ͷID*/,
pk_voiconsume_b varchar2(20) null 
/*���Ļ��ܱ���ID*/,
pk_initialest varchar2(20) null 
/*�ڳ��ݹ���ͷID*/,
pk_initialest_b varchar2(20) null 
/*�ڳ��ݹ�����ID*/,
pk_transin varchar2(20) null 
/*�������ͷID*/,
pk_transin_b varchar2(20) null 
/*���������ID*/,
pk_generalin varchar2(20) null 
/*�������ͷID*/,
pk_generalin_b varchar2(20) null 
/*���������ID*/,
pk_settlebill char(20) not null 
/*���㵥ͷ_����*/,
nadjustmny number(28,8) null 
/*����������*/,
stockbilldate char(19) null 
/*�������*/,
invoicebilldate char(19) null 
/*��Ʊ����*/,
vitctcode varchar2(40) null 
/*���ں�ͬ��*/,
 constraint pk_po_settlebill_b primary key (pk_settlebill_b),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ���㵥ͷ */
create table po_settlebill (pk_settlebill char(20) not null 
/*���㵥*/,
pk_group varchar2(20) null 
/*��������*/,
pk_org varchar2(20) null 
/*������֯*/,
pk_org_v varchar2(20) null 
/*������֯�汾*/,
ccurrencyid varchar2(20) null 
/*���ұ���*/,
bvirtualsettle char(1) default 'N' null 
/*�Ƿ����ⷢƱ�Ľ���*/,
vbillcode varchar2(40) null 
/*���㵥��*/,
dbilldate char(19) null 
/*��������*/,
btoia char(1) default 'N' null 
/*�Ѵ����*/,
iprintcount integer default 0 null 
/*��ӡ����*/,
billmaker varchar2(20) null 
/*�Ƶ���*/,
dmakedate char(19) null 
/*�Ƶ�����*/,
creationtime char(19) null 
/*����ʱ��*/,
creator varchar2(20) null 
/*������*/,
fsettletype integer null 
/*���㵥������*/,
 constraint pk_po_settlebill primary key (pk_settlebill),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

/* tablename: ������÷�̯��ϸ */
create table po_settle_feeallot (pk_settle_feeallot char(20) not null 
/*���÷�̯��ϸ*/,
pk_settlebill char(20) null 
/*���㵥ͷ*/,
pk_srcmaterial varchar2(20) null 
/*��������*/,
pk_material varchar2(20) null 
/*�������ϰ汾*/,
vallotbilltype varchar2(4) null 
/*���÷�̯���ݵĵ�������*/,
pk_allotbillid varchar2(20) null 
/*��̯�ĵ���ID*/,
pk_allotbillbid varchar2(20) null 
/*��̯�ĵ�����ID*/,
nallotmoney number(28,8) null 
/*���÷�̯���*/,
bestfirstsettle char(1) null 
/*�Ƿ��ݹ��������ϵĵ�һ�ν���*/,
ntimesafterfirst integer null 
/*��һ�ν���ĺ����ۼƷ��ý������*/,
pk_oppofee_b char(20) null 
/*��Ӧ�ķ������ڵĽ�����ID*/,
pk_supplier varchar2(20) null 
/*��Ӧ��*/,
pk_settlebill_b char(20) not null 
/*���㵥��ϸ_����*/,
nallotbillnum number(28,8) null 
/*��̯����������*/,
nallotbillmny number(28,8) null 
/*��̯�����ݽ��*/,
 constraint pk_settle_feeallot primary key (pk_settle_feeallot),
 ts char(19) default to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'),
dr number(10) default 0
)
/

