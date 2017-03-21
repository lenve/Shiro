package org.sang;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * Created by sang on 17-3-19.
 */
public class BitAndWildPermissionResolver implements PermissionResolver {
    public Permission resolvePermission(String permissionString) {
        if (permissionString.startsWith("$")) {
            return new BitPermission(permissionString);
        }
        return new WildcardPermission(permissionString);
    }
}
