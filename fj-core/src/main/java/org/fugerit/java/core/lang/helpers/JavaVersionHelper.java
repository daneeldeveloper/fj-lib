package org.fugerit.java.core.lang.helpers;

import org.fugerit.java.core.cfg.ConfigRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaVersionHelper {

	private JavaVersionHelper() {}
	
	public static final int MAJOR_VERSION_JAVA_8 = 8;
	
	public static final int MAJOR_VERSION_JAVA_9 = 9;
	
	public static final int MAJOR_VERSION_JAVA_10 = 10;
	
	public static final int MAJOR_VERSION_JAVA_11 = 11;
	
	public static final int MAJOR_VERSION_JAVA_17 = 17;
	
	public static final int MAJOR_VERSION_FJ_CORE_REF = MAJOR_VERSION_JAVA_8;	// current reference version for fj-core library!
	
	private static final Logger logger = LoggerFactory.getLogger( JavaVersionHelper.class );
	
	private static final String SYS_PROP = "java.version";
	
	private static final String REMOVE_1_X = "1.";
	
	public static final int UNDEFINED = -1;
	
	public static int parseUniversalJavaMajorVersion() {
		int res = UNDEFINED;
		String javaVersion = System.getProperty( SYS_PROP );
		if ( StringUtils.isEmpty( javaVersion ) ) {
			throw new ConfigRuntimeException( "null property "+SYS_PROP );
		} else {
			logger.info( "{} -> {}", SYS_PROP, javaVersion );
			if ( javaVersion.startsWith( REMOVE_1_X ) ) {
				javaVersion = javaVersion.substring( REMOVE_1_X.length() );
			}
			int index = javaVersion.indexOf( '.' );
			if ( index != -1 ) {
				res = Integer.parseInt( javaVersion.substring( 0, index ) );
				if ( res < MAJOR_VERSION_FJ_CORE_REF ) {
					logger.info( "major version found : '{}' is lower than minimum required -> '{}'", res, MAJOR_VERSION_FJ_CORE_REF );
				}
			}
			logger.info( "parseUniversalJavaMajorVersion -> '{}'", res );
		}
		return res;
	}
	
}
