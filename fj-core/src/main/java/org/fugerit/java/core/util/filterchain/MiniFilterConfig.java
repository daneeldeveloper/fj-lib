package org.fugerit.java.core.util.filterchain;

import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;

import org.fugerit.java.core.cfg.xml.GenericListCatalogConfig;
import org.fugerit.java.core.lang.helpers.ClassHelper;
import org.fugerit.java.core.xml.dom.DOMIO;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MiniFilterConfig extends GenericListCatalogConfig<MiniFilterConfigEntry> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 286844409632297876L;

	public static MiniFilterConfig loadConfig( InputStream is, MiniFilterConfig config ) throws Exception {
		Document doc = DOMIO.loadDOMDoc( is );
		Element root = doc.getDocumentElement();
		config.configure( root );
		return config;
	}
	
	public MiniFilterChain getChain( String id ) throws Exception {
		Collection<MiniFilterConfigEntry> c = this.getDataList( id );
		MiniFilterChain chain = new MiniFilterChain();
		Iterator<MiniFilterConfigEntry> it = c.iterator();
		while ( it.hasNext() ) {
			MiniFilterConfigEntry entry = it.next();
			String type = entry.getType();
			MiniFilter filter = (MiniFilter) ClassHelper.newInstance( type );
			filter.config( entry.getKey() , entry.getDescription(), entry.getDefaultBehaviourInt() );
			chain.getFilterChain().add( filter );
			logger.info( "adding filter to chain : "+filter );
		} 
		return chain;
	}
	
}
