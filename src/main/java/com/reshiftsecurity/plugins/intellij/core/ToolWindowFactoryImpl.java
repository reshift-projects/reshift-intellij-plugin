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

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;
import com.reshiftsecurity.plugins.intellij.gui.toolwindow.view.ToolWindowPanel;

public final class ToolWindowFactoryImpl implements ToolWindowFactory {
	@Override
	public void createToolWindowContent(@NotNull final Project project, @NotNull final ToolWindow toolWindow) {
		final ToolWindowPanel win = new ToolWindowPanel(project);
		toolWindow.getContentManager().addContent(ContentFactory.SERVICE.getInstance().createContent(win, "", false));
	}
}
