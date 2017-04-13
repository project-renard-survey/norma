package org.xmlcml.norma.input.pdf;

import java.io.File;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.xmlcml.cproject.files.CTree;
import org.xmlcml.norma.grobid.runner.GrobidOption;
import org.xmlcml.norma.grobid.runner.GrobidRunner;

public class PDF2TEIConverter {

	public PDF2TEIConverter() {
		
	}
	
	public File convertFulltextPDFToTEI(File pdfFile) throws Exception {
		
		File cTreeDirectory = pdfFile.getParentFile();
		GrobidRunner grobidRunner = new GrobidRunner();
		grobidRunner.setInputDirectory(cTreeDirectory);
		grobidRunner.setOptions(GrobidOption.PROCESS_FULL_TEXT_OPTIONS);
		grobidRunner.run();
		// this transforms foo/fulltext.pdf to foo/fulltext/fulltext.tei.xml, 
		// so rename foo/fulltext/fulltext.tei.xml -> foo/fulltext.tei.xml
		// 
		File fulltextDir = new File(cTreeDirectory, CTree.FULLTEXT);
		File fulltextTEIFile = new File(fulltextDir, CTree.FULLTEXT_TEI_XML);
		File oldFulltextTEIFile = new File(cTreeDirectory, CTree.FULLTEXT_TEI_XML);
		FileUtils.moveFile(fulltextTEIFile, oldFulltextTEIFile);
		FileUtils.deleteDirectory(fulltextDir);
		System.out.println("files "+Arrays.asList(fulltextDir.listFiles()));
		return fulltextTEIFile;

	}
		 
}