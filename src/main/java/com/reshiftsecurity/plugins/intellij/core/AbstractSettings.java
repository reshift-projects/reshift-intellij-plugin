/*
 * Copyright 2020 Reshift Security Intellij plugin contributors
 *
 * This file is part of Reshift Security Intellij plugin.
 *
 * Reshift Security Intellij plugin is free software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * Reshift Security Intellij plugin is distributed in the hope that it will
 * be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Reshift Security Intellij plugin.
 * If not, see <http://www.gnu.org/licenses/>.
 */
package com.reshiftsecurity.plugins.intellij.core;

import com.intellij.util.xmlb.Constants;
import com.intellij.util.xmlb.annotations.MapAnnotation;
import com.intellij.util.xmlb.annotations.Tag;
import com.intellij.util.xmlb.annotations.XCollection;
import com.reshiftsecurity.plugins.intellij.common.PluginConstants;
import edu.umd.cs.findbugs.BugRanker;
import edu.umd.cs.findbugs.config.ProjectFilterSettings;
import com.reshiftsecurity.plugins.intellij.common.util.FindBugsCustomPluginUtil;
import com.reshiftsecurity.plugins.intellij.common.util.New;
import com.reshiftsecurity.plugins.intellij.preferences.AnalysisEffort;

import java.util.Map;
import java.util.Set;

public abstract class AbstractSettings {

	/**
	 * @see AnalysisEffort
	 * @see edu.umd.cs.findbugs.config.UserPreferences#setEffort(String)
	 */
	@Tag
	public String analysisEffort = AnalysisEffort.DEFAULT.getEffortLevel();

	/**
	 * @see ProjectFilterSettings#setMinRank(int)
	 */
	@Tag
	public int minRank = BugRanker.VISIBLE_RANK_MAX;

	/**
	 * @see ProjectFilterSettings#setMinPriority(String)
	 */
	@Tag
	public String minPriority = ProjectFilterSettings.DEFAULT_PRIORITY;

	/**
	 * @see ProjectFilterSettings#addCategory(String)
	 * @see ProjectFilterSettings#removeCategory(String)
	 * @see ProjectFilterSettings#containsCategory(String)
	 */
	@Tag(value = "hiddenBugCategory")
	@XCollection(elementName = "category", valueAttributeName = "name")
	public Set<String> hiddenBugCategory = New.asSet("NOISE");

	@Tag
	public String suppressWarningsClassName = PluginConstants.DEFAULT_SUPPRESS_WARNINGS_CLASSNAME;

	/**
	 * Additional findbugs plugins.
	 *
	 * @see FindBugsCustomPluginUtil
	 */
	@Tag(value = "plugins")
	@XCollection(elementName = Constants.SET)
	public Set<PluginSettings> plugins = New.set();

	/**
	 * @see edu.umd.cs.findbugs.config.UserPreferences#setIncludeFilterFiles(Map)
	 */
	@Tag(value = "includeFilterFiles")
	@MapAnnotation(
			surroundWithTag = false,
			surroundValueWithTag = false,
			surroundKeyWithTag = false,
			entryTagName = "filter",
			keyAttributeName = "file",
			valueAttributeName = "enabled"
	)
	public Map<String, Boolean> includeFilterFiles = New.map();

	/**
	 * @see edu.umd.cs.findbugs.config.UserPreferences#setExcludeFilterFiles(Map)
	 */
	@Tag(value = "excludeFilterFiles")
	@MapAnnotation(
			surroundWithTag = false,
			surroundValueWithTag = false,
			surroundKeyWithTag = false,
			entryTagName = "filter",
			keyAttributeName = "file",
			valueAttributeName = "enabled"
	)
	public Map<String, Boolean> excludeFilterFiles = New.map();

	/**
	 * @see edu.umd.cs.findbugs.config.UserPreferences#setExcludeBugsFiles(Map)
	 */
	@Tag(value = "excludeBugsFiles")
	@MapAnnotation(
			surroundWithTag = false,
			surroundValueWithTag = false,
			surroundKeyWithTag = false,
			entryTagName = "bugs",
			keyAttributeName = "file",
			valueAttributeName = "enabled"
	)
	public Map<String, Boolean> excludeBugsFiles = New.map();

	/**
	 * Note that the map only contains detectors from the core plugin and
	 * only enabled state which are not equal to the default enable state
	 * {@link edu.umd.cs.findbugs.DetectorFactory#isDefaultEnabled()}.
	 * <p>
	 * Key = {@link edu.umd.cs.findbugs.DetectorFactory#getShortName()}
	 * (like {@link edu.umd.cs.findbugs.config.UserPreferences#detectorEnablementMap})
	 * <p>
	 * Value = Enabled state
	 */
	@Tag(value = "detectors")
	@MapAnnotation(
			surroundWithTag = false,
			surroundValueWithTag = false,
			surroundKeyWithTag = false,
			entryTagName = "detector",
			keyAttributeName = "name",
			valueAttributeName = "enabled"
	)
	public Map<String, Boolean> detectors = New.map();
}
