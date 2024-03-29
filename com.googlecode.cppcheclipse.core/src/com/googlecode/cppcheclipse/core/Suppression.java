package com.googlecode.cppcheclipse.core;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IProject;

import com.googlecode.cppcheclipse.core.utils.SerializeHelper;

public class Suppression {
	private final File file;
	private final String problemId;
	private final int line;
	private final IProject project;
	
	private final static String PROBLEM_ID_ALL = "allProblems";
	private final static int LINE_ALL = Integer.MAX_VALUE;
	
	private final static String DELIMITER = ";";
	public Suppression(File file, IProject project) {
		this(file, PROBLEM_ID_ALL, project);
	}
	
	public Suppression(File file, String problemId, IProject project) {
		this(file, problemId, LINE_ALL, project);
	}
	
	public Suppression(File file, String problemId, int line, IProject project) {
		super();
		this.file = file;
		this.problemId = problemId;
		this.line = line;
		this.project = project;
	}
	
	/**
	 * 
	 * @return the absolute file of this suppression
	 */
	public File getFile() {
		return getFile(true);
	}
	
	/**
	 * @param absolute if true, returns an absolute path, otherwise it might be relative
	 * @return the file of this suppression
	 */
	public File getFile(boolean absolute) {
		final File file;
		if (absolute && !this.file.isAbsolute()) {
			file = new File(project.getLocation().toFile(), this.file.toString());
		} else {
			file = this.file;
		}
		return file;
	}

	public String getProblemId() {
		return problemId;
	}
	
	public int getLine() {
		return line;
	}
	
	public boolean isFileSuppression() {
		return problemId.equals(PROBLEM_ID_ALL);
	}
	
	public boolean isAllLines() {
		return line == LINE_ALL;
	}
	
	/**
	 * 
	 * @param file (must be absolute)
	 * @param problemId
	 * @param line
	 * @return true if the given problem should be suppressed
	 */
	public boolean isSuppression(File file, String problemId, int line) {
		if (!file.equals(getFile()))
			return false;
		
		if (isFileSuppression())
			return true;

		if (getProblemId().equals(problemId)) {
			if (getLine() == LINE_ALL)
				return true;
			
			// check line
			if (getLine() == line)
				return true;
		}
		return false;
	}

	public String serialize() throws IOException {
		StringBuffer serialization = new StringBuffer();
		// serialize in a platform-portable way
		String path = SerializeHelper.toString(file);
		serialization.append(path).append(DELIMITER);
		serialization.append(problemId).append(DELIMITER);
		serialization.append(line);
		return serialization.toString();
	}
	
	public static Suppression deserialize(String serialization, IProject project) throws IOException, ClassNotFoundException {
		StringTokenizer tokenizer = new StringTokenizer(serialization, DELIMITER);
		File file =  (File) SerializeHelper.fromString(tokenizer.nextToken());
		String problemId = tokenizer.nextToken();
		int line = Integer.parseInt(tokenizer.nextToken());
		return new Suppression(file, problemId, line, project);
	}
}