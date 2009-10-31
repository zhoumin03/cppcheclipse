package com.googlecode.cppcheclipse.core.command;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.xml.sax.SAXException;

import com.googlecode.cppcheclipse.core.IConsole;
import com.googlecode.cppcheclipse.core.PreferenceConstants;
import com.googlecode.cppcheclipse.core.Problem;

public class CppcheckCommand extends AbstractCppcheckCommand {
	private final static String ARGUMENTS = "--xml";

	private final Collection<String> arguments;
	
	public CppcheckCommand(IConsole console, IPreferenceStore store, Collection<String> includePaths) {
		super(console);
		arguments = new LinkedList<String>();
		arguments.add(ARGUMENTS);
		
		if (store.getBoolean(PreferenceConstants.P_CHECK_ALL)) {
			arguments.add(" --all");
		}
		
		if (store.getBoolean(PreferenceConstants.P_CHECK_STYLE)) {
			arguments.add(" --style");
		}
		
		if (store.getBoolean(PreferenceConstants.P_CHECK_VERBOSE)) {
			arguments.add(" --verbose");
		}
		
		if (store.getBoolean(PreferenceConstants.P_CHECK_FORCE)) {
			arguments.add(" --force");
		}
		
		// when unused-function check is on, -j is not available!
		boolean checkUnusedFunctions = store.getBoolean(PreferenceConstants.P_CHECK_UNUSED_FUNCTIONS);
		if (checkUnusedFunctions) {
			arguments.add(" --unused-functions");
		} else {
			arguments.add(" -j " + String.valueOf(store.getInt(PreferenceConstants.P_NUMBER_OF_THREADS)));
		}
		
		// TODO: enable when bug 878 of cppcheck is solved, see http://sourceforge.net/apps/trac/cppcheck/ticket/878
		/* 
		if (store.getBoolean(PreferenceConstants.P_FOLLOW_SYSTEM_INCLUDES)) {
			for (String path: includePaths) {
				arguments.add(" -I " + path);
			}
		}
		*/
	}
	
	public Collection<Problem> run(String filename, IFile file, IProgressMonitor monitor)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException, InterruptedException, ProcessExecutionException {
		
		Collection<String> arguments = new LinkedList<String>(this.arguments);
		arguments.add(filename);
		CppcheckProcess process = run(arguments.toArray(new String[0]), monitor);
		// check exit code
		if (process.getExitValue() != 0) {
			throw new IOException("Invalid exit code. Stderr: " + process.getError() + "\nStdout: " + process.getOutput());
		}
		Collection<Problem> problems = parseXMLStream(process.getErrorStream(), file);
		process.close();
		return problems;
	}
}
