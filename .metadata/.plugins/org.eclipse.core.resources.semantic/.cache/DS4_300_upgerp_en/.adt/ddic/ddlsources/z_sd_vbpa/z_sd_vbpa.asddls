@AbapCatalog.sqlViewName: 'ZSDVBPAVIEW'
@AbapCatalog.compiler.compareFilter: true
@AbapCatalog.preserveKey: true
@AccessControl.authorizationCheck: #NOT_REQUIRED
@EndUserText.label: 'Sales Document Partner'
@Metadata.ignorePropagatedAnnotations: true
@OData.publish: true
define view Z_SD_VBPA as select from vbpa as BP
//inner join tpart as BT on BT.parvw = BP.parvw and spras ='E'
//inner join vbak as SO on SO.vbeln = BP.vbeln
inner join vbrp as BD on BD.aubel = BP.vbeln
inner join zsaleview as Z on Z.bill_document = BD.vbeln
{
 //   key BP.vbeln as SD_Doc,
    key BD.vbeln as Billing,
    concat(
       concat(substring(BD.fkdat_ana, 7, 2), '.'), 
       concat(substring(BD.fkdat_ana, 5, 2), concat('.', substring(BD.fkdat_ana, 1, 4)))
   ) as Billing_Date,
    BP.parvw as Partner_Type,
    BP.assigned_bp as Business_partner
  // BT.vtext as P_Type_desc
  
} 
where BD.vkorg_auft = '1000'
and (BP.parvw = 'RE' or BP.parvw = 'WE' or BP.parvw = 'ZC')
//and BD.fkdat_ana >= DATS_ADD_DAYS(cast($session.system_date as abap.dats), - 30, 'UNCHANGED')
//and BD.fkdat_ana <= DATS_ADD_DAYS(cast($session.system_date as abap.dats), 0, 'UNCHANGED')
//and BD.fkdat_ana >= '20231001' and BD.fkdat_ana <= DATS_ADD_DAYS(cast($session.system_date as abap.dats), 0, 'UNCHANGED')
 //and (SO.audat >= DATS_ADD_DAYS(cast($session.system_date as abap.dats), - 60, 'UNCHANGED') and 
//SO.audat<= DATS_ADD_DAYS(cast($session.system_date as abap.dats), 0, 'UNCHANGED'))
group by  BD.vbeln,BP.parvw,BP.assigned_bp,fkdat_ana

