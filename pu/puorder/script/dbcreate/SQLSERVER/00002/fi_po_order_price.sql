/* indexcode: i_pu_21p_b_fk */
create  index i_pu_21p_b_fk on po_order_price_b (pk_order_price)
go

/* indexcode: i_pu_21p_msoc */
create  index i_pu_21p_msoc on po_order_price (pk_srcmaterial,
pk_supplier,
pk_org,
corigcurrencyid)
go

/* indexcode: i_pu_21p_mf */
create  index i_pu_21p_mf on po_order_price (pk_srcmaterial,
pk_psfinanceorg)
go

/* indexcode: i_pu_21p_date */
create  index i_pu_21p_date on po_order_price (dbilldate)
go

/* indexcode: i_pu_21p_mm */
create  index i_pu_21p_mm on po_order_price (pk_srcmaterial,
pk_arrvstoorg)
go

