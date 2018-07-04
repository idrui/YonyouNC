/* indexcode: i_pu_23_b_dtorg */
create  index i_pu_23_b_dtorg on po_arriveorder_b (dbilldate,
pk_org)
go

/* indexcode: i_pu_23_b_src */
create  index i_pu_23_b_src on po_arriveorder_b (csourceid)
go

/* indexcode: i_pu_23_b_order */
create  index i_pu_23_b_order on po_arriveorder_b (pk_order_b)
go

/* indexcode: i_pu_23_b_fk */
create  index i_pu_23_b_fk on po_arriveorder_b (pk_arriveorder)
go

/* indexcode: i_pu_23_b_srcb */
create  index i_pu_23_b_srcb on po_arriveorder_b (csourcebid)
go

/* indexcode: i_pu_23_dtorg */
create  index i_pu_23_dtorg on po_arriveorder (dbilldate,
pk_org)
go

/* indexcode: i_pu_23_code */
create  index i_pu_23_code on po_arriveorder (vbillcode)
go

/* indexcode: i_pu_23_bb_fk */
create  index i_pu_23_bb_fk on po_arriveorder_bb (pk_arriveorder)
go

