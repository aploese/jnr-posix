package org.jruby.ext.posix;

import com.sun.jna.FromNativeConverter;
import com.sun.jna.ToNativeConverter;
import com.sun.jna.TypeMapper;
import org.jruby.ext.posix.util.Platform;

class POSIXTypeMapper implements TypeMapper {
    public static final TypeMapper INSTANCE = new POSIXTypeMapper();
    
    private POSIXTypeMapper() {}
    
    public FromNativeConverter getFromNativeConverter(Class klazz) {
        if (Passwd.class.isAssignableFrom(klazz)) {
            if (Platform.IS_MAC) {
                return MacOSPOSIX.PASSWD;
            } else if (Platform.IS_LINUX) {
                return LinuxPOSIX.PASSWD;
            } else if (Platform.IS_SOLARIS) {
                return SolarisPOSIX.PASSWD;
            }
            return null;
        } else if (Group.class.isAssignableFrom(klazz)) {
            return BaseNativePOSIX.GROUP;
        }
        
        assert false : "No type converter for " + klazz;
        
        return null;
    }
    
    public ToNativeConverter getToNativeConverter(Class klazz) {
        assert false : "No type converter for " + klazz;
        
        return null;
    }
}
