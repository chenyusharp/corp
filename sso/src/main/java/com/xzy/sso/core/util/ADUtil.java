package com.xzy.sso.core.util;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

/**
 * Date: 2023/8/6
 * <p>
 * Description:
 *
 * @author xiazhenyu
 */
public class ADUtil {


    public static void main(String[] args) {

        Properties env = new Properties();
        String adminName = "administrator@eptison.com";
        String adminPassword = "d*hqF0UX*N";
        String ldapURL = "ldap://192.168.3.4:389";

        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");//LDAP访问安全级别："none","simple","strong"
        env.put(Context.SECURITY_PRINCIPAL, adminName);// AD User
        env.put(Context.SECURITY_CREDENTIALS, adminPassword);// AD Password
        env.put(Context.PROVIDER_URL, ldapURL);// LDAP工厂类

        try {
            LdapContext ctx = new InitialLdapContext(env, null);
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String searchFilter = "(objectClass=user)";
            String searchBase = "DC=Eptison,DC=com";

//            String returnedAtts[] = { "url", "employeeID",  "mail",
//                    "name", "userPrincipalName", "physicalDeliveryOfficeName",
//                    "departmentNumber", "telephoneNumber", "homePhone",
//                    "mobile", "department", "sAMAccountName", "whenChanged"}; // 定制返回属性
//            searchControls.setReturningAttributes(returnedAtts);

            final NamingEnumeration<SearchResult> searchAnswer = ctx.search(searchBase, searchFilter, searchControls);

            while (searchAnswer.hasMoreElements()) {
                final SearchResult searchResult = searchAnswer.next();
//                if (searchResult.getName().contains("夏振宇")){
                    System.out.println("search name:" + searchResult.getName());
//                }



            }
            ctx.close();


        } catch (NamingException e) {
            e.printStackTrace();
            System.out.println("Problem searching directory:" + e);
        }


    }

}