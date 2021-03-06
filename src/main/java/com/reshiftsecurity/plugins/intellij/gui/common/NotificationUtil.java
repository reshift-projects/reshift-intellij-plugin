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

package com.reshiftsecurity.plugins.intellij.gui.common;


import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.impl.NotificationSettings;
import com.intellij.notification.impl.NotificationsConfigurationImpl;
import org.jetbrains.annotations.NotNull;
import com.reshiftsecurity.plugins.intellij.common.util.ErrorUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * @author Reto Merz<reto.merz@gmail.com>
 * @since 0.9.998
 */
public final class NotificationUtil {


	private NotificationUtil() {
	}


	public static boolean isGroupEnabled(@NotNull final String groupId) {
		return !NotificationDisplayType.NONE.equals(getSettings(groupId).getDisplayType());
	}


	@NotNull
	private static NotificationSettings getSettings(@NotNull final String groupId) {
		return NotificationsConfigurationImpl.getSettings(groupId);
	}


	@NotNull
	public static NotificationsConfigurationImpl getNotificationsConfigurationImpl() {
		try {
			try {
				final Method getInstanceImpl = NotificationsConfigurationImpl.class.getDeclaredMethod("getInstanceImpl");
				return (NotificationsConfigurationImpl) getInstanceImpl.invoke(null);
			} catch (NoSuchMethodException e) {
				try {
					final Method getNotificationsConfigurationImpl = NotificationsConfigurationImpl.class.getDeclaredMethod("getNotificationsConfigurationImpl");
					return (NotificationsConfigurationImpl) getNotificationsConfigurationImpl.invoke(null);
				} catch (NoSuchMethodException e1) {
					throw ErrorUtil.toUnchecked(e1);
				}
			}
		} catch (InvocationTargetException e) {
			throw ErrorUtil.toUnchecked(e);
		} catch (IllegalAccessException e) {
			throw ErrorUtil.toUnchecked(e);
		}
	}
}
