package test.org.fugerit.java.core.xml.dom;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.fugerit.java.core.lang.helpers.ClassHelper;
import org.fugerit.java.core.xml.XMLException;
import org.fugerit.java.core.xml.dom.DOMIO;
import org.fugerit.java.core.xml.dom.SearchDOM;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestSearchDOM {

	private boolean worker( SearchDOM search ) throws XMLException, IOException {
		boolean ok = false;
		try ( InputStream is = ClassHelper.loadFromDefaultClassLoader( "core/xml/dtd/test-content-handler.xml" ) ) {
			Document doc = DOMIO.loadDOMDoc( is );
			Element root = doc.getDocumentElement();
			List<Element> list = search.findAllTags( root, "info" );
			for ( Element current : list ) {
				log.info( "text : {}", search.findText( current ) );
			}
			Element barcode = search.findTag( root, "barcode" );
			log.info( "findTag : {}", barcode );
			log.info( "text 1 : {}", search.findText( barcode ) );
			log.info( "text 2 : {}", search.findText( root ) );
			ok = true;
		}
		return ok;
	}
	
	@Test
	public void testSearchDOM() throws XMLException, IOException {
		boolean ok = this.worker( SearchDOM.newInstance() );
		Assert.assertTrue( ok );
	}
	
	@Test
	public void testSearchDOMAlt1() throws XMLException, IOException {
		boolean ok = this.worker( SearchDOM.newInstance( true, true ) );
		Assert.assertTrue( ok );
	}
	
	@Test
	public void testSearchDOMAlt2() throws XMLException, IOException {
		boolean ok = this.worker( SearchDOM.newInstance( false, false ) );
		Assert.assertTrue( ok );
	}
	
}

