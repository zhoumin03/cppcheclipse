package com.googlecode.cppcheclipse.ui.preferences;

import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.googlecode.cppcheclipse.core.CppcheclipsePlugin;
import com.googlecode.cppcheclipse.core.PreferenceConstants;

/**
 * This class is used for WorkspacePreference as well as for ProjectProperties
 * @author Konrad Windszus
 *
 */
public class ProblemsPreferencePage extends FieldEditorOverlayPage implements
		IWorkbenchPreferencePage {

	
	public ProblemsPreferencePage() {
		super(GRID);
		// this is overridden in case of project properties
		setPreferenceStore(CppcheclipsePlugin.getWorkspacePreferenceStore());
		setDescription("Select which problems should be reported in which severity.");
	}

	@Override
	protected void createFieldEditors() {
	
		ProblemsTreeEditor problemsTreeEditor = new ProblemsTreeEditor(
				getFieldEditorParent());
		addField(problemsTreeEditor);
	}

	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}
	

	/*
	 * (non-Javadoc)
	 * @see com.googlecode.cppcheclipse.ui.FieldEditorOverlayPage#getPageId()
	 */
	@Override
	protected String getPageId() {
		return PreferenceConstants.PROBLEMS_PAGE_ID;
	}
}
