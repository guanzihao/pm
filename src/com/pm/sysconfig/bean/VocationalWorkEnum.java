package com.pm.sysconfig.bean;

public enum VocationalWorkEnum {
    BG("9b5f9ecb-e544-4724-ad56-b5fd5d2acc23","报关"),
    WL("4fb1d405-1985-4159-904b-2c78f3730cf7","物流"),
    CC("c2a9dab0-c684-4c1e-a40c-c901453265c7","仓储"),
    WM("8ea56aad-ecfa-4e3a-bc67-5ee6f9c6f389","外贸"),
    YW("456789098765456789","业务流程"),
    BG_IMCC("7994F33B-798E-4495-885B-FB8F2F39B809","进口报关","BgIc"),
    BG_EXCC("AA6F9D76-D751-4EBC-BA6A-2E4872FCA36C","出口报关","BgEc"),
    WM_IMCC("8EF423F7-8AF0-43F2-B03E-2F6E42276FE5","外贸进口","WmIc"),
    WM_EXCC("E8EA1196-D803-4040-B309-C65965A4F142","外贸出口","WmEc"),
    WL_IMCC("B04FA3B7-E0AE-4559-92C3-F6A65EC40BC7","进口物流","WlIc"),
    WL_EXCC("0320ACB2-6E61-4E14-BE1A-DB8F116F9DFE","出口物流","WlEc"),
    WL_TRANSPORT("CF734401-5F26-4A3B-82B3-1B7A6DAFE35D","物流运输","Trop"),
    CC_IMCC("D8994C5C-533C-42E2-9BFD-46E5639844D2","仓储进库","CcIc"),
    CC_EXCC("D33A899D-C46B-438A-90BC-C2F395E8AFCE","仓储出库","CcEc"),
    WLZFFS("8e2a392d-979b-4845-ae5c-0ec9fc852260","物流支付方式"),
    WLTDLX("58b0c9f1-6eaa-4c89-9c7f-32e1dc64a0dc","物流提单类型"),
    BGGQ("bf0dc54b-b98a-40b5-93db-0ddadd997733","报关关区");
    
    

    /**
     * 类型的Id
     */
    private String id;
    
    /**
     * 名称
     */
    private String name;
    
    /**
     *  跳转的url
     */
    private String url;
    
    
    
    private VocationalWorkEnum(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    private VocationalWorkEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static VocationalWorkEnum getName(String index) {
        for (VocationalWorkEnum workEnum : values()) {
            if (index.equals(workEnum.getId())) {
                return workEnum;
            }
        }
        return null;
    }
    
}
