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
package com.reshiftsecurity.plugins.intellij.collectors;

import com.intellij.psi.PsiAnonymousClass;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiElementFilter;
import com.intellij.psi.util.PsiTreeUtil;


/**
* $Date$
*
* @author Andre Pfeiler<andrepdo@dev.java.net>
* @version $Revision$
* @since 0.0.1
*/
class AnonymousClassPsiElementFilter implements PsiElementFilter {

	private final PsiElement _psiElement;


	AnonymousClassPsiElementFilter(final PsiElement psiElement) {
		_psiElement = psiElement;
	}


	public boolean isAccepted(final PsiElement e) {
		return e instanceof PsiAnonymousClass && _psiElement.equals(PsiTreeUtil.getParentOfType(e, PsiClass.class));
	}


	@Override
	public String toString() {
		return "AnonymousClassPsiElementFilter{psiElement=" + _psiElement + '}';
	}
}
