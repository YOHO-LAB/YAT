package cn.yat.ldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.OrFilter;
import org.springframework.ldap.support.LdapEncoder;
import org.springframework.util.StringUtils;

import javax.naming.directory.SearchControls;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LdapAuthService {
    @Autowired
    LdapTemplate ldapTemplate;

    public boolean login(String userName, String passWord){
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("objectclass", "person")).and(new EqualsFilter("sAMAccountName", userName));
        return ldapTemplate.authenticate("OU=技术板块", filter.toString(), passWord);
    }

    public LdapUser getUser(String userName){
        AndFilter filter = new AndFilter();
        filter.and(new EqualsFilter("objectclass", "person")).and(new EqualsFilter("sAMAccountName", userName));
        LdapUser user = null;
        int SearchScope = SearchControls.SUBTREE_SCOPE;
        AttributesMapper<LdapUser> attr = new LdapMapUser();
        List<LdapUser> users=ldapTemplate.search("OU=技术板块",filter.toString(), SearchScope, attr);
        if (users.size() !=0){
            user=users.get(0);
        }
        return user;
    }

    public List<LdapUser> findUserByName(String prename){
        OrFilter filterO = new OrFilter();
        filterO.or(new MySearchFilter("sAMAccountName" , prename)).or(new MySearchFilter("name" , prename));
        AndFilter filterA = new AndFilter();
        filterA.and(new EqualsFilter("objectclass", "person")).and(filterO);
        System.out.println(filterA.encode());
        LdapUser user = null;
        int SearchScope = SearchControls.SUBTREE_SCOPE;
        AttributesMapper<LdapUser> attr = new LdapMapUser();
        List<LdapUser> users = new ArrayList<LdapUser>();
        try{
            users =ldapTemplate.search("OU=OU-NJ",filterA.toString(), SearchScope, attr);
        }catch(NullPointerException npe){
            System.out.println("某些用户不存在某些key值");
        }
        return users;
    }
}

class MySearchFilter extends EqualsFilter{

    public MySearchFilter(String attribute, String value) {
        super(attribute, value);
        // TODO Auto-generated constructor stub
    }

    protected String encodeValue(String value)
    {
        if(!StringUtils.hasText(value))
            return "*";
        String filterEncoded = LdapEncoder.filterEncode(value.trim());
        Matcher m = starReplacePattern.matcher(filterEncoded);
        StringBuffer buff = new StringBuffer(value.length() + 1);
        for(; m.find(); m.appendReplacement(buff, "*"));
        m.appendTail(buff);
        buff.append('*');
        return buff.toString();
    }

    private  static Pattern starReplacePattern = Pattern.compile("\\s+");

}
