/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.apache.isis.viewer.wicket.ui.pages.home;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import org.apache.isis.applib.services.message.MessageService;
import org.apache.isis.runtime.session.IsisSession;
import org.apache.isis.viewer.wicket.model.models.ActionModel;
import org.apache.isis.viewer.wicket.model.models.EntityModel;
import org.apache.isis.viewer.wicket.ui.ComponentType;
import org.apache.isis.viewer.wicket.ui.components.actions.ActionFormExecutorStrategy;
import org.apache.isis.viewer.wicket.ui.components.widgets.breadcrumbs.BreadcrumbModel;
import org.apache.isis.viewer.wicket.ui.components.widgets.breadcrumbs.BreadcrumbModelProvider;
import org.apache.isis.viewer.wicket.ui.pages.PageAbstract;
import org.apache.isis.viewer.wicket.ui.panels.FormExecutorDefault;
import org.apache.isis.viewer.wicket.ui.util.Components;

import lombok.val;

/**
 * Web page representing the home page (showing a welcome message).
 */
@AuthorizeInstantiation("org.apache.isis.viewer.wicket.roles.USER")
public class HomePage extends PageAbstract {

    private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
        super(parameters, null);

        addChildComponents(themeDiv, null);
        buildGui();

        addBookmarkedPages(themeDiv);
    }

    private void buildGui() {

        if(super.getPageParameters() == null) {
            super.getCommonContext().lookupServiceElseFail(MessageService.class)
            .informUser("Page timeout");
        }
        
        val isisSession = IsisSession.currentOrElseNull();
        val homePageAction = isisSession.getHomePageAction();
        if(homePageAction != null) {
            val objectAdapter = homePageAction.getObjectAdapter();
            val action = homePageAction.getObjectAction();

            Components.permanentlyHide(themeDiv, ComponentType.WELCOME);
            val actionModel = 
                    ActionModel.create(
                            EntityModel.ofAdapter(super.getCommonContext(), objectAdapter), action);
            val formExecutor =
                    new FormExecutorDefault<>( new ActionFormExecutorStrategy(actionModel));

            formExecutor.executeAndProcessResults(getPage(), null, null, actionModel.isWithinPrompt());

        } else {
            Components.permanentlyHide(themeDiv, ComponentType.ACTION_PROMPT);
            getComponentFactoryRegistry().addOrReplaceComponent(themeDiv, ComponentType.WELCOME, null);
        }

        final BreadcrumbModelProvider session = (BreadcrumbModelProvider) getSession();
        final BreadcrumbModel breadcrumbModel = session.getBreadcrumbModel();
        breadcrumbModel.visitedHomePage();
    }



}
