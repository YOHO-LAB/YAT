package cn.yat.ldap;

import org.springframework.ldap.core.AttributesMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

public class LdapMapUser implements AttributesMapper<LdapUser> {
    @Override
    public LdapUser mapFromAttributes(Attributes attributes)
            throws NamingException {
        Attribute attr;
        LdapUser user = new LdapUser();

        attr = attributes.get("name");
        user.setName((String) attr.get());

        attr = attributes.get("sAMAccountName");
        user.setsAMAccountName((String) attr.get());
//
//        attr = attributes.get("department");
//        user.setDepartment((String) attr.get());

		attr = attributes.get("mail");
		user.setMail((String) attr.get());

        return user;
    }
}
