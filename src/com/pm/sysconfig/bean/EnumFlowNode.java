package com.pm.sysconfig.bean;


/**
 * 流程节点枚举类(获取节点的id)
 * @author hly
 *
 */
public class EnumFlowNode {
        
    /**
     * 获取流程的名称
     * @param flow流程的名称
     * @param node此流程的节点名称
     * @return
     */
    public String flownode(String flow,String node){
        String flows="";
        switch (flow) {
            case "通关进口":
                    switch (node) {
                    case "接单":
                        flows="2C0422A4E717442098B88ECA3ACE7077";
                        return flows;
                    case "放行":
                        flows="F152A84287EC49E385BB763346021EC4";
                        return flows;
                    case "查验":
                        flows="FF81ADE9E1B74FCEB81F11DE88BED991";
                        return flows;
                    }
                break;
            case "通关出口":
                switch (node) {
                case "接单":
                    flows="3718CFB999084F648541C8E94EC884F6";
                    return flows;
                case "放行":
                    flows="2B25DA1092D5455B8BF99B909E2BDF18";
                    return flows;
                case "查验":
                    flows="BEB022BCE87D40BB8EF48DDB9FD559C0";
                    return flows;
                }
              break;
                
            case "物流进口":
                switch (node) {
                case "接单":
                    flows="CBEB002ECC34488188E9C0679F5AC653";
                    return flows;
                case "订舱":
                    flows="039C73AA47974B499DDF06525B305E95";
                    return flows;
                case "国内换单":
                    flows="611C6965640B41E695F9080DAACE1A16";
                    return flows;
                case "报关":
                    flows="4928CF0138A14F8CBC6DB1657447A7D5";
                    return flows;
                case "送货":
                    flows="977F9B4EBDDD495EB90F4517BC6C5BBA";
                    return flows;
                }
              break;
            case "物流出口":
                switch (node) {
                case "接单":
                    flows="22E9FAC28B28481CAB50FBB86C2AD102";
                    return flows;
                case "订舱":
                    flows="D6C04A532EB84D3EB4301A44DDCE02D6";
                    return flows;
                case "做箱/装货":
                    flows="ED1F641684BE4902B6B6A48EAA4CDCC3";
                    return flows;
                case "报关":
                    flows="712B97904EB043FA846872FA811300AD";
                    return flows;
                case "出运":
                    flows="161B4812DFA24E229D0A3E75774AB317";
                    return flows;
                }
              break;
            case "物流运输":
                switch (node) {
                case "接单":
                    flows="CAA173E0507E4B24AF7DB211F3412A03";
                    return flows;
                case "运输":
                    flows="F89AF2FE7EAF4114B7FEF3D0C7DDB367";
                    return flows;
                case "到货签收":
                    flows="5018863C3285481AB5CB14F4EC3B1AF4";
                    return flows;
                }
              break;
              
            case "外贸进口":
                switch (node) {
                case "单证制作":
                    flows="A801E587E5544D13AB9E1586F6C6ED1B";
                    return flows;
                case "信用证开证":
                    flows="ED8DEDD3CE574C778525BAAFC7CF481F";
                    return flows;
                case "收货款":
                    flows="D4D4EE5F7E1F40F0A08D47CA1604DE97";
                    return flows;
                case "付货款":
                    flows="3EEE8970CFE343029FC165F6D0106098";
                    return flows;
                case "进口到货":
                    flows="1D80EB8CAD48484F86A9260E3EED2A19";
                    return flows;
                case "进口清关":
                    flows="AC68BD3B48994422A8B86E85BB4BDE7F";
                    return flows;
                case "业务结算":
                    flows="B8745135DF4A45A994134AE1331C626E";
                    return flows;
                }
              break;
                
            case "外贸出口":
                switch (node) {
                case "单证制作":
                    flows="05CADA4CB572454CA68A07D7551C1748";
                    return flows;
                case "信用证开证":
                    flows="D3799907448F4883A13918EAF7DB2DD4";
                    return flows;
                case "收汇":
                    flows="43E2C6CA8D4544AC881CE89B54B68543";
                    return flows;
                case "出口清关":
                    flows="FFD3DC40B4CC4D19904772418BB8742F";
                    return flows;
                case "结算开票":
                    flows="5075AD51F6704CE5B280D1582629351D";
                    return flows;
                case "退税申请":
                    flows="10A487E8CE9E4B0E85F05C4027CC1BFE";
                    return flows;
                }
              break;
                
            case "仓储进库":
                switch (node) {
                case "接单登记":
                    flows="1D6EC34B15784A9085A02DE043D475C5";
                    return flows;
                case "车辆到达":
                    flows="4501BB34D724446FADF2F640260F7183";
                    return flows;
                case "入库签收":
                    flows="7EE517B81FB94804A670D64E67D53CF5";
                    return flows;
                }
            break;
                
            case "仓储出库":
                switch (node) {
                case "接单登记":
                    flows="0F9521116FFD4FB0A9EBBE5F3A24E5BD";
                    return flows;
                case "出库":
                    flows="E06DE514712A402CAF264AC78A9CEC28";
                    return flows;
                case "签收":
                    flows="039C73AA47974B499DDF06525B305E97";
                    return flows;
                }
            break;
                
        }
        
        
        return flows;
    }
  
}
