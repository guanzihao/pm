package com.pm.right.busin.impl;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.pm.core.busin.BusinApi;
import com.pm.core.util.StringUtil;
import com.pm.right.bean.Authority;
import com.pm.right.busin.RightBusin;

/**
 * 初始化系统权限，加载菜单权限到数据库
 * 
 * @author youliang.fang
 */

@Component
public class InitRightListener implements InitializingBean, ApplicationListener<ContextRefreshedEvent> {
    private static Logger LOGGER = Logger.getLogger(InitRightListener.class);
    private static final String FILEPATH = "/conf/menu.xml";

    @Autowired
    private BusinApi businApi;

    @Autowired
    private RightBusin rightBusin;

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent evt) {
        if (evt.getApplicationContext().getParent() == null) {
            LOGGER.info("read menu.xml start...");
            long a = System.currentTimeMillis();
            readMenuXml();
            long b = System.currentTimeMillis();
            LOGGER.info("read menu.xml end, exce time : " + (b - a) + " ms...");
        }
    }

    /**
     * 解析XML
     */
    private void readMenuXml() {
        try {
            InputSource source = new InputSource(InitRightListener.class.getResourceAsStream(FILEPATH));
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(source);
            NodeList nodeList = doc.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                NodeList childNodeList = node.getChildNodes();
                for (int j = 0; j < childNodeList.getLength(); j++) {
                    readNode(childNodeList.item(j), null);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
    }

    /**
     * 读取NODE节点
     * 
     * @param node 节点
     * @param authority 父级权限
     */
    private void readNode(Node node, Authority authority) {
        if (node != null) {
            Authority auth = getAuthority(node, authority);
            if (auth != null) {
                NodeList nodeList = node.getChildNodes();
                for (int i = 0; i < nodeList.getLength(); i++) {
                    readNode(nodeList.item(i), auth);
                }
            }
        }
    }

    /**
     * 获得菜单权限
     * 
     * @param namedNodeMap
     * @return
     */
    private Authority getAuthority(Node node, Authority authority) {
        if (node != null) {
            NamedNodeMap nodeMap = node.getAttributes();
            if (nodeMap != null && nodeMap.getLength() > 0) {
                String authCode = getNodeValue(nodeMap, "code");
                String remove = getNodeValue(nodeMap, "remove");
                if (!StringUtil.isEmpty(authCode) && !StringUtil.isEmpty(remove)) {

                    //没有移除
                    if (remove.equals("0")) {
                        LOGGER.info("add or update : " + authCode);

                        Authority auth = rightBusin.getAuthorityAuthCode(authCode);
                        if (auth == null) {
                            auth = new Authority();
                        }
                        auth.setAuthCode(authCode);
                        auth.setAuthName(getNodeValue(nodeMap, "name"));
                        auth.setAuthIcon(getNodeValue(nodeMap, "icon"));
                        auth.setAuthIndex(Integer.valueOf(getNodeValue(nodeMap, "index")));
                        auth.setAuthUrl(getNodeValue(nodeMap, "url"));
                        auth.setAuthType(Integer.valueOf(getNodeValue(nodeMap, "type")));
                        if(!StringUtil.isEmpty(getNodeValue(nodeMap, "orgtype"))){
                            auth.setOrgtype(Integer.valueOf(getNodeValue(nodeMap, "orgtype")));
                        }
                        
                        auth.setParent(authority);
                        businApi.save(auth);
                        return auth;
                    }

                    //移除
                    else {
                        LOGGER.info("remove : " + authCode);
                        rightBusin.removeAuthority(rightBusin.getAuthorityAuthCode(authCode));
                    }
                }
            }
        }
        return null;
    }

    private String getNodeValue(NamedNodeMap namedNodeMap, String name) {
        for (int j = 0; j < namedNodeMap.getLength(); j++) {
            Node node = namedNodeMap.item(j);
            if (node.getNodeName().equals(name)) {
                return node.getNodeValue();
            }
        }
        return null;
    }
}
