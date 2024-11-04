@AbapCatalog.sqlViewName: 'ZCCCVIEW'
@AccessControl.authorizationCheck: #NOT_REQUIRED
@ObjectModel.usageType.serviceQuality: #X
@ObjectModel.usageType.sizeCategory: #XL
@ObjectModel.usageType.dataClass: #MIXED
@EndUserText.label: 'Compatibility view for FAGLFLEXA'
@OData.publish: true
define view ZCCC as select from setnode as C
inner join  setheadert as C1 on C.setname= C1.setname  and C1.langu = 'E' and C1.setclass ='0101' 
left outer join  setheadert as C2 on C.subsetname= C2.setname and C2.langu = 'E' and C2.setclass ='0101'
left outer join csks as CM on C.subsetname = CM.khinr
inner join cskt as CD on CM.kostl = CD.kostl and CD.spras = 'E'
{
key C.setname as CC_Group1,
C1.descript as CC_Group1_Desc,
C.subsetname as CC_Group2,
C2.descript as CC_Group2_Desc,
CM.kostl as CC_NO,
CD.ltext as CC_Desc,
C.subclass as CC_OrgUnit
}
where C.subclass ='PNCC'and C.setclass ='0101'
    
